<script setup>
import { ref, onBeforeMount } from 'vue';
import { events, eventCategories } from '../js/variable'
import { zFetch } from '../js/zLib'
const step = ref(0)
onBeforeMount(async () => {
    events.value.length === 0 ? events.value = await zFetch.get('http://ip21us2.sit.kmutt.ac.th:8080/api/events') : ''
    eventCategories.value.length === 0 ? eventCategories.value = await zFetch.get('http://ip21us2.sit.kmutt.ac.th:8080/api/eventcategories') : ''
})
</script>
 
<template>
    <!-- <div class="text-h-1 mb-8 text-center">Reserve</div>
    <div class="text-white w-full h-full flex flex-col items-center gap-4">

        <div class="flex flex-col gap-3 w-1/2">
            <span>Select Catagory</span>

            <select class="bg-white border border-gray-300 text-gray-900 
            text-sm rounded-lg  w-full p-2">
                <option>Project Management Clinic</option>
                <option>DevOps/Infra Clinic</option>
                <option>Database Clinic</option>
                <option>Client-side Clinic</option>
                <option>Server-side Clinic</option>
            </select>
        </div>
        <div class="flex flex-col gap-3 w-1/2">
            <span>Select Date</span>

            <input type="date"
                class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                placeholder=" " required="">
        </div>

    </div> -->
    <main class="h-screen w-screen screen-p-1 flex justify-center overflow-y-scroll items-center">
        <div class="h-full p-8 bg-slate-50 w-1/2 rounded-xl flex flex-col gap-2 overflow-y-scroll">
            <span class="text-h-1 text-black mb-2">Reserve</span>
            <div class="flex gap-4">
                <div class="w-full flex flex-col gap-0.5">
                    <div class="text-xs">Select Catagory</div>
                    <select class="form-1">
                        <option v-for="eventC in eventCategories">{{ eventC.id }}</option>
                    </select>
                </div>
                <div class="flex flex-col gap-0.5">
                    <div class="text-xs">Dutation</div>
                    <div class="form-1 text-center">150</div>
                </div>
            </div>
            <div class="flex sm:gap-0.5 sm:flex-col lg:flex-row lg:gap-4">
                <div class="lg:w-1/2 sm:w-full flex flex-col gap-0.5">
                    <div class="text-xs">Select Date</div>
                    <input type="date" class="form-1 font-medium">
                </div>
                <div class="lg:w-1/2 sm:w-full flex flex-col gap-0.5">
                    <div class="text-xs">Select Time</div>
                    <input type="time" class="form-1 font-medium">
                </div>
            </div>
            <div class=" flex flex-col gap-0.5">
                <div class="text-xs">Name</div>
                <input type="text" class="form-1 font-medium" placeholder="Name" name="test">
            </div>
            <div class="flex flex-col gap-0.5">
                <div class="text-xs">Email</div>
                <input type="text" class="form-1 font-medium" placeholder="Email">
            </div>
            <div class="flex gap-5">
                <div class="w-full flex flex-col gap-1">
                    <div class="text-xs">Note:</div>
                    <textarea class=" form-1 font-medium" rows="5" placeholder="Note"></textarea>
                </div>
            </div>
            <p class="text-xs text-red-500 text-right my-1">Required fields </p>
            <div class="flex gap-6 justify-end">
                <button class="mb-2 md:mb-0 bg-white px-4 py-2 text-sm shadow-sm font-medium tracking-wide border text-gray-600 rounded-3xl hover:shadow-md hover:bg-gray-100">Cancel</button>
                <button class="mb-2 md:mb-0 bg-green-400 px-4 py-2 text-sm shadow-sm font-medium tracking-wider text-white rounded-3xl hover:shadow-md hover:bg-green-500">Submit</button>
            </div>
        </div>
    </main>

</template>
 
<style>
</style>