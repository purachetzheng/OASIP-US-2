<script setup>
import dayjs from 'dayjs';
import { ref, onBeforeMount } from 'vue';
import { events, eventCategories, middleFetch } from '../js/variable'
import { zFetch } from '../js/zLib'
import { useRoute, useRouter } from 'vue-router'
const step = ref(0)
const router = useRouter()
onBeforeMount(async () => {
    await middleFetch.getEventCategoriesNull()
})
const createEvent = ref({
    bookingName: null,
    bookingEmail: null,
    // eventStartDate: null,
    // eventStartTime: null,
    evnetNotes: null,
    eventCategoryId: null,
    eventDuration: 50
})
let startDate = ref(null)
let startTime = ref(null)
const submit = async () => {
    createEvent.value.eventStartTime = dayjs(startDate.value + startTime.value).toJSON()
    const addedEvent = await zFetch.post('http://ip21us2.sit.kmutt.ac.th:8080/api/events', createEvent.value)
    addedEvent ? events.value.push(addedEvent) : ''
}

const goBack = () => router.go(-1)

const selectedCatagory = ref({
    eventCategoryDescription: null,
    eventDuration: 0
})
const selectCatagory = (e) => selectedCatagory.value = eventCategories.value.find(eC => eC.id == e.target.value)
</script>
 
<template>
    <main class="h-screen w-screen screen-p-1 flex justify-center overflow-y-scroll items-center">
        <form 
            class="flex flex-col gap-2 h-full w-1/2 overflow-y-scroll p-8 bg-white rounded-xl shadow-lg"
            @submit="submit">
            <span class="text-h-1 text-black mb-2">Reserve</span>
            <div class="flex gap-4">
                <div class="w-full flex flex-col gap-0.5 ">
                    <div class="text-xs">Select Catagory</div>
                    <select class="form-1 font-medium" v-model="createEvent.eventCategoryId" required @change="selectCatagory" name="eventCategoryId">
                        <option v-for="eventC in eventCategories" :value="eventC.id">{{ eventC.eventCategoryName }}</option>
                    </select>
                </div>
                <div class="flex flex-col gap-0.5">
                    <div class="text-xs">Dutation</div>
                    <div class="form-1 font-medium text-center">{{ selectedCatagory.eventDuration }}</div>
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
                    v-model="createEvent.bookingName">
            </div>
            <div class="flex flex-col gap-0.5">
                <div class="text-xs">Email</div>
                <input type="email" required class="form-1 font-medium" placeholder="Email" name="bookingEmail"
                    v-model="createEvent.bookingEmail">
            </div>
            <div class="flex gap-5">
                <div class="w-full flex flex-col gap-1">
                    <div class="text-xs">Note:</div>
                    <textarea class=" form-1 font-medium" rows="5" placeholder="Note" name="evnetNotes"
                        v-model="createEvent.evnetNotes"></textarea>
                </div>
            </div>
            <!-- <p class="text-xs text-red-500 text-right my-1">Required fields </p> -->
            <div class="flex gap-6 justify-end mt-4">
                <button @click="goBack"
                    class="btn-1 bg-white hover:bg-gray-100 text-gray-600">Cancel</button>
                    <!-- ยังไม่ได้ทำเช็ค -->
                <button type="submit"
                    class="btn-1 bg-blue-600 hover:bg-blue-700 text-white" value="Submit">Submit</button>
            </div>
        </form>
    </main>

</template>
 
<style>
</style>