<script setup>
import { ref, computed, onBeforeMount, onMounted, reactive, toRef, } from 'vue'
import IconArrowLeftNoLine from '../../icons/IconArrowLeftNoLine.vue';
import IconEditFill from '../../icons/Fill/IconEditFill.vue';
import IconArrowLeft from '../../icons/IconArrowLeft.vue';
import IconClose from '../../icons/IconClose.vue';
defineEmits(['emitEdit', 'emitCancel', 'emitBack'])
const props = defineProps({
    bookingName: {
        type: String,
        require: true,
    },
})
</script>
 
<template>
    <div class=" flex flex-col overflow-hidden gap-2 border-b-2 pb-2">
        <!-- header -->
        <div class="flex justify-between">
            <div class="flex">
                <button class="border-2 border-transparent hover:border-blue-600 text-gray-800 hover:text-white px-3 py-1.5 rounded text-1xl font-medium hover:bg-blue-600 transition duration-300"
                    @click="$emit('emitBack')">
                    <div class="flex gap-1">
                        <IconArrowLeft class="w-6 h-6 -ml-1" />
                        <span>Back</span>
                    </div>
                </button>
            </div>
            <div class="flex gap-4">
                <button class="bg-blue-600 text-white px-3 py-1.5 rounded text-base font-medium hover:bg-blue-800 transition duration-300 "
                    @click="$emit('emitEdit')">
                    <div class="flex items-center gap-1">
                        <IconEditFill class="w-5 h-5" />
                        <span>Edit</span>
                    </div>
                </button>
                <button class="border-2 border-red-500 text-red-500 hover:text-white px-3 py-1.5 rounded text-base font-medium hover:bg-red-500 transition duration-300"
                    @click="$emit('emitCancel')">
                    <div class="flex items-center gap-1">
                        <IconClose class="w-6 h-6" />
                        <span>Cancel</span>
                    </div>
                </button>
            </div>
        </div>

        <div class="flex h-18 items-center">
            <p class="text-3xl truncate">{{ bookingName }}</p>
        </div>
    </div>
</template>
 
<style>
</style>