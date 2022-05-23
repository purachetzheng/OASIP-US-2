<script setup>
import { ref, computed, onBeforeMount, onMounted, reactive, toRef, } from 'vue'
import dayjs from 'dayjs'
import IconEmail from '../../icons/IconEmail.vue';
import IconCalendar from '../../icons/IconCalendar.vue';
import IconTime from '../../icons/IconTime.vue';
import EventTimeleft from './EventTimeleft.vue';
defineEmits([])
const props = defineProps({
    event: {
        type: Object,
        require: true,
    }
})
// console.log(props.eventStartTime);
</script>
 
<template>
    <div class="flex h-full w-full">
        <div class="flex flex-col h-full w-2/5 bg-red-100 overflow-auto">
            <!-- <div class="flex h-40"></div> -->
            <EventTimeleft :eventStartTime="event.eventStartTime" />
            <div class="flex flex-col gap-2 px-4">
                <!-- Email -->
                <div class="flex gap-2 items-center">
                    <IconEmail class="w-6 h-6" />
                    <span class="text-base font-medium">{{ event.bookingEmail }}</span>
                </div>
                <!-- Date -->
                <div class="flex gap-2 items-center">
                    <IconCalendar class="w-6 h-6" />
                    <input v-if="0" type="date" :class="['form-input', 'w-fit py-0.5 text-base']">
                    <span v-else class="text-base font-medium">
                        {{ dayjs(event.eventStartTime).format('LL') }}
                    </span>
                </div>
                <!-- Time -->
                <div class="flex gap-2 items-center">
                    <IconTime class="w-6 h-6" />
                    <input v-if="0" type="time"
                        :class="['form-input', 'w-fit py-0.5 text-base']">
                    <span v-else class="text-base font-medium">
                        {{ dayjs(event.eventStartTime).format('LT') }} -
                        {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }}
                    </span>
                </div>
            </div>
        </div>
        
        <div class="flex flex-col w-3/5 p-6 bg-blue-100">
            <p class="flex  break-all">
                {{ event.eventNotes }}
            </p>
            <!-- <span v-show="event.eventNotes == null || event.eventNotes.length == 0" 
                        class="text-md font-medium text-gray-500">No Description</span> -->
        </div>
        
        <!-- <span class="text-md"></span>
                            <span v-show="event.eventNotes == null || event.eventNotes.length == 0" 
                                class="text-md font-medium text-gray-500">No Description</span> -->
    </div>

</template>
 
<style>
</style>