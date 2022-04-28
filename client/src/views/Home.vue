<script setup>
import { ref, computed, reactive, nextTick, onBeforeMount, onMounted } from 'vue';
import { zFetch } from './zFetch'
import dayjs from 'dayjs'
//router
import { useRoute, useRouter } from 'vue-router';
const { params } = useRoute();
const router = useRouter();

const events = ref([{
    "id": 1,
    "bookingName": "Somchai Jaidee (OR-7)",
    "bookingEmail": "somchai.jai@mail.kmutt.ac.th",
    "eventCategoryId": "DevOps/Infra Clinic",
    "eventDuration": 30,
    "eventStartTime": "2022-05-23T13:30:00Z",
    "eventNotes": null
},
{
    "id": 2,
    "bookingName": "Somsri Rakdee (SJ-3)",
    "bookingEmail": "somsri.rak@mail.kmutt.ac.th",
    "eventCategoryId": "Project Management Clinic",
    "eventDuration": 30,
    "eventStartTime": "2022-04-27T09:30:00Z",
    "eventNotes": "``"
},
{
    "id": 3,
    "bookingName": "   TT-4",
    "bookingEmail": "Test3@3",
    "eventCategoryId": "Database Clinic",
    "eventDuration": 15,
    "eventStartTime": "2022-01-01T16:30:00Z",
    "eventNotes": null
},{}
,{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}])
onBeforeMount(async () => {
    events.value = await zFetch.get('/api/events')
})
const test = () => {
    console.log('test');
}
let colorSet = '2'
</script>

<template>
    <!-- <div>{{events}}</div> -->
    <!-- dark:bg-gray-900 -->
            <main class="h-full overflow-y-auto px-6 pb-4">
                <h2 class="my-4 text-2xl font-semibold text-gray-700">OPSIP</h2>
                <div class="w-full overflow-hidden rounded-lg shadow-lg">
                    <div class="w-full overflow-x-auto">
                        <table class="w-full whitespace-no-wrap">
                            <thead>
                                <!-- dark:border-gray-700 dark:text-gray-400 dark:bg-gray-800 -->
                                <tr class="text-tr-head border-b bg-teal-300">
                                    <th class="px-4 py-3">Booking Name</th>
                                    <th class="px-4 py-3">Category Name</th>
                                    <th class="px-4 py-3">Date</th>
                                    <th class="px-4 py-3">Start Time</th>
                                    <th class="px-4 py-3">Duration</th>
                                </tr>
                            </thead>
                            <!-- dark:divide-gray-700 dark:bg-gray-800 -->
                            <tbody class="bg-white divide-y">
                                <!-- dark:text-gray-400 -->
                                <tr class="text-gray-700 hover:bg-gray-100 cursor-pointer" v-for="event in events"
                                    v-show="events.length > 0" @click="test">
                                    <td class="px-4 py-3 text-sm">{{ event.bookingName }}</td>
                                    <td class="px-4 py-3 text-sm">{{ event.eventCategoryId }}</td>
                                    <td class="px-4 py-3 text-sm">{{ dayjs(event.eventStartTime).format("D MMM YYYY") }}
                                    </td>
                                    <td class="px-4 py-3 text-sm">
                                        <div class="flex flex-col">
                                            <span>{{ dayjs(event.eventStartTime).format("HH:mm:ss") }}</span>
                                            <!-- <span>{{event.eventStartTime}}</span> -->
                                        </div>
                                    </td>
                                    <td class="px-4 py-3 text-sm">{{ event.eventDuration }}</td>
                                </tr>
                                <tr v-show="events.length === 0">
                                    <td colspan="5" class="px-4 py-3 text-center">No Scheduled Events</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
</template>

<style>
</style>