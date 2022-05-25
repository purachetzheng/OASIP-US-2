<script setup>
import { ref, computed, onBeforeMount, reactive } from 'vue'
import CategoryDetailHeader from '../components/ViewCategory/CategoryList/CategoryDetailHeader.vue';
import { useRoute, useRouter } from 'vue-router'

import { eventCategories } from '../js/eventCategory'
import BaseModalFailed from '../components/modal/BaseModalFailed.vue';

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
    editing.category = { ...category.value }
})
const editCategory = ref({
    eventCategoryDescription: null,
    eventCategoryName: null,
    eventDuration: null,
})
const editing = reactive({
    modalFailed: {
        visible: false,
        titil: 'Update Failed',
        desc: 'Category'
    },
    category:{
        eventCategoryDescription: null,
        eventCategoryName: null,
        eventDuration: null,
    },
    async save() {
        const obj = {
            eventCategoryName: this.category.eventCategoryName.trim(),
            eventDuration: this.category.eventDuration
        }
        const desc = this.category.eventCategoryDescription
        if(desc != null) obj.eventCategoryDescription = desc
        console.log(obj);
        const res = await eventCategories.patch(categoryId, obj)
        console.log(res);
        if (res.status) {
            category.value = res.category
            alert('Edit complete')
            return this.setAttr()
        }

        let message = ''
        await res.error.details.forEach(d => message += `${d.field}: ${d.errorMessage}; `)

        this.modalFailed.desc = message
        this.modalFailed.visible = true
    },
    setAttr(){
        this.category = { ...category.value }
    }
})
</script>
 
<template>
    <main class="h-full w-screen overflow-auto flex flex-col px-28 py-15">
        <div class="flex flex-col bg-white rounded-xl h-full shadow-md p-5">
            <div class="flex justify-center text-xl tracking-wide font-bold text-blue-500">
                Category Detail
            </div>
            <!-- <CategoryDetailHeader :categoryName="category.eventCategoryName" /> -->
            <form class="flex flex-col space-y-5" @submit.prevent="editing.save()">
                <div class="flex flex-col">
                    <span class="font-semibold text-lg">
                        Category Name <span class="text-red-500">*</span>
                    </span>
                    <input type="text" class="form-input" pattern="^[^-\s][a-zA-Z0-9_\W-]+$" v-model="editing.category.eventCategoryName">
                </div>
                <div class="flex flex-col">
                    <span class="font-semibold text-lg">
                        Category Duration
                    </span>
                    <div class="flex items-center gap-4">
                        <input type="number" min="1" max="480" class="form-input w-20"
                            v-model="editing.category.eventDuration">
                        <span>Minutes</span>
                    </div>
                </div>
                <div class="flex flex-col">
                    <span class="font-semibold text-lg">
                        Category Description
                    </span>
                    <textarea class="form-input" name="" id="" cols="30" rows="6" pattern="^[^-\s][a-zA-Z0-9_\W-]+$"
                        v-model="editing.category.eventCategoryDescription"></textarea>
                </div>
                <div class="flex justify-center gap-4">
                    <button type="submit" class="bg-blue-600 text-white px-3 py-1.5 rounded text-base font-medium hover:bg-blue-800 transition duration-300 disabled:bg-gray-300 disabled:text-slate-500">
                        Save
                    </button>
                    <button type="button" @click="routerSigns.goBack" class="bg-red-600 text-white px-3 py-1.5 rounded text-base font-medium hover:bg-red-800 transition duration-300 disabled:bg-gray-300 disabled:text-slate-500">
                        Cancel
                    </button>
                </div>

            </form>
        </div>
        <BaseModalFailed :visible="editing.modalFailed.visible" :title="editing.modalFailed.titil" :toSchedules="false"
            :desc="editing.modalFailed.desc" @closeModal="() => editing.modalFailed.visible = false" />
    </main>
</template>
 
<style>
</style>