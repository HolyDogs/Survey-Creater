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
widgets.nouislider(SurveyKo);
widgets.select2tagbox(SurveyKo);
widgets.signaturepad(SurveyKo);
widgets.sortablejs(SurveyKo);
widgets.ckeditor(SurveyKo);
widgets.autocomplete(SurveyKo);
widgets.bootstrapslider(SurveyKo);

export default {
  name: 'survey-editor',
  data () {
    return {
    }
  },
  mounted () {
    let me = this;
    let editorOptions = { showEmbededSurveyTab: false,showJSONEditorTab:false };
    this.editor = new SurveyEditor.SurveyEditor('surveyEditorContainer', editorOptions);
    this.editor.saveSurveyFunc = function() {
      let params = JSON.stringify(this.text);
          me.$axios.post('user/createSurvey',qs.stringify({params}),function(r){
      })
    };                                  
  }
}
</script>