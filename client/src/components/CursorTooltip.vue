<script setup>
import { useMouse } from '../js/mouse'
const mousePos = useMouse()
defineEmits([])
const prop = defineProps({
        mouseOn:{
        type:String,
        default: null
   },
})

</script>
 
<template>
    <div class="absolute truncate m-4 py-0.5 px-2 text-sm font-medium bg-gray-600 text-gray-300 rounded-xl pointer-events-none"
            v-if="mouseOn !== null" :style="[`left: ${mousePos.x}px`, `top: ${mousePos.y}px`]">
            {{mouseOn}}
        </div>
</template>
 
<style>

</style>