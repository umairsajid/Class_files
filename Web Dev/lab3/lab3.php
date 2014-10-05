<!DOCTYPE html>
<html>
<!-- 
	Page information
	Name: Sterling Kohel
	Class: CS 346
	Last updated: Sep 23, 2014
 -->
	<head>		
	</head>
	<body>
		<?php
			
			function fibonacci($num)
			{
				if ($num == 0)
					return 0;
				
				else if ($num == 1)
					return 1;
				
				return (fibonacci($num - 1 ) + fibonacci($num - 2));
			}

			$answer = fibonacci(5);

			echo ($answer);
		?>
	</body> 
</html>
