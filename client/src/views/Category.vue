<script setup>
import { ref, computed, onBeforeMount, onMounted, nextTick, reactive, } from 'vue'

import CategoryHeader from '../components/CategoryView/CategoryHeader.vue';
import CategoryList from '../components/CategoryView/CategoryList.vue';

const categoryList = ref({});
onBeforeMount(async () => {
    await events.get()
    await eventCategoryFetch.getIfEmpty()
    updateEventList()
})

</script>

<template>
  <main class="h-full w-screen overflow-auto p-6">
    <div class="w-full flex flex-col">
        <CategoryHeader :category-amount="categoryList.length" />

        <CategoryList />
    </div>
  </main>
</template>

<style></style>
