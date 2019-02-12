// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import routes from './config/routes'
import api from './config/api'
import aapi from './config/index'
import utils from './utils/fortime'
import 'bootstrap/dist/css/bootstrap.css'

Vue.prototype.$api=api
Vue.prototype.$axapi=aapi
Vue.prototype.$fortime=utils

Vue.use(VueRouter)

const router = new VueRouter({
	routes
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: (h) => h(App)
})
