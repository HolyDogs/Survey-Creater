<template>
	<div class="mainDiv" v-bind:style="{height:theHeight}">
		<div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div style="text-align: center;" class="jumbotron">
                        <h1>
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
                name:this.state.identify.name,
                theHeight:window.innerHeight+'px'
            }
        },
        created:function(){
            var me = this;
/*            if(!me.state.islogin){
                me.$router.push({path:"/"});
            }*/
        }
    }
</script>

<style type="text/css">
    .toDown{
        width: 0;
        margin-top: 30px;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: -50px;
        color: red;
        border: 100px solid transparent;
        border-top: 100px solid green;
        box-shadow: 0 0px 0 -4px;
    }

.mainDiv{
    height: 100%;
    background-color: #DFDBE5;
    background-image: url("data:image/svg+xml,%3Csvg width='60' height='60' viewBox='0 0 60 60' xmlns='http://www.w3.org/2000/svg'%3E%3Cg fill='none' fill-rule='evenodd'%3E%3Cg fill='%2354b2b2' fill-opacity='0.52'%3E%3Cpath d='M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E");
}

.row{
        
}

</style>