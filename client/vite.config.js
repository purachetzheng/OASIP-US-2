import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  // base: 'intproj21.sit.kmutt.ac.th/us2/'
  base: '/us2/'
  // base: process.env.VITE_BASE_URL
})

