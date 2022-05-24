<script setup>
import { ref, computed, onBeforeMount, onMounted, reactive, toRef, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//router
import { useRoute, useRouter } from 'vue-router';
//js
import { events } from '../js/event'
//icon

import IconCalendar from '../components/icons/IconCalendar.vue';
import IconTime from '../components/icons/IconTime.vue';

import IconEmail from '../components/icons/IconEmail.vue';

import IconClose from '../components/icons/IconClose.vue';
import IconSave from '../components/icons/IconSave.vue';



import BaseModalFailed from '../components/modal/BaseModalFailed.vue';
import EventDetailHeader from '../components/ViewEvent/EventDetail/EventDetailHeader.vue';
import EventTimeleft from '../components/ViewEvent/EventDetail/EventTimeleft.vue';
import IconHourglass from '../components/icons/IconHourglass.vue';


dayjs.extend(localizedFormat)

//use router
const { params } = useRoute()
const eventId = params.eventId
const router = useRouter()
const routerSigns = {
    goBack: () => router.go(-1),
    schedules: () => router.push({ name: 'Schedules' })
}

const event = ref({})
onBeforeMount(async () => {
    event.value = await events.getById(eventId)
})

const combineDT = (date, time) => dayjs(date + time).toJSON()

const checker = {
    isBlank: (string) => string.match(/^\s+$/),
    isFuture: (datetime) => dayjs(datetime).isAfter(dayjs()),
}
const editing = reactive({
    modalFailed: {
        visible: false,
        titil: 'Update Failed',
        desc: 'Event '
    },
    mode: false,
    on() {
        this.updateValue()
        events.get()
        this.mode = true
    },
    off() { this.mode = false },
    datetime: {
        time: null,
        date: null,
        errors: [],
        isError() { return this.errors.length !== 0 },
        checkError() {
            console.log(this.errors);
            this.errors = []
            //if some of 2 null return
            if (!this.date || !this.time) return
            !checker.isFuture(this.date + this.time) ? this.errors.push('Date&Time must be a future date') : ''
            events.checkOverlap(this.date + this.time, event.value.eventDuration, event.value.eventCategoryId, event.value.id) 
                ? this.errors.push('Time is Overlaped') : ''
            // inform.checkFormComplete()
        }
    },
    note: {
        note: null,
        errors: [],
        isError() { return this.errors.length !== 0 },
        checkError() {


            this.errors = []
            if (this.note == null) return
            checker.isBlank(this.note) ? this.errors.push('Note must not be white space') : ''
            // inform.checkFormComplete()
        }
    },
    updateValue() {
        this.datetime.date = dayjs(event.value.eventStartTime).format('YYYY-MM-DD')
        this.datetime.time = dayjs(event.value.eventStartTime).format('HH:mm')
        this.note.note = event.value.eventNotes
    },
    async save() {
        const obj = {
            eventStartTime: combineDT(this.datetime.date, this.datetime.time),
            eventNotes: this.note.note
        }
        const res = await events.patch(eventId, obj)
        console.log(res);
        if (res.status) {
            event.value = res.event
            alert('Edit complete')
            return this.cancel()
        }

        let message = ''
        await res.error.details.forEach(d => message += `${d.field}: ${d.errorMessage}; `)

        editing.modalFailed.desc = message
        editing.modalFailed.visible = true
    },
    cancel() {
        this.mode = false
        this.updateValue()
    },
})

const removeEvent = async () => {
    if(!confirm('Are you sure you want to cancel this event?')) return
    await events.remove(eventId)
    alert('Remove complete')
    router.push({name: 'Schedules'})
}
</script>
 
<template>
    <main class="h-full w-screen overflow-auto flex flex-col px-28 py-8 gap-6">
        <EventDetailHeader :bookingName="event.bookingName" @emitBack="routerSigns.goBack" @emitEdit="editing.on()" @emitCancel="removeEvent" />
        <div class="flex h-full w-full overflow-auto">
            <div class="flex flex-col h-full w-2/5 overflow-auto gap-10 py-4">
                <!-- <div class="flex h-40"></div> -->
                <EventTimeleft :eventStartTime="event.eventStartTime" />
                <div class="flex flex-col gap-2 px-4">
                    <!-- Email -->
                    <div class="flex gap-2 items-center">
                        <IconEmail class="w-6 h-6" />
                        <p class="text-base font-medium truncate">{{ event.bookingEmail }}</p>
                    </div>
                    <!-- Date -->
                    <div class="flex gap-2 items-center">
                        <IconCalendar class="w-6 h-6" />
                        <input v-if="editing.mode" type="date" v-model="editing.datetime.date"
                            :min="dayjs().format('YYYY-MM-DD')" :max="dayjs().add('3', 'M').format('YYYY-MM-DD')"
                            :class="[editing.datetime.isError() ? 'form-input-error' : 'form-input']"
                            @change="editing.datetime.checkError">
                        <span v-else class="text-base font-medium">
                            {{ dayjs(event.eventStartTime).format('LL') }}
                        </span>
                    </div>
                    <!-- Time -->
                    <div class="flex gap-2 items-center">
                        <IconTime class="w-6 h-6" />
                        <input v-if="editing.mode" type="time" v-model="editing.datetime.time"
                            :class="[editing.datetime.isError() ? 'form-input-error' : 'form-input']"
                            @change="editing.datetime.checkError">
                        <span v-else class="text-base font-medium">
                            {{ dayjs(event.eventStartTime).format('LT') }} -
                            {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }}
                        </span>
                    </div>
                    <!-- Duration -->
                    <div class="flex gap-2 items-center">
                        <IconHourglass class="w-6 h-6" />
                        <p class="text-base font-medium truncate">{{ event.eventDuration }} minute</p>
                    </div>

                </div>

            </div>

            <div class="flex flex-col w-3/5 px-6 pb-10 justify-between">
                <!-- EventCategory -->


                <!-- Note -->
                <div class="flex-flex-col">
                    <div class="text-xl font-medium mb-6">{{ event.eventCategoryName }} </div>
                    <textarea v-if="editing.mode" name="eventNotes" placeholder="Note" maxlength="500" rows="10"
                        :class="[editing.note.isError() ? 'form-input-error' : 'form-input', 'text-sm']"
                        @keyup="editing.note.checkError()" v-model="editing.note.note"></textarea>
                    <div v-else>
                        <p class="flex break-all">
                            {{ event.eventNotes }}
                        </p>
                        <span v-show="event.eventNotes == null || event.eventNotes.length == 0"
                            class="text-md font-medium text-gray-500">No Description</span>
                    </div>
                </div>


                <div v-if="editing.mode" class="flex flex-col gap-4">


                    <div class="flex flex-col">
                        <div v-show="editing.note.isError()" class="-bottom-5 text-sm font-medium text-red-500 pl-0.5">
                            Invalid note error:
                            <span v-for="error in editing.note.errors">{{ error }}; </span>
                        </div>
                        <div v-show="editing.datetime.isError()"
                            class="-bottom-5 text-sm font-medium text-red-500 pl-0.5">
                            Invalid note error:
                            <span v-for="error in editing.datetime.errors">{{ error }}; </span>
                        </div>
                    </div>
                    <div class="flex gap-4">
                        <button :disabled="editing.note.isError() || editing.datetime.isError()"
                            class="bg-blue-600 text-white px-3 py-1.5 rounded text-base font-medium hover:bg-blue-800 transition duration-300 disabled:bg-gray-300 disabled:text-slate-500"
                            @click="editing.save()">
                            <div class="flex items-center gap-1">
                                <IconSave class="w-5 h-5" />
                                <span>Save</span>
                            </div>
                        </button>
                        <button
                            class="border-transparent border-red-500 text-gray-800 hover:text-white px-3 py-1.5 rounded text-base font-medium hover:bg-red-500 transition duration-300"
                            @click="editing.cancel()">
                            <div class="flex items-center gap-1">
                                <IconClose class="w-6 h-6" />
                                <span>Cancel</span>
                            </div>
                        </button>
                    </div>

                </div>
            </div>
        </div>
        <BaseModalFailed :visible="editing.modalFailed.visible" :title="editing.modalFailed.titil" :toSchedules="false"
            :desc="editing.modalFailed.desc" @closeModal="() => editing.modalFailed.visible = false" />
    </main>
</template>
 
<style>
</style>
