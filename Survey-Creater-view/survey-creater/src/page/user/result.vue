<template>
  <div>
  		<h1>{{data}}</h1>
  </div>
</template>

<script type="text/javascript">
	export default{
		data(){
			return{
				data:''
			}
		},
		created(){
            this.$axios.post('resultGet')
		},
		methods:{
            
		},
	}
</script>