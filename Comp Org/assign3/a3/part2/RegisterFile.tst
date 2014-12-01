load RegisterFile.hdl,
output-file RegisterFile.out,
compare-to RegisterFile.cmp,
output-list time%S1.4.1 rr1%D1.2.1 rr2%D1.2.1 wr%D1.2.1 wd%D1.5.1 load%B2.1.2 rd1%D1.5.1 rd2%D1.5.1;

set rr1 0,
set rr2 1,
set wr  0,
set wd  10,
set load 1,
tick,
output;

tock,
output;

set rr1 0,
set rr2 1,
set wr  1,
set wd  11,
set load 1,
tick,
output;

tock,
output;

set rr1 1,
set rr2 2,
set wr  2,
set wd  12,
set load 1,
tick,
output;

tock,
output;

set rr1 2,
set rr2 3,
set wr  3,
set wd  13,
set load 1,
tick,
output;

tock,
output;

set rr1 3,
set rr2 4,
set wr  4,
set wd  14,
set load 1,
tick,
output;

tock,
output;

set rr1 4,
set rr2 5,
set wr  5,
set wd  15,
set load 1,
tick,
output;

tock,
output;

set rr1 5,
set rr2 6,
set wr  6,
set wd  16,
set load 1,
tick,
output;

tock,
output;

set rr1 6,
set rr2 7,
set wr  7,
set wd  17,
set load 1,
tick,
output;

tock,
output;

set rr1 7,
set rr2 8,
set wr  8,
set wd  18,
set load 1,
tick,
output;

tock,
output;

set rr1 8,
set rr2 9,
set wr  9,
set wd  19,
set load 1,
tick,
output;

tock,
output;

set rr1 9,
set rr2 10,
set wr  10,
set wd  20,
set load 1,
tick,
output;

tock,
output;

set rr1 10,
set rr2 11,
set wr  11,
set wd  21,
set load 1,
tick,
output;

tock,
output;

set rr1 11,
set rr2 12,
set wr  12,
set wd  22,
set load 1,
tick,
output;

tock,
output;

set rr1 12,
set rr2 13,
set wr  13,
set wd  23,
set load 1,
tick,
output;

tock,
output;

set rr1 13,
set rr2 14,
set wr  14,
set wd  24,
set load 1,
tick,
output;

tock,
output;

set rr1 14,
set rr2 15,
set wr  15,
set wd  25,
set load 1,
tick,
output;

tock,
output;

// read the same register twice

set rr1 1,
set rr2 1,
set wr  1,
set wd  111,
set load 0,
tick,
output;

tock,
output;

// read and write the same register

set rr1 1,
set rr2 1,
set wr  1,
set wd  111,
set load 1,
tick,
output;

tock,
output;
