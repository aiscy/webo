import { route } from 'quasar/wrappers'
import VueRouter from 'vue-router'
import { Store } from '../store'
import routes from './routes'

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation
 */

export default route<Store>(function({ Vue }) {
  Vue.use(VueRouter)

  return new VueRouter({
    scrollBehavior: () => ({ x: 0, y: 0 }),
    routes,

    // quasar.conf.js -> build -> vueRouterMode
    // quasar.conf.js -> build -> publicPath
    mode: process.env.VUE_ROUTER_MODE,
    base: process.env.VUE_ROUTER_BASE
  })
})
