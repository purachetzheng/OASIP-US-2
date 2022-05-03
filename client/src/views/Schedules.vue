<script setup>
import { ref, computed, onBeforeMount, onMounted, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//js
import { zFetch } from '../js/zLib'
//mouse
import { useMouse } from '../js/mouse'
//router
import { useRoute, useRouter } from 'vue-router'

import { events, eventCategories } from '../js/variable'
dayjs.extend(localizedFormat)

//use router
// const { params } = useRoute()
const router = useRouter()
//use mouse
const mousePos = useMouse()

// const events = ref([])
onBeforeMount(async () => {
    events.value.length === 0 ? events.value = await zFetch.get('http://ip21us2.sit.kmutt.ac.th:8080/api/events') : ''
    eventCategories.value.length === 0 ? eventCategories.value = await zFetch.get('http://ip21us2.sit.kmutt.ac.th:8080/api/eventcategories') : ''
})

const rId = ref(0)
let mouseOnRow = ref(null)
const getDate = (dateTime) => dayjs(dateTime).format('LL')
const getTime = (dateTime) => dayjs(dateTime).format('HH:mm')
// const getTime = (dateTime) => dayjs(dateTime).format('LT')

const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
const viewDetail = (id) => {
    goTo('EventDetail', { eventId: id })
}

</script>

<template>
    <div class="">
        <!-- Header -->
        <h2 class="my-4 text-h-1">Schedules</h2>
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
                    <tbody class="bg-white divide-y divide-gray-700 dark:divide-gray-400 dark:bg-neutral-700">
                        <tr v-show="events.length === 0">
                            <td colspan="5" class="px-4 py-3 text-center">
                                No Scheduled Events
                            </td>
                        </tr>
                        <!-- dark:text-gray-400 -->
                        <tr class="text-sm text-gray-700 dark:text-gray-300 dark:hover:bg-gray-500 hover:bg-gray-200 cursor-pointer"
                            v-for="(event, index) in events" @click="viewDetail(event.id)"
                            @mouseenter="mouseOnRow = index" @mouseleave="mouseOnRow = null">
                            <td class="px-4 py-3">{{ event.bookingName }}</td>
                            <td class="px-4 py-3">{{ event.eventCategoryId }}</td>
                            <td class="px-4 py-3">{{ getDate(event.eventStartTime) }}</td>
                            <td class="px-4 py-3">{{ getTime(event.eventStartTime) }}</td>
                            <td class="px-4 py-3">{{ event.eventDuration }} minutes</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<style>
</style>
