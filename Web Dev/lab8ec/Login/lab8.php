<!doctype html>
<html ng-app>
<div ng-controller="MyCtrl">
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
      session_start();

      $servername = "localhost";
      $username = "kohels65";     
      $password = "style69!!";
      $dbname = "kohels65";
      $myregex = '^(?=.*[0-9])(?=.*[a-z])(?=.*[\W])([a-zA-Z0-9\W]{8,})$';
      $passed = false;
      $phrase = "";
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
              $phrase =  "Username already exists <a href=\"index.php\">Please try another username.</a>";
            }
            else{
              
              $sql = "INSERT INTO LabFive (username,password,birthdate) VALUES (\"$username2\",\"$password2\",\"$birthdate\");";
              $conn->exec($sql);
              $phrase = "Welcome $username2 <br>";
              $passed = true;              
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

        $sql = "SELECT count(*) FROM LabFive WHERE username = \"$username2\" and password=\"$password2\";";

        try{
          $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);
          $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
              
          $rows = $conn->query($sql);

          foreach($rows as $row){
            if($row[0] > 0){
              $phrase = "Welcome $username2 <br>"; 
              $passed = true;
              $sql = "SELECT birthdate FROM LabFive WHERE username = \"$username2\" and password=\"$password2\";";
              $results = $conn->query($sql);
              foreach($results as $result)
                $birthdate = $result[0];
            }
            else{
              $phrase = "Username does not exist <a href=\"index.php\">Please try another username.</a>";
            }
          }
        }
      catch(PDOException $e){
          echo $sql . "<br>" . $e->getMessage();
      }
    }
?>
  <div class="container" >
    <div ng-controller="MyCtrl" ng-init="setName('<?php echo $username2;?>')">
      <div class="row">
          <?php echo $phrase;
          if ($passed){
            echo "<h5>Birthdate</h5>";
            echo "<input ng-model='bd' id=\"bd\" ng-change=\"swap()\" placeholder=$birthdate><br>";
            echo "<a href=\"index.php\">Log out</a>";            
          }?>
      </div>
    </div>
  </div>    
</body>
</html>
