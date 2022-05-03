import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/api': {
        target: 'http://ip21us2.sit.kmutt.ac.th:8080',
        changeOrigin: true,
        secure: false,      
        ws: true,
        // rewrite:(path) => path.replace(/^\/api/, '')
      }
    }
    
  }
})

