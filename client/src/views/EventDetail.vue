<script setup>
import { ref, computed, onBeforeMount, onMounted, reactive, } from 'vue'

//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//router
import { useRoute, useRouter } from 'vue-router';
//js

// import { events, eventCategories, middleFetch } from '../js/variable'
import { events, eventFetch } from '../js/event'
import IconCalendarFill from '../components/icons/IconCalendarFill.vue';
import IconCalendar from '../components/icons/IconCalendar.vue';
import IconTime from '../components/icons/IconTime.vue';
import IconPin from '../components/icons/IconPin.vue';
import IconEmail from '../components/icons/IconEmail.vue';
import Hourglass from '../components/icons/Hourglass.vue';
import IconEditFill from '../components/icons/Fill/IconEditFill.vue';
import IconClose from '../components/icons/IconClose.vue';
//icon


//use router
const { params } = useRoute()
const id = params.eventId
const myRouter = useRouter()
const goBack = () => myRouter.go(-1)


const event = ref({})
onBeforeMount(async () => {
    event.value = await eventFetch.getById(id)
})

dayjs.extend(localizedFormat)

// const event = events.find(e => e.id === id)

onBeforeMount(async () => {
    updateTimeleft()
    //     zFetch.settings.baseUrl = 'http://ip21us2.sit.kmutt.ac.th:8080'
    // event.value = await zFetch.get('/api/events/' + id)
})

// Countdown
const timeleft = reactive({ day: 0, hour: 0, minute: 0, second: 0 })
const updateTimeleft = () => {
    const eventDT = dayjs(event.value.eventStartTime)
    const nowDT = dayjs()
    timeleft.day = eventDT.diff(nowDT, 'day')
    timeleft.hour = eventDT.diff(nowDT, 'hour') % 24
    timeleft.minute = eventDT.diff(nowDT, 'minute') % 60
    timeleft.second = eventDT.diff(nowDT, 'second') % 60
}
setInterval(updateTimeleft, 1000);
//
</script>
 
<template>
    <main class="h-full w-screen overflow-y-auto">
        <div class="flex flex-col h-full w-full py-16 px-56 justify-center items-center gap-2">
            <div class="flex h-4/5 w-full gap-2">
                <div class="flex flex-col h-full  w-3/4 bg-white shadow-md px-6 ">
                    <!-- Name -->
                    <div class="my-6">
                        <p class="text-3xl font-medium truncate">{{ event.bookingName }}</p>
                    </div>
                    <div class="flex flex-col gap-2">
                        <!-- Email -->
                        <div class="flex gap-4 items-center">
                            <IconEmail class="w-6 h-6" />
                            <span class="text-lg font-medium">{{ event.bookingEmail }}</span>
                        </div>
                        <!-- Category -->
                        <!-- <div class="flex gap-4 items-center">
                            <IconPin class="w-6 h-6" />
                            <span class="text-lg font-medium">{{ event.eventCategoryName }}</span>
                        </div> -->

                        <!-- Date -->
                        <div class="flex gap-4 items-center">
                            <IconCalendar class="w-6 h-6" />
                            <span class="text-lg font-medium">{{ dayjs(event.eventStartTime).format('LL') }}</span>
                        </div>
                        <!-- Time -->
                        <div class="flex gap-4 items-center">
                            <IconTime class="w-6 h-6" />
                            <span class="text-lg font-medium">
                                {{ dayjs(event.eventStartTime).format('LT') }} -
                                {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }}
                            </span>
                        </div>

                    </div>
                    <div class="flex h-full w-full bg-white mt-6">
                        <div class="">
                            <span class="text-lg">{{ event.eventNotes }}</span>
                        </div>
                    </div>
                </div>

                <div class="flex flex-col h-full w-1/4 items-center gap-2 ">
                    <!-- Option -->
                    <div class="flex  w-full gap-2">
                        <div @click="" class="flex w-1/2 justify-center shadow-md 
                                bg-white hover:bg-blue-500 text-blue-500 hover:text-gray-100">
                            <button type="button" class="font-medium tracking-wider px-4 py-2 
                                    flex items-center gap-1">
                                <IconEditFill class="w-6 h-6" />
                                <span class="">Edit</span>
                            </button>
                        </div>
                        <div
                            class="flex w-1/2 justify-center shadow-md bg-white hover:bg-red-500 text-red-500  hover:text-gray-100">
                            <button type="button" class="font-medium tracking-wider px-4 py-2 
                                    flex items-center gap-1">
                                <IconClose class="w-6 h-6" />
                                <span class="">Cancel</span>
                            </button>
                        </div>
                    </div>
                    <!-- Category -->
                    <div class="bg-white shadow-md p-6 h-2/5 w-full">
                        <div class="h-16">
                            <span class="text-lg font-medium">{{ event.eventCategoryName }}</span>
                        </div>
                        <div class="flex gap-2">
                            <Hourglass class="w-6 h-6" />
                            <span class="text-md font-medium">{{ event.eventDuration }} minute</span>
                        </div>
                    </div>
                    <!-- Timeleft -->
                    <div class="flex flex-col bg-white shadow-md h-2/3 py-4 w-full gap-2 justify-center">
                        <span class="text-lg font-medium text-center">Time Left</span>
                        <div class="flex gap-1 w-full justify-center overflow-x-scroll ">
                            <!-- day -->
                            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                                <div class="text-sm font-medium">Day</div>
                                <span class="text-2xl font-medium">
                                    {{ timeleft.day }}
                                </span>
                            </div>

                            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                                <div class="text-sm font-medium">Hour</div>
                                <span class="text-2xl font-medium">
                                    {{ timeleft.hour }}
                                </span>
                            </div>

                            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                                <div class="text-sm font-medium">Min</div>
                                <span class="text-2xl font-medium">
                                    {{ timeleft.minute }}
                                </span>
                            </div>

                            <div class="flex flex-col items-center bg-blue-50 w-10 py-1.5">
                                <div class="text-sm font-medium">Sec</div>
                                <span class="text-2xl font-medium">
                                    {{ timeleft.second }}
                                </span>
                            </div>
                        </div>
                    </div>


                </div>

            </div>

        </div>


    </main>
</template>
 
<style>
.custom-header {
    font-size: 3.25rem
        /* 36px */
    ;
    line-height: 3.25rem
        /* 40px */
    ;
    font-weight: 700;
}
</style>
