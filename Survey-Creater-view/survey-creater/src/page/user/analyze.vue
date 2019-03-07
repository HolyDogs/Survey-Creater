<template>
    <div class="maxDiv">
        <BUTTON v-if="!startFlag" @click="startClick" class="btn btn-info btn-start">START</BUTTON>
        <button v-if="startFlag" :disabled="leftFlag" @click="forLeft" class="arrow arrow-left btn-default btn"></button>
        <button v-if="startFlag" :disabled="rightFlag" @click="forRight" class="arrow arrow-right btn-default btn"></button>
        <div class="analyzeDiv">
            <div class="chart" id="myChart" v-show="!bar" :style="{width: '500px', height: '500px'}"></div>
            <div class="chart" id="myChartB" v-show="bar" :style="{width: '500px', height: '500px'}"></div>
        </div>
    </div>
</template>

<script type="text/javascript">

// 引入基本模板
let echarts = require('echarts/lib/echarts')
// 引入柱状图组件
require('echarts/lib/chart/pie')
require('echarts/lib/chart/bar')
// 引入提示框和title组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')

export default {
  name: 'hello',
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
      bar:true
    }
  },
  mounted() {
      this.myChart = echarts.init(document.getElementById('myChart'));
      this.myChartB = echarts.init(document.getElementById('myChartB'));
  },
  created() {
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
    drawpie() {
        let me = this;

        me.bar = false;
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
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
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
                            shadowColor: 'rgba(0, 250, 150, 0.5)'
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
            }else{
                me.items = r.data;
                me.drawpie();
            }
            me.myChart.hideLoading();
        });
    },
    startClick() {
        this.myChart.showLoading();
        this.startFlag = !this.startFlag;
        this.getAnalyze();
    },
    forLeft:function(){
        this.myChart.showLoading();
        this.num = this.num - 1;
        this.rightFlag = false;
        if(this.num <= 0){
            this.leftFlag = true;
        }
        this.getAnalyze();
    },
    forRight:function(){
        this.myChart.showLoading();
        this.num = this.num + 1;
        this.leftFlag = false;
        if(this.num >= (this.size-1)){
            this.rightFlag = true;
        }
        this.getAnalyze();
    }
  }
}
</script>

<style type="text/css">
    .maxDiv {
        text-align: center;
    }

    .btn-start {
        font-size: 200px;
        margin-top: 250px;
    }

    .analyzeDiv {
        margin-top: 100px;
        margin-bottom: 0px;
        text-align: center;
    }

    .chart{
        left: 50%;
        top: 50%;
        transform: translate(-50%,-0%);
    }

    .arrow{
        position: absolute; 
        top: 50%;
        transform: translate(0,-50%);

    }

    .arrow-left{
        border: 80px solid transparent;
        border-right: 100px solid #bb3d6f;
        left: -0px;
    }

    .arrow-right{
        right: -0px;
        border: 80px solid transparent;
        border-left: 100px solid #bb3d6f;
    }
</style>