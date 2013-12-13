#include <iostream>
#include "Stack4.h"

using namespace std;

int main()
{
	int i;
	StackType<int> st, st3;

	for( i = 0; i < 10; i++)
		st.Push(i);
	
	StackType<int> st2(st);
	cout << st << endl;
	cout << st2 << endl;

	st3 = st;
	cout << st3 << endl;

	cout << st3.size() << endl;


	
}

