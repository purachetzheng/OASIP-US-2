<script setup>
import { ref, computed, onBeforeMount, onMounted, nextTick, reactive, } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { events } from '../js/event'
import { eventCategories } from '../js/eventCategory'
import CategoryHeader from '../components/ViewCategory/CategoryList/CategoryHeader.vue';
import CategoryList from '../components/ViewCategory/CategoryList/CategoryList.vue';

const categoryList = ref([]);
const updateList = () => categoryList.value = eventCategories.categories.value
onBeforeMount(async () => {
    await events.get()
    await eventCategories.get()
    updateList()
    console.log(categoryList.value);
})
const router = useRouter()

const viewDetail = async (id) => {
    router.push({ name: 'CategoryDetail', params: {categoryId: id }})
}

</script>

<template>
  <main class="h-full w-screen overflow-auto p-6">
    <div class="w-full flex flex-col">
          <CategoryHeader :categoryAmount="categoryList.length" />
          <div v-show="!categoryList.length" class="text-center my-10 text-xl">No Scheduled Events</div>
          <CategoryList :categories="categoryList" @emit-view-detail="viewDetail"/>
    </div>
  </main>
</template>

<style></style>
