import { ref } from "vue"


export const zFetch = {
  settings: {
    baseUrl: 'http://ip21us2.sit.kmutt.ac.th:8080',
  },
  async get(urlQuery) {
    const res = await fetch(urlQuery)
    if (res.status === 200) {
      console.log(`get successfully`)
      return await res.json()
    } else console.log('error, cannot get')
  },
  async post(urlQuery, newObj) {
    const res = await fetch(urlQuery, {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify(newObj),
    })
    if (res.status === 201 || res.status === 200) {
      console.log('created successfully')
      return await res.json()
    } else console.log('error, cannot create')
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
