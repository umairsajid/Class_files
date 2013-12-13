#include <string>
#include <vector>
#include <iterator>
#include <iostream>
#include <sstream>
#include "stdlib.h"
#include "infix.h"


using namespace std;

int main()
{
	infix test("( A + B ) *  ( C - D ) ");
	test.split();
	test.display();
	test.insertOperandValue();
	test.display();
	test.calculate();

	cout<<endl;	

	infix test3(" A + ( ( B + C ) * ( E - F ) - G ) / ( H - I )");
	test3.split();
	test3.display();
	test3.insertOperandValue();
	test3.display();
	test3.calculate();
	
	
}
