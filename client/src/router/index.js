import {
  createRouter,
  createWebHashHistory,
  createWebHistory
} from 'vue-router'
import Home from '../views/Home.vue'
import NotFound from '../views/NotFound.vue'
import Schedules from '../views/Schedules.vue'
import SchedulesBeta from '../views/SchedulesBeta.vue'
import EventDetail from '../views/EventDetail.vue'
import Reserve from '../views/Reserve.vue'
import TestModal from '../views/TestModal.vue'

const history = createWebHistory('/us2/')
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/schedules',
    name: 'Schedules',
    component: Schedules,
  },
  {
    path: '/schedules/:eventId',
    name: 'EventDetail',
    component: EventDetail,
  },
  {
    path: '/SchedulesBeta',
    name: 'SchedulesBeta',
    component: SchedulesBeta,
  },
  {
    path: '/reserve',
    name: 'Reserve',
    component: Reserve,
  },
  {
    path: '/test',
    name: 'TestModal',
    component: TestModal,
  },
  {
    path: '/:catchNotMatchPath(.*)',
    name: 'NotFound',
    component: NotFound,
  },
]

const router = createRouter({ history, routes })
export default router
