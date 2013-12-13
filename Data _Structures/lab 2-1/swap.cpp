#include <iostream>

using namespace std;

template <class elemType>
void swap(elemType*, elemType*);

int main()
{
	int integerOne = 5, integerTwo = 10;
	double doubleOne = 1, doubleTwo = 2;
	
	cout <<"\t   integerOne"<<"\t\tintegerTwo"<<"\t     doubleOne"<<"\t\tdoubleTwo"<<endl;
	cout <<"Before Swap"<<"\t"<<integerOne<<"\t\t    "<<integerTwo
         <<"\t\t\t"<<doubleOne<<"\t\t    "<<doubleTwo<<endl;
	swap(integerOne, integerTwo);
	swap(doubleOne, doubleTwo);
	cout <<"After Swap"<<"\t"<<integerOne<<"\t\t    "<<integerTwo
         <<"\t\t\t"<<doubleOne<<"\t\t    "<<doubleTwo<<endl<<endl;
	system("pause");
	
	return 0;
}

template <class elemType>
void swap(elemType *x, elemType *y)
{
	elemType* temp;
	
	*temp = x;
	*x = y;
	*y = temp;

}
