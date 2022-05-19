<script setup>
import ModalCreate from '../components/modal/ModalCreate.vue';
import { useRouter } from 'vue-router';
const myRouter = useRouter()
const closeModal = () => myRouter.push({name: 'Home'})
</script>
 
<template>
    <ModalCreate :visible="true" />
    <!-- <main class="h-full w-full overflow-y-auto screen-p-1">
        <h2 class="mb-4 text-h-1">Event Details</h2>
        <button class="bg-cyan-500 hover:bg-blue-700 text-gray-100 py-1 px-3 rounded" @click="goBack">
            Back
        </button>
        <div class="flex justify-center">
            <div class="p-8 overflow-hidden rounded-xl shadow-lg dark:bg-zinc-700 bg-zinc-300 h-8/12 flex"> -->
                <!-- <div class="w-2/6 flex flex-col dark:text-gray-100 font-bold space-y-4">
                    <span>Booking Name</span>
                    <span>Booking Email</span>
                    <span>Category Name</span>
                    <span>Start Date</span>
                    <span>Start Time</span>
                    <span>Duration</span>
                    <span>Note</span>
                </div>
                <div class="flex flex-col space-y-4 dark:text-gray-100">
                    <span>{{event.bookingName}}</span>
                    <span>{{event.bookingEmail}}</span>
                    <span>{{ event.eventCategoryId }}</span>
                    <span>{{ getDate(event.eventStartTime) }}</span>
                    <span>{{ getTime(event.eventStartTime) }}</span>
                    <span>{{ event.eventDuration }}</span>
                    <span>{{ event.eventNotes }}</span>
                </div> -->
                <!-- min-w-0 p-4 bg-white rounded-lg shadow-xs dark:bg-gray-800 -->
                <!-- <div class="flex flex-col space-y-2 dark:text-gray-200 min-w-0 p-4 ">
                    <div><span class="font-bold">Booking Name:</span> {{ event.bookingName }}</div>
                    <div><span class="font-bold">Booking Email:</span> {{ event.bookingEmail }}</div>
                    <div><span class="font-bold">Category Name:</span> {{ event.eventCategoryId }}</div>
                    <div><span class="font-bold">Start Date:</span> {{ getDate(event.eventStartTime) }}</div>
                    <div><span class="font-bold">Start Time:</span> {{ getTime(event.eventStartTime) }}</div>
                    <div><span class="font-bold">Duration:</span> {{ event.eventDuration }} minutes</div>
                    <div><span class="font-bold">Note:</span> {{ event.eventNotes }}</div>
                </div>
            </div>
        </div> -->

</template>
 
<style>

</style>