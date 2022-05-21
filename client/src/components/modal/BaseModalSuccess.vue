<script setup>
import IconCheck from '../icons/IconCheck.vue';
import { useRoute, useRouter } from 'vue-router'
const router = useRouter()
defineEmits(['closeModal', 'go'])
const props = defineProps({
    visible: {
        type: Boolean,
        default: false
    },
    title:{
        type: String,
        default: 'complete'
    },
    desc:{
        type: String,
        default: 'Congratulation!'
    },
    toSchedules:{
        type: Boolean,
        default: false
    }
})
const test = ['btn-modal text-sm w-24 shadow-sm', 
                                    `bg-gradient-to-l from-green-400  to-green-500 
                                    hover:bg-gradient-to-l hover:from-green-500  hover:to-green-500
                                    text-green-100 hover:bg-green-600 focus:ring-green-300`]
</script>
 
<template>
    <!-- modal background -->
    <div v-show="visible" class="absolute top-0 left-0 w-screen h-screen flex justify-center items-center ">
        <div
            @click.self="$emit('closeModal')"
            class="absolute w-screen h-screen bg-gray-500 transition ease-in-out opacity-70"></div>
        <!-- modal body -->
        <div :class="['w-96 h-96 relative flex justify-center overflow-hidden', 
                    'shadow-lg border-0 rounded-md',
                    'mx-auto', 
                    'bg-white']">
            <!-- <div :class="['w-52 h-52 -top-20 -left-20 absolute', 'rounded-full', 'bg-green-400']">
                <div class="mt-28 ml-28 flex items-center justify-center h-12 w-12 rounded-full bg-green-100">
                    <IconCheck class="h-6 w-6 text-green-600"/>
                </div>
            </div> -->
            <div :class="['w-160 h-80 -top-36 absolute flex justify-center', 'round', 
                        'bg-gradient-to-tl from-green-300 via-green-500 to-green-600']">
                <div class="mt-48 flex items-center justify-center h-20 w-20 rounded-full bg-green-200">
                    <IconCheck class="h-12 w-12 text-green-500"/>
                </div>
            </div>
            <div :class="['flex flex-col items-center', 'mt-48 px-14']">
                <span :class="['text-2xl font-medium ', 'text-gray-900']">
                    {{ title }}
                </span>
                <div :class="['h-14 overflow-hidden flex items-center', 'my-4',
                            'text-sm font-medium', 'text-gray-500']">
                    <span>{{ desc }}</span>
                </div>
                <div class="flex items-center px-4 py-3 gap-4">
                    <router-link :to="{name: 'Schedules'}" v-show="toSchedules">
                        <button
                            :class="['btn-modal text-sm whitespace-nowrap', 
                            'hover:bg-gradient-to-l from-green-400  to-green-500', 
                            'text-green-500 hover:text-gray-100 hover:bg-blue-600 focus:ring-blue-300']">
                            SCHEDULES
                        </button>
                    </router-link>
                    <button :class="['btn-modal text-sm shadow-sm', 
                                    `bg-gradient-to-l from-green-400  to-green-500 
                                    hover:bg-gradient-to-l hover:from-green-500  hover:to-green-500
                                    text-green-100 hover:bg-green-600 focus:ring-green-300`]"
                        @click="$emit('closeModal')">
                        CONTINUE
                    </button>
                </div>
            </div>
        </div>
    </div>

</template>
 
<style>
.round {
  border-radius: 50%;
}
.round-bottom {
  border-bottom-left-radius: 40%;
  border-bottom-right-radius: 40%;
}
</style>