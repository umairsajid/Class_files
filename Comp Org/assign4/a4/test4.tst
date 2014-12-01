load Larc.hdl,

// function call NOT
set RAM4K[0]  %X8AFA,    // li  R10 1111_1010 or -6(10)
set RAM4K[1]  %X9D08,    // lui R13  <address of function> >> 8  [ 2048(dec) ]
set RAM4K[2]  %XE9D0,    // jal R9 R13  (call NOT function)     R9 <-- 3
set RAM4K[3]  %X0000,    // nop  // R12 <-- 5(dec) 

// function call OR
set RAM4K[4]  %X8AFA,    // li  R10 1111_1010 or -6(10)
set RAM4K[5]  %X8B35,    // li  R11 0011_0101 or 53(dec)
set RAM4K[6]  %X9D09,    // lui R13  <address of function> >> 8  [ 2304(dec) ]
set RAM4K[7]  %XE9D0,    // jal R9 R13  (call OR function)      R9 <-- 8
set RAM4K[8]  %X0000,    // nop  // R12 <-- -1(dec) 

// function call AND
set RAM4K[9]  %X8AFA,    // li  R10 1111_1010 or -6(10)
set RAM4K[10] %X8B35,    // li  R11 0011_0101 or 53(dec)
set RAM4K[11] %X9D0A,    // lui R13  <address of function> >> 8  [ 2560(dec) ]
set RAM4K[12] %XE9D0,    // jal R9 R13  (call AND function)     R9 <-- 13
set RAM4K[13] %X0000,    // nop  // R12 <-- 48(dec) 

// function call MULT
set RAM4K[14] %X8A10,    // li  R10 16(dec)
set RAM4K[15] %X8B0D,    // li  R11 13(dec)
set RAM4K[16] %X9D0B,    // lui R13  <address of function> >> 8  [ 2816(dec) ]
set RAM4K[17] %XE9D0,    // jal R9 R13  (call MULT function)     R9 <-- 18
set RAM4K[18] %X0000,    // nop  // R12 <-- 208(dec) 

set RAM4K[19] %XF000,    // halt

// subroutine NOT:
//       R12 <- NOT(R10)
// address %X0800 or 2048(dec)
set RAM4K[2048] %X6CAA,  // R12 <-- 5 (dec) 
set RAM4K[2049] %XE090,  // jump back to address 3

// subroutine OR:
//       R12 <- OR(R10,R11)
// address %X0900 or 2304(dec)
set RAM4K[2304] %X6CAB,  // R12 <-- NOR(R10,R11) = 0
set RAM4K[2305] %X6CCC,  // R12 <-- NOR(R12,R12) = -1(dec)
set RAM4K[2306] %XE090,  // jump back to address 8

// subroutine AND:
//        R12 <- AND(R10,R11)
// address %X0A00 or 2560(dec)
set RAM4K[2560] %X6AAA,  // R10 <-- NOR(R10,R10) = 5(dec)
set RAM4K[2561] %X6BBB,  // R11 <-- NOR(R11,R11) = -54(dec)
set RAM4K[2562] %X6CAB,  // R12 <-- NOR(R10,R11) = 48
set RAM4K[2563] %XE090,  // jump back to address 13

// subroutine MULT:
//        R12 <- MULT(R10,R11)
// address %X0B00 or 2816(dec)
set RAM4K[2816] %X8C00,
set RAM4K[2817] %X8F00,
set RAM4K[2818] %X8E01,
set RAM4K[2819] %X6EEE,
set RAM4K[2820] %X6BBB,
set RAM4K[2821] %X6DEB,
set RAM4K[2822] %X6EEE,
set RAM4K[2823] %X6BBB,
set RAM4K[2824] %XA01D,
set RAM4K[2825] %X0CCA,
set RAM4K[2826] %X0AAA,
set RAM4K[2827] %X0EEE,
set RAM4K[2828] %X8D01,
set RAM4K[2829] %X0FFD,
set RAM4K[2830] %X8D10,
set RAM4K[2831] %X1DFD,
set RAM4K[2832] %XBF2D,
set RAM4K[2833] %XE090
;

repeat 1000 { tick, tock; }
