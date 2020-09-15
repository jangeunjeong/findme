import Vue from "vue";
import "es6-promise/auto";
import Vuex from "vuex";
import App from "./App.vue";
import Router from "vue-router";
import routes from "./routes";
import vuetify from "./plugins/vuetify";
import "vuetify/dist/vuetify.min.css";
import VueAwesomeSwiper from "vue-awesome-swiper";
import "swiper/css/swiper.css";
import store from './vuex/store'
import cookie from "./cookie";

Vue.config.productionTip = false;

Vue.use(Router);
Vue.use(Vuex);
Vue.use(VueAwesomeSwiper);
Vue.use(store)

const router = new Router({
  routes,
  mode: "history",
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  }
});

router.beforeEach((to, from, next) => {
  // if (cookie.cookieAuth() == 0 && to.name != 'Admin' && to.name != 'Login') {
  //   return next('admin')
  // }
  if (cookie.updateCookie()) {
    if (to.meta.loginRequire === false ) {
      if (from.name === null) {
        return next('Main')
      } else {
        return next(false);
      }
    }
    next();
  } else {
    if (to.meta.loginRequire === true) {
      alert('로그인 해주세요');
      return next('/')
    } 
    return next();
  }
})

new Vue({
  router,
  vuetify,
  store,
  render: (h) => h(App),
}).$mount("#app");
