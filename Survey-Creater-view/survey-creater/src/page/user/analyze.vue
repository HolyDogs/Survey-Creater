<style type="text/css" src="@/style/user/analyze.css"></style>

<template>
    <div class="maxDiv"  v-bind:style="{height: theight}">
        <BUTTON v-if="!startFlag" @click="startClick" class="btn btn-info btn-start">START</BUTTON>
        <button v-show="startFlag" class="arrow arrow-left btn" :disabled="leftFlag" @click="forLeft" ></button>
        <button v-show="startFlag" class="arrow arrow-right btn" :disabled="rightFlag" @click="forRight"></button>
        <div class="analyzeDiv">
            <div class="chart" id="myChart" v-show="pie" :style="{width: '500px', height: '500px'}"></div>
            <div class="chart" id="myChartB" v-show="bar" :style="{width: '500px', height: '500px'}"></div>
            <div class="chart" id="myChartC" v-show="thrd" :style="{width: '700px', height: '700px'}"></div>
            <div class="vchart" v-if="vchart"><h4>{{title}}</h4><ve-line class="vchartC" :loading="loading" :extend="extend" :data="vchartData"></ve-line></div>

            <div class="vchart" v-if="vmap"><h4>{{title}}</h4><ve-map :loading="loading" :data="vchartData"></ve-map></div>
        </div>
    </div>
</template>

<script type="text/javascript">

// 引入基本模板
let echarts = require('echarts/lib/echarts')
// 引入柱状图组件
require('echarts/lib/chart/pie')
require('echarts/lib/chart/bar')
require('echarts-gl')

// 组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
require("echarts/lib/component/dataset")

import VeLine from 'v-charts/lib/line'
import VeMap from 'v-charts/lib/map'

