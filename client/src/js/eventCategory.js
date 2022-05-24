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

    async getById(id) {
        const res = await fetch(url+id)
        console.log(res.status === 200 
            ? `get category id ${id} successfully`
            : `error, cannot get category id ${id}`);
        if(res.status === 200) 
            return res.json()
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
