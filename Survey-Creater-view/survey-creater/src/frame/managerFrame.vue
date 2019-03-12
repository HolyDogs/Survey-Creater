<template>
    <div class="maxDiv" v-bind:style="{height: theight}">
        <div class="col-md-12 column">
            <div class="row clearfix">
                <div class="col-md-1 column">
                    <div class="manageDiv nav">
                            <router-link class="routerlinkM" active-class="routerActive" exact to="/manager/">🏠用户管理</router-link>
                            <router-link class="routerlinkM" active-class="routerActive" exact to="/manager/timeLine">🕑使用记录</router-link>
                            <button class="btn btn-danger routerlinkM" @click.passive="logout">💨退出登陆</button>
                    </div>
                </div>

                <div class="col-md-11 column">
                    <router-view></router-view>
                </div>
            </div>
        </div>
    </div>
</template>

<script type="text/javascript">
    
    export default{
        data() {
            return{
                theight:''
            }
        },
        created(){
            this.theight=window.innerHeight+'px';
        },
        mounted(){
            let me = this;
            if(!me.state.islogin){
                me.$axios.get('tokenCheck',null,function(r){
                    me.state.login(r.name,r.manager,r.id);
                })
            }else{
                if(me.state.manager){
                    me.$router.push({path:'/manager/'})
                }else{
                    me.$router.push({path:'/user/'})
                }
            }
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

<style type="text/css" src="@/style/mFrame.css"></style>