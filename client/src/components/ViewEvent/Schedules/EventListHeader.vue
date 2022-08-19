<script setup>
//Icon
import IconGrid from '../../icons/IconGrid.vue'
import IconList from '../../icons/IconList.vue'
import IconRefresh from '../../icons/IconRefresh.vue'
defineEmits(['emitRefreshEvent', 'emitLayout'])
const props = defineProps({
    eventAmount: {
        type: Number,
        default: 0
    },
})

</script>
 
<template>
    <div class="flex justify-between h-ful">
        <div class="flex items-center gap-1 dark:text-white">
            <!-- Refresh -->
            <button class="flex items-end p-1 rounded-full bg-blue-300 hover:bg-blue-600" @click="$emit('emitRefreshEvent')">
                <IconRefresh class="w-5 h-5 text-blue-50 " />
            </button>
            <!-- Header Text -->
            <div class="flex items-baseline gap-2">
                <span class="text-lg tracking-wide font-medium">Schedules: </span>

                <span class="font-medium">
                    {{ eventAmount }} events.
                </span>
            </div>
        </div>
        <div v-if="0" class="flex gap-4 pr-4 dark:text-white">
            <button class="flex items-center gap-1.5" @click="$emit('emitLayout', 'grid')">
                <IconGrid class="w-4 h-4" />
                <button class="text-sm font-medium">Grid</button>
            </button>
            <button class="flex items-center gap-1.5" @click="$emit('emitLayout', 'list')">
                <IconList class="w-5 h-5" />
                <button class="text-sm font-medium">List</button>
            </button>
        </div>
    </div>
</template>
 
<style>
</style>