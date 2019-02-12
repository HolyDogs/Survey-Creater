<template>
  <div class="registerdiv">
    <form class="register-form" id="demo_10" action="#" method="post" autocomplete="off">
        <div class="form-group">
            <input name="email" class="form-control" placeholder="email" type="email">
        </div>
        <div class="form-group">
            <input name="username" class="form-control" placeholder="name" type="text">
        </div>
        <div class="form-group">
            <input name="userpwd" @change="changePw" v-model="password" class="form-control" placeholder="密码" type="password" minlength="3" maxlength="8">
        </div>
        <div class="form-group" style="display: flex;">
        	<button class="btn btn-success sendbtn"  :class="[sflag?'disabled':'']" :disabled="sflag" @click="sendcode" type="button">{{send}}</button>
            <input name="checkcode" class="form-control" placeholder="xxxxxx" type="text" minlength="6" maxlength="6">
        </div>
        <p style="color:red" v-show="pflag">{{message}}</p>
        <button class="btn btn-success" @click="" type="submit">{{register}}</button>
        &nbsp;&nbsp;
        <button type="reset" class="btn btn-danger">{{reset}}</button>
        &nbsp;&nbsp;
        <router-link class="btn-info" to="/">已有账号?登陆</router-link>
    </form>
    <myFooter/>
    
  </div>
</template>

<script>
import myFooter from '../components/footer.vue'
export default {
  components: { myFooter },
  data() {
    return {
       password:"",
       send:"发送验证码",
       register:"注册",
       reset:"重置",
       message:"密码为3-8位字母或数字",
       pflag:false,
       sflag:false
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
      sendcode:function(){
      	var num=60;
      	var me=this;
      	this.sflag=true;
      	var timer=setInterval(function(){
      		num--;
      		me.send=num+'秒后刷新';
            if(num == 0){
               me.sflag=false;
               me.send='发送验证码';
               clearInterval(timer);
               timer=null;
            }
      	},1000);
      }
  }
}
</script>

<style type="text/css" src="@/style/register.css"></style>
