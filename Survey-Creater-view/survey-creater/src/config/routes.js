import login from '../page/login.vue'
import register from '@/page/register.vue'
import mySurvey from '../page/content.vue'

import uFrame from '../frame/userFrame.vue'
import mFrame from '../frame/managerFrame.vue'

import userMain from '../page/user/main.vue'
import surveyCreater from '../page/user/surveyCreater.vue'
import result from '../page/user/result.vue'
import analyze from '../page/user/analyze.vue'


import manageUser from '../page/manager/manageUser'
import timeLine from '../page/manager/timeLine'

export default[
    {
    	path:'/',
    	component:login
    },
    {
        path:'/register',
        component:register
    },
    {
    	path: '/survey/:id',
    	component:mySurvey
    },
    {
    	path: '/user',
    	component:uFrame,
    	children: [
    	    {path:'/',component:userMain},
    	    {path:'surveyCreater',component:surveyCreater},
            {path:'result/:id',component:result},
            {path:'analyze',component:analyze}
    	]

        /*,
        beforeEnter:(to,from,next) => {
            if(this.state.islogin && !(this.state.manager)){
                next();
            }else{
                alert("xxxxxxx")
            }
        }*/
    },
    {
        path: '/manager',
        component: mFrame,
        children: [
            {path:'/',component:manageUser},
            {path:'timeLine',component:timeLine}
        ]

        /*,
        beforeEnter:(to,from,next) => {
            alert("NO!!!!!!!")
        }*/
    }

]