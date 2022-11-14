import { createApp } from 'vue'
import App from './App.vue'
import { createPinia } from 'pinia'
import router from "./router"

import "bootstrap"
import "bootstrap/dist/css/bootstrap.min.css"
import 'bootstrap-icons/font/bootstrap-icons.css'
import "./css/default.css"

const pinia=createPinia()
const app = createApp(App)
app.use(pinia)
app.use(router)
app.mount('#app')
