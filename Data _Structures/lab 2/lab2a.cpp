#include "stdio.h"
#include <iostream>
#include <string>

class persontype
{ 
      public:     
           void setName();
                
};

int main()
{
    char c = 'a';
    int i= -950;
    short int si= 50;
    long int li= 10000000;
    unsigned int ui= 55;
    unsigned short int usi= 44;
    unsigned long int uli= 2421241;
    persontype student;
    
    printf("sizeof(char)=%lubytes\n", sizeof(c));
    printf("sizeof(int)=%lubytes\n", sizeof(i));
    printf("sizeof(short int)=%lubytes\n", sizeof(si));
    printf("sizeof(long int)=%lubytes\n", sizeof(li));
    printf("sizeof(unsigned int)=%lubytes\n", sizeof(ui));
    printf("sizeof(unsigned short int)=%lubytes\n", sizeof(usi));
    printf("sizeof(unsigned long int)=%lubytes\n", sizeof(uli));
    printf("sizeof(persontype)=%lubytes\n", sizeof(student));
    system("pause");
    return 0;
    
}




