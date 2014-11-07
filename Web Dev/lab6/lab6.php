<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lab 6</title>
</head>
<body>	
	<?php
		$servername = "localhost";
		$username = "kohels65";
		$password = "style69!!";
		$dbname = "kohels65";
		
		$birth = explode(" ", $_POST["birthInfo"]);
		$state = array_pop($birth);
		$city = implode(" ", $birth);
		$salary = FALSE;

		if (array_key_exists("salary", $_POST))
			$salary = TRUE;
		
			

		$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);

		if($salary)
			$sql = "SELECT nameFirst, nameLast, birthMonth, birthDay, birthYear, sum(salary) sum "
			     . "from Salaries a, Master b "
			     . "where a.playerID = b.playerID "
			     . "and birthCity = \"$city\" "
			     . "and birthState = \"$state\" "
			     . "group by b.playerID "
			     . "order by sum";
		else
			$sql = "SELECT nameFirst, nameLast, birthMonth, birthDay, birthYear "
			     . "from Master "
			     . "where birthCity = \"$city\" "
			     . "and birthState = \"$state\" "
			     . "order by nameLast,nameFirst";
		
		$rows = $conn->query($sql);

		if ($rows !== FALSE){
?>
			<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Birth Date</th>	
<?php           if ($salary)
					echo "<th>Salary</th>";
?>
			</tr>

<?php

			foreach($rows as $row){	
					$date = $row[4] .  "-".$row[2]."-".$row[3];
					echo "<tr>";			
					echo "<td>$row[0]</td>";
					echo "<td>$row[1]</td>";
					if ($_POST["format"] === "monthFirst")
						echo "<td>" . date_format(new DateTime($date), 'F d, Y') . "</td>";
					else 
						echo "<td>" . date_format(new DateTime($date), 'd F, Y') . "</td>";
					if ($salary)
						echo "<td>$$row[5]</td>";	
					echo "</tr>";				
			}
?>		</table>
<?php

		}
		
		
	?>
</body>
</html>
