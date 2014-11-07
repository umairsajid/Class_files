<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lab 5</title>
    <link href="lab4.css" rel="stylesheet">
</head>
<body>	
	<?php
		$servername = "localhost";
		$username = "kohels65";
		$password = "style69!!";
		$dbname = "kohels65";

		
		$fname = $_POST["fname"];
		$lname = $_POST["lname"];
		$size = "";		
		$font = "";
		$result = "NOT FOUND";

		$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);
		$sql = "SELECT fontsize FROM LabFive WHERE fname=\"$fname\" AND lname=\"$lname\";";
			
		$rows = $conn->query($sql);

		foreach($rows as $row){
			
			$font = $row["fontsize"];

			if ($font === "10"){
				$size = "small";
				$result = "<p class=\"$size\">$fname $lname</p>";
			}
			if ($font === "12"){
				$size = "medium";
				$result = "<p class=\"$size\">$fname $lname</p>";
			}
			if ($font === "20"){
				$size = "large";
				$result = "<p class=\"$size\">$fname $lname</p>";
			}
			if ($font === "24"){
				$size = "x-large";
				$result = "<p class=\"$size\">$fname $lname</p>";
			}
			
			
		}
		echo $result;
		
	?>
</body>
</html>
