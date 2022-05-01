<script setup>
import CarbonEventSchedule from './components/icons/CarbonEventSchedule.vue'
</script>

<template>
    <div class="flex h-screen bg-gray-50 dark:bg-neutral-900">
        <aside class=" hidden w-64 overflow-y-auto bg-white dark:bg-neutral-800 md:block flex-shrink-0">
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
                            <CarbonEventSchedule class="w-5 h-5" />
                            <span class="ml-4">Schedules</span>
                        </router-link>
                    </li>
                </ul>

                <ul>
                    <li class="relative px-6 py-3">
                        <router-link :to="{ name: 'Home' }"
                            class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200">
                            <svg class="w-5 h-5" aria-hidden="true" fill="none" stroke-linecap="round"
                                stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                                <path
                                    d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01">
                                </path>
                            </svg>
                            <span class="ml-4">Tests</span>
                        </router-link>
                    </li>
                </ul>
            </div>
        </aside>

        <div class="flex flex-col flex-1 w-full">
            <header class="py-4 bg-white shadow-md dark:bg-neutral-800">
                &nbsp;
            </header>
            <main class="h-full overflow-y-auto px-6 pb-4">
                <router-view></router-view>
            </main>

        </div>

    </div>
    
    <!-- <div class="flex flex-col flex-wrap min-h-screen">
        <nav
            class="min-w-full bg-gray-800 flex flex-col md:flex-row items-center md:justify-between px-6 py-4 border-b border-b-gray-60 shadow-sm max-w-screen-2xl mx-auto">
            <div class="rounded-lg px-3 py-2 text-gray-400 font-medium hover:text-white">
                <router-link :to="{ name: 'Home' }">Home</router-link>
            </div>
        </nav>
        <div class="flex flex-1 flex-col flex-wrap min-w-full">
            <div class="flex flex-1 bg-white">
                <router-view></router-view>
            </div>
        </div>
    </div> -->
   
   

</template>

<style>
/* .router-link-active {
    color: white;
} */
</style>
