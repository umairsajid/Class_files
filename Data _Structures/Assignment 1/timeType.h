#ifndef H_timeType
#define H_timeType
#include <iostream>
using namespace std;

class timeType
{     
    private:
		int hours;
		int minutes;
		int seconds;	
    public:
		timeType(int, int, int);
        // Precondition:
        //     0 <= initHrs <= 23  &&  0 <= initMins <= 59
        //  && 0 <= initSecs <= 59
        // Postcondition:
        //     Class object is constructed
        //  && Time is set according to the incoming parameters

    	timeType();
        // Postcondition:
        //     Class object is constructed  &&  Time is 0:0:0
        
		void set(int,int,int);
        // Precondition:
        //     0 <= hours <= 23  &&  0 <= minutes <= 59
        //  && 0 <= seconds <= 59
        // Postcondition:
        //     Time is set according to the incoming parameters
		
		void increment();
        // Postcondition:
        //     Time has been advanced by one second, with
        //     23:59:59 wrapping around to 0:0:0

		void write() const;
        // Postcondition:
        //     Time has been output in the form HH:MM:SS

    	bool equal(timeType otherTime ) const;
        // Postcondition:
        //     Function value == TRUE, if this time equals otherTime
        //                    == FALSE, otherwise

    	bool lessThan(timeType otherTime ) const;
        // Precondition:
        //     This time and otherTime represent times in the
        //     same day
        // Postcondition:
        //     Function value == TRUE, if this time is earlier
        //                             in the day than otherTime
        //                    == FALSE, otherwise

};
#endif
#include "timeTypeImp.cpp"
