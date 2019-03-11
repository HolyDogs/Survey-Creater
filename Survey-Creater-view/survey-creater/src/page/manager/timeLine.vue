<template>
  <hzqing-vue-timeline class="timeline" timelineColor="#3a8c94" timeContentColor="#fff"
   :dataList="data"></hzqing-vue-timeline>
</template>

<script type="text/javascript">
    export default{
        data() {
            return{
                data: []
            }
        },
        mounted(){
          let me = this;
          this.$axios.get('manager/getHistory',null,function(r){
              me.data = r.dataList;
          })
        }
    }
</script>

<style type="text/css">

</style>