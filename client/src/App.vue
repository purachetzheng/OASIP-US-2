<script setup>
//Comp
import Sidebar from './components/Sidebar.vue';
import Navbar from './components/Navbar.vue';

</script>

<template>
    <div class="flex h-screen bg-gray-50 dark:bg-neutral-900 w-screen fixed">
        <!-- <Sidebar class="hidden w-64 overflow-y-auto bg-white dark:bg-neutral-800 md:block flex-shrink-0" /> -->
        <div class="flex flex-col flex-1 w-full">
            <Navbar />
            <main class="h-full overflow-y-auto px-20 py-12">
                <router-view></router-view>
            </main>
        </div>
    </div>

</template>

<style>
/* .router-link-active {
    color: white;
} */
</style>
