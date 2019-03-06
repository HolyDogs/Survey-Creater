<template>
    <div>
        <div id="surveyEditorContainer"></div>
    </div>
</template>

<script>
import * as SurveyEditor from 'surveyjs-editor'
import 'surveyjs-editor/surveyeditor.css';

import * as SurveyKo from "survey-knockout";
import * as widgets from "surveyjs-widgets";
import qs from 'qs';

import "inputmask/dist/inputmask/phone-codes/phone.js";

widgets.icheck(SurveyKo);
widgets.select2(SurveyKo);
widgets.inputmask(SurveyKo);
widgets.jquerybarrating(SurveyKo);
widgets.jqueryuidatepicker(SurveyKo);
/*widgets.nouislider(SurveyKo);*/
widgets.select2tagbox(SurveyKo);
/*widgets.signaturepad(SurveyKo);*/
widgets.sortablejs(SurveyKo);
widgets.ckeditor(SurveyKo);
widgets.autocomplete(SurveyKo);
/*widgets.bootstrapslider(SurveyKo);*/

export default {
  name: 'survey-editor',
  data () {
    return {
    }
  },
  mounted () {
    let me = this;
    let editorOptions = { showEmbededSurveyTab: false,showJSONEditorTab:true };
    let pageid = me.$forCrypto.forCrypto(me.state.identify.email);
    this.editor = new SurveyEditor.SurveyEditor('surveyEditorContainer', editorOptions);
    this.editor.saveSurveyFunc = function() {
      let params = JSON.stringify(this.text);
          me.$axios.post('user/createSurvey',qs.stringify({"params":params,"pageid":pageid}),function(r){
            if(r.rSuccess){
              alert("创建成功，即将跳转到实现页面，复制该地址分享给您想要参与调查的人群");
              me.$router.push({path:'/survey/'+pageid});
            }else{
              alert("您已有其他调查项目，请结束后再创建新的调查或者重新登陆再试");
            }
      })
    };                                  
  }
}
</script>