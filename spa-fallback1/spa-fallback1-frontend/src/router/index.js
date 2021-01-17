import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/home/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/page1",
    name: "Page1",
    component: () =>
      import(/* webpackChunkName: "page1" */ "../views/page1/Page1.vue")
  },
  {
    path: "/page2",
    name: "Page2",
    component: () =>
      import(/* webpackChunkName: "page2" */ "../views/page2/Page2.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
