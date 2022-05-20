import { ref } from 'vue'
import { eventCategories } from './eventCategory'

const url = import.meta.env.VITE_BASE_URL + 'events/'
// const url = import.meta.env.BASE_URL + 'api/events/'

// export const events = ref([])

export const events = {
    events: ref([]),
    //GET
    async get() {
        const res = await fetch(url)
        console.log(res.status === 200 
            ? 'get events successfully'
            : 'error, cannot get events');
        if(res.status === 200) 
            return this.events.value = await res.json()
    },

    async getIfEmpty() {
        const isEmpty = this.events.value.length === 0
        isEmpty ? this.get() : ''
    },

    async getById(id) {

        const res = await fetch(url+id)
        console.log(res.status === 200 
            ? `get event id ${id} successfully`
            : `error, cannot get event id ${id}`);
        if(res.status === 200) 
            return await res.json()
    },

    //POST
    async post(newEvent) {
        const res = await fetch(url, {
            method: 'POST',
            headers: { 'content-type': 'application/json' },
            body: JSON.stringify(newEvent)
        })
        
        // console.log(await res.json().id);
        console.log(res.status === 200 || res.status === 201 
            ? 'created event successfully'
            : 'error, cannot create event');

        if(res.status !== 200 && res.status !== 201) 
            return { posted: false, res: await res.json() }

        const addedEvent = await res.json()
        //find eventCategoryName
        const categoryId = addedEvent.eventCategoryId
        addedEvent.eventCategoryName = eventCategories.value.find((e) => e.id == categoryId).eventCategoryName
        
        this.events.value.push(addedEvent)

        return { posted: true, event:addedEvent }
    },

    //REMOVE
    async remove(id) {
        const res = await fetch(url + id, { method: 'DELETE',})

        console.log(res.status === 200 
            ? 'deleted event successfully'
            : 'error, cannot delete event');
        if(res.status !== 200) return false

        this.events.value = this.events.value.filter((event) => event.id !== id)
        return true
    },

    //UPDATE
    // async put(id, editObj){
    //     const modifyEvent = await fetchData.put(`events/${id}`, editObj)
    //     this.events.value = this.events.value.map((event) => 
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
        console.log(res);
        if (res.status === 200) {
            // console.log(`edited ${entity} successfully`)

            const modifyEvent = await res.json()

            // console.log(modifyEvent);
            this.events.value = this.events.value.map((event) =>
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

const afterGet = {
    succeed:() => {},
    Failed:() => {}
}