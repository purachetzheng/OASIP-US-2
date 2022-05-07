<script setup>
//Comp
import Sidebar from './components/Sidebar.vue';
import Navbar from './components/Navbar.vue';
import { darkMode } from './js/variable'

</script>

<template>
  <div :class="[darkMode.mode.value === 'dark'?'dark' : '' ]">
    <div class=" flex h-screen bg-gray-50 dark:bg-neutral-900 w-screen fixed ">
      <!-- <Sidebar class="hidden w-64 overflow-y-auto bg-white dark:bg-neutral-800 md:block flex-shrink-0" /> -->
      <div class="flex flex-col flex-1 w-full ">
        <Navbar class="" />
        <router-view></router-view>
        <!-- <main class="h-full overflow-y-auto sm:px-6 sm:py-4 md:px-12 md:py-8 lg:px-20 lg:py-12 ">
                
            </main> -->
      </div>
    </div>
  </div>


</template>

<style>
/* .router-link-active {
    color: white;
} */
/* width */
::-webkit-scrollbar {
  width: 10px;
}


/* Handle */
::-webkit-scrollbar-thumb {
  background: gray;
  border-radius: 3px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #515151;
}
</style>
