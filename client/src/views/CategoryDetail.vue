<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import CategoryDetailHeader from '../components/ViewCategory/CategoryList/CategoryDetailHeader.vue';
import { useRoute, useRouter } from 'vue-router'

import { eventCategories } from '../js/eventCategory'

// router
const { params } = useRoute()
const categoryId = params.categoryId
const router = useRouter()
const routerSigns = {
    goBack: () => router.go(-1),
    schedules: () => router.push({ name: 'Category' })
}

const category = ref({})

onBeforeMount(async () => {
    category.value = await eventCategories.getById(categoryId)
})

</script>
 
<template>
    <main class="h-full w-screen overflow-auto flex flex-col px-28 py-15">
        <div class="flex flex-col bg-white rounded-xl h-full shadow-md p-5">
            <div class="flex justify-center text-xl tracking-wide font-bold text-blue-500">
                Category Detail
            </div>
            <!-- <CategoryDetailHeader :categoryName="category.eventCategoryName" /> -->
            <div class="flex flex-col space-y-5">
                <div class="flex flex-col">
                    <span class="font-semibold text-lg">
                        Category Name
                    </span>
                    {{ category.eventCategoryName }}
                </div>
                <div class="flex flex-col">
                    <span class="font-semibold text-lg">
                        Category Duration
                    </span>
                    {{ category.eventDuration }} Minutes
                </div>
                <div class="flex flex-col">
                    <span class="font-semibold text-lg">
                        Category Description
                    </span>
                    {{ category.eventCategoryDescription }}
                </div>
            </div>
        </div>
    </main>
</template>
 
<style>
</style>