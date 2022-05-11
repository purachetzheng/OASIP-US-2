import { ref } from "vue"
import { fetchData } from "./fetchFunc"
export const eventCategories = ref([])

export const eventCategoryFetch = {
    //GET
    async get(){
        eventCategories.value = await fetchData.get('eventcategories')
    },
    async getIfEmpty(){
        const isEmpty = eventCategories.value.length === 0
        isEmpty ? this.get() : ''
    },
    getById(id) {
        return fetchData.get('eventcategories/' + id)
    },
    //POST


    //REMOVE

}
