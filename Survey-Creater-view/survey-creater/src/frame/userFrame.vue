<template>
	<div>
        <div class="userDiv nav" style="position: absolute; right: 50px; top: 10px;z-index: 9999">
                <router-link class="routerlink" active-class="routerActive" exact to="/user/">HOME</router-link>
                <router-link class="routerlink" active-class="routerActive" exact to="/user/surveyCreater">START</router-link>
                <router-link class="routerlink" active-class="routerActive" exact :to="'/survey/'+pageid">PAGE</router-link>
                <router-link class="routerlink" active-class="routerActive" exact :to="'/user/result/'+pageid">RESULT</router-link>
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
            const me = this;
            this.pageid=this.$forCrypto.forCrypto(me.state.identify.email);
        }
    }
</script>

<style type="text/css" src="@/style/uFrame.css"></style>