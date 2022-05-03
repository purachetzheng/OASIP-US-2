<script setup>
import { ref, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router'

//Icon
import CarbonEventSchedule from './icons/CarbonEventSchedule.vue'
import CarbonHome from './icons/CarbonHome.vue';
const router = useRouter()

const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })

</script>
 
<template>
    <header class="py-4 px-8 bg-white shadow-md dark:bg-neutral-800">
        <div
            class="flex items-center justify-between h-full text-purple-600 dark:text-purple-300 w-full">
            <!-- Logo -->
            <button @click="goTo('Home')" class="text-lg font-bold text-gray-700 dark:text-gray-200">
                OASIP
            </button>
            <!-- Navi -->
            <div class="">
                <div class="flex space-x-6 text-sm font-medium">
                    <button @click="goTo('Home')"
                        :class="[$route.name==='Home'?'dark:text-cyan-500 hover:text-cyan-900 dark:hover:text-cyan-700':'dark:text-gray-100 hover:text-gray-900 dark:hover:text-gray-300','inline-flex font-semibold']">
                        <!-- <div class="rounded-full p-2 bg-gradient-to-r from-green-400 to-blue-700"></div> -->
                        <CarbonHome class="w-5 h-5 mr-2" />
                        <span>Home</span>
                    </button>
                    <button @click="goTo('Schedules')"
                        :class="[$route.name==='Schedules'?'dark:text-cyan-500 hover:text-cyan-900 dark:hover:text-cyan-700':'dark:text-gray-100 hover:text-gray-900 dark:hover:text-gray-300','inline-flex font-semibold']">
                        <!-- <div class="rounded-full p-2 bg-gradient-to-r from-green-400 to-blue-700"></div> -->
                        <CarbonEventSchedule class="w-5 h-5 mr-2" />
                        <span :class="[]">Schedules</span>
                    </button>
                </div>
            </div>
        </div>
    </header>
</template>
 
<style>
</style>