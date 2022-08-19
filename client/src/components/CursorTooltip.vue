<script setup>
import { useMouse } from '../js/mouse'
const mousePos = useMouse()
defineEmits([])
const prop = defineProps({
    mouseOn:{
        type:String,
        default: null
    },
    minusY:{
        type: Number,
        default: 0
    },
    minusX:{
        type: Number,
        default: 0
    },
})

</script>
 
<template>
    <div class="absolute m-4 py-0.5 px-2 text-sm font-medium bg-gray-600 text-gray-300 rounded-xl pointer-events-none"
        v-if="mouseOn !== null" :style="[`left: ${mousePos.x-minusX}px`, `top: ${mousePos.y-minusY}px`]">
        {{mouseOn}}
    </div>
</template>
 
<style>

</style>