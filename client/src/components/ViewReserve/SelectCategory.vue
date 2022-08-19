<script setup>
import { ref } from 'vue';
import IconSearch from '../icons/IconSearch.vue'
import IconHourglass from '../icons/IconHourglass.vue'

const emits = defineEmits(['emitChooseCategory', 'emitNext'])
const props = defineProps({
        categories:{
        type: Array,
        require: [],
   },
})
const choosed = ref(null)
</script>
 
<template>
    <div :class="['']">

        <div class="flex flex-col items-center overflow-auto mt-2">
            <div :class="['flex']">
                <h1 :class="['text-4xl', 'text-center', 'font-semibold']">Select Clinic</h1>
            </div>
            <!-- Search -->
            <div v-if="0" class="flex">
                <div :class="['relative mt-6 w-160']">
                    <button @click="" class="absolute inset-y-0 items-center left-3">
                        <IconSearch class="w-5 h-5" />
                    </button>
                    <input ref="searchBox" type="text" 
                        class="p-2 pl-10 border-2 rounded-md text-sm w-full
                                    bg-white outline-none focus:border-blue-600"
                        placeholder="Search">
                </div>
            </div>
            <!-- Category List -->
            <div 
                class="h-full w-full p-2 overflow-auto auto-rows-min gap-x-4 gap-y-4 px-40 my-10
                    grid grid-cols-3">

                <div v-for="category in categories" :key="category.id"
                    @click="choosed = category.id"
                    :class="[choosed == category.id 
                        ? 'text-gray-100 bg-gradient-to-br from-blue-600 via-purple-500 to-purple-300' 
                        : 'bg-white hover:bg-gray-100',
                        'flex flex-col gap-2', 'p-4', 'border shadow-md rounded-lg', 'cursor-pointer', 'transition']">
                    <span class="h-14 overflow-hidden text-lg font-semibold">{{ category.eventCategoryName }}</span>
                    <div class="flex items-center gap-1.5">
                        <IconHourglass class="w-5 h-5"/>
                        <span>{{ category.eventDuration }} minute</span>
                    </div>
                </div>
            </div>
        </div>
        <!-- Next -->
        <div class="flex w-full justify-center">
            <button 
                :class="['btn-primary flex', 
                    'disabled:bg-gray-300 disabled:text-slate-500']"
                @click="$emit('emitChooseCategory', choosed)"
                :disabled="choosed == null"
            >Next Step</button>
        </div>
    </div>
</template>
 
<style>

</style>