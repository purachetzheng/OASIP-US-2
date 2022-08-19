import { ref } from "vue";

export const darkMode = {
    mode: ref(localStorage.getItem('theme') !== null? localStorage.getItem('theme'): 'light'),
    on(){
        // localStorage.setItem('theme', 'dark')
        localStorage.theme = 'dark'
        this.mode.value = 'dark'
        console.log('Set theme to dark mode')
    },
    off(){
        // localStorage.setItem('theme', 'light')
        localStorage.theme = 'light'
        this.mode.value = 'light'
        console.log('Set theme to light mode')
    }
}
