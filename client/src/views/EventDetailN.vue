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
import EventDetailHeader from '../components/ViewEvent/EventDetail/EventDetailHeader.vue';
import EventDetailMain from '../components/ViewEvent/EventDetail/EventDetailMain.vue';


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
    <main class="h-full w-screen overflow-auto flex flex-col px-28 py-8 gap-6">
       <EventDetailHeader :bookingName="event.bookingName" />
       <EventDetailMain :event="event" />
    </main>
</template>
 
<style>

</style>
