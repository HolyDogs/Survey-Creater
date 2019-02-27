<template>
  <div>
  		<h1>{{md5str}}</h1>
  </div>
</template>

<script type="text/javascript">
	export default{
		data(){
			return{
				md5str:"" 
			}
		},
		created(){
            this.md5str=this.$forCrypto.forCrypto("null");
		},
		methods:{
            
		},
	}
</script>