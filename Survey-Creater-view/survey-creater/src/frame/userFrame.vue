<template>
	<div>
        <div class="logoutDiv">
            <button class="btn btn-danger logoutbtn" @click.passive="logout">注销</button>
        </div>
        <div class="userDiv nav">
                <router-link class="routerlink" active-class="routerActive" exact to="/user/">HOME</router-link>
                <router-link class="routerlink" active-class="routerActive" exact to="/user/surveyCreater">START</router-link>
                <router-link class="routerlink" active-class="routerActive" exact :to="'/user/result/'+pageid">RESULT</router-link>
                <router-link class="routerlink" active-class="routerActive" exact :to="'/survey/'+pageid">PAGE</router-link>
        </div>

		<router-view></router-view>
	</div>
</template>

<script type="text/javascript">
    export default{
        data(){
            return{
                pageid:""
            }
        },
        created(){


        },
        mounted(){
            let me = this;
            if(!me.state.islogin){
                me.$axios.get('tokenCheck',null,function(r){
                    me.state.login(r.name,false,r.id);
                    me.pageid=me.$forCrypto.forCrypto(me.state.identify.email);
                })
            }else{
                if(me.state.manager){
                    me.$router.push({path:'/manager/'})
                }
            }
            me.pageid=me.$forCrypto.forCrypto(me.state.identify.email);
        },
        methods:{
            logout:function(){
                alert("注销成功");
                this.state.logout();
                localStorage.removeItem('token');
                this.$router.push({path:'/'})
            }
        }
    }
</script>

<style type="text/css" src="@/style/uFrame.css"></style>