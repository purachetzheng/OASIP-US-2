export const fetchData = {
  settings: {
    baseUrl: import.meta.env.VITE_BASE_URL + 'api/',
  },
  async get(entity, notFound = []) {
    const res = await fetch(this.settings.baseUrl + entity)
    if (res.status === 200) {
      console.log(`get ${entity} successfully`)
      return await res.json()
    } else {
      console.log(`error, cannot get ${entity}`)
      return notFound
    }
  },
  async post(entity, newObj) {
    const res = await fetch(this.settings.baseUrl + entity, {
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
  async put(entity, obj) {
    const res = await fetch(this.settings.baseUrl + entity, {
      method: 'PUT',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify(obj),
    })
    if (res.status === 200) {
      console.log('edited successfully')
      return await res.json()
    } else console.log('error, cannot edit')
  },
  async remove(entity) {
    const res = await fetch(this.settings.baseUrl + entity, { method: 'DELETE',})
    
    if (res.status === 200) return true
    else return false
  },
}