import { ref } from "vue";
import { zFetch } from '../js/zLib'

export const events = ref([])
export const eventCategories = ref([])

// middle layer
export const middleFetch = {
    event:{
        async post(newEvent){
            const addedEvent = await fetchData.post('events', newEvent)
            addedEvent ? events.value.push(addedEvent) : '';
        },
    },
    async getEvents(){
        events.value = await fetchData.get('events')
    },
    async getEventsNull(){
        events.value.length === 0 ? this.getEvents() : ''
    },
    getEventsById(id){
        return fetchData.get('events/'+id)
    },
    async getEventCategories(){
        eventCategories.value = await zFetch.get('http://intproj21.sit.kmutt.ac.th/us2/api/eventcategories')
    },
    async getEventCategoriesNull(){
        eventCategories.value.length === 0 ? this.getEventCategories(): ''
    },
    async removeEvent(id){
        events.value = await zFetch.remove('http://intproj21.sit.kmutt.ac.th/us2/api/events/'+ id) ? events.value.filter((event) => event.id !== id) : events.value
    }
}

export const darkMode = {
    mode: ref(localStorage.getItem('theme')),
    on(){
        // localStorage.setItem('theme', 'dark')
        localStorage.theme = 'dark'
        this.mode.value = 'dark'
        console.log('Set theme to dark mode')
    },
    off(){
        // localStorage.setItem('theme', 'light')
        localStorage.theme = 'light'
        this.mode.value = 'light'
        console.log('Set theme to light mode')
    }
}

export const fetchData = {
    settings: {
        baseUrl: 'http://intproj21.sit.kmutt.ac.th/us2/api/',
    },
    async get(entity, notFound = []) {
      const res = await fetch(this.settings.baseUrl+entity)
      if (res.status === 200) {
        console.log(`get ${entity} successfully`)
        return await res.json()
      } else {
        console.log(`error, cannot get ${entity}`)
        return notFound
      }
    },
    async post(entity, newObj) {
      const res = await fetch(this.settings.baseUrl+entity, {
        method: 'POST',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(newObj),
      })
      if (res.status === 201 || res.status === 200) {
        console.log(`created ${entity} successfully`)
        return await res.json()
      } else {
        console.log(`error, cannot create ${entity}`)
        return false
      }
    },
    async put(urlQuery, obj) {
      const res = await fetch(urlQuery, {
        method: 'PUT',
        headers: { 'content-type': 'application/json' },
        body: JSON.stringify(obj),
      })
      if (res.status === 200) {
        console.log('edited successfully')
        return await res.json()
      } else console.log('error, cannot edit')
    },
    async remove(urlQuery) {
      const res = await fetch(urlQuery, {
        method: 'DELETE',
      })
      if (res.status === 200) {
        console.log('deleted successfully')
        return true
      } else {
        console.log('error, cannot delete')
        return false
      }
    },
  }
  