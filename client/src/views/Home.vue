<script setup>
import { ref } from 'vue';
import CarbonUserAvatarFilledVue from '../components/icons/CarbonUserAvatarFilled.vue';
import CarbonUserAvatarFilled from '../components/icons/CarbonUserAvatarFilled.vue';
import { useRoute, useRouter } from 'vue-router'

const teamMem = [
    {id:'63130500083', name:'Purachet Zheng'},
    {id:'63130500094', name:'Phumin Chumphu'},
    {id:'63130500097', name:'Yuttasart Prajaksuwan'},
]
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
            <div class="flex justify-center items-center h-full w-full dark:text-gray-200 text-lg">
                <div>
                    <span class="font-bold text-2xl">
                        Team Member
                    </span>
                    <div class="flex flex-col gap-2 font-thin text-xl mt-3">
                        <div class="flex items-center gap-2 zoom-125-300 duration-300" v-for="mem in teamMem" :key="mem.id">
                            <CarbonUserAvatarFilled class="h-10 w-10" />
                            <div class="flex flex-col">
                                <span>
                                    {{mem.name}}
                                </span>
                                <span class="text-[1rem]">
                                    {{mem.id}}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style>
</style>