import login from '../page/login.vue'
import register from '@/page/register.vue'
import mySurvey from '../page/content.vue'

import uFrame from '../frame/userFrame.vue'
import mFrame from '../frame/managerFrame.vue'

import userMain from '../page/user/main.vue'
import surveyCreater from '../page/user/surveyCreater.vue'
import result from '../page/user/result.vue'


import addUser from '../page/manager/addUser'
import selectUser from '../page/manager/selectUser'

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
            {path:'result/:id',component:result}
    	]
    },
    {
        path: '/manager',
        component: mFrame,
        children: [
            {path:'/',component:addUser},
            {path:'/selectUser',component:selectUser}
        ]
    }
]