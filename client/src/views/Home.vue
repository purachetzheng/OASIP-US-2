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
            <div class="flex flex-col h-full w-full justify-center items-center gap-6 bg-gradient-to-r
    from-blue-500
    to-purple-500">
                <div class="flex flex-col text-3xl text-white" @mouseenter="mouseNow = 'logo'"
                    @mouseleave="mouseNow = null">
                    <div class="font-semibold dark:text-gray-200">
                        Reservation Web Application
                    </div>
                    <h2 class="font-thin dark:text-gray-200">OASIP-US2</h2>
                    <div class="flex text-lg gap-2 mt-2">
                        <button @click="goTo('Schedules')"
                            class="p-1 px-4 rounded bg-white hover:bg-gray-900 hover:text-white text-gray-900 transition">Schedules</button>
                        <button @click="goTo('Reserve')"
                            class="p-1 px-4 rounded bg-white hover:bg-gray-900 hover:text-white text-gray-900 transition">Reserve</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style>
</style>