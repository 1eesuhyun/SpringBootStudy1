const foodApp=Vue.createApp({
		data(){
			return{
				address:'마포',
				food_list:[],
				curpage:1,
				endPage:0,
				totalpage:0,
				startPage:0
			}
		},mounted(){
			this.dataRecv()
		},
		methods:{
			async dataRecv(){
				await axios.get('http://localhost/food/find_vue/',{
					params:{
						page:this.curpage,
						address:this.address
					}
				}).then(response=>{
					console.log(response.data)
					this.food_list=response.data.list
					this.curpage=response.data.curpage
					this.totalpage=response.data.totalpage
					this.endPage=response.data.endPage
					this.startPage=response.data.startPage
				})
			},
			find(){
				if(this.address==="")
				{
					this.$refs.addr.focus()
					return
				}
				this.curpage=1
				this.dataRecv()
			},
			range(start,end){
				let arr=[]
				let len=end-start
				for(let i=0;i<=len;i++)
				{
					arr[i]=start
					start++
				}
				return arr
			},
			pageChange(page){
				this.curpage=page
				this.dataRecv()
			}
		}
	})
	foodApp.config.compilerOptions.delimiters=['((','))']
	foodApp.mount(".container")