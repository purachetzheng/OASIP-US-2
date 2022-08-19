import { onMounted, onUnmounted, ref } from 'vue'

export function useMouse() {
    const mousePos = ref({
        x: 0,
        y: 0
    })

  function update(event) {
    mousePos.value.x = event.pageX
    mousePos.value.y = event.pageY
  }

  onMounted(() => window.addEventListener('mousemove', update))
  onUnmounted(() => window.removeEventListener('mousemove', update))

  return mousePos
}
