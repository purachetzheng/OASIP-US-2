<script setup>
//Icon
import IconGrid from '../icons/IconGrid.vue'
import IconList from '../icons/IconList.vue'
import IconRefresh from '../icons/IconRefresh.vue'
defineEmits(['emitRefreshEvent'])
const props = defineProps({
    eventAmount: {
        type: Number,
        default: 0
    },
})

</script>
 
<template>
    <div class="flex justify-between">
        <div class="flex items-center gap-1">
            <!-- Refresh -->
            <button class="flex items-end" @click="$emit('emitRefreshEvent')">
                <IconRefresh class="w-6 h-6 text-blue-600 hover:text-blue-700" />
            </button>
            <!-- Header Text -->
            <div class="flex items-baseline gap-2">
                <span class="text-lg tracking-wide font-medium">Schedules: </span>

                <span class="font-medium">
                    {{ eventAmount }} events.
                </span>
            </div>
        </div>
        <div class="flex gap-4 pr-4">
            <button class="flex items-center gap-1.5">
                <IconGrid class="w-4 h-4" />
                <p class="text-sm font-medium">Grid</p>
            </button>
            <button class="flex items-center gap-1.5">
                <IconList class="w-5 h-5" />
                <p class="text-sm font-medium">List</p>
            </button>
        </div>
    </div>
</template>
 
<style>
</style>