<template>
	<div id="surveyContainer">
 
<!-- <link href="https://surveyjs.azureedge.net/1.0.66/survey.css" type="text/css" rel="stylesheet" /> -->
		<survey :survey="survey"></survey>
	</div>
</template>

<script type="text/javascript">
import * as Survey from 'survey-vue'
import 'bootstrap/dist/css/bootstrap.css';

Survey.Survey.cssType = "bootstrap";

var surveyJSON = {"pages":[{"name":"page1","elements":[{"type":"comment","name":"question1","title":"aaa"},{"type":"dropdown","name":"question2","choices":["item1","item2","item3"]}]},{"name":"page2","elements":[{"type":"radiogroup","name":"question3","choices":["item1","item2","item3"]}]},{"name":"page3","elements":[{"type":"matrix","name":"question4","columns":["Column 1","Column 2","Column 3"],"rows":["Row 1","Row 2"]}]}],"maxTimeToFinishPage":10,"showTimerPanel":"top"}

var mysurvey = new Survey.Model(surveyJSON);


export default{
    data(){
    	return{
    		survey:mysurvey
    	}
    },
    methods:{
          sendDataToServer:function(survey){
    	    alert("The results are:" + JSON.stringify(survey.data));
        }
    },
    created(){
        mysurvey.onComplete.add(this.sendDataToServer);
    }

}
</script>