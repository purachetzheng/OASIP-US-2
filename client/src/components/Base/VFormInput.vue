<script setup>
defineEmits([])
const prop = defineProps({
  isError: {
    type: Boolean,
    default: false
  }
})
</script>

<template>
  <input

    :class="[
      'w-full rounded-md outline-none border-2',
      'p-2 pl-2',
      'text-md',
      isError
        ? 'border-red-600 bg-red-50'
        : 'focus:border-blue-600 bg-gray-50',
    ]"
  />

</template>

<style></style>
