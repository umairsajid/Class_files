<?php
	$data = json_decode(file_get_contents("php://input"));

	$bd = $data->birthdate;
	$uname = $data->username;	

	$servername = "localhost";
    $username = "kohels65";     
    $password = "style69!!";
    $dbname = "kohels65";

    $sql = "UPDATE LabFive set birthdate = \"$bd\" where username = \"$uname\";"; 
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);
    if($conn->query($sql) !== FALSE)
    	$result = "updated";
    else
    	$result = "failed";
	header('Content-Type: application/json');
	echo $json_response = json_encode($result); 
	
?>