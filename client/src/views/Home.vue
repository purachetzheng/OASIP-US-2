<script setup>
import { ref } from 'vue';
import CarbonUserAvatarFilledVue from '../components/icons/Fill/CarbonUserAvatarFilled.vue';
import CarbonUserAvatarFilled from '../components/icons/Fill/CarbonUserAvatarFilled.vue';
import { useRoute, useRouter } from 'vue-router'

const mouseNow = ref(null)

const router = useRouter()
const goTo = (pageName, param = null) => router.push({ name: pageName, params: param ? param : '' })
</script>
 
<template>
    <div class="flex h-full">
        <div class="flex h-full w-full cursor-default">
            <div class="flex h-full w-full">
                <div class="flex justify-center items-center w-full">
                    <img src="/img/home.png" class="h-4/5" alt="">
                </div>
                <div class="flex flex-col justify-center items-start text-3xl text-blue-500 w-2/3" @mouseenter="mouseNow = 'logo'"
                    @mouseleave="mouseNow = null">
                    <div class="flex flex-col text-6xl font-semibold dark:text-gray-200">
                        <span>
                        Reservation 
                        </span>                        
                        <span>
                            Web Application
                        </span>
                    </div>
                    <div class="font-thin my-5 text-xl text-gray-500">
                        Web application scheduling and managing your bookings.
                    </div>
                    <div class="flex text-2xl gap-5 mt-4">
                        <button @click="goTo('Schedules')"
                            class="p-3 px-10 rounded-full bg-blue-500 shadow-md hover:bg-gray-900 hover:text-white text-white transition">Schedules</button>
                        <button @click="goTo('Reserve')"
                            class="p-3 px-10 rounded-full bg-blue-500 shadow-md hover:bg-gray-900 hover:text-white text-white transition">Reserve</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style>
</style>