<script setup>
import { useRouter } from 'vue-router';

//Comp
import Sidebar from './components/Sidebar.vue';
import Navbar from './components/Navbar.vue';
//Icon
import CarbonEventSchedule from './components/icons/CarbonEventSchedule.vue'

const router = useRouter()
const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param:'' })
</script>

<template>
    <div class="flex h-screen bg-gray-50 dark:bg-neutral-900 w-screen fixed">
        <!-- <Sidebar class="hidden w-64 overflow-y-auto bg-white dark:bg-neutral-800 md:block flex-shrink-0" /> -->
        <div class="flex flex-col flex-1 w-full">
            <header class="py-4 bg-white shadow-md dark:bg-neutral-800">
                <div
                    class="container flex items-center justify-between h-full px-6 mx-auto text-purple-600 dark:text-purple-300">

                    <router-link :to="{ name: 'Home' }" class="text-lg font-bold text-gray-700 dark:text-gray-200">
                        OASIP
                    </router-link>
                    <div class="w-full w-auto ">
                        <div class="flex space-x-8 text-sm font-medium">
                            <button @click="goTo('Schedules')"
                                class="inline-flex font-semibold hover:text-gray-900 dark:hover:text-gray-300 dark:text-gray-100">
                                <!-- <div class="rounded-full p-2 bg-gradient-to-r from-green-400 to-blue-700"></div> -->
                                <CarbonEventSchedule class="w-5 h-5" />
                                <span class="ml-2">Schedules</span>
                            </button>
                            
                        </div>
                        <!-- <a href="#/"
                                class="block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">About
                                Us</a>
                        <a href="#/schedule"
                                class="router-link-active router-link-exact-active block py-2 pr-4 pl-3 text-gray-700 border-b border-gray-100 hover:bg-gray-50 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700"
                                aria-current="page">Schedule</a> -->

                    </div>
                    
                </div>

            </header>


            <main class="h-full overflow-y-auto px-6 pb-4 ">
                <router-view></router-view>
            </main>

        </div>

    </div>




</template>

<style>
/* .router-link-active {
    color: white;
} */
</style>
