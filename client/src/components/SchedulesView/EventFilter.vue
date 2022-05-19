<script setup>
import { ref, computed, onBeforeMount, onMounted, nextTick, } from 'vue'
import IconSearch from '../icons/IconSearch.vue'
import { eventCategories, eventCategoryFetch } from '../../js/eventCategory'
const emits = defineEmits(['emitSelectCategory', 'emitSelectDayStatus'])
const props = defineProps({
    first: {
        type: String,
        require: true,
    },
})
const searchBox = ref(null)
</script>
 
<template>
    <div class="flex flex-col w-1/4 gap-y-2 pt-9">
        <!-- Search -->
        <div class="flex flex-col gap-2">
            <div class="text-sm font-medium">
                <h2 class="">Search</h2>
            </div>
            <div class="relative">
                <button @click="nextTick(() => searchBox.focus())" class="absolute flex inset-y-0 items-center pl-2">
                    <IconSearch class="w-5 h-5" />
                </button>
                <input ref="searchBox" class="p-2 pl-8 border-2 rounded-md w-full text-sm
                                bg-white outline-none focus:ring-2 focus:border-blue-600" type="text"
                    placeholder="Search">
            </div>
        </div>
        <!-- Category -->
        <div class="flex flex-col gap-2">

            <div class="text-sm font-medium">
                <span class="">Category</span>
            </div>
            <div class="">
                <select name="" id="" 
                    @change="$emit('emitSelectCategory', $event.target.value)"
                    class="p-2 border-2 rounded-md w-full text-sm
                        bg-white outline-none focus:ring-2 focus:border-blue-600">
                    <option value="all" selected>All</option>
                    <option 
                        v-for="category in eventCategories" :key="category.id"
                        :value="category.eventCategoryName"
                        >
                        {{ category.eventCategoryName }}
                    </option>
                </select>
            </div>
        </div>

        <!-- Status -->
        <div class="flex flex-col gap-2">

            <div class="text-sm font-medium">
                <span class="">Status</span>
            </div>
            <div class="">
                <select name="" id="" 
                    @change="$emit('emitSelectDayStatus', $event.target.value)"
                    class="p-2 border-2 rounded-md w-full text-sm
                        bg-white outline-none focus:ring-2 focus:border-blue-600">
                    <option value="all">All</option>
                    <option value="upcoming">Upcoming</option>
                    <option value="past">Past</option>
                </select>
            </div>
        </div>


        <!-- <div class="p-4 h-2/3 bg-green-200 rounded-lg">
                    div
                </div> -->
    </div>
</template>
 
<style>
</style>