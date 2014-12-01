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
			//event.preventDefault();
    		//event.stopPropagation();
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
		//alert(document.getElementById("p2").style.backgroundColor == "green");

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
	}

	$scope.newUser = true;

	$scope.switch = function(){
		$scope.newUser = false;
	}

 };	
