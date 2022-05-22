<script setup>

const props = defineProps({
    categoryAmount: {
        type: Number,
        default: 0
    }
});

</script>
 
<template>
    <div class="flex justify-center">
        <div class="flex items-center gap-1 dark:text-white">
            <!-- Header Text -->
            <div class="flex items-baseline gap-2">
                <span class="text-lg tracking-wide font-medium">Categories available: </span>
                <span class="font-light">
                    {{ categoryAmount }} Categories
                </span>
            </div>
        </div>
    </div>
</template>
 
<style>

</style>