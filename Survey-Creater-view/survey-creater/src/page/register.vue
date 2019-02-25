<template>
  <div class="registerdiv" v-bind:style="{height: theight}">
    <form class="register-form" id="demo_10" autocomplete="off">
        <div class="form-group">
            <input v-model="rform.email" class="form-control" placeholder="email" type="email">
        </div>
        <div class="form-group">
            <input v-model="rform.name" class="form-control" placeholder="name" type="text">
        </div>
        <div class="form-group">
            <input v-model="rform.password" @change="changePw" class="form-control" placeholder="密码" type="password" minlength="3" maxlength="8">
        </div>
        <div class="form-group" style="display: flex;">
        	<button class="btn btn-success sendbtn"  :class="[sflag?'disabled':'']" :disabled="sflag" @click="sendcode" type="button">{{send}}</button>
            <input v-model="rform.checkcode" class="form-control" placeholder="xxxxxx" type="text" minlength="6" maxlength="6">
        </div>
        <p style="color:red" v-show="pflag">{{message}}</p>
        <button class="btn btn-success" @click.passive="registerAction" type="submit">{{register}}</button>
        &nbsp;&nbsp;
        <button type="reset" class="btn btn-danger">{{reset}}</button>
        &nbsp;&nbsp;
        <router-link class="btn-info" to="/">{{loginmessage}}</router-link>
    </form>
    <myFooter/>
    
  </div>
</template>

<script>
import myFooter from '../components/footer.vue'
import qs from 'qs'
export default {
  components: { myFooter },
  data() {
    return {
       send:"发送验证码",
       register:"注册",
       reset:"重置",
       message:"密码为3-8位字母或数字",
       loginmessage:"已有账号?登陆",
       pflag:false,
       sflag:false,
       rform:{
         email:"",
         name:"",
         password:"",
         checkcode:"",
       },
       theight:''
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
        if(!reg.test(this.rform.password)){
          this.pflag=true;
          return;
        }
        this.pflag=false;
      },
      sendcode:function(){
      	var num=60;
      	var me=this;
        me.$axios.get('sendcode',{email:me.rform.email});
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
      },
      registerAction:function(){
        let me = this;
        let param=qs.stringify(this.rform);
        this.$axios.post('register',param,function(r){
          if(!r.registed){
            alert(r.message);
          }else{
            alert("注册成功");
          }
        })
      }
  }
}
</script>

<style type="text/css" src="@/style/register.css"></style>
