<script setup>
defineProps({
    categoryName: {
        type: String,
        default: ''
    }
});
</script>
 
<template>
    <div class="flex justify-center">
        <div class="flex items-center gap-1 dark:text-white">
            <!-- Header Text -->
            <div class="flex items-baseline gap-2">
                <span class="text-xl tracking-wide font-bold text-blue-500">{{ categoryName }}</span>
            </div>
        </div>
    </div>
</template>
 
<style>

</style>