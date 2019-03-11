<template>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="row clearfix">
                    <div class="col-md-4 column column-me">
                        <div class="row clearfix">
                            <div class="col-md-3 column">
                                <LABEL class="label label-info">id:</LABEL>
                            </div>
                            <div class="col-md-6 column">
                                <input v-model="selectform.id"  class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 column column-me">
                        <div class="row clearfix">
                            <div class="col-md-3 column">
                                <LABEL class="label label-info">name:</LABEL>
                            </div>
                            <div class="col-md-6 column">
                                <input v-model="selectform.name" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 column column-me">
                        <div class="row clearfix">
                            <div class="col-md-3 column">
                                <LABEL class="label label-info">权限:</LABEL>
                            </div>
                            <div class="col-md-6 column">
                                <input v-model="selectform.identify" class="form-control"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-4 column column-me">
                            <div class="row clearfix">
                            <div class="col-md-3 column">
                                <LABEL class="label label-info">邮箱:</LABEL>
                            </div>
                            <div class="col-md-6 column">
                                <input type="email" v-model="selectform.email" class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 column column-me">
                        <button @click="selectPage" class="btn btn-info btn-block">查询</button>
                    </div>
                </div>

                <br/></br><br/></br>

                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>
                                id
                            </th>
                            <th>
                                name
                            </th>
                            <th>
                                identify
                            </th>
                            <th>
                                email
                            </th>
                            <th>
                                操作
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="user in userData">
                            <td>
                                <B>{{user.id}}</B>
                            </td>
                            <td>
                                <B>{{user.name}}</B>
                            </td>
                            <td>
                                <B>{{user.identify}}</B>
                            </td>
                            <td>
                                <B>{{user.email}}</B>
                            </td>
                            <td>
                                <button class="btn btn-warning" @click="clickDelete(user.id)">删除</button>
                            </td>
                        </tr>
                    </tbody>
                    
                    <B>共有{{size}}页 、 {{total}}条数据 、 当前页数{{selectform.current}}</B>

                    <ul class="pagination pagination-lg">
                        <li>
                             <Button class="btn btn-info btn-inverse" :disabled="!hasPre"  @click="clickPage($event)">Prev</Button>
                        </li>
                        <li v-for="n in size">
                             <Button class="btn btn-default btn-inverse"  @click="clickPage($event)">{{n}}</Button>
                        </li>
                        <li>
                             <Button class="btn btn-info btn-inverse" :disabled="!hasNext"  @click="clickPage($event)">Next</Button>
                        </li>
                    </ul>
                </table>
            </div>
        </div>
    </div>
</template>

<script type="text/javascript">
export default {
    data() {
        return {
            userData:[],
            size:0,
            total:0,
            hasPre:false,
            hasNext:false,
            selectform:{
                id:'',
                name:'',
                identify:'',
                email:'',
                current:1,
            }
        }
    },
    mounted(){
        this.getUserData();
    },
    methods:{
        getUserData:function(){
            let me = this;
            this.$axios.get('manager/selectPage',me.selectform,function(r){
                me.userData = r.page.records;
                me.size = r.page.pages;
                me.total = r.page.total;
                me.selectform.current = r.page.current;
                if (me.selectform.current<me.size) {
                    me.hasNext=true;
                }else{
                    me.hasNext=false;
                }
                if (me.selectform.current>1) {
                    me.hasPre=true;
                }else{
                    me.hasPre=false;
                }
            })
        },
        clickPage:function(btn){
            let me = this;
            if(btn.target.innerText == "Prev"){
                me.selectform.current = me.selectform.current-1;
                me.getUserData();
                return;
            }
            if(btn.target.innerText == "Next"){
                me.selectform.current = me.selectform.current+1;
                me.getUserData();
                return;
            }
            me.getUserData()
        },
        clickDelete:function(theid){
            let me = this;
            this.$axios.delete('manager/deleteUser',{userId:theid},function(r){
                me.getUserData();
            })
        },
        selectPage:function(){
            this.getUserData();
        }
    }
 
}
</script>

<style type="text/css">
    .column-me {
        margin-top: 50px;
    }

    .managerDiv {
        background-color: #171618;
background-image: url("data:image/svg+xml,%3Csvg width='24' height='40' viewBox='0 0 24 40' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M0 40c5.523 0 10-4.477 10-10V0C4.477 0 0 4.477 0 10v30zm22 0c-5.523 0-10-4.477-10-10V0c5.523 0 10 4.477 10 10v30z' fill='%230ce8f2' fill-opacity='0.87' fill-rule='evenodd'/%3E%3C/svg%3E");
    }
</style>