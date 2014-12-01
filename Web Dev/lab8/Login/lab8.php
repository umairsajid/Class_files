<!doctype html>
<html ng-app>
<div ng-controller="MyCtrl">
<head
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lab 8</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="lab8.css">  
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> 
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.27/angular.min.js"></script>
    <script src="lab8.js" type="text/javascript"></script>  
</head>
<body>
<?php
      session_start();

      $servername = "localhost";
      $username = "kohels65";     
      $password = "style69!!";
      $dbname = "kohels65";
      $myregex = '^(?=.*[0-9])(?=.*[a-z])(?=.*[\W])([a-zA-Z0-9\W]{8,})$';

      if (isset($_POST["submitbtn"]) && 
          $_POST["password1"] == $_POST["password2"]){
        if($_POST["username"] != "" ){
          $_SESSION["username"] = $_POST["username"];
          $_SESSION["password"] = $_POST["password1"];
          $_SESSION["birthdate"] = $_POST["birthdate"];
        }
      
        $username2 = $_SESSION["username"];    
        $password2 = md5($_SESSION["password"]);
        $birthdate = $_SESSION["birthdate"];  

        $sql = "SELECT COUNT(*) FROM LabFive WHERE username = \"$username2\";";

        try{
          $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);
          $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
              
          $rows = $conn->query($sql);

          foreach($rows as $row){
            if($row[0] > 0){
              echo "Username already exists <a href=\"index.php\">Please try another username.</a>";
            }
            else{
              $sql = "INSERT INTO LabFive (username,password,birthdate) VALUES (\"$username2\",\"$password2\",$birthdate);";
              $conn->exec($sql);
              echo "Welcome " . $username2 ."<br>";
              exit;
            }
          }
        }
      catch(PDOException $e){
          echo $sql . "<br>" . $e->getMessage();
      }
    }
    if (isset($_POST["subbtn"])){
      if($_POST["username"] != ""){
          $_SESSION["username"] = $_POST["username"];
          $_SESSION["password"] = $_POST["password1"];
       }
      
        $username2 = $_SESSION["username"];    
        $password2 = md5($_SESSION["password"]);

        $sql = "SELECT COUNT(*) FROM LabFive WHERE username = \"$username2\" and password=\"$password2\";";

        try{
          $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);
          $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
              
          $rows = $conn->query($sql);

          foreach($rows as $row){
            if($row[0] > 0){
              echo "Welcome " . $username2 ."<br>";
            }
            else{
              echo "Username does not exist <a href=\"index.php\">Please try another username.</a>";
            }
          }
        }
      catch(PDOException $e){
          echo $sql . "<br>" . $e->getMessage();
      }
    }
?>
  <div class="container" >
    <div ng-controller="MyCtrl">
      <div class="row">
        <a href="index.php">Log out</a>
      </div>
    </div>
  </div>    
</body>
</html>
