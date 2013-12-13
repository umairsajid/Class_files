#include <iostream>
#include <string>
#include "person.h"
#include "partTimeEmployee.h"

using namespace std;

void partTimeEmployee::print()
{
	personType::print();//prints the name of the //employee
	cout<<" wages are : "<<calculatePay()<<endl;
}

double partTimeEmployee::calculatePay()
{
	return (payRate * hoursWorked);
}

void partTimeEmployee::setNameRateHours(string first, 
                string last, double rate, double hours)
{
	personType::setName(first,last);
	payRate = rate;
	hoursWorked = hours;
}

partTimeEmployee::partTimeEmployee(string first, string last, 
                     double rate, double hours)
          : personType(first, last) 
{
	payRate = rate;
	hoursWorked = hours;
} 
