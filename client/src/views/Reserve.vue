<script setup>
import dayjs from 'dayjs';
import { ref, onBeforeMount } from 'vue';
import { events, eventCategories, middleFetch } from '../js/variable'
import { useRoute, useRouter } from 'vue-router'
import IconCheckCircleFill from '../components/icons/IconCheckCircleFill.vue'
import ModalCreate from '../components/modal/ModalCreate.vue';

const step = ref(0)
const router = useRouter()
onBeforeMount(async () => {
    await middleFetch.getEventCategories()
})
const createEvent = ref({
    bookingName: null,
    bookingEmail: null,
    eventNotes: null,
    eventCategoryId: null,
    eventDuration: null
})
let startDate = ref(null)
let startTime = ref(null)

const modal = ref({visible: false})
const closeModal = () => modal.value.visible = false 

const send = ref(false)
const submit = async () => {
    createEvent.value.eventStartTime = dayjs(startDate.value + startTime.value).toJSON()
    createEvent.value.eventDuration = selectedCatagory.value.eventDuration
    middleFetch.event.post(createEvent.value)
    // modal.value.visible = true
    send.value = true 
}


const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
const goBack = () => router.go(-1)

const selectedCatagory = ref({
    eventCategoryDescription: null,
    eventDuration: 0
})
const selectCatagory = (e) => selectedCatagory.value = eventCategories.value.find(eC => eC.id == e.target.value)

const alertSuccess = ref(null)

</script>
 
<template>
    <main class="h-screen w-screen screen-p-1 flex justify-center overflow-y-scroll items-center">
        <form action="#" class="flex flex-col gap-2 h-full w-1/2 overflow-y-scroll p-8 bg-white rounded-xl shadow-lg"
            @submit="submit">
            <span class="text-h-1 text-black mb-2">Reserve</span>
            <div class="flex gap-4 sm:flex-col lg:flex-row">
                <div class="w-full flex flex-col gap-0.5 ">
                    <div class="text-xs">Select Catagory</div>
                    <select class="form-1 font-medium" v-model="createEvent.eventCategoryId" required
                        @change="selectCatagory" name="eventCategoryId">
                        <option v-for="eventC in eventCategories" :value="eventC.id">{{ eventC.eventCategoryName }}
                        </option>
                    </select>
                </div>
                <div class="flex flex-col gap-0.5 ">
                    <div class="text-xs whitespace-nowrap">Dutation (minutes)</div>
                    <!-- <input type="number" name="eventDuration" disabled class="form-1 w-12 font-medium text-center" 
                        v-model="selectedCatagory.eventDuration"> -->
                    <div class="form-1-off font-medium text-center whitespace-nowrap cursor-default">{{
                            selectedCatagory.eventDuration
                    }}</div>
                </div>
            </div>
            <div class="flex sm:gap-0.5 sm:flex-col lg:flex-row lg:gap-4">
                <div class="lg:w-1/2 sm:w-full flex flex-col gap-0.5">
                    <div class="text-xs">Select Date</div>
                    <input type="date" required class="form-1 font-medium" v-model="startDate">
                </div>
                <div class="lg:w-1/2 sm:w-full flex flex-col gap-0.5">
                    <div class="text-xs">Select Time</div>
                    <input type="time" required class="form-1 font-medium" v-model="startTime">
                </div>
            </div>
            <div class="flex flex-col gap-0.5">
                <div class="text-xs">Name</div>
                <input type="text" required class="form-1 font-medium" placeholder="Name" name="bookingName"
                    maxlength="100" v-model="createEvent.bookingName">
            </div>
            <div class="flex flex-col gap-0.5">
                <div class="text-xs">Email</div>
                <input type="email" required class="form-1 font-medium" placeholder="Email" name="bookingEmail"
                    v-model="createEvent.bookingEmail">
            </div>
            <div class="flex gap-5">
                <div class="w-full flex flex-col gap-1">
                    <div class="text-xs">Note:</div>
                    <textarea class=" form-1 font-medium" rows="5" placeholder="Note" name="eventNotes"
                        v-model="createEvent.eventNotes"></textarea>
                </div>
            </div>
            <!-- <p class="text-xs text-red-500 text-right my-1">Required fields </p> -->
            <div v-show="send" ref="alertSuccess"
                class="flex items-center bg-green-100 rounded-lg text-green-700 text-sm font-medium px-4 py-3"
                role="alert">
                <IconCheckCircleFill class="w-4 h-4 mr-2" />
                <p>The event has been created successfully.</p>
            </div>
            <div class="flex gap-6 justify-end mt-4">
                <button @click="goBack" class="btn-1 bg-white hover:bg-gray-100 text-gray-600">Back</button>
                <button type="submit" class="btn-1 bg-blue-600 hover:bg-blue-700 text-white"
                    value="Submit">Submit</button>
            </div>
        </form>
        <ModalCreate :visible="modal.visible" @closeModal="closeModal" />
    </main>

</template>
 
<style>
</style>
