<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lab 4</title>
    <link href="lab4.css" rel="stylesheet">   
</head>
<body>	
	<?php
		$blue = "";
		$green = "";
		$red = "";
		$name = $_POST["name"];
		$text = $_POST["box"];
		if (array_key_exists("blue", $_POST))
			$blue = "blue";
		if (array_key_exists("red", $_POST))
			$red = "red";
		if (array_key_exists("green", $_POST))
			$green = "green";
		$font = $_POST["size"];
		$class = $_POST["class"];		
		
		if ($red === "" && $blue === "" && $green ==="")
			echo "<p class = \"danger\"> Error: No colors selected <a href=\"lab4.html\">Try again</a></p>";
		else{
			$names = explode(" ", $name);
			if ($blue === "" && $red ==="red" && $green ==="")
				echo "<p class = \"fire\"> $name   </p>";
			else if ($blue === "blue" && $red === "" && $green ==="")
				echo "<p class = \"sky\" $name  </p>";
			else if ($blue === "" && $red === "" && $green ==="green")
				echo "<p class = \"forest\"> $name  </p>";
			else if ($blue === "blue" && $red ==="red" && $green ==="")
				echo "<p class = \"sky\">$names[0]" . " " . "<span class =\"fire\">$names[1]</span></p>";
			else if ($blue === "blue" && $red === "" && $green ==="green")
				echo "<p class = \"sky\">$names[0]" . " " . "<span class =\"forest\">$names[1]</span></p>";
			else if ($blue === "" && $red ==="red" && $green ==="green")
				echo "<p class = \"fire\">$names[0]" . " " . "<span class =\"forest\">$names[1]</span></p>";
			else if ($blue === "blue" && $red ==="red" && $green ==="green"){
				$color = "forest";
				for($i = 0; $i < strlen($name); $i++){
					if ($color === "forest" && !ctype_space($name[$i]))
						$color = "sky";
					else if ($color === "sky" && !ctype_space($name[$i]))
						$color = "fire";
					else if ($color === "fire" && !ctype_space($name[$i]))
						$color = "forest";
					else{}

					echo "<span class =\"$color\">$name[$i]</span>";
				}
			}
			

			$array = str_split($text);
			$array = array_count_values($array);

			$numVowels = 0;
			if(array_key_exists('a', $array))
				$numVowels += $array['a'];
			if(array_key_exists('e', $array))
				$numVowels += $array['e'];
			if(array_key_exists('i', $array))
				$numVowels += $array['i'];
			if(array_key_exists('o', $array))
				$numVowels += $array['o'];
			if(array_key_exists('u', $array))
				$numVowels += $array['u'];
			echo "Number of vowels in given text: " . $numVowels. "<br>";
			
			if ($font === "10")
				$size = "small";
			if ($font === "12")
				$size = "medium";
			if ($font === "20")
				$size = "large";
			if ($font === "24")
				$size = "x-large";
			
			echo "<span class = \"$size\">$class</span>";
		}	

	?>
	
</body>
</html>
