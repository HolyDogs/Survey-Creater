// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import routes from './config/routes'
import aapi from './config/axios'
import utils from './utils/fortime'
import forCrypto from './utils/forCrypto.js'
import 'bootstrap/dist/css/bootstrap.css'

var state={
	islogin:false,
	manager:false,
	identify:{
        name:'null',
        email:'null',
	},
	login(name,manager,email){
        this.islogin=true;
        this.identify.name=name;
        this.manager=manager;
        this.identify.email=email;
	},
	logout(){
        this.islogin=false;
        this.identify.name='null';
        this.manager=false;
        this.identify.email='null';
	}
}

Vue.prototype.state=state
Vue.prototype.$axios=aapi
Vue.prototype.$fortime=utils
Vue.prototype.$forCrypto=forCrypto

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
