<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lab 5</title>
</head>
<body>	
	<?php
		$servername = "localhost";
		$username = "kohels65";
		$password = "style69!!";
		$dbname = "kohels65";



		$name = $_POST["name"];
		$names = explode(" ", $name);
		$font = $_POST["size"];

		
		try{
			$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);
			$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$sql = "INSERT INTO LabFive (fname,lname,fontsize) VALUES (\"$names[0]\",\"$names[1]\",$font);";
					

			$conn->exec($sql);
			echo "New record created successfully";
    	}
		catch(PDOException $e){
		    echo $sql . "<br>" . $e->getMessage();
		}


	?>
	
</body>
</html>
