<template>
    <div>
        <div class="chart" id="myChart" :style="{width: '800px', height: '800px'}"></div>
    </div>
</template>


<script type="text/javascript">

let echarts = require('echarts/lib/echarts')
// 引入柱状图组件
require('echarts/lib/chart/pie')
require('echarts/lib/chart/bar')
require('echarts-gl')

require('echarts/lib/component/visualMap')
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
require("echarts/lib/component/dataset")



export default{
    data(){
        return{
            myChart:''
        }
    },
    created(){

    },
    mounted(){
            this.myChart = echarts.init(document.getElementById('myChart'));
            this.myChart.setOption({
            title:{ 
                text: 'hhhhhh',
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
            tooltip:{},
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
                max: 100,
                dimension: 'number',
                inRange: {
                    color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
                }
            },
            dataset: {
                dimensions: ['columns', 'choices', 'number'],
                source: [
                    {columns:"dffsf",choices:"sadfasdf",number:12},
                    {columns:"dfcxfsf",choices:"zzzz",number:123},   
                    {columns:"dffsf",choices:"dgg",number:15},       
                ]
            },
            series: [
                {
                    type: 'bar3D',
                    shading: 'lambert',
                    symbolSize: 2.5,
                    label: {
                        show: false,
                        textStyle: {
                            fontSize: 16,
                            borderWidth: 1
                        }
                    },
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
                        tooltip: [0,1,2,3]
                    }
                }
            ]
        });
    }
}
</script>