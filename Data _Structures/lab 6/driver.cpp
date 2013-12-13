#include <iostream>
#include "Queue1.h"

using namespace std;

int main()
{
	int i = 0;	
	QueType<int> queue(10);
	
	while(i <= 15)
	{
		queue.AddQ(i);
		cout<<"adding "<< i<<endl;
		i++;
	}
	queue.RemoveQ(i);
	queue.RemoveQ(i);
	queue.AddQ(756);
	queue.AddQ(356);
	while(!queue.IsEmpty())
	{
		queue.RemoveQ(i);
		cout<<i<<" emptying que should be what went it"<<endl;
		i++;
	}

}

	
