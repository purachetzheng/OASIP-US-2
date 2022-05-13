<script setup>
import { ref, computed, onBeforeMount, onMounted, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
import { events, eventFetch } from '../js/event'
import IconPinFill from '../components/icons/IconPinFill.vue'
import IconTimeFill from '../components/icons/IconTimeFill.vue'
import IconCalendarFill from '../components/icons/IconCalendarFill.vue'
import IconEmailFill from '../components/icons/Fill/IconEmailFill.vue'
import IconArrowLeft from '../components/icons/IconArrowLeft.vue'
import IconNoteFill from './icons/Fill/IconNoteFill.vue'
import IconEditFill from './icons/Fill/IconEditFill.vue'
defineEmits([])
const props = defineProps({
    // selectedId: {
    //     type: Number,
    //     require: true,
    // },
    detailModal: {
        type: Object,
        require: true,
    },
    event:{
        type: Object,
        default: null
    }
})
// const event = ref({})
onBeforeMount(async () => {
    // await eventFetch.getById(props.selectedId)

})
console.log(props.event);
</script>
 
<template>
    <div :class="[
    detailModal.visible ? 'slide-event-detail-off' : 'slide-event-detail-on'
    , 'absolute p-8 top-15 right-0 w-160 h-full flex flex-col shadow-lg bg-gray-50'
    , 'overflow-y-scroll']">
        <!-- <div class="flex absolute top-5 left-1.5 cursor-pointer" @click="detailModal.visible = false">
            <IconArrowLeft class="w-6 h-6" />
        </div> -->
        <div class="flex mb-2 justify-between" >
            <button class="p-1" @click="detailModal.visible = false">
                <IconArrowLeft class="w-6 h-6" />
            </button>
            
            <button class="p-1">
                <IconEditFill class="w-6 h-6" />
            </button>
        </div>
        <div class="h-16 mb-6 flex gap-4 ">
            <span class="text-xl font-bold ">
                {{ event.bookingName }}
            </span>
        </div>

        <div class="flex flex-col gap-4">
            <!-- Email -->
            <div class="flex items-center gap-4">
                <div class="p-1.5 rounded-full gap-2 bg-orange-500">
                    <IconEmailFill class="w-4 h-4 text-orange-100" />
                </div>
                <!-- <div class="flex  font-semibold">Time</div> -->
                <span class="text-lg text-gray-700 font-medium">
                    {{event.bookingEmail}}
                </span>
            </div>

            <!-- Date -->
            <div class="flex items-center gap-4">
                <div class="p-1.5 rounded-full gap-2 bg-red-500">
                    <IconCalendarFill class="w-4 h-4 text-rose-100" />
                </div>
                <!-- <div class="flex  font-semibold">Time</div> -->
                <span class="text-lg text-gray-700 font-medium">
                    {{ dayjs(event.eventStartTime).format('LL') }}
                </span>
            </div>

            <!-- Time -->
            <div class="flex items-center gap-4">
                <div class="p-1.5 rounded-full gap-2 bg-blue-500">
                    <IconTimeFill class="w-4 h-4 text-blue-100" />
                </div>
                <!-- <div class="flex  font-semibold">Time</div> -->
                <span class="text-lg text-gray-700 font-medium">
                    {{ dayjs(event.eventStartTime).format('LT') }}
                    - {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }}
                     ({{event.eventDuration}} min)
                </span>
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
                <span v-show="event.eventNotes != null"
                    class="text-base text-gray-700 font-medium">
                        {{event.eventNotes}}
                </span>
                <span v-show="event.eventNotes == null"
                    class="text-base text-gray-700 font-medium">
                        -
                </span>
            </div>
        </div>

    </div>
</template>
 
<style>
</style>