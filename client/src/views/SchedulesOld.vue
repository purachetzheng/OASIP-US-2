<script setup>
import { ref, computed, onBeforeMount, onMounted, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//js

//router
import { useRoute, useRouter } from 'vue-router'
import CarbonTrashCan from '../components/icons/CarbonTrashCan.vue'
import {  events } from '../js/event'
//icons
import RiTimeFill from '../components/icons/RiTimeFill.vue'
import RiMapPin2Fill from '../components/icons/RiMapPin2Fill.vue'
import RiInformationFill from '../components/icons/RiInformationFill.vue'
//components
import CursorTooltip from '../components/CursorTooltip.vue'

//use router
// const { params } = useRoute()
const router = useRouter()
const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
const goBack = () => router.go(-1)

//dayjs
dayjs.extend(localizedFormat)

//set up
onBeforeMount(async () => {
    await events.getIfEmpty()
    // await middleFetch.getEvents()
})

let mouseOn = ref(null)

const viewDetail = (id) => {
    goTo('EventDetail', { eventId: id })
}

const removeEvent = async (id) => {
    confirm('Are you sure you want to cancel this event?')?await events.remove(id) : ''
}
</script>

<template>
    <main class="h-full overflow-y-auto screen-p-1">
        <!-- <div class="absolute h-full top-0 left-4 text-gray-100 flex flex-col justify-center">
        <button>Test</button>
    </div> -->
        <div class="flex h-full justify-between gap-10">

            <div class="p-4 flex flex-col w-8/12 gap-2 bg-white rounded-xl shadow-lg">
                <div class="flex items-baseline justify-center">
                    <!-- header -->
                    <span class="text-h-2 text-lg tracking-wide font-medium">Schedules</span>
                </div>

                <div v-if="false" class="flex gap-4 bg-gray-100 p-1.5 rounded-lg">
                    <div class="flex w-1/3 items-center justify-center gap-2 py-2 rounded-lg bg-white">
                        <div class="w-3 h-3 border-blue-400  border-3 rounded-full"></div>
                        <span class="text-sm font-semibold">All</span>
                        <div class="flex text-base font-semibold bg-blue-200 px-1 rounded-md">2</div>
                    </div>
                    <div class="flex w-1/3 items-center justify-center gap-2 py-2 rounded-lg">
                        <div class="w-3 h-3 border-green-400  border-3 rounded-full"></div>
                        <span class="text-sm font-semibold">Upcoming</span>
                        <div class="flex text-base font-semibold bg-green-200 px-1 rounded-md">2</div>
                    </div>
                    <div class="flex w-1/3 items-center justify-center gap-2 py-2 rounded-lg">
                        <div class="w-3 h-3 border-red-400  border-3 rounded-full"></div>
                        <span class="text-sm font-semibold">Past</span>
                        <div class="flex text-base font-semibold bg-red-200 px-1 rounded-md">2</div>
                    </div>
                </div>
                <!-- events list -->
                <div class="h-full overflow-y-auto grid grid-cols-1 auto-rows-min gap-x-4 gap-y-0.5 p-1">
                    <!-- loop events -->
                    <div class="relative flex "
                        v-for="(event, index) in events">
                        <span class="bg-green-300 w-1.5 rounded-l-lg"></span>
                        <div class="flex flex-col w-20 p-2 pr-1 bg-gray-50 ">
                            <!-- Month Year -->
                            <div class="whitespace-nowrap text-sm font-medium text-gray-600 ">{{ dayjs(event.eventStartTime).format('MMM YY') }}</div>
                            <!-- Day -->
                            <div class="text-xl font-semibold">{{ dayjs(event.eventStartTime).format('D') }}</div>
                        </div>
                        <div class="flex flex-col h-full w-full py-2 pl-1 bg-gray-50 ">
                            <!-- Name -->
                            <div class="flex items-center gap-2 w-full">
                                <div class="text-base font-medium break-all text-gray-700">{{ event.bookingName }}</div>
                            </div>
                            <div class="flex items-center gap-2">
                                
                            </div>
                            <div class="flex flex-wrap gap-x-2 text-sm font-medium text-gray-600">
                                <!-- Time -->
                                <div class="flex items-center gap-2">
                                    <RiTimeFill />
                                    <div class="whitespace-nowrap">{{ dayjs(event.eventStartTime).format('LT') }}</div> 
                                </div>
                                <!-- Duration -->
                                <div class="flex items-center gap-2">
                                    <div class="w-1.5 h-1.5 bg-gray-600 rounded-full"></div>
                                    <div class="whitespace-nowrap">{{ event.eventDuration }} min</div>
                                </div>
                                <!-- EventCategoryName -->
                                <div class="flex items-center gap-2">
                                    <RiMapPin2Fill />
                                    <div class="">{{ event.eventCategoryName }}</div>
                                </div>
                            </div>
                        </div>
                        <div class="flex justify-end items-center gap-1 p-2 bg-gray-50 rounded-r-lg">
                            <button class="" @click="viewDetail(event.id)" 
                                @mouseenter="mouseOn = 'Show Detail'"
                                @mouseleave="mouseOn = null">
                                <RiInformationFill class="text-xl text-gray-500 text-center"/>
                            </button>
                            <button class="" @click="removeEvent(event.id)"
                                @mouseenter="mouseOn = 'Delete Event'"
                                @mouseleave="mouseOn = null">
                                <CarbonTrashCan class="text-xl text-red-500 text-center"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="flex flex-col w-4/12 gap-y-4">

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
        </div>
        <CursorTooltip :mouseOn="mouseOn" />
    </main>
</template>

<style>
</style>
