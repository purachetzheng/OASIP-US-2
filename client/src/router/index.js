import { createRouter, createWebHashHistory, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import NotFound from '../views/NotFound.vue';
import Schedules from '../views/Schedules.vue';
import SchedulesOld from '../views/SchedulesOld.vue';
import EventDetail from '../views/EventDetail.vue';
import Reserve from '../views/Reserve.vue';
import AboutUs from '../views/AboutUs.vue';
import Category from '../views/Category.vue';
import CategoryDetail from '../views/CategoryDetail.vue';

const history = createWebHistory('/us2/');
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
        path: '/SchedulesOld',
        name: 'SchedulesOld',
        component: SchedulesOld,
    },
    {
        path: '/reserve',
        name: 'Reserve',
        component: Reserve,
    },
    {
        path: '/about-us',
        name: 'AboutUs',
        component: AboutUs,
    },
    {
        path: '/category',
        name: 'Category',
        component: Category,
    },
    {
        path: '/category/:categoryId',
        name: 'CategoryDetail',
        component: CategoryDetail,
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
];

const router = createRouter({ history, routes });
export default router;
