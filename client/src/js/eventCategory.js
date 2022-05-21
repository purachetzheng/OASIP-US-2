import { ref } from "vue"


const url = import.meta.env.VITE_BASE_URL + 'eventcategories/'
// export const eventCategories = ref([])

export const eventCategories = {
    categories: ref([]),
    //GET
    async get(){
        const res = await fetch(url)
        console.log(res.status === 200 
            ? 'get categories successfully'
            : 'error, cannot get categories');
        if(res.status === 200) 
            return this.categories.value = await res.json()
    },
    async getIfEmpty(){
        const isEmpty = this.categories.value.length === 0
        isEmpty ? this.get() : ''
    },
    async getById(id) {

        const res = await fetch(url+id)
        console.log(res.status === 200 
            ? `get category id ${id} successfully`
            : `error, cannot get category id ${id}`);
        if(res.status === 200) 
            return res.json()
    },
    //POST
    async post(newCategories) {
        const res = await fetch(url, {
            method: 'POST',
            headers: { 'content-type': 'application/json' },
            body: JSON.stringify(newEvent)
        })
        
        // console.log(await res.json().id);
        console.log(res.status === 200 || res.status === 201 
            ? 'created category successfully'
            : 'error, cannot create category');

        if(res.status !== 200 && res.status !== 201) 
            return { posted: false, res: await res.json() }

        const addedCategories = await res.json()
        //find eventCategoryName
        
        this.categories.value.push(addedCategories)

        return { posted: true, category:addedCategories }
    },

    //REMOVE
    async remove(id) {
        const res = await fetch(url + id, { method: 'DELETE',})

        console.log(res.status === 200 
            ? 'deleted category successfully'
            : 'error, cannot delete category');
        if(res.status !== 200) return false

        this.categories.value = this.categories.value.filter((event) => event.id !== id)
        return true
    },
}
