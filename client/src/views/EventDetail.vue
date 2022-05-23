<script setup>
import { ref, computed, onBeforeMount, onMounted, reactive, toRef, } from 'vue'
//date-time lib
import dayjs from 'dayjs'
import localizedFormat from 'dayjs/plugin/localizedFormat'
//router
import { useRoute, useRouter } from 'vue-router';
//js
import { events } from '../js/event'
//icon
import IconCalendarFill from '../components/icons/Fill/IconCalendarFill.vue';
import IconCalendar from '../components/icons/IconCalendar.vue';
import IconTime from '../components/icons/IconTime.vue';
import IconPin from '../components/icons/IconPin.vue';
import IconEmail from '../components/icons/IconEmail.vue';
import IconHourglass from '../components/icons/IconHourglass.vue';
import IconEditFill from '../components/icons/Fill/IconEditFill.vue';
import IconClose from '../components/icons/IconClose.vue';
import IconSave from '../components/icons/IconSave.vue';
import IconArrowLeft from '../components/icons/IconArrowLeft.vue';

import EventTimeleft from '../components/ViewEvent/EventDetail/EventTimeleft.vue';
import BaseModalFailed from '../components/modal/BaseModalFailed.vue';

dayjs.extend(localizedFormat)

//use router
const { params } = useRoute()
const eventId = params.eventId
const router = useRouter()
const routerSigns = {
    goBack: () => router.go(-1),
    schedules: () => router.push({ name: 'Schedules' })
}

const event = ref({
    // eventStartTime: null
})
onBeforeMount( async() => {
    event.value = await events.getById(eventId)
    editing.setup()
})

const combineDT = (date, time) => dayjs(date + time).toJSON()
const editing = reactive({
    modalFailed: {
        visible: false,
        titil: 'Update Failed',
        desc: 'Event '
    },
    mode: false,
    event: { date: null, time: null, note: null},
    isTimeError: false,
    checkTimeError(){
        console.log('s');
        if(this.event.date == null || this.event.time == null) return this.isTimeError = false
        this.isTimeError = !checker.isFuture(this.event.date + this.event.time) 
        console.log(this.isTimeError);
    },
    toggle(){
        this.mode? this.cancel() : this.mode = true
    },
    async save(){
        console.log(this.event); 
        const obj = {
            eventStartTime: combineDT(this.event.date, this.event.time),
            eventNotes: this.event.note
        }
        const res = await events.patch(eventId, obj)
        if(res.status) this.cancel()

        let message = ''
        await res.error.details.forEach(d => message += `${d.field}: ${d.errorMessage}; `)

        editing.modalFailed.desc = message
        editing.modalFailed.visible = true
    },
    cancel(){
        this.mode = false
        this.setup()
    },
    setup(){
        this.event = {
            date: dayjs(event.value.eventStartTime).format('YYYY-MM-DD'),
            time: dayjs(event.value.eventStartTime).format('HH:mm'),
            note: event.value.eventNotes
        }
    }
})
const checker = {
    isBlank: (string) => string.match(/^\s+$/),
    isFuture: (datetime) => dayjs(datetime).isAfter(dayjs()),
}
</script>
 
<template>
    <main class="h-full w-screen overflow-auto">
        <div class="flex flex-col h-full w-full justify-center items-center">
            <div class="flex h-4/5 w-4/5 bg-white shadow-md ">
                <!-- <EventDetailComp :event="event" :editingMode="editing.mode"
                    @emitCancel="editing.cancel" @emitSave="editing.save" /> -->
                <div v-if="true" class="flex flex-col h-full w-3/4 px-6 border-r-2 overflow-auto">
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
                                @change="editing.checkTimeError()"
                                :class="[editing.isTimeError ?'form-input-error':'form-input', ' w-fit py-0.5 text-base']"
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
                                @change="editing.checkTimeError()"
                                :class="[editing.isTimeError ?'form-input-error':'form-input', 'w-fit py-0.5 text-base']"
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
                    <div class="flex h-full w-full bg-white mt-6 mb-2">
                        <textarea v-if="editing.mode"
                            name="eventNotes"  placeholder="Note" maxlength="500"
                            class="w-full border p-1 text-md font-medium bg-gray-200"
                            @change="" v-model="editing.event.note"
                        ></textarea>
                        <div v-else
                            class="">
                            <span class="text-md">{{ event.eventNotes }}</span>
                            <span v-show="event.eventNotes == null || event.eventNotes.length == 0" 
                                class="text-md font-medium text-gray-500">No Description</span>
                        </div>
                        
                    </div>
                    <div v-show="editing.checkTimeError && editing.mode" class=" text-sm font-medium text-red-500">
                            Time not be Past
                        </div>
                    <!-- Show when editing mode on -->
                    <div v-if="editing.mode"
                        class="flex mb-6 gap-4">
                        <button type="button" 
                                @click="editing.save"
                                :disabled="editing.isTimeError"
                                class="text-sm font-medium tracking-wide px-3 py-2 rounded-full bg-blue-500 text-gray-100
                                    flex items-center gap-1 disabled:bg-gray-300 disabled:text-slate-500">
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
                            <IconHourglass class="w-6 h-6" />
                            <span class="text-md font-medium">{{ event.eventDuration }} minute</span>
                        </div>
                    </div>
                    <!-- **** -->

                    <EventTimeleft :eventStartTime="event.eventStartTime" />
                </div>
            </div>
        </div>
        <BaseModalFailed :visible="editing.modalFailed.visible" :title="editing.modalFailed.titil" :toSchedules="false"
            :desc="editing.modalFailed.desc"
            @closeModal="() => editing.modalFailed.visible = false" />
    </main>
</template>
 
<style>

</style>
