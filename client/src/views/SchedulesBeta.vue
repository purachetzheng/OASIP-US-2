<script setup>
import { ref, computed, onBeforeMount, onMounted, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//js

//router
import { useRoute, useRouter } from 'vue-router'
import CarbonTrashCan from '../components/icons/CarbonTrashCan.vue'
import { events, eventCategories, middleFetch } from '../js/variable'
//icons
import RiTimeFill from '../components/icons/RiTimeFill.vue'
import RiMapPin2Fill from '../components/icons/RiMapPin2Fill.vue'
import RiInformationFill from '../components/icons/RiInformationFill.vue'
//components
import CursorTooltip from '../components/CursorTooltip.vue'
import IconGrid from '../components/icons/IconGrid.vue'
import IconList from '../components/icons/IconList.vue'


//use router
// const { params } = useRoute()
const router = useRouter()
const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
const goBack = () => router.go(-1)

//dayjs
dayjs.extend(localizedFormat)

//set up
onBeforeMount(async () => {
    await middleFetch.getEventsNull()
    // await middleFetch.getEvents()
})

let mouseOn = ref(null)

const viewDetail = (id) => {
    goTo('EventDetail', { eventId: id })
}

const removeEvent = async (id) => {
    confirm('Are you sure you want to cancel this event?') ? await middleFetch.removeEvent(id) : ''
}
</script>

<template>
    <main class="h-full w-screen overflow-y-auto p-2">
        <div class="flex h-full justify-between ">

            <div class="p-4 flex flex-col w-1/4 gap-y-2 pr-2">
                <div class="h-8">
                </div>
                <div class="h-1/3 rounded-lg flex flex-col">
                    <!-- Search -->
                    <div class="text-sm font-medium">
                        <h2 class="">Search</h2>
                    </div>
                    <input class="form-2 " type="text" placeholder="Search">
                </div>
                <!-- <div class="p-4 h-2/3 bg-green-200 rounded-lg">
                    div
                </div> -->
            </div>

            <div class="p-4 flex flex-col w-full gap-2 rounded-xl ">
                <!-- header -->
                <div class="flex justify-between">
                    <div class="">
                        <span class="text-lg tracking-wide font-medium">Schedules:</span> {{events.length}}
                         events.
                    </div>
                    <div class="flex gap-4 pr-4">
                        <button class="flex items-center gap-1.5">
                            <IconGrid class="w-4 h-4" />
                            <p class="text-sm font-medium">Grid</p>
                        </button>
                        <button class="flex items-center gap-1.5">
                            <IconList class="w-5 h-5" />
                            <p class="text-sm font-medium">List</p>
                        </button>
                    </div>
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
                <div v-show="events.length === 0">
                    No Scheduled Events
                </div>

                <div v-show="events.length !== 0" class="h-full overflow-y-auto grid grid-cols-3 auto-rows-min gap-x-2 gap-y-2 p-1">
                    <!-- loop events -->
                    <div class="flex flex-col shadow-lg p-4 bg-white rounded-md" v-for="(event, index) in events">

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
                        <div class="flex pt-4 gap-2">
                            <button 
                                class="btn-2 text-gray-50 bg-blue-400 hover:bg-blue-500"
                                @click="viewDetail(event.id)"
                            >
                                Detail
                            </button>
                            <button
                                class="btn-2 text-gray-700 bg-gray-50 hover:text-gray-50 hover:bg-red-400"
                                @click="removeEvent(event.id)"
                            >
                                Cancel
                            </button>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    </main>
</template>

<style>
</style>
