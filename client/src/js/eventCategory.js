import { ref } from "vue"
import { fetchData } from "./fetchFunc"

const url = import.meta.env.VITE_BASE_URL + 'eventcategories/'
export const eventCategories = ref([])

export const eventCategoryFetch = {
    //GET
    async get(){
        const res = await fetch(url)
        console.log(res.status === 200 
            ? 'get eventcategories successfully'
            : 'error, cannot get eventcategories');
        if(res.status === 200) 
            return eventCategories.value = await res.json()
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
