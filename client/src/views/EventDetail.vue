<script setup>
import { ref, computed, onBeforeMount, onMounted, reactive, toRef, } from 'vue'

//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//router
import { useRoute, useRouter } from 'vue-router';
//js
import { events, eventFetch } from '../js/event'

//icon
import IconCalendarFill from '../components/icons/IconCalendarFill.vue';
import IconCalendar from '../components/icons/IconCalendar.vue';
import IconTime from '../components/icons/IconTime.vue';
import IconPin from '../components/icons/IconPin.vue';
import IconEmail from '../components/icons/IconEmail.vue';
import Hourglass from '../components/icons/Hourglass.vue';
import IconEditFill from '../components/icons/Fill/IconEditFill.vue';
import IconClose from '../components/icons/IconClose.vue';
import IconSave from '../components/icons/IconSave.vue';
import IconArrowLeft from '../components/icons/IconArrowLeft.vue';

dayjs.extend(localizedFormat)

//use router
const { params } = useRoute()
const eventId = params.eventId
const router = useRouter()
const routerSigns = {
    goBack: () => router.go(-1),
    schedules: () => router.push({ name: 'Schedules' })
}

const event = ref({})
onBeforeMount(async () => {
    event.value = await eventFetch.getById(eventId)
    
    editing.setup()
    //first time left trick
    updateTimeleft()
})

const combineDT = (date, time) => dayjs(date + time).toJSON()
const editing = reactive({
    mode: false,
    event: {
        date: null,
        time: null,
        note: null
    },
    toggle(){
        this.mode? this.cancel() : this.mode = true
    },
    async save(){
        const obj = {
            eventStartTime: combineDT(this.event.date, this.event.time),
            eventNotes: this.event.note
        }
        const res = await eventFetch.patch(eventId, obj)
        event.value = res.event
        // console.log(res.event);
        this.cancel()
    },
    cancel(){
        this.mode = false
        this.setup()
    },
    setup(){
        this.event = {
            date: dayjs(event.eventStartTime).format('YYYY-MM-DD'),
            time: dayjs(event.eventStartTime).format('HH:mm'),
            note: event.value.eventNotes
        }
    }
    
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
</script>
 
<template>
    <main class="h-full w-screen overflow-y-auto">
        <button @click="test">test</button>
        <div class="flex flex-col h-full w-full justify-center items-center">
            <div class="flex h-4/5 w-4/5 bg-white shadow-md ">
                <div class="flex flex-col h-full w-3/4 px-6 border-r-2 overflow-y-scroll">
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
                        <!-- Date -->
                        <div class="flex gap-4 items-center">
                            <IconCalendar class="w-6 h-6" />
                            <input v-if="editing.mode" type="date" name="" id=""
                                class="text-lg font-medium w-fit bg-gray-200 rounded-md px-2"
                                v-model="editing.event.date"
                            >
                            <span v-else
                                class="text-lg font-medium">
                                {{ dayjs(event.eventStartTime).format('LL') }}
                            </span>
                        </div>
                        <!-- Time -->
                        <div class="flex gap-4 items-center">
                            <IconTime class="w-6 h-6" />
                            <input v-if="editing.mode" type="time" name="" id=""
                                class="text-lg font-medium w-fit bg-gray-200 rounded-md px-2"
                                v-model="editing.event.time"
                            >
                            <span v-else
                                class="text-lg font-medium">
                                {{ dayjs(event.eventStartTime).format('LT') }} -
                                {{ dayjs(event.eventStartTime).add(event.eventDuration, 'm').format('LT') }}
                            </span>
                            
                        </div>

                    </div>
                    <!-- Note -->
                    <div class="flex h-full w-full bg-white my-6">
                        <textarea v-if="editing.mode"
                            name="eventNotes"  placeholder="Note" maxlength="500"
                            class="w-full border p-1 text-md font-medium bg-gray-200"
                            @change="" v-model="editing.event.note"
                        ></textarea>
                        <div v-else
                            class="">
                            <span class="text-md">{{ event.eventNotes }}</span>
                        </div>
                    </div>
                    <!-- Show when editing mode on -->
                    <div v-if="editing.mode"
                        class="flex mb-6 gap-4">
                        <button type="button" 
                                @click="editing.save"
                                class="text-sm font-medium tracking-wide px-3 py-2 rounded-full bg-blue-500 text-gray-100
                                    flex items-center gap-1">
                            <IconSave class="w-6 h-6" />
                            <span class="hidden md:block">Save</span>
                        </button>
                        <button type="button" 
                            @click="editing.cancel"
                            class="text-sm font-medium tracking-wide px-3 py-2 rounded-full text-gray-800 hover:text-gray-100 hover:bg-red-500  
                                    flex items-center gap-1"
                        >
                            <IconClose class="w-6 h-6 md:-ml-1.5" />
                            <span class="hidden md:block">Cancel</span>
                        </button>
                    </div>
                </div>

                <div class="flex flex-col h-full w-1/4 items-center">
                    <!-- Option -->
                    <div class="flex w-full  divide-x-2">
                        <button @click="editing.toggle"
                            :class="[editing.mode ? 'text-gray-100 bg-blue-500':'bg-white text-blue-500 hover:bg-blue-500 hover:text-gray-100', 
                            `flex w-1/2 justify-center overflow-hidden `]">
                            <div
                                class="text-sm font-medium tracking-wide px-4 py-2
                                    flex items-center gap-1">
                                <IconEditFill class="w-6 h-6" />
                                <span class="hidden lg:block">Edit</span>
                            </div>
                        </button>
                        <button @click="routerSigns.goBack"
                            class="flex w-1/2 justify-center overflow-hidden 
                                bg-white hover:bg-red-500 text-red-500  hover:text-gray-100">
                            <div
                                
                                class="text-sm font-medium tracking-wide px-4 py-2 
                                    flex items-center gap-1">
                                <IconArrowLeft class="w-6 h-6" />
                                <span class="hidden lg:block">Back</span>
                            </div>
                        </button>
                    </div>
                    <!-- Category -->
                    <div class="bg-white p-6 h-2/5 w-full overflow-hidden border-y-2">
                        <div class="h-16">
                            <span class="text-lg font-medium">{{ event.eventCategoryName }}</span>
                        </div>
                        <div class="flex gap-2">
                            <Hourglass class="w-6 h-6" />
                            <span class="text-md font-medium">{{ event.eventDuration }} minute</span>
                        </div>
                    </div>
                    <!-- **** -->
                    <div v-if="false" class="flex flex-col bg-white h-2/3 py-4 w-full gap-2 justify-center overflow-hidden">
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

</style>
