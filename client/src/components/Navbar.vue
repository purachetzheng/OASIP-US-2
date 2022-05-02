<script setup>
import { useRouter } from 'vue-router';

//Icon
import CarbonEventSchedule from './icons/CarbonEventSchedule.vue'
import CarbonHome from './icons/CarbonHome.vue';
const router = useRouter()
const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
</script>
 
<template>
    <header class="py-4 bg-white shadow-md dark:bg-neutral-800">
        <div
            class="container flex items-center justify-between h-full px-6 mx-auto text-purple-600 dark:text-purple-300">
            <!-- Logo -->
            <button @click="goTo('Home')" class="text-lg font-bold text-gray-700 dark:text-gray-200">
                OASIP
            </button>
            <!-- Navi -->
            <div class="w-full w-auto ">
                <div class="flex space-x-6 text-sm font-medium">
                    <button @click="goTo('Home')"
                        class="inline-flex font-semibold hover:text-gray-900 dark:hover:text-gray-300 dark:text-gray-100">
                        <!-- <div class="rounded-full p-2 bg-gradient-to-r from-green-400 to-blue-700"></div> -->
                        <CarbonHome class="w-5 h-5" />
                        <span class="ml-2">Home</span>
                    </button>
                    <button @click="goTo('Schedules')"
                        class="inline-flex font-semibold hover:text-gray-900 dark:hover:text-gray-300 dark:text-gray-100">
                        <!-- <div class="rounded-full p-2 bg-gradient-to-r from-green-400 to-blue-700"></div> -->
                        <CarbonEventSchedule class="w-5 h-5" />
                        <span class="ml-2">Schedules</span>
                    </button>
                </div>
            </div>
        </div>
    </header>
</template>
 
<style>
</style>