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

		$sql = "SELECT Distinct birthCity, birthstate "
			. "FROM `Master` "
            . "WHERE birthCity is not null "
            . "and birthCountry = 'USA' "
            . "order by birthCity,birthState;";
       	$conn = new PDO("mysql:host=$servername;dbname=$dbname", $username,$password);  

       	$rows = $conn->query($sql);

       	if ($rows !== FALSE){

	?>
	
	<form name="labform" method="post" action="lab6.php">
		
		<p>
			<label>Cities</label><br>
			<select name="birthInfo">
<?php			foreach($rows as $row){
					if ($row[0] === 'Marshfield' && $row[1] === 'WI')
       					echo "<option selected=\"selected\">$row[0] $row[1]</option>";
       				else
						echo "<option>$row[0] $row[1]</option>";
				}
		}
?>				
			</select>
		</p>
		<p>		
			<label><input type="checkbox" name="salary" value="true">Show Salary</label>
		</p>
		<p>
			<label>Date Format</label><br>
			<label><input type="radio" name="format" value="monthFirst">Month DD, YYYY</label>
			<label><input type="radio" name="format" value="dayFirst" checked>DD Month, YYYY</label>
		</p>				
		<input type="submit" value="Submit">
	</form>
</body>
</html>
