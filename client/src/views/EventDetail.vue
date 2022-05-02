<script setup>
import {
    ref,
    computed,
    reactive,
    nextTick,
    onBeforeMount,
    onMounted,
} from 'vue'
//fetch
import { zFetch } from '../js/zFetch'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
import utc from 'dayjs/plugin/utc'
import timezone from 'dayjs/plugin/timezone'

import { useRoute, useRouter } from 'vue-router';
import IcOutlineArrowBackIos from '../components/icons/IcOutlineArrowBackIos.vue';
const { params } = useRoute()
const id = params.eventId

const myRouter = useRouter()
const goBack = () => myRouter.go(-1)


dayjs.extend(utc)
dayjs.extend(timezone)
dayjs.extend(localizedFormat)
const event = ref({})

onBeforeMount(async () => {
    zFetch.settings.baseUrl = 'http://ip21us2.sit.kmutt.ac.th:8080'
    event.value = await zFetch.get('/api/events/' + id)
})
const getDate = (dateTime) => dayjs(dateTime).tz('GMT').format('LL')
const getTime = (dateTime) => dayjs(dateTime).tz('GMT').format('HH:mm')

</script>
 
<template>
    <div class="h-full w-full">
        <h2 class="my-4 text-2xl font-semibold text-gray-700 dark:text-gray-200">Event Details</h2>
        <button class="bg-cyan-500 hover:bg-blue-700 text-gray-100 py-1 px-3 rounded" @click="goBack">
            Back
        </button>
        <div class="flex justify-center">
            <div class="p-8 overflow-hidden rounded-xl shadow-lg bg-zinc-700 h-8/12 flex">
                <!-- <div class="w-2/6 flex flex-col dark:text-gray-100 font-bold space-y-4">
                    <span>Booking Name</span>
                    <span>Booking Email</span>
                    <span>Category Name</span>
                    <span>Start Date</span>
                    <span>Start Time</span>
                    <span>Duration</span>
                    <span>Note</span>
                </div>
                <div class="flex flex-col space-y-4 dark:text-gray-100">
                    <span>{{event.bookingName}}</span>
                    <span>{{event.bookingEmail}}</span>
                    <span>{{ event.eventCategoryId }}</span>
                    <span>{{ getDate(event.eventStartTime) }}</span>
                    <span>{{ getTime(event.eventStartTime) }}</span>
                    <span>{{ event.eventDuration }}</span>
                    <span>{{ event.eventNotes }}</span>
                </div> -->
                <!-- min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800 -->
                <div class="flex flex-col space-y-2 dark:text-gray-200 min-w-0 p-4 ">
                    <div><span class="font-bold">Booking Name:</span> {{ event.bookingName }}</div>
                    <div><span class="font-bold">Booking Email:</span> {{ event.bookingEmail }}</div>
                    <div><span class="font-bold">Category Name:</span> {{ event.eventCategoryId }}</div>
                    <div><span class="font-bold">Start Date:</span> {{ getDate(event.eventStartTime) }}</div>
                    <div><span class="font-bold">Start Time:</span> {{ getTime(event.eventStartTime) }}</div>
                    <div><span class="font-bold">Duration:</span> {{ event.eventDuration }} minutes</div>
                    <div><span class="font-bold">Note:</span> {{ event.eventNotes }}</div>
                </div>
            </div>
        </div>



    </div>
</template>
 
<style>
</style>