<template>
  <div id="surveyContainer">
    <survey :survey="survey"></survey>
  </div>
</template>

<script type="text/javascript">
import * as Survey from 'survey-vue'
import 'bootstrap/dist/css/bootstrap.css'
import qs from 'qs'
export default{
    data(){
      return{
        survey:'',
        surveyJSON:''
      }
    },
    methods:{
        sendDataToServer:function(survey){
          let me = this;
          let params = JSON.stringify(survey.data);
          this.$axios.post('surveys/commit',qs.stringify({"params":params,"tableId":me.$route.params.id}),function(){

          });
        },
        doInit(){
          let me = this;
          this.$axios.get('surveys/page',{pageId:me.$route.params.id},function(r){
            me.surveyJSON=r.data;
            let mysurvey = new Survey.Model(me.surveyJSON);
            mysurvey.onComplete.add(me.sendDataToServer);
            me.survey=mysurvey;
          });
        }
    },
    created(){
/*        Survey.Survey.cssType = "bootstrap";*/
        this.doInit();
        /*let surveyJSON = {"pages":[{"name":"page1","elements":[{"type":"comment","name":"question1","title":"aaa"},{"type":"dropdown","name":"question2","choices":["item1","item2","item3"]}]},{"name":"page2","elements":[{"type":"radiogroup","name":"question3","choices":["item1","item2","item3"]}]},{"name":"page3","elements":[{"type":"matrix","name":"question4","columns":["Column 1","Column 2","Column 3"],"rows":["Row 1","Row 2"]}]}],"maxTimeToFinishPage":10,"showTimerPanel":"top"};*/

        let mysurvey = new Survey.Model(this.surveyJSON);
        mysurvey.onComplete.add(this.sendDataToServer);
        this.survey=mysurvey;

    }
}
</script>

<style type="text/css" src="../style/survey.css"></style>