export default {
  name: 'hello',
  components:{ VeLine,VeMap },
  data() {
    return {
      startFlag:false,
      qlist:'',
      size:'',
      leftFlag:true,
      rightFlag:false,
      num:0,
      title:'',
      items:'',
      name:'',
      type:'',
      count:'',
      myChart:'',
      myChartB:'',
      myChartC:'',
      vchartData:{},
      vchart:false,
      vmap:false,
      bar:false,
      pie:false,
      thrd:false,
      extend:{
        series: {
            smooth: false
        }
      },
      loading:false,
      theight:''
    }
  },
  mounted() {
      this.myChart = echarts.init(document.getElementById('myChart'));
      this.myChartB = echarts.init(document.getElementById('myChartB'));
      this.myChartC = echarts.init(document.getElementById('myChartC'));
  },
  created() {
    this.theight=window.innerHeight+'px';
    let me = this;
    this.$axios.get('analyze',{pageId:me.$route.query.pageid},function(r){
        me.qlist = r.qlist;
        me.size = r.qlist.length;
        me.leftFlag = true;
        if (me.size == 1) {
            me.rightFlag = true;
        }
    })
  },
  methods: {
    drawVmap() {
        let me = this;
        me.vchartData = {
            columns: me.items,
            rows: me.count
        };
      me.bar = false;
      me.pie = false;
      me.thrd = false;
      me.vchart = false;
      me.vmap = true
    },
    draw3d() {
        let me = this;
        me.vchart = false;
        me.bar = false;
        me.pie = false;
        me.vmap = false;
        me.thrd = true;

            me.myChartC.setOption({
            title:{ 
                text: me.title,
                left:'center',
                top:'10px',
                textStyle:{
                    color:'#ccc',
                    fontStyle:'normal',
                    fontWeight:'bold',
                    fontFamily:'sans-serif',
            　　　　 fontSize:30
                }
            },
            tooltip:{

            },
            grid3D: {},
            xAxis3D: {
                // 因为 x 轴和 y 轴都是类目数据，所以需要设置 type: 'category' 保证正确显示数据。
                type: 'category'
            },
            yAxis3D: {
                type: 'category'
            },
            zAxis3D: {
            },
            visualMap: {
                max: 10,
                dimension: 'number',
                inRange: {
                    color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026'],

                }
            },
            dataset: {
                dimensions: ['columns', 'choices', 'number'],
                source: me.items
            },
            series: [
                {
                    type: 'bar3D',
                    shading: 'lambert',
                    symbolSize: 2.5,
/*                    label: {
                        show: false,
                        textStyle: {
                            fontSize: 16,
                            borderWidth: 1
                        }
                    },*/
                    itemStyle: {
                        opacity: 0.85
                    },
                    emphasis: {
                        label: {
                            textStyle: {
                                fontSize: 20,
                                color: '#900'
                            }
                        },
                        itemStyle: {
                            color: '#900'
                        }
                    },
                    encode: {
                        // 维度的名字默认就是表头的属性名
                        x: 'columns',
                        y: 'choices',
                        z: 'number',
                        tooltip: [0,1,2,3,4]
                    }
                }
            ]
        });
    },
    drawMline() {
      let me = this;
      me.vchartData = {
        columns: me.items,
        rows: me.count
      };
      me.bar = false;
      me.pie = false;
      me.vmap = false;
      me.thrd = false;
      me.vchart = true;
    },
    drawpie() {
        let me = this;
        me.vchart = false;
        me.thrd = false;
        me.vmap = false;
        me.bar = false;
        me.pie = true;
          let option = {
            title:{ 
                text: me.title,
                left:'center',
                top:'5px',
                textStyle:{
                    color:'#ccc',
                    fontStyle:'normal',
                    fontWeight:'bold',
                    fontFamily:'sans-serif',
            　　　　 fontSize:30
                }
            },
            tooltip:{},
            backgroundColor: '#2c343c',
            visualMap: {
                show: false,
                inRange: {
                    colorLightness: [0.5, 1],
                }
            },
            series : [
                {
                    name: '人数',
                    type: me.type,
                    radius: '55%',
                    data: me.items,
                    roseType: 'angle',
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 200, 150, 0.5)'
                        }
                    }
                }
            ]
        };

        me.myChart.setOption(option);

    },
    drawLine() {
        let me = this;
      // 基于准备好的dom，初始化echarts实例

      me.vchart = false;
      me.thrd = false;
      me.vmap = false;
      me.pie = false;
      me.bar = true;
      // 绘制图表
      me.myChartB.setOption({
        title: { text: me.title },
        tooltip: {},
        xAxis: {
          data: me.items
        },
        yAxis: {
        },
        series: [{
          name: me.name,
          type: me.type,
          data: me.count
        }],
      });
    },
    getAnalyze() {
        let me = this;
        this.$axios.get('analyzeQuestion',{pageId:this.$route.query.pageid,question:this.qlist[this.num]},function(r){
            me.title = r.title;
            me.name = r.name;
            me.type = r.type;
            me.count = r.count;
            if(me.type == "bar"){
                me.items = r.items;
                me.drawLine();
            }else if(me.type == "pie"){
                me.items = r.data;
                me.drawpie();
            }else if(me.type == "line"){
                me.loading = true;
                me.count = r.data;
                me.items = r.items;
                me.drawMline();
            }else if(me.type == "3DMap"){
                me.items = r.data;
                me.draw3d();
            }else if(me.type == "tmap"){
                me.loading = true;
                me.count = r.data;
                me.items = r.items;
                me.drawVmap();
            }
            me.loading = false;
            me.myChart.hideLoading();
            me.myChartB.hideLoading();
            me.myChartC.hideLoading();
        });
    },
    startClick() {
        this.myChart.showLoading();
        this.myChartB.showLoading();
        this.myChartC.showLoading();
        this.startFlag = !this.startFlag;
        this.getAnalyze();
    },
    forLeft:function(){
        this.num = this.num - 1;
        this.rightFlag = false;
        if(this.num <= 0){
            this.leftFlag = true;
        }
        this.myChart.showLoading();
        this.myChartB.showLoading();
        this.myChartC.showLoading();
        this.getAnalyze();
    },
    forRight:function(){
        this.num = this.num + 1;
        this.leftFlag = false;
        if(this.num >= (this.size-1)){
            this.rightFlag = true;
        }
        this.myChart.showLoading();
        this.myChartB.showLoading();
        this.myChartC.showLoading();
        this.getAnalyze();
    }
  }
}
</script>

