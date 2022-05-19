import { ref } from 'vue'
import { fetchData } from './fetchFunc'
import { eventCategories } from './eventCategory'

const url = import.meta.env.VITE_BASE_URL + 'events/'
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
        if (!addedEvent) return false
        const categoryId = addedEvent.eventCategoryId
        //EventCategoryName Null, Find EventCategoryName by EventCategoryId
        // console.log(addedEvent.EventCategoryName);
        // console.log(eventCategories.value);
        // console.log(categoryId);
        // console.log(eventCategories.value.find(e => e.id == categoryId));
        // console.log(eventCategories.value.find((e) => e.id == categoryId).eventCategoryName);
        addedEvent.EventCategoryName = eventCategories.value.find((e) => e.id == categoryId).eventCategoryName
        addedEvent ? events.value.push(addedEvent) : ''
        return true
    },

    //REMOVE
    async remove(id) {
        const isDeleted = await fetchData.remove(`events/${id}`)

        if (isDeleted) {
            events.value = events.value.filter((event) => event.id !== id)
            console.log('deleted event successfully')
        } else console.log('error, cannot delete event')
    },

    //UPDATE
    // async put(id, editObj){
    //     const modifyEvent = await fetchData.put(`events/${id}`, editObj)
    //     events.value = events.value.map((event) => 
    //         event.id === modifyEvent.id 
    //         ? { 
    //             ...event,
    //             bookingName: modifyEvent.bookingName,
    //             bookingEmail: modifyEvent.bookingEmail,
    //             eventStartTime: modifyEvent.eventStartTime,
    //             eventDuration: modifyEvent.eventDuration,
    //             eventNotes: modifyEvent.eventNotes,
    //             eventCategoryId: modifyEvent.eventCategoryId
    //         } 
    //         : event )
    // }
    async patch(id, editObj) {
        const res = await fetch(url + id, {
            method: 'PATCH',
            headers: { 'content-type': 'application/json' },
            body: JSON.stringify(editObj),
        })
        console.log(res.json());
        if (res.status === 200) {
            // console.log(`edited ${entity} successfully`)

            const modifyEvent = await res.json()

            // console.log(modifyEvent);
            events.value = events.value.map((event) =>
                event.id === modifyEvent.id
                    ? {
                        ...event,
                        eventStartTime: modifyEvent.eventStartTime,
                    }
                    : event)
            return {status: true, event: modifyEvent}
        } else {
            const error = await res.json()
            // console.log(`error, cannot edit ${entity}`)
            return {status: false, error: error}
        }
    }


}
