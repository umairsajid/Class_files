<!DOCTYPE html>
<html>
<!-- 
	Page information
	Name: Sterling Kohel
	Class: CS 346
	Last updated: Sep 26, 2014
 -->
	<head>		
		<title>Lab 3</title>
		<link rel="stylesheet" type="text/css" href="lab3.css">
		<meta charset = "UTF-8"/>
	</head>
	<body>
		<?php
			
			function fibonacci($num){
				if ($num == 0)
					return 0;
				
				else if ($num == 1)
					return 1;
				
				return (fibonacci($num - 1 ) + fibonacci($num - 2));
			}			

			function isoceles(){
				$myfile = fopen("triangle.txt", "r") or die("Unable to open file!");
				$perimeter = array();
				
				$area = array();
				
				while (!feof($myfile)){
					$line = fgets($myfile);
					$parameter = explode(" ", $line);
					$myTriangle = new IsocelesTriangle($parameter[0], $parameter[1]);
					array_push($area, $myTriangle->getArea());
					array_push($perimeter, $myTriangle->getPerimeter());
				}
				
				fclose($myfile);
				sort($area);
				sort($perimeter);?>
				<p>smallest perimeter: <?= $perimeter[0]; ?></p>
			    <p>largest perimeter: <?= $perimeter[count($area) - 1]; ?></p>
				<p>smallest area: <?= $area[0]; ?></p>
				<p>largest area:  <?= $area[count($area) - 1]; ?></p>				
	<?php      }

			class IsocelesTriangle{
				private $base;
				private $height;

				public function __construct($base, $height){
					$this->base = $base;
					$this->height = $height;
				}

				public function getBase(){
					return $this->base;
				}
				public function getArea(){
					return (.5 * ($this->base * $this->height));
				}

				public function getPerimeter(){
					$side = sqrt(pow($this->base,2) + pow($this->height,2));
					return ($side * 2 + $this->base);
				}				
			}

			function stringTable($words){   
				
				$charArray = array();
				$letterArray = array();
				$str = "";
				foreach($words as $oldWord){
					$word = strtolower($oldWord);
					$letterArray = str_split($word);
					
					foreach($letterArray as $letter){
						if(!in_array($letter, $charArray))
							array_push($charArray, $letter);
					}
				}
				sort($charArray);
				print $str;
?>
				<table>
					<tr>
						<td></td>
						<?php foreach($words as $word){  ?>
							<td><?= $word ?></td>
						<?php	}   ?>
					</tr>
						<?php foreach($charArray as $char){  ?>
					<tr>
						<td><?=$char?></td>
						<?php foreach($words as $choice){  ?>
						<td><?=substr_count($choice, (string)$char)?></td>
						<?php	}   ?>
					</tr>
	<?php				}?>
				</table>				
	<?php	}

	

			function subsetMultiples($array, $target){				
				if ($target == 1)
					return "true";
				if (count($array) == 0)
					return "false";
				if ($target % $array[0] == 0)
					$target = $target / $array[0];	
				$used = array();
				$number = array_shift($array);	
				array_unshift($used, $number);
				return subsetMultiples($array, $target);
			}

			function censorWord($phrase, $censored, $replacement){
				if (strlen($replacement) != 1)
					$array = explode(" ", $phrase);
				else 
					$array = str_split($phrase);
				for ($i = 0; $i < count($array); $i++){
					if ($array[$i] == $censored)
						$array[$i] = $replacement;
				}
				if (strlen($replacement) != 1)
					$phrase = implode(" ", $array);
				else
					$phrase = implode("", $array);
				return $phrase;				
			}

			function main(){
				print "The 10th fib is: " . fibonacci(10);
				isoceles();
				$fruit = array("hello", "how", "are", "you");
				stringTable($fruit);
				$nums = array(1,6,3,4);
				print subsetMultiples($nums, 12) . "<br>";	
				print (censorWord("cat","c", "f")) . "<br>";
				print (censorWord("cat dog cat","cat", "fish"));			
			}
			main();
		?>
		<p id = "validate">
			<a href="http://validator.w3.org/check/referer">Validate Me</a>
		</p>
	</body> 
</html>