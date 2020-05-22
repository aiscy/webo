import Vue from 'vue'
import Router, {Route} from 'vue-router'

const { Toast } = require('buefy/dist/components/toast')

const Home = () => import('@/components/home/Home.vue')
const Playground = () => import('@/components/Playground.vue')
const Evolve = () => import('@/components/evolve/Evolve.vue')

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/playground', component: Playground },
        { path: '/evolve', component: Evolve },
        {
            path: '/redirect',
            redirect: (to: Route) => {
                if (to.query.successful === 'true') {
                    Toast.open({
                        message: 'Something happened correctly!',
                        type: 'is-success',
                        duration: 5000,
                    })
                } else {
                    Toast.open({
                        message: 'Something\'s not good!',
                        type: 'is-danger',
                        duration: 5000,
                    })
                }
                const redirect = localStorage.getItem('redirectTo')
                if (redirect) {
                    localStorage.removeItem('redirectTo')
                    return redirect
                } else {
                    return '/'
                }
            },
        },
        { path: '*', component: Home }, // TODO 404
    ],
})
