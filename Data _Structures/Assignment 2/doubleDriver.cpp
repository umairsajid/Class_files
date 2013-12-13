#include "doubleStackT.h"
#include <iostream>
using namespace std;

int main()
{
	doubleStack<char> charSt, testStack; 
	int i;

  	for (char ch = 'A'; ch <= 'E'; ch++)
	{
    	charSt.push(ch,1);
		charSt.push(ch,2);
	}
	cout<<"stack 1"<<endl;
  	charSt.display(cout, 1);
	cout<<"stack 2"<<endl;
	charSt.display(cout, 2);
	cout<<endl;
	cout<<"Attempt to add to a full stack 1"<<endl;
	charSt.push('Z', 1);
	cout <<"Pop off of stack 2 add one to stack 1"<<endl;
    charSt.pop(2);
	charSt.push('F', 1);
	charSt.display(cout, 1);
	cout<<endl;
	charSt.display(cout, 2);
	cout<<"Attempt to add to a full stack 2"<<endl;
	charSt.push('T', 2);
	cout<<endl;
	cout<<"Getting top of stack1 using member function"<<endl;
	cout<<charSt.top(1)<<endl;
	cout<<"Getting top of stack2 using non-member function"<<endl;
	cout<<top2(charSt, 2)<<endl;
	cout<<"Replacing C with R in stack 2 using member function"<<endl;
	charSt.replace('C', 'R');
	charSt.display(cout, 2);
	cout<<"Replacing B with W in stack 1 using non-member fuction"<<endl;
	replace2(charSt, 'B', 'W');
	charSt.display(cout, 1);
	cout<<"test stack to compare with"<<endl;
	testStack.push('A', 1);
	testStack.push('W', 1);
	testStack.push('R', 1);
	testStack.push('D', 1);
	testStack.push('E', 1);
	testStack.push('F', 1);
	testStack.push('A', 2);
	testStack.push('W', 2);
	testStack.push('R', 2);
	testStack.push('D', 2);
	testStack.display(cout, 1);
	cout<<endl;
	testStack.display(cout, 2);
	cout<<"testing member identical and non-member with 2 stacks that are the same"<<endl;
	cout<<charSt.identical(testStack)<<endl;
	cout<<identical2(charSt, testStack)<<endl;
	testStack.replace('A', 'Q');
	cout<<"testing member identical and non-member with 2 stacks that are different"<<endl;
	cout<<charSt.identical(testStack)<<endl;
	cout<<identical2(charSt, testStack)<<endl;
}












