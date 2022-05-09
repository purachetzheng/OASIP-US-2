<script setup>
import ModalCreate from '../components/modal/ModalCreate.vue';
import { useRouter } from 'vue-router';
const myRouter = useRouter()
const closeModal = () => myRouter.push({name: 'Home'})
</script>
 
<template>
    <ModalCreate :visible="true" />
</template>
 
<style>

</style>