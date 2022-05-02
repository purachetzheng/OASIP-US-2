import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import NotFound from '../views/NotFound.vue';
import Schedules from '../views/Schedules.vue';
import EventDetail from '../views/EventDetail.vue';


const history = createWebHistory();
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
      component: EventDetail
    }
    ,
      {
        path: '/:catchNotMatchPath(.*)',
        name: 'NotFound',
        component: NotFound,
      }
];

const router = createRouter({ history, routes });
export default router;
