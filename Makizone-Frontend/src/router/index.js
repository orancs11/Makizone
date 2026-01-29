import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import DashboardView from '../views/DashboardView.vue'
import GardenView from '../views/GardenView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
      {
	  path: '/register',
	  name: 'register',
	  component: RegisterView
      },
      {
	  path: '/login',
	  name: 'login',
	  component: LoginView

      },
      {
	  path: '/dashboard',
	  name: 'dashboard',
	  component: DashboardView,
	  meta: {requiresAuth: true}
      },
      {
	  path: '/garden',
	  name: 'garden',
	  component: GardenView,
	  meta: {requiresAuth: true}
      }
  ]
})

router.beforeEach((to, from, next) => {
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
    const token = localStorage.getItem('token')

    if(requiresAuth && !token){
	next('/login')
    }
    else{
	next()
    }

})

export default router
