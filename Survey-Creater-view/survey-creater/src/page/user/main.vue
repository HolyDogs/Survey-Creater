<template>
	<div class="mainDiv" v-bind:style="{height:theHeight}">
		<div class="container">
            <div style="text-align: center;padding-top: 20px">
                <h1 v-if=name>
                    您好,{{name}},欢迎使用本系统
                </h1>
            </div>
        </div>

        <agile class="v-slide" :arrows="false" :dots="false" :autoplay="true" :autoplaySpeed="1000" :fade="false">
            <div class="slide slide--1"><h3>拖拽自动生成问卷</h3></div>
            <div class="slide slide--2"><h3>灵活强大的控件</h3></div>
            <div class="slide slide--3"><h3>直观智能的分析</h3></div>
            <div class="slide slide--4"><h3>针对性突出的图表</h3></div>
            <div class="slide slide--5"><h3>可供多角度分析的3维立体图</h3></div>
            <div class="slide slide--6"><h3>还等什么，马上点击右上方START开始吧！</h3></div>
        </agile>

        <h2 style="text-align: center;color: yellow;">相关技术</h2>
        <div class="container">
            <div class="row clearfix myDiv1">
                <div class="col-md-4 column">
                    <h2>
                        SurveyJS
                    </h2>
                    <p>
                        SurveyJS 是一款足够炫酷的 JS库，顾名思义，它是一款帮你创造调查问卷的JS库，其中包含有各种各样的调查问卷，并且它还被移植到了多个流行的JS框架中，包括 AngularJS，VueJS 和 React
                    </p>
                    <p>
                         <a class="btn btn-primary btn-sm" href="https://surveyjs.io/survey/Builder/">View details »</a>
                    </p>
                </div>
                <div class="col-md-4 column">
                    <h2>
                        ECharts
                    </h2>
                    <p>
                        ECharts，一个使用 JavaScript 实现的开源可视化库，可以流畅的运行在 PC 和移动设备上，兼容当前绝大部分浏览器（IE8/9/10/11，Chrome，Firefox，Safari等），底层依赖轻量级的矢量图形库 ZRender，提供直观，交互丰富，可高度个性化定制的数据可视化图表。
                    </p>
                    <p>
                         <a class="btn btn-primary btn-sm" href="https://www.echartsjs.com/feature.html">View details »</a>
                    </p>
                </div>
                <div class="col-md-4 column">
                    <h2>
                        更多
                    </h2>
                    <p>
                        除此之外，项目还使用了v-charts、VueAgile、Fullscreen等插件工具，提供更好的使用体验
                    </p>
                    <p>
                         <a class="btn btn-primary btn-sm" href="https://codesandbox.io/s/z69myovqzx">View details »</a>
                    </p>
                </div>
            </div>
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
            me.$axios.get('tokenCheck',null,function(r){
                me.name = r.name
            })
        }
    }
</script>

<style type="text/css" src="@/style/user/main.css"></style>