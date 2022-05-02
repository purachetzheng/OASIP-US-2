<script setup>
import CarbonEventSchedule from './icons/CarbonEventSchedule.vue'
</script>
 
<template>
    <div class="py-4 text-gray-500 dark:text-gray-400">
        <router-link :to="{ name: 'Home' }" class="ml-6 text-lg font-bold text-gray-700 dark:text-gray-200">
            OASIP
        </router-link>
        <ul class="mt-6">
            <li class="relative px-6 py-3">
                <span class="absolute inset-y-0 left-0 w-1 bg-cyan-600 rounded-tr-lg rounded-br-lg"
                    aria-hidden="true"></span>

                <router-link :to="{ name: 'Home' }"
                    class="inline-flex items-center w-full text-sm font-semibold text-gray-800 transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200 dark:text-gray-100">
                    <!-- <div class="rounded-full p-2 bg-gradient-to-r from-green-400 to-blue-700"></div> -->
                    <CarbonEventSchedule class="w-5 h-5" />
                    <span class="ml-4">Schedules</span>
                </router-link>
            </li>
        </ul>

        <ul>
            <li class="relative px-6 py-3">
                <router-link :to="{ name: 'Home' }"
                    class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
                    <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round" stroke-linejoin="round"
                        stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                        <path
                            d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01">
                        </path>
                    </svg>
                    <span class="ml-4">Tests</span>
                </router-link>
            </li>
        </ul>
    </div>
</template>
 
<style>
</style>