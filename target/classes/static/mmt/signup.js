"use strict";
//varible
let email = "";
let emailFormat = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
let password = "";
let passwordFormat = /^[\w_-]{6,16}$/;
//const






//function
function doSignUp()
{
	email = $("#Email").val();
	password = $("#Password1").val();
	
	if(email==undefined||email==null||email=="")
	{
		alert("邮箱不能为空");
		return
	}
	
	if(emailFormat.test(email)==false)
	{
		alert("邮箱格式不正确");
		return
	}
	
	if(password==undefined||password==null||password=="")
	{
		alert("密码不能为空");
		return
	}
	
	if($("#Password1").val()!=$("#Password2").val())
	{
		alert("密码与确认密码不一致");
		return
	}
	
	
	alert(signup_url);
	$.ajax({
		  type: 'POST',
		  url: signup_url,
		  data: 
		  {
			  "email":email,
			  "password":password
		  },
		  success: signup_success(),
		  dataType: "json"
		});
	
	
}

function signup_success(res)
{
	alert(res);
}
