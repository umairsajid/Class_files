
#ifndef H_personType
#define H_personType

#include <string>

using namespace std;

class personType
{
public:
    void print() const;
		//Function to output the first name and last name
		//in the form firstName lastName
  
    void setName(string first, string last);
		//Function to set firstName and lastName according to 
 		//the parameters
		//Postcondition: firstName = first; lastName = last

    void getName(string& first, string& last);
		//Function to return firstName and lastName via the 
		//parameters
		//Postcondition: first = firstName; last = lastName

    personType(string first = "", string last = "");
		//Constructor
		//Set firstName and lastName according to the parameters
 		//The default values of the parameters are empty strings
 		//Postcondition: firstName = first; lastName = last  

 private:
    string firstName; //stores the first name
    string lastName;  //stores the last name
};

#endif
