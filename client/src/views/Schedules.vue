<script setup>
import {
    ref,
    computed,
    reactive,
    nextTick,
    onBeforeMount,
    onMounted,
} from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
import utc from 'dayjs/plugin/utc'
import timezone from 'dayjs/plugin/timezone'
//fetch
import { zFetch } from '../js/zFetch'
//mouse
import { useMouse } from '../js/mouse'
//router
import { useRoute, useRouter } from 'vue-router'


dayjs.extend(utc)
dayjs.extend(timezone)
dayjs.extend(localizedFormat)

//use router
const { params } = useRoute()
const router = useRouter()
//use mouse
const mousePos = useMouse()

const events = ref([])
onBeforeMount(async () => {
    zFetch.settings.baseUrl = 'http://ip21us2.sit.kmutt.ac.th:8080'
    events.value = await zFetch.get('/api/events')
})

let mouseOnRow = ref(null)
const mouseMoveOverRow = () => {
    // mousePos.value.x
}
const getDate = (dateTime) => dayjs(dateTime).tz('GMT').format('LL')
const getTime = (dateTime) => dayjs(dateTime).tz('GMT').format('HH:mm')
// const getTime = (dateTime) => dayjs(dateTime).format('LT')
const modal = ref({ visible: false })
const selectedEvent = ref({})
const viewDetail = (index) => {
    selectedEvent.value = events.value[index]
    modal.value.visible = true
}

</script>

<template>
    <div class="">
        <h2 class="my-4 text-2xl font-semibold text-gray-700 dark:text-gray-200">Schedules</h2>

        <!-- show detail when mouse over -->
        <!-- <div id="cursor" class="m-6 p-4 bg-teal-50 rounded-xl max-w-4xl absolute pointer-events-none"
            v-if="mouseOnRow !== null" :style="[`left: ${mousePos.x}px`, `top: ${mousePos.y}px`]">
            <span><b>Booking Name:</b> {{ events[mouseOnRow].bookingName }}</span><br>
            <span><b>Booking Email:</b> {{ events[mouseOnRow].bookingEmail }}</span><br>
            <span><b>Category Name:</b> {{ events[mouseOnRow].eventCategoryId }}</span><br>
            <span><b>Start Date:</b> {{ getDate(events[mouseOnRow].eventStartTime) }}</span><br>
            <span><b>Start Time:</b> {{ getTime(events[mouseOnRow].eventStartTime) }}</span><br>
            <span><b>Duration:</b> {{ events[mouseOnRow].eventDuration }} minutes</span><br>
            <span v-show="events[mouseOnRow].eventNotes"><b>Note:</b> {{ events[mouseOnRow].eventNotes }}</span>
        </div> -->

        <!-- show detail cursor -->
        <div id="cursor"
            class="absolute truncate m-4 py-1 px-3 bg-gray-50 dark:bg-cyan-600 dark:text-gray-300 rounded-xl pointer-events-none"
            v-if="mouseOnRow !== null" :style="[`left: ${mousePos.x}px`, `top: ${mousePos.y}px`]">
            click to show details of: <b>{{ events[mouseOnRow].bookingName }}</b>
        </div>

        <div class="w-full overflow-hidden rounded-lg shadow-lg">
            <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap">
                    <thead>
                        <!-- dark:border-gray-700 dark:text-gray-400 dark:bg-gray-800 -->
                        <tr class="text-tr-head border-b dark:border-gray-700 bg-cyan-600">
                            <th class="px-4 py-3">Booking Name</th>
                            <th class="px-4 py-3">Category Name</th>
                            <th class="px-4 py-3">Date</th>
                            <th class="px-4 py-3">Start Time</th>
                            <th class="px-4 py-3">Duration</th>
                        </tr>
                    </thead>
                    <!-- dark:divide-gray-700 dark:bg-gray-800 -->
                    <tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-neutral-700">
                        <!-- dark:text-gray-400 -->
                        <tr class="text-gray-700 dark:text-gray-300 hover:bg-gray-500 cursor-pointer"
                            v-for="(event, index) in events" @click="viewDetail(index)" @mouseenter="mouseOnRow = index"
                            @mousemove="mouseMoveOverRow()" @mouseleave="mouseOnRow = null">
                            <td class="px-4 py-3 text-sm">{{ event.bookingName }}</td>
                            <td class="px-4 py-3 text-sm">{{ event.eventCategoryId }}</td>
                            <td class="px-4 py-3 text-sm">
                                {{ getDate(event.eventStartTime) }}
                                <!-- {{ dayjs(event.eventStartTime) }}  -->
                            </td>
                            <td class="px-4 py-3 text-sm">
                                <div class="flex flex-col">
                                    <span>{{
                                            getTime(event.eventStartTime)
                                    }}</span>
                                    <!-- <span>{{event.eventStartTime}}</span> -->
                                </div>
                            </td>
                            <td class="px-4 py-3 text-sm">{{ event.eventDuration }} minutes</td>
                        </tr>
                        <tr v-show="events.length === 0">
                            <td colspan="5" class="px-4 py-3 text-center">
                                No Scheduled Events
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- modal -->
    <div v-show="modal.visible"
        class="absolute min-h-screen top-0 left-0 w-full h-full flex justify-center items-center z-99">
        <div @click.self="modal.visible = false"
            class="absolute w-full h-full bg-gray-500 transition ease-in-out opacity-60"></div>
        <div class="bg-white dark:bg-gray-800 absolute rounded-md shadow-xl max-w-4xl py-4 text-left px-6">

            <!--Title-->
            <div class="flex justify-end items-center">
                <!-- <p class="text-2xl font-bold dark:text-gray-200">Event Detail!</p> -->
                <div class="modal-close cursor-pointer z-50" @click="modal.visible = false">
                    <svg class="fill-current text-black dark:text-gray-200" xmlns="http://www.w3.org/2000/svg"
                        width="18" height="18" viewBox="0 0 18 18">
                        <path
                            d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z">
                        </path>
                    </svg>
                </div>
            </div>

            <!--Body-->
            <div class="flex flex-col space-y-2 mr-8 dark:text-gray-400">
                <div><span class="font-bold">Booking Name:</span> {{ selectedEvent.bookingName }}</div>
                <div><span class="font-bold">Booking Email:</span> {{ selectedEvent.bookingEmail }}</div>
                <div><span class="font-bold">Category Name:</span> {{ selectedEvent.eventCategoryId }}</div>
                <div><span class="font-bold">Start Date:</span> {{ getDate(selectedEvent.eventStartTime) }}</div>
                <div><span class="font-bold">Start Time:</span> {{ getTime(selectedEvent.eventStartTime) }}</div>
                <div><span class="font-bold">Duration:</span> {{ selectedEvent.eventDuration }} minutes</div>
                <div><span class="font-bold">Note:</span> {{ selectedEvent.eventNotes }}</div>
            </div>

            <!-- Footer-->
            <!-- <div class="flex justify-end pt-2">
                <button
                    class="px-4 bg-transparent p-3 rounded-lg text-indigo-500 hover:bg-gray-100 hover:text-indigo-400 mr-2">Action</button>
                <button
                    class="modal-close px-4 bg-indigo-500 p-3 rounded-lg text-white hover:bg-indigo-400">Close</button>
            </div> -->

        </div>
    </div>


</template>

<style>
</style>
