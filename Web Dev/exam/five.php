<DOCTYPE html>
<html>
	<head>
		<title>Sterling's #5</title>
		<meta charset="utf-8"/>
		<meta name="author" content="Sterling"/>
		<link rel="stylesheet" href="style5.css">
	</head>
	<body>
	<?php
	
		function nums(){			
			$count = 0;
			$i = 1;
?>			<ul>
<?php			
			while($count != 100){
				if($i % 2 !== 0){
?>				<li>
<?=			  	 $i; 
?>				</li>
<?php			 	$count++;
				}
				$i++;				

			}		
		}
?>			</ul>
<?php		
	

	
	
		function middle($line){
			if ($line === null)
				$line = "hello";
			$size = strlen($line);
			if ($size%2 === 0){
				$array = str_split($line);
		 		return  $array[$size/2-1] . $array[$size/2];
			}
			else{
				$array = str_split($line);
		 		return $array[$size/2];
			}

		}
		
	
		function meow(){
			$num = rand(0, 100);
			
			if ($num >= 0 && $num < 50)
				echo '<p class="red">cat<p>';
			elseif ($num >= 49 && $num < 79)
				echo '<p class="blue">cat<p>';
			else
				echo '<p class="green">cat<p>';
		}
	
		function median($array){
			sort($array);
			$length = count($array);
			return $array[(int)$array/2+1];
		}


		function most(){
			$myfile = fopen("input.txt", "r") or die("Unable to open file!");
			$line = "";

			while (!feof($myfile)){
				$line = $line . fgets($myfile);
			}
			
			$array = str_split($line);
			$array = array_count_values($array);
			arsort($array);
			//print_r($array);
			foreach($array as $key => $val){
				echo $key;
				break;
			}
		}

		function main(){
			//nums();
			//echo middle();
			$array = array(34,35,1,99,33);
			//echo median($array);
			//meow();
			most();
		}
		main();
		?>
	</body>
</html>