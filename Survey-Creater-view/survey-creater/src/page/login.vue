<template>
  <div>
    <div class="login">
    <myHeader/>  
    <form class="theform" id="demo_11" action="#" method="post" autocomplete="off">
        <div class="form-group">
            <input name="username" class="form-control" placeholder="email" type="email">
        </div>
        <div class="form-group">
            <input name="userpwd" @change="changePw" v-model="password" class="form-control" placeholder="密码" type="password" maxlength="8">

        </div>
        <p style="color:red" v-show="pflag">{{message}}</p>
        <button class="btn btn-success" @click="" type="submit">{{login}}</button>
        &nbsp;&nbsp;
        <button type="reset" class="btn btn-danger">{{reset}}</button>
        &nbsp;&nbsp;
        <button type="button" @click="toRegister" class="btn btn-primary">{{register}}</button>
    </form>
    </div>
    <myFooter/>
  </div>
</template>

<script>
import myHeader from '../components/header.vue'
import myFooter from '../components/footer.vue'
export default {
  components: { myHeader, myFooter },
  data() {
    return {
       password:"",
       login:"登陆",
       reset:"重置",
       register:"注册",
       message:"密码为3-8位字母或数字",
       pflag:false
    }
  },
  created() {

  },
  methods: {
      changePw:function(){
        var reg=/[a-zA-Z0-9]{3,8}$/;
        if(!reg.test(this.password)){
          this.pflag=true;
          return;
        }
        this.pflag=false;
      },
      toRegister:function(){
        this.$router.push({path:'/register'});
      }
  }
}
</script>

<style type="text/css" src="@/style/login.css"></style>