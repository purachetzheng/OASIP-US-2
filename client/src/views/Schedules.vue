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

const viewDetail = async (id) => {
    goTo('EventDetail', { eventId: id })
}

const refreshEvent = () => events.get()

const selectedTag = {
    category: 'all',
    status: 'all',
    day: ''
}
const doSelect = () => {
    eventsList.value = events.events.value
    const category = selectedTag.category
    eventsList.value = eventsList.value.filter(event => event.eventCategoryName == category || category === 'all')

    //Status
    const status = selectedTag.status
    if (status == 'upcoming'){
        eventsList.value = eventsList.value.filter(event => isTimeFuture(event.eventStartTime))
        //ascending order on event time
        eventsList.value.sort((a, b) => a.eventDuration - b.eventDuration)
    }
    else if (status == 'past')
        eventsList.value = eventsList.value.filter(event => !isTimeFuture(event.eventStartTime))
    //Day
    if (selectedTag.day === '') return;
    const day = dayjs(selectedTag.day)
    eventsList.value = eventsList.value.filter(event => dayjs(event.eventStartTime).isSame(day, 'day'))
    //ascending order on event time
    eventsList.value.sort((a, b) => a.eventDuration - b.eventDuration)
}
const selectCategory = (category) => {
    selectedTag['category'] = category
    doSelect()
}
const selectDayStatus = (status) => {
    selectedTag.status = status
    doSelect()
}
const selectDay = (day) => {
    selectedTag.day = day
    doSelect()
}
const changeLayout = (test) => {
    console.log(test)
}
const isTimeFuture = (time) => dayjs(time).isAfter(dayjs())

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
                    <p v-show="selectedTag.status == 'all'">No Scheduled Events</p>
                    <p v-show="selectedTag.status == 'upcoming'">No On-Going or Upcoming Events</p>
                    <p v-show="selectedTag.status == 'past'">No Past Events</p>
                </div>

                <!-- events list -->
                <EventList :events="eventsList" @emitViewDetail="viewDetail" />
            </div>
        </div>

        <!-- <EventDetail :event="selectedEvent" :detailModal="detailModal" @emitUpdateEvent="updateEvent" /> -->
        <!-- <CursorTooltip :mouseOn="mouseOn" :minusY="0" /> -->
    </main>
</template>

<style>
</style>
