#include "StackT.h"
#include <iostream>
using namespace std;

int main()
{

	Stack<int> intSt;      // stack of ints
 	Stack<char> charSt;    // stack of chars
	Stack<char> charSt2;
	int i;

  	for (i = 1; i <= 4; i++)
    		intSt.push(i);
  	while (!intSt.empty())
  	{
    		i = intSt.top(); 
		intSt.pop();
    		cout << i << endl;
  	}
  	for (char ch = 'A'; ch <= 'D'; ch++)
	{
    		charSt.push(ch);
		charSt2.push(ch);
	}



	charSt.display(cout);
	cout<<endl;
	charSt2.display(cout);
	cout<<"Member top(): "<<charSt.top()<<endl;
	charSt.pop();
	charSt2.pop();
	cout<<"Friend top(): "<<top2(charSt)<<endl;
    	charSt.pop();
	charSt2.pop();
	cout<<"Non-Member top(): "<<top3(charSt)<<endl;
	charSt.display(cout);
	cout<<"**** End of top  ****"<<endl;
	for (char ch = 'A'; ch <= 'D'; ch++)
	{
    	charSt.push(ch);
		charSt2.push(ch);
	}
    	charSt.display(cout);
	cout<<"**** End of push ****"<<endl;
	charSt.replace('C', 'Z');
   	replace2(charSt, 'A', 'V');
	replace3(charSt, 'B', 'T'),
	charSt.display(cout);
	cout<<"**  End of replace ***"<<endl;
	charSt2.display(cout);
	cout<<charSt.identical(charSt2)<<endl;
	charSt2.replace('C', 'Z');
   	replace2(charSt2, 'A', 'V');
	replace3(charSt2, 'B', 'T'),
	cout<<charSt.identical(charSt2)<<endl;
	cout<<identical2(charSt, charSt2)<<endl;
	cout<<identical3(charSt, charSt2)<<endl;
    	cout<<"**  End of identical ***"<<endl;
    	cout<<size(charSt2)<<endl;

}












