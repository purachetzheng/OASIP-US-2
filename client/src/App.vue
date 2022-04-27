<script setup>
import {ref, onBeforeMount} from 'vue'
import {zFetch} from './zFetch'
import dayjs from 'dayjs'

const events = ref([])
onBeforeMount(async () => {
    events.value = await zFetch.get('/api/events')
    console.log(events.value);
})
</script>

<template>
    <div>{{events}} </div>
    
</template>

<style>
</style>