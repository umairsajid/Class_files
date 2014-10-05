load ALU.hdl,
output-file ALU.out,
compare-to ALU.cmp,
output-list nor%B1.1.1 sub%B1.1.1 a%D1.5.1 b%D1.5.1 out%D1.5.1 z%D1.1.1;

set sub 0,      /* addition */
set nor 0,

set a   0,
set b   1,
eval,
output;

set a   1,
set b   0,
eval,
output;

set a   256,
set b   4,
eval,
output;

set a   -1,
set b   0,
eval,
output;

set a   0,
set b   -1,
eval,
output;

set a   -1,
set b   -1,
eval,
output;

set a   2000,
set b   -10,
eval,
output;

set a   -10,
set b   2000,
eval,
output;

set a   -2000,
set b   -2000,
eval,
output;

set a   1000,
set b   -1000,
eval,
output;

set sub 1,      /* subtraction */
set nor 0,

set a   0,
set b   1,
eval,
output;

set a   1,
set b   0,
eval,
output;

set a   256,
set b   4,
eval,
output;

set a   -1,
set b   0,
eval,
output;

set a   0,
set b   -1,
eval,
output;

set a   -1,
set b   -1,
eval,
output;

set a   2000,
set b   -10,
eval,
output;

set a   -10,
set b   2000,
eval,
output;

set a   -2000,
set b   -2000,
eval,
output;

set a   1000,
set b   -1000,
eval,
output;


output-list nor%B1.1.1 sub%B1.1.1 a%B1.16.1 b%B1.16.1 out%B1.16.1 z%D1.1.1;

set sub 0,      /* NOR */
set nor 1,

set a   %B0000000000000000,
set b   %B0000000000000000,
eval,
output;

set a   %B0000000000000000,
set b   %B1111111111111111,
eval,
output;

set a   %B1111111111111111,
set b   %B0000000000000000,
eval,
output;

set a   %B1111111111111111,
set b   %B1111111111111111,
eval,
output;

set a   %B0101010101010101,
set b   %B1010101010101010,
eval,
output;

set a   %B0010101101010101,
set b   %B1000010101001110,
eval,
output;

output-list nor%B1.1.1 sub%B1.1.1 a%D1.5.1 b%D1.5.1 out%D1.1.1 z%D1.1.1;

set sub 1,      /* set-on-less-than */
set nor 1,

set a   0,
set b   1,
eval,
output;

set a   1,
set b   0,
eval,
output;

set a   256,
set b   4,
eval,
output;

set a   -1,
set b   0,
eval,
output;

set a   0,
set b   -1,
eval,
output;

set a   -1,
set b   -1,
eval,
output;

set a   2000,
set b   -10,
eval,
output;

set a   -10,
set b   2000,
eval,
output;

set a   -2000,
set b   -2000,
eval,
output;

set a   1000,
set b   -1000,
eval,
output;

