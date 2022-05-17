<script setup>
import { ref, computed, onBeforeMount, onMounted, onBeforeUpdate, } from 'vue'
//date-time lib
import dayjs from 'dayjs'

import { events, eventFetch } from '../../js/event'
import IconPinFill from '../icons/IconPinFill.vue'
import IconTimeFill from '../icons/IconTimeFill.vue'
import IconCalendarFill from '../icons/IconCalendarFill.vue'
import IconEmailFill from '../icons/Fill/IconEmailFill.vue'
import IconArrowLeft from '../icons/IconArrowLeft.vue'
import IconNoteFill from '../icons/Fill/IconNoteFill.vue'
import IconEditFill from '../icons/Fill/IconEditFill.vue'
import IconArrowBack from '../icons/IconArrowBack.vue'
import ModalError from '../modal/ModalError.vue'
const emits = defineEmits(['emitUpdateEvent'])
const props = defineProps({
    // selectedId: {
    //     type: Number,
    //     require: true,
    // },
    detailModal: {
        type: Object,
        require: true,
    },
    event: {
        type: Object,
        default: null
    }
})

onBeforeMount(async () => {
    // stage.value.editing = false
    // await eventFetch.getById(props.selectedId)

})
const dateNow = () => dayjs().format('YYYY-MM-DD')
const stage = ref({
    editing: false,
})

let inputEvent = ref({
    time: null,
    date: null,
    note: null
})
const isTimePast = computed(
    () => inputEvent.value.time && inputEvent.value.date && dayjs(inputEvent.value.date + inputEvent.value.time).isBefore(dayjs()))

const updateEvent = async (e) => {
    // if(!isTimePast.value){
    //     console.log('no');
    //     return

    // }
    // ถ้าค่าเหมือนเดิมให้ใช้ของเก่า
    const time = inputEvent.value.time == null
        ? dayjs(props.event.eventStartTime).format('HH:mm')
        : inputEvent.value.time

    const date = inputEvent.value.date == null
        ? dayjs(props.event.eventStartTime).format('YYYY-MM-DD')
        : inputEvent.value.date

    const note = inputEvent.value.note == null
        ? props.event.eventNotes
        : inputEvent.value.note


    const editEvent = {
        eventStartTime: dayjs(date + time).toJSON(),
        eventNotes: note
    }

    // updateFetch(props.event.id, editEvent)
    const res = await eventFetch.put(props.event.id, editEvent)
    if (res.status == true) {
        // props.event = res.event
        console.log(res.event);
        stage.value.editing = false
        emits('emitUpdateEvent', res.event)
    }
    else {
        const errorList = res.error.details
        let mes = ''
        for (const [key, value] of Object.entries(errorList)) {
            mes += value.errorMessage +'\n'
        }

        console.log(mes);
        modal.value.text = mes
        modal.value.visible = true
    }
    // console.log(res);
    // stage.value.editing = false
}

const closeDetail = () => {
    stage.value.editing = false
    props.detailModal.visible = false
}
const modal = ref({ visible: false, text: 'error' })
// const updateModal
</script>
 
