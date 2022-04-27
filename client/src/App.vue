<script setup>
import {ref, onBeforeMount} from 'vue'
import {zFetch} from './zFetch'
import dayjs from 'dayjs'

const events = ref([1])
// onBeforeMount(async () => {
//     events.value = await zFetch.get('/api/events')
//     console.log(events.value);
// })
let colorSet = '2'
</script>

<template>
    <!-- <div>{{events}} </div> -->
    <!-- dark:bg-gray-900 -->
    <div class="flex h-screen bg-gray-50">
<div class="flex flex-col flex-1 w-full">
        <!-- Title -->
        <header class="bg-teal-300 px-6 py-2">This is Nav</header>
        <main class="h-full overflow-y-auto px-6 pb-4">
            <h2 class="my-4 text-2xl font-semibold text-gray-700">OPSIP</h2>
            <div class="w-full overflow-hidden rounded-lg shadow-lg">
                <div class="w-full overflow-x-auto ">
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
                            <tr class="text-gray-700 " v-for="el in 30" v-show="events.length > 0">
                                <td class="px-4 py-3 text-sm">{{el}}</td>
                                <td class="px-4 py-3 text-sm">{{el}}</td>
                                <td class="px-4 py-3 text-sm">{{el}}</td>
                                <td class="px-4 py-3 text-sm">{{el}}</td>
                                <td class="px-4 py-3 text-sm">{{el}}</td>
                            </tr>
                            <tr v-show="events.length === 0">
                                <td colspan="5" class="px-4 py-3 text-center">No Scheduled Events</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
    </div>
    
</template>

<style>
</style>