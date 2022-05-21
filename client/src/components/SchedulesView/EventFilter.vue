<script setup>
import { ref, computed, onBeforeMount, onMounted, nextTick, } from 'vue'
import dayjs from 'dayjs'
import IconSearch from '../icons/IconSearch.vue'
import { eventCategories } from '../../js/eventCategory'
import IconCalendar from '../icons/IconCalendar.vue';
const emits = defineEmits(['emitSelectCategory', 'emitSelectDayStatus', 'emitSelectDay'])
const props = defineProps({
    first: {
        type: String,
        require: true,
    },
})
const categories = ref([])
onBeforeMount(async () => {
    await eventCategories.get()
    categories.value = eventCategories.categories.value
})
const searchBox = ref(null)
const datePicker = ref(null)
// fix some area when click input date don't work
const clickIconDatePicker = () => 
    nextTick(() => {
        datePicker.value.showPicker()
        datePicker.value.focus()
    })

</script>
 
<template>
    <div class="flex flex-col w-1/4 gap-y-2 pt-9">
        <!-- Search -->
        <div class="flex flex-col gap-2">
            <div class="text-sm font-medium">
                <h2 class="dark:text-white">Search</h2>
            </div>
            <div class="relative">
                <button @click="nextTick(() => searchBox.focus())" class="absolute flex inset-y-0 items-center pl-2">
                    <IconSearch class="w-5 h-5" />
                </button>
                <input ref="searchBox" type="text" 
                    class="p-2 pl-8 border-2 rounded-md w-full text-sm
                                bg-white outline-none focus:ring-2 focus:border-blue-600"
                    placeholder="Search">
            </div>
        </div>
        <!-- Category -->
        <div class="flex flex-col gap-2">

            <div class="text-sm font-medium">
                <span class="dark:text-white">Category</span>
            </div>
            <div class="">
                <select name="" id="" 
                    @change="$emit('emitSelectCategory', $event.target.value)"
                    class="p-2 border-2 rounded-md w-full text-sm
                        bg-white outline-none focus:ring-2 focus:border-blue-600">
                    <option value="all" selected>All</option>
                    <option 
                        v-for="category in categories" :key="category.id"
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
                <span class="dark:text-white">Status</span>
            </div>
            <div class="">
                <select name="" id="" 
                    @change="$emit('emitSelectDayStatus', $event.target.value)"
                    class="p-2 border-2 rounded-md w-full text-sm
                        bg-white outline-none focus:ring-2 focus:border-blue-600">
                    <option value="all" selected>All</option>
                    <option value="upcoming">Upcoming</option>
                    <option value="past">Past</option>
                </select>
            </div>
        </div>

        <!-- Day -->
        <div class="flex flex-col gap-2">

            <div class="text-sm font-medium">
                <span class="">Day</span>
            </div>
            <div class="relative" >
                <button @click="clickIconDatePicker" class="absolute flex inset-y-0 items-center pl-2">
                    <IconCalendar class="w-5 h-5" />
                </button>
                <input ref="datePicker" type="date" 
                    @change="$emit('emitSelectDay', $event.target.value)"
                    
                    class="p-2 pl-8 border-2 rounded-md w-full text-sm
                                bg-white outline-none focus:ring-2 focus:border-blue-600">
            </div>
        </div>


        <!-- <div class="p-4 h-2/3 bg-green-200 rounded-lg">
                    div
                </div> -->
    </div>
</template>
 
<style scoped>
.input-container input {
    border: none;
    box-sizing: border-box;
    outline: 0;
    padding: .75rem;
    position: relative;
    width: 100%;
}

input[type="date"]::-webkit-calendar-picker-indicator {
    background: transparent;
    bottom: 0;
    color: transparent;
    cursor: pointer;
    height: auto;
    left: 0;
    position: absolute;
    right: 0;
    top: 0;
    width: auto;
}
</style>