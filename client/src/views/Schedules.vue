<script setup>
import { ref, computed, onBeforeMount, onMounted, nextTick, reactive, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//js
import { events } from '../js/event'
import { eventCategories } from '../js/eventCategory'

//router
import { useRoute, useRouter } from 'vue-router'

//components
import CursorTooltip from '../components/CursorTooltip.vue'

import EventList from '../components/ViewEvent/Schedules/EventList.vue'
import EventListHeader from '../components/ViewEvent/Schedules/EventListHeader.vue'
import EventFilter from '../components/ViewEvent/Schedules/EventFilter.vue'

//icons

//use router
// const { params } = useRoute()
const router = useRouter()
const goTo = (pageName, param = null) =>
    router.push({ name: pageName, params: param ? param : '' })
const goBack = () => router.go(-1)

//dayjs
dayjs.extend(localizedFormat)


const eventsList = ref([])
const updateEventList = () => eventsList.value = events.events.value
//set up
onBeforeMount(async () => {
    await events.get()

    updateEventList()
})

let mouseOn = ref(null)
let detailModal = ref({ visible: false })
const selectedEvent = ref({})

const viewDetail = async (id) => {
    // detailModal.value.visible = true
    // selectedEvent.value = await events.getById(id)
    goTo('EventDetail', { eventId: id })
}

const refreshEvent = () => events.get()
const removeEvent = async (id) => {
    confirm('Are you sure you want to cancel this event?') ? await events.remove(id) : ''
    updateEventList()
}
const updateEvent = (resEvent) => {
    // selectedEvent.value = resEvent
    selectedEvent.value.eventStartTime = resEvent.eventStartTime
    selectedEvent.value.eventNotes = resEvent.eventNotes
    updateEventList()
}
const selectedTag = {
    category: 'all',
    status: 'all',
    day: ''
}
const doSelect = () => {
    // console.log(selectedTag);
    eventsList.value = events.events.value
    const category = selectedTag.category
    eventsList.value = eventsList.value.filter(event => event.eventCategoryName == category || category === 'all')

    const status = selectedTag.status
    if (status == 'upcoming')
        eventsList.value = eventsList.value.filter(event => isTimeFuture(event.eventStartTime))
    else if (status == 'past')
        eventsList.value = eventsList.value.filter(event => !isTimeFuture(event.eventStartTime))

    if (selectedTag.day === '') return;
    const day = dayjs(selectedTag.day)
    eventsList.value = eventsList.value.filter(event => dayjs(event.eventStartTime).isSame(day, 'day'))
}
const selectCategory = (category) => {
    selectedTag['category'] = category
    doSelect()
    // const eventsPool = events.events.value
    // if(category == 'all') 
    //     return eventsList.value = eventsPool
    // eventsList.value = eventsPool.filter(event => event.eventCategoryName == category)
}
const selectDayStatus = (status) => {
    selectedTag.status = status
    doSelect()
    // const eventsPool = events.events.value
    // if(status == 'all') 
    //     return eventsList.value = eventsPool
    // if(status == 'upcoming')
    //     return eventsList.value = eventsPool.filter(event => isTimeFuture(event.eventStartTime))

    // eventsList.value = eventsPool.filter(event => !isTimeFuture(event.eventStartTime))
}
const isTimeFuture = (time) => dayjs(time).isAfter(dayjs())
const selectDay = (day) => {
    selectedTag.day = day
    doSelect()
    // console.log(dayjs('2022-05-20').isSame('2022-05-20T18:17:00Z','D'));
    // console.log(typeof(day));
    // console.log(day == null);
    // console.log(day == '');
}

const changeLayout = (test) => {
    console.log(test)
}

</script>

<template>
    <main class="h-full w-screen overflow-auto p-6">
        <div class="flex h-full justify-between gap-6">

            <!-- Filter -->
            <EventFilter @emitSelectCategory="selectCategory" @emitSelectDayStatus="selectDayStatus"
                @emitSelectDay="selectDay" />

            <!-- Layout - Event Schedules  -->
            <div class="flex flex-col w-full h-full gap-2">
                <!-- header -->
                <EventListHeader :eventAmount="eventsList.length" @emitRefreshEvent="refreshEvent" @emitLayout="changeLayout" />

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
                <div v-show="eventsList.length === 0">
                    No Scheduled Events
                </div>

                <!-- events list -->
                <EventList :events="eventsList" @emitRemoveEvent="removeEvent" @emitViewDetail="viewDetail" />
            </div>
        </div>

        <!-- <EventDetail :event="selectedEvent" :detailModal="detailModal" @emitUpdateEvent="updateEvent" /> -->
        <CursorTooltip :mouseOn="mouseOn" :minusY="0" />
    </main>
</template>

<style>
</style>
