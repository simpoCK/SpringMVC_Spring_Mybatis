 $(document).ready(function(){
	listInit(); //列表信息初始化
 });
 
 //列表信息初始化
 function listInit(){
	 $.ajax({
		 type:"POST",
		 url:"userAction/listUserVo.html",
		 success:function(data){
			 $(".head").after(data);
		 }	 
	 });
 }
 
 //删除用户信息
 function removeUserVo(userId){
	 var url="userAction/removeUserVo.html?userId="+userId;
	 $.ajax({
		 url:url,
		 success:function(data){
			 
		 }	 
	 });
 }
 
 //修改用户信息
 function toFormPage(userId){
	 var url="userAction/toFormPage.html?userId="+userId;
	 $.ajax({
		 url:url	 
	 });
 }
 
 
 
 
 
 