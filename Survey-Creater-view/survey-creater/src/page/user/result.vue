<template>
  <div class="resultDiv">
  	<div class="tableDiv" v-if="bflag">
                <table class="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th v-for="(vv,head) in headList">
                                {{head}}
                            </th>
                        </tr>
                    </thead>
                   
                    <tbody>
                        <tr v-for="map in data">
                            <td v-for="(value,key) in map">
                                {{value}}
                            </td>
                        </tr>
                    </tbody>
                </table>
                <B>共有{{pages}}页 、 {{total}}条数据 、 当前页数{{current}}</B>

                <ul class="pagination pagination-lg">
                    <li>
                         <Button class="btn btn-info btn-inverse" :disabled="!hasPre"  @click="clickPage($event)">Prev</Button>
                    </li>
                    <li v-for="n in pages">
                         <Button class="btn btn-default btn-inverse"  @click="clickPage($event)">{{n}}</Button>
                    </li>
                    <li>
                         <Button class="btn btn-info btn-inverse" :disabled="!hasNext"  @click="clickPage($event)">Next</Button>
                    </li>
                </ul>
    </div>
    <div class="btnDiv">
        <button v-if="!bflag" @click="selectAll" class="btn btn-block btn-info">查询所有结果</button><br/>
        <button v-if="bflag" @click="analyze" class="btn btn-block btn-warning">可视化分析</button><br/>
        <button v-if="dflag" class="btn btn-block btn-danger" @click="deleteSurvey">删除已有项目</button><br/>
    </div>

  </div>
</template>

<script type="text/javascript">
	export default{
		data(){
			return{
                headList:'',
                data:'',
                bflag:false,
                current:1,
                pages:0,
                total:0,
                hasPre:false,
                hasNext:false,
                dflag:true
			}
		},
		created(){

		},
		methods:{
            selectAll:function(){
                let me = this;
                me.bflag=!me.bflag;
                me.selectPage('resultGet',{pageId:me.$route.params.id,current:me.current});
            },
            clickPage:function(btn){
                let me = this;
                if(btn.target.innerText == "Prev"){
                    me.current = me.current-1;
                    me.selectPage('resultGet',{pageId:me.$route.params.id,current:me.current});
                    return;
                }
                if(btn.target.innerText == "Next"){
                    me.current = me.current+1;
                    me.selectPage('resultGet',{pageId:me.$route.params.id,current:me.current});
                    return;
                }
                me.selectPage('resultGet',{pageId:me.$route.params.id,current:btn.target.innerText})
            },
            deleteSurvey:function(){
                this.dflag=!this.dflag;
                let me = this;
                this.$axios.delete('deleteSurvey',{pageId:me.$route.params.id},function(r){
                    alert(r.data);
                })
            },
            selectPage:function(url,params){
                    let me = this;
                    me.$axios.get(url,params,function(r){
                    if(r.rSuccess){
                        alert("未创建项目");
                    }else{
                        me.current = r.page.current;
                        me.pages = r.page.pages;
                        me.total = r.page.total;
                        me.hasPre = r.page.hasPrevious;
                        me.hasNext = r.page.hasNext;
                        me.headList = r.page.list[0];
                        me.data = r.page.list;
                    }
                })
            },
            analyze:function(){
                let me = this;
                me.$router.replace({path:'/user/analyze',query:{
                    pageid:me.$route.params.id
                }})
            }
		}
	}
</script>

<style type="text/css" src="@/style/user/result.css"></style>