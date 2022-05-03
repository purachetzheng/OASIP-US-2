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
//fetch
import { zFetch } from '../js/zFetch'
//mouse
import { useMouse } from '../js/mouse'
//router
import { useRoute, useRouter } from 'vue-router'

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
const mouseMoveOverRow = (index) => {
    // console.log(index);
    // mousePos.value.x
}
const getDate = (dateTime) => dayjs(dateTime).format('LL')
const getTime = (dateTime) => dayjs(dateTime).format('HH:mm')
// const getTime = (dateTime) => dayjs(dateTime).format('LT')
const modal = ref({ visible: false })
const selectedEvent = ref({})
const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
const viewDetail = (id) => {
    // selectedEvent.value = events.value[index]
    // modal.value.visible = true
    goTo('EventDetail', {eventId:id})
}

</script>

<template>
    <div class="">
        <h2 class="my-4 text-2xl font-semibold text-gray-700 dark:text-gray-200">Schedules</h2>

        <!-- show detail cursor -->
        <div id="cursor"
            class="absolute truncate m-4 py-1 px-3 bg-cyan-600 dark:bg-cyan-600 text-gray-100 rounded-xl pointer-events-none"
            v-if="mouseOnRow !== null" :style="[`left: ${mousePos.x}px`, `top: ${mousePos.y}px`]">
            click to show details of: <b>{{ events[mouseOnRow].bookingName }}</b>
        </div>

        <div class="w-full overflow-hidden rounded-lg shadow-lg">
            <div class="w-full overflow-x-auto">
                <table class="w-full whitespace-no-wrap">
                    <thead>
                        <!-- dark:border-gray-700 dark:text-gray-400 dark:bg-gray-800 -->
                        <tr class="text-xs font-semibold tracking-wider text-left text-gray-100 
                        dark:text-cyan-50 uppercase border-b dark:border-gray-700 bg-cyan-600">
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
                        <tr class="text-gray-700 dark:text-gray-300 dark:hover:bg-gray-500 hover:bg-gray-200 cursor-pointer"
                            v-for="(event, index) in events" @click="viewDetail(event.id)" @mouseenter="mouseOnRow = index"
                            @mousemove="mouseMoveOverRow(index)" @mouseleave="mouseOnRow = null">
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
</template>

<style>
</style>
