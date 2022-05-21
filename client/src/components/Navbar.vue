<script setup>
import { ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'
import { darkMode } from '../js/darkMode'

//Icon
import CarbonEventSchedule from './icons/CarbonEventSchedule.vue'
import CarbonHome from './icons/CarbonHome.vue';
import CarbonSun from './icons/CarbonSun.vue';
import CarbonMoon from './icons/CarbonMoon.vue';

const router = useRouter()
const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })


const naviList = [
    // {
    //     name: 'Home',
    //     text: 'Home',
    //     icon: CarbonHome
    // },
    {
        name: 'Schedules',
        text: 'Schedules',
        icon: CarbonEventSchedule
    },
    // {
    //     name: 'SchedulesBeta',
    //     text: 'SchedulesBeta',
    //     icon: CarbonEventSchedule
    // },
    {
        name: 'Reserve',
        text: 'Reserve',
        icon: CarbonEventSchedule
    },
    {
        name: 'Category',
        text: 'Category',
        icon: CarbonEventSchedule
    },
    {
        name: 'AboutUs',
        text: 'About Us',
        icon: CarbonEventSchedule
    },
]

</script>
 
<template>
    <header class="py-4 px-20 dark:bg-gray-800">
        <div class="flex items-center justify-between h-full w-full">
            <!-- Logo -->
            <button @click="goTo('Home')" class="text-xl font-bold zoom-125-300 transition  text-gray-800 dark:text-gray-200">
                OASIP
            </button>
            <!-- Navi -->
            <div class="">
                <div class="flex space-x-8 text-sm font-medium">
                    <button v-for="navi in naviList" :key="navi.name" @click="goTo(navi.name)"
                        :class="[$route.name === navi.name 
                        ? 'text-blue-600 dark:text-blue-300' 
                        : 'text-gray-500 hover:text-gray-800 dark:text-gray-200 dark:hover:text-gray-300 zoom-110-300 transition ', 
                        'inline-flex font-semibold']">
                        <!-- <CarbonHome class="w-5 h-5 mr-2" /> -->
                        {{ navi.text }}
                    </button>
                    <!-- <button @click="goTo('Home')"
                        :class="[$route.name === 'Home' ? 'text-cyan-600' : 'text-gray-100 hover:text-gray-900 hover:zoom-110', 'inline-flex font-semibold']">
                        <CarbonHome class="w-5 h-5 mr-2" />
                        <span>Home</span>
                    </button>
                    <button @click="goTo('Schedules')"
                        :class="[$route.name === 'Schedules' ? 'text-cyan-600' : 'text-gray-100 hover:text-gray-900 hover:zoom-110', 'inline-flex font-semibold']">
                        <CarbonEventSchedule class="w-5 h-5 mr-2" />
                        <span>Schedules</span>
                    </button> -->
                </div>
            </div>
            <!-- Menu -->
            <div class="w-1/12 flex justify-end">
                <!-- Dark Mode Toggle -->
                <button class="text-gray-500 hover:text-gray-800 dark:text-gray-200 dark:hover:text-gray-300 zoom-125-300 transition">
                    <CarbonSun class="w-5 h-5" @click="darkMode.on()" v-show="darkMode.mode.value === 'light'" />
                    <CarbonMoon class="w-5 h-5" @click="darkMode.off()" v-show="darkMode.mode.value === 'dark'" />
                </button>
            </div>
        </div>
    </header>
</template>
 
<style>
</style>