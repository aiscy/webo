import Vue from 'vue'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'
import '@fortawesome/fontawesome-free/css/all.css'
import VueCookies from 'vue-cookies'
import Axios from 'axios'
import VueAxios from 'vue-axios'
import i18n from '@/i18n'
import router from '@/router'
import App from '@/components/App.vue'

Vue.config.productionTip = false

Vue.use(Buefy, {
    defaultIconPack: 'fas',
})
Vue.use(VueCookies)
Axios.defaults.withCredentials = true
Vue.use(VueAxios, Axios)

new Vue({
    router,
    i18n,
    render: (h) => h(App),
}).$mount('#app')
