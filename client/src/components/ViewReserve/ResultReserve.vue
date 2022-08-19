<script setup>
import { ref } from 'vue';
import IconSearch from '../icons/IconSearch.vue'
import IconHourglass from '../icons/IconHourglass.vue'
import IconCheck from '../icons/IconCheck.vue';

const emits = defineEmits(['emitChooseCategory', 'emitNext', 'emitReset'])
const props = defineProps({
    categories: {
        type: Array,
        require: [],
    },
})
const choosed = ref(null)
</script>
 
<template>
    <div :class="['flex flex-col items-center justify-between']">
        <div class="flex flex-col overflow-auto">
            <div :class="['flex my-6']">
                <h1 :class="['text-4xl', 'text-center']">Select Clinic</h1>
            </div>
    
            <div :class="['w-160 h-full relative flex justify-center overflow-hidden mt-4',
            ' border-0 rounded-md',
            '',
            '']">
    
                <div :class="['w-192 h-72 -top-32 absolute flex justify-center', 'round',
                'bg-gradient-to-tl from-green-300 via-green-500 to-green-600']">
                </div>
    
                <div class="absolute mt-6 flex items-center justify-center h-28 w-28 rounded-full bg-green-200">
                    <IconCheck class="h-18 w-18 text-green-500" />
                </div>
                <div :class="['flex flex-col items-center', 'mt-56 px-14']">
                    <span :class="['text-3xl font-medium ', 'text-gray-900']">
                        Create Complete
                    </span>
                    <div :class="['overflow-hidden flex items-center', 'my-4',
                    'text-lg font-medium', 'text-gray-500']">
                        <span>Event has been successfully created.</span>
                    </div>
    
                </div>
            </div>
        </div>
        <!-- button Success -->
        <div class="flex items-center px-4 py-3 gap-4">
            <router-link :to="{name: 'Schedules'}" class="btn-green-ghost">
                SCHEDULES
            </router-link>
            <button class="btn-green" @click="$emit('emitReset')">CONTINUE</button>
        </div>

    </div>
</template>
 
<style>
</style>