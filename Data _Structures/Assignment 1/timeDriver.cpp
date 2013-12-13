/*
Name: Sterling Kohel
Class: CSC 271
Assigment 1
*/

#include <iostream>
#include <cstdio>
#include "timeType.h"

using namespace std;

int main()
{
	timeType clock1;
	timeType clock2(23,61,59);
	clock1.set(23, 59, 58);
	clock1.write();
	clock2.write();
	cout<<clock1.lessThan(clock2)<<endl;
	cout<<clock2.lessThan(clock1)<<endl;
	clock1.increment();
	clock1.write();
	cout<<clock1.equal(clock2)<<endl;
	clock2.increment();
	clock2.write();
	return 0;	
}	
	
