<template>
  <div>
    <div class="login" v-bind:style="{height: theight}">
    <myHeader/>  
    <form class="theform" autocomplete="off">
        <div class="form-group">
            <input class="form-control" v-model="theform.email" placeholder="email" type="email">
        </div>
        <div class="form-group">
            <input @change="changePw" v-model="theform.password" class="form-control" placeholder="密码" type="password" maxlength="8">

        </div>
        <p style="color:red" v-show="pflag">{{message}}</p>
        <button class="btn btn-success" @click.passive="login" type="submit">{{loginmessage}}</button>
        &nbsp;&nbsp;
        <button type="reset" class="btn btn-danger">{{reset}}</button>
        &nbsp;&nbsp;
        <button type="button" @click="toRegister" class="btn btn-primary">{{register}}</button>
    </form>
        <myFooter/>
    </div>

  </div>
</template>

<script>
import myHeader from '../components/header.vue'
import myFooter from '../components/footer.vue'
import qs from 'qs'
export default {
  components: { myHeader, myFooter },
  data() {
    return {
       loginmessage:"登陆",
       reset:"重置",
       register:"注册",
       message:"密码为3-8位字母或数字",
       theform:{
          email:"",
          password:"",
       },
       pflag:false,
       theight:'',
    }
  },
  created() {
     this.theight=window.innerHeight+'px';
  },
  mounted(){

  },
  methods: {
      changePw:function(){
        var reg=/[a-zA-Z0-9]{3,8}$/;
        if(!reg.test(this.theform.password)){
          this.pflag=true;
          return;
        }
        this.pflag=false;
      },
      toRegister:function(){
        this.$router.push({path:'/register'});
      },
      login:function(){
          let me=this;
          let params=qs.stringify(this.theform);
          this.$axios.post('login',params,function(r){
            if(!r.islogin){
              alert("邮箱或密码错误");
              me.$router.push({path:'/'});
            }else{
              me.state.login(r.name,r.manager,r.email);
              if(r.manager){
                me.$router.push({path:'/manager/'});
              }else{
                me.$router.push({path:'/user/'});
              }
            }
          });
      } 
  }
}
</script>

<style type="text/css" src="@/style/login.css"></style>