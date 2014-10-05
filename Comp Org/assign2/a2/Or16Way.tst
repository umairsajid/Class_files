load Or16Way.hdl,
output-file Or16Way.out,
compare-to Or16Way.cmp,
output-list a b c d e f g h i j k l m n o p out;

set a 0, set b 0, set c 0, set d 0, set e 0, set f 0, set g 0, set h 0, 
set i 0, set j 0, set k 0, set l 0, set m 0, set n 0, set o 0, set p 0,
eval,
output;

set a 1, set b 1, set c 1, set d 1, set e 1, set f 1, set g 1, set h 1, 
set i 1, set j 1, set k 1, set l 1, set m 1, set n 1, set o 1, set p 1,
eval,
output;

set a 1, set b 0, set c 0, set d 0, set e 0, set f 0, set g 0, set h 0, 
set i 0, set j 0, set k 0, set l 0, set m 0, set n 0, set o 0, set p 0,
eval,
output;

set a 0, set b 0, set c 0, set d 0, set e 0, set f 0, set g 0, set h 0, 
set i 0, set j 0, set k 0, set l 0, set m 0, set n 0, set o 0, set p 1,
eval,
output;

set a 0, set b 1, set c 0, set d 0, set e 1, set f 1, set g 1, set h 0, 
set i 0, set j 1, set k 0, set l 1, set m 0, set n 0, set o 1, set p 1,
eval,
output;
