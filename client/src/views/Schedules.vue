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
        <div class="mb-4">
            <span class="text-h-1">Schedules</span>
            <span class="ml-4 text-lg dark:text-gray-200"> total: {{ events.length }}</span>
        </div>

        <div class="grid lg:grid-cols-2 md:grid-cols-1 gap-x-4 gap-y-2">
            <div class="flex bg-neutral-800 text-gray-100 hover:bg-neutral-700 cursor-pointer" 
                v-for="(event, index) in events" @click="viewDetail(event.id)" @mouseenter="mouseOnRow = index" @mouseleave="mouseOnRow = null">
                <div class="relative p-2 pl-4 w-3/12">
                    <span class="absolute inset-y-0 left-0 w-1 bg-green-600 " aria-hidden="true"></span>
                    <div class="font-bold text-4xl">{{ dayjs(event.eventStartTime).format('D') }}</div>
                    <div class="font-semibold text-lg">{{ dayjs(event.eventStartTime).format('MMM YY') }}</div>
                </div>
                <div class="w-full py-2 px-4 flex flex-col">
                    <div class="font-bold text-xl">{{ event.bookingName }}</div>
                    <div class="font-semibold ">{{ event.eventCategoryId }}</div>
                    <div class="">{{ dayjs(event.eventStartTime).format('LT') }} - Duration: {{ event.eventDuration }} minutes
                    </div>
                </div>
                <!-- <div class="w-2/12 p-2">Footer</div> -->
            </div>

        </div>


        <!-- show detail cursor -->
        <div id="cursor"
            class="absolute truncate m-4 py-1 px-3 bg-cyan-600 dark:bg-cyan-700 text-gray-100 rounded-xl pointer-events-none"
            v-if="mouseOnRow !== null" :style="[`left: ${mousePos.x}px`, `top: ${mousePos.y}px`]">
            show detail
        </div>

    </div>
</template>

<style>
</style>
