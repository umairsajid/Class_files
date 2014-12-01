load Larc.hdl,

set RAM4K[0]  %X8101,   // 1. li R1 1
set RAM4K[1]  %XA070,   // 2. beq R0 7  (jump to address 9)                
set RAM4K[2]  %XF000,   // 
set RAM4K[3]  %XF000,   // 
set RAM4K[4]  %XF000,   // 
set RAM4K[5]  %XF000,   // 
set RAM4K[6]  %XF000,   // 
set RAM4K[7]  %XF000,   // 
set RAM4K[8]  %XF000,   // 
set RAM4K[9]  %XBF71,   // 3. bne R1 -9 (jump back to address 1)
set RAM4K[10] %XF000    // 4. halt
;
repeat 100 { tick, tock; }

