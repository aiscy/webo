import { RouteConfig } from 'vue-router'

const routes: RouteConfig[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/evolve',
    component: () => import('layouts/EvolveLayout.vue'),
    children: [
      { path: '', component: () => import('pages/EvolveMain.vue') },
      { path: ':name', component: () => import('pages/EvolveData.vue') }
    ]
  },
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
