<script setup>
import dayjs from 'dayjs';
import { ref, reactive, onBeforeMount, computed } from 'vue';
// import { events, eventCategories, middleFetch } from '../js/variable'
import { eventCategories } from '../js/eventCategory'
import { events } from '../js/event'
import { useRoute, useRouter } from 'vue-router'
import IconCheckCircleFill from '../components/icons/IconCheckCircleFill.vue'
import ModalCreate from '../components/modal/ModalCreate.vue';
import BaseModalSuccess from '../components/modal/BaseModalSuccess.vue';
import SelectCategory from '../components/ReserveView/SelectCategory.vue';
import DisplayStateBar from '../components/ReserveView/DisplayStateBar.vue';
import FillForm1 from '../components/ReserveView/FillForm1.vue';
import ResultReserve from '../components/ReserveView/ResultReserve.vue';
import BaseModalFailed from '../components/modal/BaseModalFailed.vue';

const step = ref(0)
const router = useRouter()
const routerSigns = {
    goBack: () => router.go(-1),
    refresh: () => router.go(0),
    schedules: () => router.push({ name: 'Schedules' })
}

const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
const goBack = () => router.go(-1)
const categories = ref([])
onBeforeMount(async () => {
    // await middleFetch.getEventCategories()
    await eventCategories.get()
    categories.value = eventCategories.categories.value
})
const createEvent = ref({
    bookingName: '',
    bookingEmail: '',
    eventNotes: '',
    eventCategoryId: null,
    eventDuration: null
})
let startDate = ref(null)
let startTime = ref(null)

const modal = ref({ visible: false })
const closeModal = () => modal.value.visible = false


// const combineDT = (date, time) => dayjs(date + time).toJSON()
const creating = reactive({
    modalFailed: {
        visible: false,
        titil: 'Create Failed',
        desc: 'Event has been successfully created.'
    },
    choosedCategory: 0,
    event: { 
        // name: null, 
        // email: null, 
        // categoryId: null, 
        // datetime: null, 
        // note: null
    },
    stage: 1,
    stageOne(id){
        creating.choosedCategory = id
        creating.event.eventCategoryId = id
        creating.stage++
    },
    async stageTwo(inform){
        creating.event = {...creating.event, ...inform}
        console.log(creating.event);
        // submit()
        const postRes = await events.post(creating.event)
        if(postRes.posted) return creating.stage++
        console.log(postRes.res.details);
        let message = ''
        await postRes.res.details.forEach(d => message += `${d.field}: ${d.errorMessage}; `)

        creating.modalFailed.desc = message
        creating.modalFailed.visible = true
        
    },
})

</script>
 
<template>
    <main :class="['h-full w-screen overflow-auto', 'p-6', 
            'flex flex-col justify-between items-center']">
        <!-- main -->
        <div class="h-full w-full">
            <!-- #1 Select Category -->
            <SelectCategory v-show="creating.stage === 1" :class="['h-full w-full flex flex-col justify-between']"
                :categories="categories"
                @emitChooseCategory="creating.stageOne"
            />
            <!-- #2 Fill Form -->
            <FillForm1 v-show="creating.stage === 2" :class="['h-full w-full']" 
                :selectedCategory="parseInt(creating.choosedCategory)"
                @emitBack="() => creating.stage--" @emitSubmitForm="creating.stageTwo"
            />
            <!-- #3 Success -->
            <ResultReserve v-show="creating.stage === 3"
                :class="['h-full w-full']"
                @emitReset="routerSigns.refresh" />
        </div>
        
        <!-- state display bar -->
        <DisplayStateBar :class="['mt-6 w-full']" :stage="creating.stage" />
        <BaseModalFailed :visible="creating.modalFailed.visible" :title="creating.modalFailed.titil" :toSchedules="true"
            :desc="creating.modalFailed.desc"
            @closeModal="() => creating.modalFailed.visible = false" />
    </main>

</template>
 
<style>
</style>
