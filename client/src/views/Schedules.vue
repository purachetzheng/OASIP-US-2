<script setup>
import { ref, computed, onBeforeMount, onMounted, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//js
import { zFetch } from '../js/zLib'
import { events, eventCategories, middleFetch } from '../js/variable'
import { useMouse } from '../js/mouse'
//router
import { useRoute, useRouter } from 'vue-router'

import CarbonTrashCan from '../components/icons/CarbonTrashCan.vue'
dayjs.extend(localizedFormat)

//use router
// const { params } = useRoute()
const router = useRouter()
//use mouse
const mousePos = useMouse()

// const events = ref([])
onBeforeMount(async () => {
    await middleFetch.getEventsNull()
    await middleFetch.getEventCategoriesNull()
})

let mouseOnRow = ref(null)

const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
const viewDetail = (id) => {
    goTo('EventDetail', { eventId: id })
}

const removeEvent = async (id) => {
    // events.value = await zFetch.remove('http://intproj21.sit.kmutt.ac.th/us2/api/events/'+ id) ? events.value.filter((event) => event.id !== id) : events.value
    await middleFetch.removeEvent(id)
}
</script>

<template>
    <main class="h-full overflow-y-auto screen-p-1">
        <!-- Header -->
        <div class="mb-4">
            <span class="text-h-1">Schedules</span>
            <span class="ml-4 text-lg dark:text-gray-200">Total: {{ events.length }}</span>
        </div>
        <span class="text-gray-100 text-lg" v-show="events.length === 0">No Scheduled Events</span>
        <div class="grid xl:grid-cols-3 lg:grid-cols-2 md:grid-cols-1 gap-x-4 gap-y-2">
            <!-- bg-neutral-800 hover:bg-neutral-600 -->
            <div class="relative flex bg-neutral-800 hover:bg-neutral-600 cursor-pointer rounded-lg"
                v-for="(event, index) in events" @mouseenter="mouseOnRow = index"
                @mouseleave="mouseOnRow = null">
                <!-- <div class=" inset-y-0 left-0 p-0.5 rounded-l-lg bg-red-600" aria-hidden="true"></div> -->
                    <!-- <div class="pl-4 py-4 rounded-l-lg lg:w-3/12 md:w-2/12  block h-full shadow-inner min-w-[25%]">
                        <div class="text-base text-gray-300 tracking-wide ">{{ dayjs(event.eventStartTime).format('MMM YY') }}</div>
                        <div class="font-semibold text-4xl text-gray-100">{{ dayjs(event.eventStartTime).format('D') }}</div>
                    </div> -->
                <div class="h-full w-full flex" @click="viewDetail(event.id)">
                        <span class="bg-cyan-600 w-2 rounded-l-lg"></span>
                    <div class="rounded-lg lg:w-3/12 py-2 sm:px-4 block h-full shadow-inner">
                        <div class="text-center">
                            <div class="text-white font-bold text-4xl">{{ dayjs(event.eventStartTime).format('D') }}</div>
                            <div class="text-white font-normal text-2xl tracking-wider">{{ dayjs(event.eventStartTime).format('MMM') }} </div>
                            <div class="text-white font-normal text-xl tracking-wider">{{dayjs(event.eventStartTime).format('YYYY')}}</div>
                        </div>
                    </div>
                    <div class="bg-slate-200 w-0.5 my-5"></div>
                    <div class="w-full py-4 px-4 flex flex-col justify-center ">
                        <div class="font-bold text-xl text-gray-200">{{ event.bookingName }}</div>
                        <div class="font-semibold text-gray-300">{{ event.eventCategoryName }}</div>
                        <div class="text-gray-400">{{ dayjs(event.eventStartTime).format('LT') }} ({{
                                event.eventDuration
                        }} minutes )
                        </div>
                    </div>
                </div>
                
                <div class="w-2/12 p-2 flex flex-col justify-center items-center">
                    <div class="" @click="removeEvent(event.id)">
                        <CarbonTrashCan class="text-3xl text-gray-200 text-center"/>
                    </div>
                </div>
            </div>

        </div>


        <!-- show detail cursor -->
        <div id="cursor"
            class="absolute truncate m-4 py-1 px-3 bg-cyan-600 dark:bg-cyan-700 text-gray-100 rounded-xl pointer-events-none"
            v-if="mouseOnRow !== null" :style="[`left: ${mousePos.x}px`, `top: ${mousePos.y}px`]">
            show detail
        </div>

    </main>
</template>

<style>
</style>
