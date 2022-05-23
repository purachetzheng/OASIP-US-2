<script setup>
import IconHourglass from '../../icons/IconHourglass.vue';
import IconArrowLeft from '../../icons/IconArrowLeft.vue';
import IconCheck from '../../icons/IconCheck.vue';
import CategoryBox from '../../icons/CategoryBox.vue'
defineEmits(['emitRemoveEvent', 'emitViewDetail'])
const props = defineProps({
        categories:{
        type:Array,
        require:true,
   },
})

const color = [
    'bg-radial-at-bl from-yellow-300 via-orange-500 to-rose-500',
    '',
    '',
    '',
    '',]
</script>
 
<template>

    <div :class="['h-full overflow-auto auto-rows-min p-1 mt-4', 
        'grid lg:grid-cols-3 md:grid-cols-2 sm:grid-cols-1', 'gap-x-4 gap-y-4']">
        <div v-for="category in categories" :key="category.id"
            @click="$emit('emitViewDetail', category.id)"
            :class="['h-52 justify-between', 'flex flex-col', 'px-12 py-8 border', 'hover:text-gray-50' , 
                'rounded-lg shadow-md cursor-pointer ', 
                'hover:rose-yellow-1', 'transition'
                ]">
            <div class="flex flex-col gap-2">
                <div class="flex items-center gap-1.5">
                    <CategoryBox />
                    <p class="text-lg font-semibold truncate ">{{ category.eventCategoryName }} </p>
                </div>
                <div class="overflow-auto h-18">
                    <span class="text-sm  overflow-auto" >{{ category.eventCategoryDescription }}</span>
                </div>
            </div>
            <div class="flex  justify-between ">
                <div class="flex  items-center gap-1">
                    <IconHourglass class="w-5 h-5" />
                    <span class="text-sm font-medium">Duration: {{ category.eventDuration }} min</span>
                </div>
                <IconArrowLeft />
            </div>
        </div>
    </div>
</template>
 
<style>

</style>