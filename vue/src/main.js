import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import router from "./router"

import "bootstrap"
import "bootstrap/dist/css/bootstrap.min.css"
import 'bootstrap-icons/font/bootstrap-icons.css'
import '@fortawesome/fontawesome-free/js/all.js'
import "./css/default.css"
import "./css/ext.css"
const pinia = createPinia()
const app = createApp(App)
app.use(pinia)
app.use(router)
app.mount('#app')
