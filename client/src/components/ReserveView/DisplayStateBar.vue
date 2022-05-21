<script setup>
const emits = defineEmits([])
const props = defineProps({
        stage:{
        type: Number,
        require:true,
   },
})

</script>
 
<template>
    <div class="flex gap-8 justify-center items-center">
        <div v-for="item in 3"
            :class="[stage === item ? 'bg-blue-500' : 'bg-gray-500' , 'w-4 h-4', 'rounded-full', '']">
        </div>
    </div>
</template>
 
<style>

</style>