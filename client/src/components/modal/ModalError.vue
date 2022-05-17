<script setup>
import IconCheck from '../icons/IconCheck.vue';
defineEmits(['closeModal', 'go'])
const props = defineProps({
    visible: {
        type: Boolean,
        default: false
    },
    text:{
        type: String,
        default: "error"
    }
})
</script>
 
<template>
    <!-- modal background -->
    <div v-show="visible" class="absolute top-0 left-0 w-screen h-screen flex justify-center items-center ">
        <div class="absolute  w-screen h-screen bg-gray-500 transition ease-in-out opacity-70"></div>
        <!-- modal body -->
        <!-- https://www.devwares.com/blog/how-to-create-tailwind-css-modal/ -->
        <div class="relative mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
            <div class="mt-3 text-center">
                <div class="mx-auto flex items-center justify-center h-12 w-12 rounded-full bg-red-100">
                    <IconCheck class="h-6 w-6 text-red-600"/>
                </div>
                <h3 class="text-lg leading-6 font-medium text-gray-900 mt-2">Error</h3>
                <div class="mt-2 px-7 py-3">
                    <p class="text-sm font-medium text-gray-500">{{text}}</p>
                </div>
                <div class="flex items-center px-4 py-3 gap-4">
                    <!-- <button class="btn-modal text-sm whitespace-nowrap
                        text-gray-700 hover:text-gray-100 hover:bg-blue-600 focus:ring-blue-300">
                        BACK
                    </button> -->
                    <button class="btn-modal text-sm w-24 shadow-sm
                        bg-blue-500 text-gray-100 hover:bg-blue-600 focus:ring-blue-300"
                        @click="$emit('closeModal')">
                        BACK
                    </button>
                </div>
            </div>
        </div>
    </div>

</template>
 
<style>
</style>