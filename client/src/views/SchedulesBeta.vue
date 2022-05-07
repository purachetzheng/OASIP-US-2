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

import { events, eventCategories, middleFetch } from '../js/variable'
dayjs.extend(localizedFormat)

//use router
// const { params } = useRoute()
const router = useRouter()
//use mouse
const mousePos = useMouse()

// const events = ref([])
onBeforeMount(async () => {
<<<<<<< HEAD
    events.value.length === 0 ? events.value = await zFetch.get('http://intproj21.sit.kmutt.ac.th/us2/api/events') : ''
    eventCategories.value.length === 0 ? eventCategories.value = await zFetch.get('http://intproj21.sit.kmutt.ac.th/us2/api/eventcategories') : ''
=======
    await middleFetch.getEventsNull()
    await middleFetch.getEventCategoriesNull()
>>>>>>> dev
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
    <main class="h-full overflow-y-auto screen-p-1">
        <!-- <div class="absolute h-full top-0 left-4 text-gray-100 flex flex-col justify-center">
        <button>Test</button>
    </div> -->
        <div class="flex h-full justify-between gap-10">

            <div class="flex flex-col w-2/5 gap-y-4">

                <div class="p-4 h-1/3 bg-red-200 rounded-lg flex flex-col">
                    <span class="text-h-1 text-gray-800">Filter</span>
                    <input class="rounded-md py-0.5 pl-2 w-5/12" type="text" placeholder="Search">
                    <!-- <div>Category:</div>
                    <div>Category</div> -->
                </div>
                <div class="p-4 h-2/3 bg-green-200 rounded-lg">
                    div
                </div>
            </div>

            <div class="flex flex-col w-3/5 ">
                <div class="flex gap-4 mb-4">
                    <span class="text-h-1">Schedules</span>

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
                <div class="overflow-y-auto grid 2xl:grid-cols-2 xl:grid-cols-1 lg:grid-cols-1 sm:grid-cols-1 gap-x-4 gap-y-2">
                    <div class="flex bg-white dark:bg-neutral-800 dark:hover:bg-neutral-600 cursor-pointer rounded-lg"
                        v-for="(event, index) in events">
                        <span class="bg-cyan-600 w-2 rounded-l-lg"></span>
                        <!-- <div class=" inset-y-0 left-0 p-0.5 rounded-l-lg bg-red-600" aria-hidden="true"></div> -->
                        <div class="pl-4 py-4 rounded-l-lg block h-full min-w-[25%]">
                            <div class="text-base dark:text-gray-300 tracking-wide ">
                                {{ dayjs(event.eventStartTime).format('MMM YY') }}</div>
                            <div class="font-semibold text-4xl dark:text-gray-100">{{ dayjs(event.eventStartTime).format('D')
                            }}
                            </div>
                        </div>

                        <div class="bg-gray-500 dark:bg-gray-200 w-0.5 my-4"></div>
                        <div class="w-full py-2 px-4 flex flex-col justify-center">
                            <div class="font-bold text-lg dark:text-gray-200">{{ event.eventCategoryId }}</div>
                            <div class="font-semibold dark:text-gray-300">{{ event.bookingName }}</div>
                            <div class="text-sm dark:text-gray-400">{{ dayjs(event.eventStartTime).format('LT') }} -
                                {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }} ({{
                                        event.eventDuration
                                }} minutes )</div>
                        </div><!-- <div class="w-2/12 p-2">Footer</div> -->
                    </div>

                </div>
            </div>

        </div>
    </main>
</template>

<style>
</style>
