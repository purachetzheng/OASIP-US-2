import { ref } from 'vue'
import { fetchData } from './fetchFunc'
export const events = ref([])

export const eventFetch = {
    //GET
    async get() {
        events.value = await fetchData.get('events')
    },

    async getIfEmpty() {
        const isEmpty = events.value.length === 0
        isEmpty ? this.get() : ''
    },

    getById(id) {
        return fetchData.get('events/' + id)
    },

    //POST
    async post(newEvent) {
        const addedEvent = await fetchData.post('events', newEvent)
        const categoryId = addedEvent.eventCategoryId
        //EventCategoryName Null, Find EventCategoryName by EventCategoryId
        addedEvent.EventCategoryName = eventCategories.find(
            (e) => e.id === categoryId
        ).eventDuration
        addedEvent ? events.value.push(addedEvent) : ''
    },

    //REMOVE
    async remove(id) {
        const isDeleted = await fetchData.remove('events/' + id)

        if(isDeleted){
            events.value = events.value.filter((event) => event.id !== id)
            console.log('deleted event successfully')
        } else console.log('error, cannot delete event')
    },
}
