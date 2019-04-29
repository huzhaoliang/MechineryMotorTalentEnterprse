var myApp = angular.module('myApp', []);
myApp.controller('loginCtrl', function($scope, $http) {
	$scope.submit = function(){
		var name = $scope.username;
		if(name == null){
			alert("请填写用户名！");
			return;
		}
		var password = $scope.password;
		if(password == null){
			alert("请填写密码！");
			return;
		}
		$http({
		    method: 'POST',
		    url: '/manage/login',
		    data: {username: name, password:password}, 
	        headers: {'Content-Type':'application/x-www-form-urlencoded'} 
		}).then(function successCallback(res) {
			var status = res.data;
	        console.log("status = " + status);
	        if (status == false){
	        	alert("请输入正确的用户名和密码！");
	        	return;
	        }else{
	        	alert("ddd")
	        }
        	}, function errorCallback(res) {
        		alert("登录失败，请稍后再试！");
        		return;
		});    
	}
});