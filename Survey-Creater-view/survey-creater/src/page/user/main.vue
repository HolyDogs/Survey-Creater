<template>
	<div class="mainDiv" v-bind:style="{height:theHeight}">
		<div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div style="text-align: center;" class="jumbotron">
                        <h1 v-if=name>
                            欢迎:{{name}},请按照如下的步骤使用该系统
                        </h1>

<!--                         <p>
     <a class="btn btn-primary btn-large" href="#">Learn more</a>
</p> -->
                    </div>
                </div>
            </div>
        </div>
        <div class="container" style="text-align: center;">
            <h1>点击右上方的<router-link to="/user/surveyCreater">START</router-link>开始</h1>
            <p><i>在这里创建您想生成的问卷调查或者在线考试试卷</i></p>
            <div class="toDown"></div>
            <h1>点击<router-link to="xxxx">Test</router-link>查看您已生成的项目</h1>
            <i>这也是您用来给调查人群访问的页面</i>
            <div class="toDown"></div>
            <h1>点击<router-link to="xxxx">RESULT</router-link>查看您项目的结果分析</h1>
            <i>在这里对您项目的结果进行一些可视化分析，或者您也可以直接查看所有结果的具体值</i>
        </div>
    </div>
</template>

<script type="text/javascript">
    export default{
        data(){
            return{
                name:'',
                theHeight:window.innerHeight+'px'
            }
        },
        mounted:function(){
            var me = this;
            this.name = this.state.identify.name;
/*            if(!me.state.islogin){
                me.$router.push({path:"/"});
            }*/
        }
    }
</script>

<style type="text/css" src="@/style/user/main.css"></style>