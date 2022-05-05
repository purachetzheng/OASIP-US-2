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
const goBack = () => router.go(-1)
</script>

<template>
    <div class="flex h-full justify-between gap-10 ">

        <div class="flex flex-col w-1/2 gap-y-4 ">
            <span class="text-h-1">Schedules</span>
            <div class="p-4 h-1/3 bg-red-200 rounded-lg">
                div
            </div>
            <div class="p-4 h-2/3 bg-green-200 rounded-lg">
                div
            </div>
        </div>

        <div class="flex flex-col w-1/2 gap-3 ">
            <div class="flex gap-4">
                <input class="rounded-md py-0.5 pl-2 w-5/12" type="text" placeholder="Search">
                <!-- <div class="w-0.5 bg-gray-50 "></div>
                <span class="text-gray-100">
                    sort by: Date
                </span>
                <select
                    class="">
                    <option>Date</option>
                    <option>Name</option>
                    <option>Duration</option>
                    <option>Category</option>
                </select>
                <span class="text-gray-100">with:</span> -->
            </div>
            <div class="overflow-y-auto flex flex-col gap-1 ">
                <div class="flex bg-neutral-800 hover:bg-neutral-600 cursor-pointer rounded-lg"
                    v-for="(event, index) in events">
                    <span class="bg-cyan-600 w-2 rounded-l-lg"></span>
                    <!-- <div class=" inset-y-0 left-0 p-0.5 rounded-l-lg bg-red-600" aria-hidden="true"></div> -->
                    <div class="pl-4 py-4 rounded-l-lg block h-full shadow-inner min-w-[20%]">
                        <div class="text-base text-gray-300 tracking-wide ">
                            {{ dayjs(event.eventStartTime).format('MMMYY') }}</div>
                        <div class="font-semibold text-4xl text-gray-100">{{ dayjs(event.eventStartTime).format('D') }}
                        </div>
                    </div>

                    <div class="bg-slate-200 w-0.5 my-4"></div>
                    <div class="w-full py-2 px-4 flex flex-col justify-center">
                        <div class="font-bold text-lg text-gray-200">{{ event.eventCategoryId }}</div>
                        <div class="font-semibold text-gray-300">{{ event.bookingName }}</div>
                        <div class="text-gray-400">{{ dayjs(event.eventStartTime).format('LT') }} ({{
                                event.eventDuration
                        }} minutes )</div>
                    </div><!-- <div class="w-2/12 p-2">Footer</div> -->
                </div>
            </div>
        </div>

    </div>
</template>

<style>
</style>
