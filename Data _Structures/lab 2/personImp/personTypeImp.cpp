#include <iostream>
#incldue <string>
#include "personType.h"

using namespace std;


void personType::print() const
{
	cout<<firstName<<" "<<lastName;
}

void personType::setName(string first, string last)
{
	firstName = first;
	lastName = last;
}

void personType::getName(string& first, string& last)
{
	first = firstName;
	last = lastName;
}

//constructor 
personType::personType(string first, string last) 

{ 
	firstName = first;
	lastName = last;
}
