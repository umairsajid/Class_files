<!DOCTYPE html>
<html ng-app>
<head
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab 8</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/lab8.css">  
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.27/angular.min.js"></script>
    <script src="js/lab8.js" type="text/javascript"></script>  
</head>
<body>
<?php
        if (isset($_SESSION['errors']) && !empty($_SESSION['errors'])) {
            session_destroy();
        }


      session_start();
?>
    <div class="container">
        <div ng-controller="MyCtrl">
            <div class="row">
                <form ng-show="newUser" method="post" action="lab8.php">
                    <div class="row">
                        <div class="col-lg-6 text-right">
                            <h4>Username</h4>
                        </div>
                        <div class="col-lg-2">
                            <input ng-model="username" class="form-control" name="username">
                        </div>
                        <div class="col-lg-2 col-lg-offset-2">
                            <a href="" ng-click="switch()" >Log In</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 text-right">
                            <h4>Password</h4>
                        </div>
                        <div class="col-lg-2">
                            <input ng-model="password1" ng-keyup="cheat()" name="password1" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 text-right">
                            <h4>Re-enter Password</h4>
                        </div>
                        <div class="col-lg-2">
                            <input id="p2" ng-keyup="colors()" name="password2" ng-model="password2" ng-disabled="!isCorrect()"type="password"  class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 text-right ">
                            <h4>Date of Birth</h4>
                        </div>
                        <div class="col-lg-2" >
                            <input ng-model="birthdate" name="birthdate" class="form-control" placeholder="yyyy/mm/dd">
                        </div>
                    </div>
                    <div class="row">
                        <div class="row col-lg-offset-6">
                            <input ng-mousedown="formSubmit()" class="btn btn-primary" name="submitbtn"type="submit" value="Submit">
                        </div>
                    </div>
                </form>
                <form ng-show="!newUser" method="post" action="lab8.php">
                    <div class="row">
                        <div class="col-lg-6 text-right">
                            <h4>Username</h4>
                        </div>
                        <div class="col-lg-2">
                            <input ng-model="username" class="form-control" name="username">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6 text-right">
                            <h4>Password</h4>
                        </div>
                        <div class="col-lg-2">
                            <input ng-model="password1" ng-keyup="cheat()" name="password1" type="password" class="form-control">
                        </div>
                    </div>
                    <div class="row">
                        <div class="row col-lg-offset-6">
                            <input class="btn btn-primary" ng-mousedown="setname()" name="subbtn"type="submit" value="Submit">
                        </div>
                    </div>
                </form>
            </div>
	   </div>
    </div>
</body>
</html>
