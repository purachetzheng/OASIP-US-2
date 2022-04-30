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
//fetch
import { zFetch } from '../js/zFetch'
//mouse
import { useMouse } from '../js/mouse'
//router
import { useRoute, useRouter } from 'vue-router'

//use router
const { params } = useRoute()
const router = useRouter()
//use mouse
const mousePos = useMouse()

const events = ref([
    {
        id: 1,
        bookingName: 'Somchai Jaidee (OR-7)',
        bookingEmail: 'somchai.jai@mail.kmutt.ac.th',
        eventCategoryId: 'DevOps/Infra Clinic',
        eventDuration: 30,
        eventStartTime: '2022-05-23T13:30:00Z',
        eventNotes: null,
    },
    {
        id: 2,
        bookingName: 'Somsri Rakdee (SJ-3)',
        bookingEmail: 'somsri.rak@mail.kmutt.ac.th',
        eventCategoryId: 'Project Management Clinic',
        eventDuration: 30,
        eventStartTime: '2022-04-27T09:30:00Z',
        eventNotes: 'ขอปรึกษาปัญหา เพื่อนไม่ช่วยงาน',
    },
    {
        id: 3,
        bookingName: '   TT-4',
        bookingEmail: 'Test3@3',
        eventCategoryId: 'Database Clinic',
        eventDuration: 15,
        eventStartTime: '2022-01-01T16:30:00Z',
        eventNotes: 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nostrum molestiae quidem esse praesentium explicabo. Odit vel unde, nesciunt eum quod molestiae aliquid, veniam in suscipit at dolore saepe, ipsam ipsum repellat mollitia aspernatur quibusdam vitae nostrum corporis reprehenderit dolorem hic deleniti! Explicabo nesciunt ab cupiditate tenetur nam possimus veniam quo unde eligendi nihil assumenda sequi accusantium corporis debitis dicta blanditiis numquam ea, iste nostrum perferendis! Repell',
    },
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
    //   {},
])
onBeforeMount(async () => {
    events.value = await zFetch.get('/api/events')
})
const test = () => {
    console.log('test')
}
let colorSet = '2'
let mouseOnRow = ref(null)
const mouseMoveOverRow = () => {
    // mousePos.value.x
}
const getDate =(dateTime)=> dayjs(dateTime).format('D MMM YYYY')
const getTime =(dateTime)=> dayjs(dateTime).format('HH:mm:ss')

</script>

<template>
    <!-- <div>{{events}}</div> -->
    <!-- dark:bg-gray-900 -->
    <main class="h-full overflow-y-auto px-6 pb-4">
        <h2 class="my-4 text-2xl font-semibold text-gray-700">OPSIP</h2>
        <!-- <div>
            <div>Mouse position is at: {{ mousePos.x }}, {{ mousePos.y }}</div>
            <div>Mouse on: {{ mouseOnRow }}</div>
        </div> -->

        <!-- show detail when mouse over -->
        <div id="cursor" class="m-6 p-4 bg-teal-50 rounded-xl max-w-4xl absolute pointer-events-none" v-if="mouseOnRow !== null"
            :style="[`left: ${mousePos.x}px`, `top: ${mousePos.y}px`]">
            <span><b>Booking Name:</b> {{ events[mouseOnRow].bookingName }}</span><br>
            <span><b>Booking Email:</b> {{ events[mouseOnRow].bookingEmail}}</span><br>
            <span><b>Category Name:</b> {{ events[mouseOnRow].eventCategoryId }}</span><br>
            <span><b>Start Date:</b> {{ getDate(events[mouseOnRow].eventStartTime)}}</span><br>
            <span><b>Start Time:</b> {{ getTime(events[mouseOnRow].eventStartTime)}}</span><br>
            <span><b>Duration:</b> {{ events[mouseOnRow].eventDuration }} minutes</span><br>
            <span v-show="events[mouseOnRow].eventNotes"><b>Note:</b> {{ events[mouseOnRow].eventNotes }}</span>
        </div>
        <div class="w-full overflow-hidden rounded-lg shadow-lg">
            <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap">
                    <thead>
                        <!-- dark:border-gray-700 dark:text-gray-400 dark:bg-gray-800 -->
                        <tr class="text-tr-head border-b bg-teal-300">
                            <th class="px-4 py-3">Booking Name</th>
                            <th class="px-4 py-3">Category Name</th>
                            <th class="px-4 py-3">Date</th>
                            <th class="px-4 py-3">Start Time</th>
                            <th class="px-4 py-3">Duration</th>
                        </tr>
                    </thead>
                    <!-- dark:divide-gray-700 dark:bg-gray-800 -->
                    <tbody class="bg-white divide-y">
                        <!-- dark:text-gray-400 -->
                        <tr class="text-gray-700 hover:bg-gray-100" v-for="(event, index) in events"
                            v-show="events.length > 0" @click="test" @mouseenter="mouseOnRow = index"
                            @mousemove="mouseMoveOverRow()" @mouseleave="mouseOnRow = null">
                            <td class="px-4 py-3 text-sm">{{ event.bookingName }}</td>
                            <td class="px-4 py-3 text-sm">{{ event.eventCategoryId }}</td>
                            <td class="px-4 py-3 text-sm">
                                {{ getDate(event.eventStartTime) }}
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
    </main>
</template>

<style>
</style>
