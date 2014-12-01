load BoothMultiplier.hdl,

set reset 1,
set initM %D25,
set initQ %D10;

tick, tock,
set reset 0;


repeat 100 { 
  tick, tock;
}



