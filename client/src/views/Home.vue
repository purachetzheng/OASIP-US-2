<script setup>
import CarbonUserAvatarFilledVue from '../components/icons/CarbonUserAvatarFilled.vue';
import CarbonUserAvatarFilled from '../components/icons/CarbonUserAvatarFilled.vue';
</script>
 
<template>
    <div class="flex h-full justify-center items-center">
        <div class="flex h-full w-full">
            <div class="flex flex-col h-full w-full justify-center items-center text-3xl">
                <h2 class=" text-4xl font-semibold text-gray-700 dark:text-gray-200">OASIP-US2</h2>
                <div class="font-thin dark:text-gray-200">
                    Reservation Web Application
                </div>
            </div>
            <div class="flex justify-center items-center h-full w-full dark:text-gray-200 text-lg">
                <div>
                    <span class="font-bold text-2xl">
                        Team Member
                    </span>
                    <div class="flex flex-col gap-2 font-thin text-xl mt-3">
                        <div class="flex items-center gap-2">
                            <CarbonUserAvatarFilled class="h-10 w-10" />
                            <div class="flex flex-col">
                                <span>
                                    Purachet Zheng
                                </span>
                                <span class="text-[1rem]">
                                    63130500083
                                </span>
                            </div>
                        </div>
                        <div class="flex items-center gap-2">
                            <CarbonUserAvatarFilled class="h-10 w-10" />
                            <div class="flex flex-col">
                                <span>
                                    Phumin Chumphu
                                </span>
                                <span class="text-[1rem]">
                                    63130500094
                                </span>
                            </div>
                        </div>
                        <div class="flex items-center gap-2">
                            <CarbonUserAvatarFilled class="h-10 w-10" />
                            <div class="flex flex-col">
                                <span>
                                    Yuttachart Prajaksuwan
                                </span>
                                <span class="text-[1rem]">
                                    63130500096
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