<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import CategoryDetailHeader from '../components/ViewCategory/CategoryList/CategoryDetailHeader.vue';

import { eventCategories } from '../js/eventCategory'

// router
const { params } = useRoute()
const categoryId = params.categoryId
const router = useRouter()
const routerSigns = {
    goBack: () => router.go(-1),
    schedules: () => router.push({ name: 'Category'})
}

const category = ref({})

onBeforeMount( async() => {
    category.value = await eventCategories.getById(categoryId)
    editing.setup()
    //first time left trick
    updateTimeleft()
})

</script>
 
<template>
    <main class="h-full w-screen overflow-auto flex flex-col px-28 py-8">
        <CategoryDetailHeader :categoryName="category.name" />
        <div>

        </div>
    </main>
</template>
 
<style>
</style>