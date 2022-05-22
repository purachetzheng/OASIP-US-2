<script setup>
import CarbonUserAvatarFilled from '../components/icons/Fill/CarbonUserAvatarFilled.vue';

const teamMembers = [
    { student_id: '63130500083', name: 'Purachet Zheng' },
    { student_id: '63130500094', name: 'Phumin Chumphu' },
    { student_id: '63130500097', name: 'Yuttasart Prajaksuwan' },
]
</script>
 
<template>
    <div class="flex h-full justify-center items-center bg-gradient-to-r
    from-blue-500
    via-purple-500
    to-blue-500 background-animate">
        <div class="flex h-full w-full cursor-default text-white">
            <div class="flex flex-col justify-center items-center h-full w-full dark:text-gray-200 text-lg">
                <div>
                    <span class="text-3xl font-bold ">
                        INT221 Information Technology Integrated Project
                    </span>
                </div>
                <div class="mt-4 space-y-3">
                    <span class="font-normal text-2xl w-full flex justify-center">
                        Group Project - Fail Fast, Fail Cheap, Fail Forever
                    </span>
                    <div class="flex gap-5 font-thin text-xl">
                        <div class="flex items-center gap-2 zoom-110-300 duration-300" v-for="teamMember in teamMembers"
                            :key="teamMember.id">
                            <CarbonUserAvatarFilled class="h-10 w-10" />
                            <div class="flex flex-col">
                                <span>
                                    {{ teamMember.name }}
                                </span>
                                <span class="text-[1rem]">
                                    {{ teamMember.student_id }}
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
  .background-animate {
    background-size: 400%;

    -webkit-animation: AnimationName 3s ease infinite;
    -moz-animation: AnimationName 3s ease infinite;
    animation: AnimationName 3s ease infinite;
  }

  @keyframes AnimationName {
    0%,
    100% {
      background-position: 0% 50%;
    }
    50% {
      background-position: 100% 50%;
    }
  }
</style>