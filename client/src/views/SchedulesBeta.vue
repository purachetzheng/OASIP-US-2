<script setup>
import { ref, computed, onBeforeMount, onMounted, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//js
import { events, eventFetch } from '../js/event'

//router
import { useRoute, useRouter } from 'vue-router'

//components
import CursorTooltip from '../components/CursorTooltip.vue'
import EventDetail from '../components/SchedulesView/EventDetail.vue'
import EventList from '../components/SchedulesView/EventList.vue'
import EventListHeader from '../components/SchedulesView/EventListHeader.vue'

//icons

//use router
// const { params } = useRoute()
const router = useRouter()
const goTo = (pageName, param = null) => 
    router.push({ name: pageName, params: param ? param : '' })
const goBack = () => router.go(-1)

//dayjs
dayjs.extend(localizedFormat)

//set up
onBeforeMount(async () => { 
    await eventFetch.get() 
})

let mouseOn = ref(null)
let detailModal = ref({ visible: false })
const selectedEvent = ref({})

const viewDetail = async(id) => {
    // detailModal.value.visible = true
    // selectedEvent.value = await eventFetch.getById(id)
    goTo('EventDetail', { eventId: id })
}

const refreshEvent = () => eventFetch.get()
const removeEvent = async (id) => 
    confirm('Are you sure you want to cancel this event?') ? await eventFetch.remove(id) : ''
const updateEvent =  (resEvent) => {
    // selectedEvent.value = resEvent
    selectedEvent.value.eventStartTime = resEvent.eventStartTime
    selectedEvent.value.eventNotes = resEvent.eventNotes

}

// console.log('this is test');
// console.log(import.meta.env.VITE_BASE_URL);
// console.log(import.meta.env.BASE_URL);
</script>

<template>
    <main class=" h-full w-screen overflow-y-auto p-6">
        <div class="flex h-full justify-between gap-6">

            <!-- Filter -->
            <div class="flex flex-col w-1/4 gap-y-2">
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

            <!-- Layout - Event Schedules  -->
            <div class=" flex flex-col w-full gap-2 rounded-xl ">
                <!-- header -->
                <EventListHeader :eventAmount="events.length" @emitRefreshEvent="refreshEvent" />
                

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

                <!-- no event -->
                <div v-show="events.length === 0">
                    No Scheduled Events
                </div>

                <!-- events list -->
                <EventList :events="events"
                    @emitRemoveEvent="removeEvent" @emitViewDetail="viewDetail" />
            </div>
        </div>

        <EventDetail :event="selectedEvent" :detailModal="detailModal" 
            @emitUpdateEvent="updateEvent" />
        <CursorTooltip :mouseOn="mouseOn" :minusY="0" />
    </main>
</template>

<style>
</style>
