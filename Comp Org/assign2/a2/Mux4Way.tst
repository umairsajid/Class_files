load Mux4Way.hdl,
output-file Mux4Way.out,
compare-to Mux4Way.cmp,
output-list a%B1.1.1 b%B1.1.1 c%B1.1.1 d%B1.1.1 sel%B1.2.1 out%B1.1.1;

set a 1,
set b 0,
set c 0,
set d 0,
set sel 0,
eval,
output;

set a 0,
set b 1,
set c 0,
set d 0,
set sel 1,
eval,
output;

set a 0,
set b 0,
set c 1,
set d 0,
set sel 2,
eval,
output;

set a 0,
set b 0,
set c 0,
set d 1,
set sel 3,
eval,
output;