<template>

    <div :class="[
    detailModal.visible ? 'slide-event-detail-off' : 'slide-event-detail-on'
    , 'absolute top-15 right-0 w-160 h-full flex shadow-lg bg-gray-50'
    , 'overflow-y-scroll']">
        <!-- <div class="flex absolute top-5 left-1.5 cursor-pointer" @click="detailModal.visible = false">
            <IconArrowLeft class="w-6 h-6" />
        </div> -->
        <button class="flex w-14 h-full items-center justify-center " @click="closeDetail">
            <IconArrowBack class="w-10 h-10 text-gray-700" />
        </button>
        <!-- Header -->
        <div class="flex flex-col py-8 pr-4 w-full">
            <div class="flex mb-6 justify-between">
                <div class="h-16 flex gap-4 ">
                    <span class="text-xl font-bold ">
                        {{ event.bookingName }}
                    </span>
                </div>
                <div class="flex ">
                    <button class="flex h-min p-1.5 rounded-full bg-blue-300 hover:bg-blue-600"
                        @click="stage.editing = true">
                        <IconEditFill class="w-5 h-5 text-blue-100" />
                    </button>
                </div>
            </div>


            <div class="flex flex-col gap-4">
                <!-- Email -->
                <div class="flex items-center gap-4">
                    <div class="p-1.5 rounded-full gap-2 bg-orange-500">
                        <IconEmailFill class="w-4 h-4 text-orange-100" />
                    </div>
                    <!-- <div class="flex  font-semibold">Time</div> -->
                    <span class="text-lg text-gray-700 font-medium">
                        {{ event.bookingEmail }}
                    </span>
                </div>

                <!-- Date -->
                <div class="flex items-center gap-4">
                    <div class="p-1.5 rounded-full gap-2 bg-red-500">
                        <IconCalendarFill class="w-4 h-4 text-rose-100" />
                    </div>
                    <!-- <div class="flex  font-semibold">Time</div> -->
                    <span v-show="!stage.editing" class="text-lg text-gray-700 font-medium">
                        {{ dayjs(event.eventStartTime).format('LL') }}
                    </span>
                    <div v-show="stage.editing" class="flex">
                        <input type="date"
                            class="bg-white text-gray-700 text-sm border border-gray-200 rounded-lg px-1.5 py-0.5 font-medium"
                            required :value="dayjs(event.eventStartTime).format('YYYY-MM-DD')" :min="dateNow()"
                            @change="inputEvent.date = $event.target.value">
                    </div>
                </div>


                <!-- Time -->
                <div class="flex items-center gap-4">
                    <div class="p-1.5 rounded-full gap-2 bg-blue-500">
                        <IconTimeFill class="w-4 h-4 text-blue-100" />
                    </div>
                    <!-- <div class="flex  font-semibold">Time</div> -->
                    <span v-show="!stage.editing" class="text-lg text-gray-700 font-medium">
                        {{ dayjs(event.eventStartTime).format('LT') }}
                        - {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }}
                        ({{ event.eventDuration }} min)
                    </span>

                    <div v-show="stage.editing" class="flex">
                        <input type="time"
                            class="bg-white text-gray-700 text-sm border border-gray-200 rounded-lg px-1.5 py-0.5 font-medium"
                            required :value="dayjs(event.eventStartTime).format('HH:mm')"
                            @change="inputEvent.time = $event.target.value">
                    </div>
                </div>

                <!-- Clinic -->
                <div class="flex items-center gap-4">
                    <div class="p-1.5 rounded-full gap-2 bg-purple-500">
                        <IconPinFill class="w-4 h-4 text-purple-100" />
                    </div>
                    <!-- <div class="flex  font-semibold">Time</div> -->
                    <span class="text-lg text-gray-700 font-medium">{{ event.eventCategoryName }}</span>
                </div>

                <!-- Notes -->
                <div class="flex items-start gap-4">
                    <!-- <div class="flex  font-semibold">Time</div> -->
                    <div class="p-1.5 rounded-full gap-2 bg-green-500">
                        <IconNoteFill class="w-4 h-4 text-green-100" />
                    </div>
                    <span v-show="!stage.editing" class="text-base text-gray-700 font-medium"
                        v-text="event.eventNotes != null ? event.eventNotes : '-'">
                    </span>

                    <!-- <span v-show="event.eventNotes == null" class="text-base text-gray-700 font-medium">
                        -
                    </span> -->
                    <textarea v-show="stage.editing" name="eventNotes" rows="8" placeholder="Note" maxlength="500"
                        class="w-full form-1 font-medium"
                        @change="inputEvent.note = $event.target.value">{{ event.eventNotes }}</textarea>
                </div>
                <!-- button -->
                <div v-show="stage.editing" class="flex gap-4 mt-4 justify-center">
                    <button class="disabled:opacity-75 btn-1 text-sm bg-blue-600 hover:bg-blue-700 text-blue-50"
                        @click="updateEvent"> Update
                    </button>
                    <button class="btn-1 text-sm border-2 hover:border-red-500 hover:text-red-50 hover:bg-red-500"
                        @click="stage.editing = false"> Cancel
                    </button>
                </div>
            </div>
        </div>
    </div>
    <ModalError :visible="modal.visible" :text="modal.text" @close-modal="modal.visible = false" />
</template>
 
<style>
</style>