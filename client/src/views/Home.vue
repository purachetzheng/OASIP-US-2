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
    <div class="flex h-full justify-center items-center ">
        <div class="flex h-full w-full cursor-default">
            <div class="flex flex-col h-full w-full justify-center items-center gap-6">
                <div class="flex flex-col text-3xl zoom-110-500" 
                    @mouseenter="mouseNow = 'logo'" @mouseleave="mouseNow = null">
                    <h2 class="text-4xl font-semibold text-gray-700 dark:text-gray-200">OASIP-US2</h2>
                    <div class="font-thin dark:text-gray-200">
                    Reservation Web Application
                    </div>
                    
                </div>
                <div class="flex gap-10">
                    <button @click="goTo('Schedules')"
                    class="btn-1 bg-cyan-600 hover:bg-cyan-700 text-white">Schedules</button>
                    <button @click="goTo('Reserve')"
                    class="btn-1 bg-cyan-600 hover:bg-cyan-700 text-white">Reserve</button>
                </div>
                
            </div>
        </div>
    </div>
</template>
 
<style>
</style>