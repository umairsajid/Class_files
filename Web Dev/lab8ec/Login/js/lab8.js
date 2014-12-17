var MyCtrl = function($scope, $http){
 	
	$scope.formSubmit = function(){
	 	var username = $scope.username;
		var password = $scope.password1;
		var age = Math.abs(new Date() - new Date($scope.birthdate))/(365*24*60*60*1000);
		
		if (age < 18 || age > 120){
			alert("Invalid age!");
			return;
		}

		if (!password.match($scope.myRegex) || 
			$scope.password1 != $scope.password2){
			return;
		}
		
	};

	$scope.myRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[\W])([a-zA-Z0-9\W]{8,})$/;

	$scope.isCorrect = function(){

		var password = $scope.password1;
		var x = document.getElementById("p2");

		if (password == undefined)
			return false;
		else if (!password.match($scope.myRegex)){
			$scope.password2 = "";
			x.style.backgroundColor = "white";
		}
		else{
			if($scope.password2 == "")
				x.style.backgroundColor = "red";
			return true; 
		}
	};

	$scope.cheat = function(){
		
		if (document.getElementById("p2").style.backgroundColor == "green" && $scope.password1 != $scope.password2){
			document.getElementById("p2").style.backgroundColor = "white";
			$scope.password2 = "";
		}
	};

	$scope.colors = function(){
		var x = document.getElementById("p2");

		if ($scope.password1 != $scope.password2 && $scope.isCorrect())
			x.style.backgroundColor = "red";
		if ($scope.password1 == $scope.password2 && $scope.isCorrect())
			x.style.backgroundColor = "green";
	};

	$scope.newUser = true;

	$scope.switch = function(){
		$scope.newUser = false;
	};

	$scope.setName = function(name){
		$scope.username = name;
	};

	$scope.swap = function(){
		var x = document.getElementById("bd").value;
		
		$scope.bdregex = /^([0-9]{4})-([0-9]{2})-([0-9]{2})$/;
		
		if (x.match($scope.bdregex) != null){
			$http.post('/students/kohels65/Labs/Login/switch.php', {'birthdate': x, 'username': $scope.username})
				.success(function(data, status, headers, config) {
					console.log(data);
	    			if (data === "updated")
	    				alert("Birthdate successfully changed!");	    
	  			});
		}	  	
	};
 };	
