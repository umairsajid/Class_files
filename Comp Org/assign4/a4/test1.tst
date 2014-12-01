load Larc.hdl,

set RAM4K[0]  %X8101,   // 1.  li  R1 1         R1  <-- 1                  
set RAM4K[1]  %X8202,   // 2.  li  R2 2         R2  <-- 2
set RAM4K[2]  %X9FFF,   // 3.  lui R15 -1       R15 <-- -256
set RAM4K[3]  %X0312,   // 4.  add R3 R1 R2     R3  <-- 3 
set RAM4K[4]  %X141F,   // 5.  sub R4 R1 R15    R4  <-- 257                 
set RAM4K[5]  %X6512,   // 6.  nor R5 R1 R2     R5  <-- -4   
set RAM4K[6]  %X7612,   // 7.  slt R6 R1 R2     R6  <-- 1
set RAM4K[7]  %X7621,   // 8.  slt R6 R2 R1     R6  <-- 0
set RAM4K[8]  %X7F22,   // 9.  slt R15 R2 R2    R15 <-- 0
set RAM4K[9]  %XF000    // 10. halt             
;

repeat 12 { 
  tick, tock;
}
