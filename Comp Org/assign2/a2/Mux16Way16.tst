load Mux16Way16.hdl,
output-file Mux16Way16.out,
compare-to Mux16Way16.cmp,
output-list a%B1.16.1 b%B1.16.1 c%B1.16.1 d%B1.16.1 e%B1.16.1 f%B1.16.1 g%B1.16.1 h%B1.16.1 i%B1.16.1 j%B1.16.1 k%B1.16.1 l%B1.16.1 m%B1.16.1 n%B1.16.1 o%B1.16.1 p%B1.16.1;

set a %B0001001000110100,
set b %B0010001101000101,
set c %B0011010001010110,
set d %B0100010101100111,
set e %B0101011001111000,
set f %B0110011110001001,
set g %B0111100010011010,
set h %B1000100110101011,
set i %B1001001000110100,
set j %B1010001101000101,
set k %B1011010001010110,
set l %B1100010101100111,
set m %B1101011001111000,
set n %B1110011110001001,
set o %B1111100010011010,
set p %B1100100110101011;

eval,
output;

output-list sel%B1.4.1 out%B1.16.1;

set sel 0,
eval,
output;

set sel 1,
eval,
output;

set sel 2,
eval,
output;

set sel 3,
eval,
output;

set sel 4,
eval,
output;

set sel 5,
eval,
output;

set sel 6,
eval,
output;

set sel 7,
eval,
output;

set sel 8,
eval,
output;

set sel 9,
eval,
output;

set sel 10,
eval,
output;

set sel 11,
eval,
output;

set sel 12,
eval,
output;

set sel 13,
eval,
output;

set sel 14,
eval,
output;

set sel 15,
eval,
output;

