/*Sterling Kohel
  CS 271
  Assignment 1 */

#include <iostream>
using namespace std;

#ifndef doubleStackT
#define doubleStackT

const int STACK_CAPACITY = 10;

template <class StackElement>
class doubleStack
{

public:
	
	doubleStack( );

	bool empty(int tier) const;

	void push(const StackElement & value, int tier);

	void pop(int tier);

	void display(ostream & out, int tier) const;

	StackElement top(int tier) const;

	void replace(StackElement oldItem, StackElement newItem);

	bool identical(doubleStack &stack);


	
private:

	  StackElement myArray[STACK_CAPACITY];
	  int myTop1;
	  int myTop2;
}; 		

template <class StackElement>
inline doubleStack<StackElement>::doubleStack() 
{ 
	myTop1 = -1; 
	myTop2= STACK_CAPACITY; 
} 

template <class StackElement>
void doubleStack<StackElement>::push(const StackElement &value, int tier)
{
	if(myTop1 < myTop2 - 1)	
	{
		if (tier == 1)  
  		{
			++myTop1;    		
			myArray[myTop1] = value;			
  		}
    	else if(tier == 2) 
		{
			--myTop2;			
			myArray[myTop2] = value;			
		}
	}
	else 
		cerr << "*** Stack is full -- can't add new value ***\n"
         << "Must Increase value of STACK_CAPACITY in Stack.h\n";
}

template <class StackElement>
void doubleStack<StackElement>::pop(int tier)
{
	if (myTop1 >= 0 && tier == 1)    
    	myTop1--;
  	else if (myTop2 < STACK_CAPACITY && tier == 2)
		myTop2++;
	else
    	cerr << "*** Stack is empty -- can't remove a value ***\n";
}

template <class StackElement>
inline bool doubleStack<StackElement>::empty(int tier) const
{ 
	if (tier == 1)
		return (myTop1 == -1);
	else 
		return (myTop2 == STACK_CAPACITY);
} 

template <class StackElement>
void doubleStack<StackElement>::display(ostream & out, int tier) const
{
	if(tier == 1)
	{
  		for (int i = myTop1; i >= 0; i--) 
    	out << myArray[i] << endl;
	}
	else 
	{
  		for (int i = myTop2; i < STACK_CAPACITY; i++) 
    	out << myArray[i] << endl;
	}
}

template <class StackElement>
StackElement doubleStack<StackElement>::top(int tier) const
{
  	if (myTop1 >= 0 && tier == 1)
    	return (myArray[myTop1]);
  	else if (myTop2 < STACK_CAPACITY && tier == 2)
		return (myArray[myTop2]);
  cerr << "*** Stack is empty -- returning 'garbage value' ***\n";
  return myArray[STACK_CAPACITY -1];
}

template <class StackElement>
void doubleStack<StackElement>::replace(StackElement oldItem, 
                                     StackElement newItem)
{		
		for(int i = 0; i <= myTop1; i++)
		{
			if (myArray[i] == oldItem)
				myArray[i] = newItem;
		}
	
		for(int i = STACK_CAPACITY-1; i >= myTop2; i--)
		{
			if (myArray[i] == oldItem)
				myArray[i] = newItem;
		}   
}



template <class StackElement>
bool doubleStack<StackElement>::identical(doubleStack &stack)
{
	doubleStack<StackElement> tempStack;
	StackElement temp1, temp2;
    bool same = true;
	 
    for(int tier = 1; tier < 3; tier++)
	{
    	while(!this -> empty(tier) && !stack.empty(tier) )
       	{
        	temp1 = this -> top(tier);
            temp2 = stack.top(tier);
			cout << temp1 << " -- " <<temp2<<endl;
            if (temp1 != temp2)
			{
			   same = false;
			   break;
            }
			this -> pop(tier);
         	stack.pop(tier); 
            tempStack.push(temp1, tier);
        }
		same = same && this -> empty(tier) && stack.empty(tier);
     	while(!tempStack.empty(tier))
     	{       
         	temp1 = tempStack.top(tier);
			tempStack.pop(tier);
         	this -> push(temp1, tier);
         	stack.push(temp1, tier);       
     	}
	}
    return same;
}

template<class StackElement>
StackElement top2(doubleStack<StackElement> &stack, int tier)
{
	return stack.top(tier);
}

template <class StackElement>
void replace2(doubleStack <StackElement> &stack, StackElement olditem, 
              StackElement newitem)
{
     doubleStack<StackElement> tempStack;
     StackElement temp;

	for (int tier = 1; tier < 3; tier++)
	{
     	while(!stack.empty(tier))
     	{          
		  	temp = stack.top(tier);
          	if(temp==olditem)
               temp = newitem;
		  	stack.pop(tier);	
          	tempStack.push(temp, tier);
		}     
     	while(!tempStack.empty(tier))
     	{
          	temp = tempStack.top(tier);
		  	tempStack.pop(tier);
          	stack.push(temp, tier);
     	}
    }
} 

template <class StackElement>
bool identical2(doubleStack<StackElement> &st1, doubleStack<StackElement> &st2)
{
	doubleStack<StackElement> tempStack;
	StackElement temp1, temp2;
    bool same = true;
	 
    for(int tier = 1; tier < 3; tier++)
	{
    	while(!st1.empty(tier) && !st2.empty(tier) )
       	{
        	temp1 = st1.top(tier);
            temp2 = st2.top(tier);
            if (temp1 != temp2)
			{
			   same = false;
			   break;
            }
			st1.pop(tier);
         	st2.pop(tier); 
            tempStack.push(temp1, tier);
        }
		same = same && st1.empty(tier) && st2.empty(tier);
     	while(!tempStack.empty(tier))
     	{       
         	temp1 = tempStack.top(tier);
			tempStack.pop(tier);
         	st1.push(temp1, tier);
         	st2.push(temp1, tier);       
     	}
	}
    return same;
}
#endif















