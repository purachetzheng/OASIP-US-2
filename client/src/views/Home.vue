<script setup>
import { ref } from 'vue';
import CarbonUserAvatarFilledVue from '../components/icons/CarbonUserAvatarFilled.vue';
import CarbonUserAvatarFilled from '../components/icons/CarbonUserAvatarFilled.vue';
const teamMem = [
    {id:'63130500083', name:'Purachet Zheng'},
    {id:'63130500094', name:'Phumin Chumphu'},
    {id:'63130500097', name:'Yuttasart Prajaksuwan'},
]
const mouseNow = ref(null)
</script>
 
<template>
    <div class="flex h-full justify-center items-center ">
        <div class="flex h-full w-full cursor-default">
            <div class="flex h-full w-full justify-center items-center">
                <div class="flex flex-col text-3xl hover:scale-125 duration-300" 
                    @mouseenter="mouseNow = 'logo'" @mouseleave="mouseNow = null">
                    <h2 class="text-4xl font-semibold text-gray-700 dark:text-gray-200">OASIP-US2</h2>
                    <div class="font-thin dark:text-gray-200">
                    Reservation Web Application
                    </div>
                </div>
            </div>
            <div class="flex justify-center items-center h-full w-full dark:text-gray-200 text-lg">
                <div>
                    <span class="font-bold text-2xl">
                        Team Member
                    </span>
                    <div class="flex flex-col gap-2 font-thin text-xl mt-3">
                        <div class="flex items-center gap-2 hover:scale-125 duration-300" v-for="mem in teamMem" :key="mem.id">
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