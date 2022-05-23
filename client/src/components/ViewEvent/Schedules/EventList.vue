<script setup>
import { ref } from 'vue'
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//Icon
import RiTimeFill from '../../icons/Fill/RiTimeFill.vue'
import RiMapPin2Fill from '../../icons/Fill/RiMapPin2Fill.vue'
import CursorTooltip from '../../CursorTooltip.vue'

let mouseOn = ref(null)
//dayjs
dayjs.extend(localizedFormat)


defineEmits([ 'emitViewDetail'])
const props = defineProps({
    events: {
        type: Array,
        default: []
    },
    selectCategory:{
        type: String,
        default: null
    },
    
})

</script>
 
<template>
    <div v-show="events.length !== 0" 
        class="h-full overflow-auto auto-rows-min gap-x-2 gap-y-2 p-1
                grid xl:grid-cols-3 lg:grid-cols-2 md:grid-cols-1">

        <!-- loop events -->
        <div class="flex flex-col shadow-lg p-4 bg-white bg-opacity-75 rounded-md hover:bg-gray-100 cursor-pointer" 
            v-for="(event, index) in events" @mouseleave="mouseOn = null" 
            @mouseenter="event.bookingName.length >60 ? mouseOn = event.bookingName: ''"
            @click="$emit('emitViewDetail',event.id)">
        
            <div class="flex text-lg font-bold h-14 overflow-hidden">
                <!-- <p class="truncate">{{ event.bookingName }}</p> -->
                <p class="">{{ event.bookingName }}</p>
            </div>

            <div class="flex flex-wrap items-center gap-x-2 text-sm font-medium text-gray-600 pt-4">
                <RiTimeFill />
                <div class="whitespace-nowrap">{{ dayjs(event.eventStartTime).format('LL') }}</div>
                <div class="w-1 h-1 bg-gray-600 rounded-full"></div>
                <div class="whitespace-nowrap">{{ dayjs(event.eventStartTime).format('LT') }}</div>
                <div class="w-1 h-1 bg-gray-600 rounded-full"></div>
                <div class="whitespace-nowrap">{{ event.eventDuration }} min</div>
            </div>
            <div class="flex items-center gap-2 text-sm font-medium text-gray-600 ">
                <RiMapPin2Fill />
                <!-- <div class="w-3.5 h-3.5 bg-green-400 rounded-full"></div> -->
                <span>{{ event.eventCategoryName }}</span>
            </div>
        </div>

    </div>
    <CursorTooltip :mouseOn="mouseOn" />
</template>
 
<style>
</style>