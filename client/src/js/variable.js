import { ref } from "vue";
import { zFetch } from '../js/zLib'

export const events = ref([])
export const eventCategories = ref([])

export const middleFetch = {
    async getEvents(){
        events.value = await zFetch.get('http://intproj21.sit.kmutt.ac.th/us2/api/events')
    },
    async getEventsNull(){
        events.value.length === 0 ? this.getEvents() : ''
    },
    async getEventCategories(){
        eventCategories.value = await zFetch.get('http://intproj21.sit.kmutt.ac.th/us2/api/eventcategories')
    },
    async getEventCategoriesNull(){
        eventCategories.value.length === 0 ? this.getEventCategories(): ''
    }
}
