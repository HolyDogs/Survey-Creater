<template>
    <div class="maxDiv">
        <BUTTON v-if="!startFlag" @click="startClick" class="btn btn-info btn-start">START</BUTTON>
        <div class="analyzeDiv">
            <div class="chart" id="myChart" :style="{width: '500px', height: '500px'}"></div>
        </div>
    </div>
</template>

<script type="text/javascript">

// 引入基本模板
let echarts = require('echarts/lib/echarts')
// 引入柱状图组件
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
      num:0
    }
  },
  mounted() {

  },
  created() {
    let me = this;
    this.$axios.get('analyze',{pageId:me.$route.query.pageid},function(r){
        me.qlist = r.qlist;
    })
  },
  methods: {
    drawLine() {
        let me = this;
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        title: { text: 'ECharts 入门示例' },
        tooltip: {},
        xAxis: {
          data: me.qlist
        },
        yAxis: {},
        series: [{
          name: '销量',
          type: 'bar',
          data: [5, 20, 36, 10, 10, 20]
        }]
      });
    },
    startClick() {
        this.startFlag = !this.startFlag;
        this.$axios.get('analyzeQuestion',{pageId:this.$route.query.pageid,question:this.qlist[this.num]},function(r){
            
        });
        this.drawLine();
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
</style>