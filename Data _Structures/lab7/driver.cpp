#include <iostream>
#include "queue2.h"

using namespace std;

int main()
{
	int i;

	QueType<int> myQ, yourQ;

	for(i = 0; i < 10; i++)
	{
		myQ.AddQ(i);
	}
	cout<<"myQ size:"<<myQ.size()<<endl;
	cout <<"myQ: " <<myQ <<endl;
	yourQ = myQ;
	cout <<"yourQ: "<< yourQ<<endl;
	QueType<int> herQ(myQ);
	cout<<"herQ: "<< herQ<<endl;
	cout<<"herQ size:"<<herQ.size()<<endl;
}
