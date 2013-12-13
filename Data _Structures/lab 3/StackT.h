#include <iostream>
using namespace std;

#ifndef STACKT
#define STACKT

const int STACK_CAPACITY = 128;



template <class StackElement>
class Stack
{
	template<class T>
	friend T top2(Stack<T> &stack);
    
	template<class T>
	friend void replace2(Stack<T> &stack, T oldItem, T newItem);

	template<class T>
	friend bool identical2(Stack<T> &stack1, Stack<T> &stack2);



	/***** Function Members *****/
public:
	/* --- Constructor ---
	 *
	 * Precondition:  A stack has been declared.
	 * Postcondition: The stack has been constructed as an 
	 *                 empty stack.
	 ************************************************************/

	Stack( );

	/* --- Is the Stack empty? ---
	 * Receive: Stack containing this function (implicitly)
	 * Returns: True iff the Stack containing this function is empty
	 *****************************************************************/

	bool empty( ) const;

	/* --- Add a value to the stack ---
	 *
	 * Receive: The Stack containing this function (implicitly)
	 *          A value to be added to a Stack
	 * Return:  The Stack (implicitly), with value added at its 
	 *          top, provided there's space
	 * Output:  "Stack full" message if no space for value
	 *************************************************************/

	void push(const StackElement & value);


	/* --- Display values stored in the stack ---
	 *
	 * Receive: The Stack containing this function (implicitly)
	 *          The ostream out
	 * Output:  The Stack's contents, from top down, to out
	 *************************************************************/

	void display(ostream & out) const;


	/* --- Return value at top of the stack ---
	 *
	 * Receive: The Stack containing this function (implicitly)
	 * Return:  The value at the top of the Stack, if nonempty;
	 *          else a "garbage value"
	 * Output:  "Stack empty" message if stack is empty
	 *************************************************************/

	StackElement top( ) const;


	/* --- Remove value at top of the stack ---
	 *
	 * Receive: The Stack containing this function (implicitly)
	 * Return:  The Stack containing this function with its top
	 *          value (if any) removed
	 * Output:  "Stack-empty" message if stack is empty.
	 *************************************************************/

	void pop( );

	void replace(StackElement oldItem, StackElement newItem);

	bool identical(Stack &stack);


	/***** Data Members *****/
private:

	  StackElement myArray[STACK_CAPACITY];
	  int myTop;

}; 		// end of class declaration


//--- Definition of Class Constructor
template <class StackElement>
inline Stack<StackElement>::Stack( ) 
{ myTop = -1; } 


//--- Definition of empty
template <class StackElement>
inline bool Stack<StackElement>::empty() const
{ return (myTop == -1); } 


//--- Definition of push()
template <class StackElement>
void Stack<StackElement>::push(const StackElement & value)
{
  if (myTop < STACK_CAPACITY - 1)  // Preserve stack invariant
  {
    ++myTop;
    myArray[myTop] = value;
  }                      // or simply, myArray[++myTop] = value;
  else
    cerr << "*** Stack is full -- can't add new value ***\n"
         << "Must Increase value of STACK_CAPACITY in Stack.h\n";
}

//--- Definition of display()
template <class StackElement>
void Stack<StackElement>::display(ostream & out) const
{
  for (int i = myTop; i >= 0; i--) 
    out << myArray[i] << endl;
}


//--- Definition of top()

template <class StackElement>
StackElement Stack<StackElement>::top() const
{
  if (myTop >= 0)
    return (myArray[myTop]);
  cerr << "*** Stack is empty -- returning 'garbage value' ***\n";
  return myArray[STACK_CAPACITY -1];
}


//--- Definition of pop()
template <class StackElement>
void Stack<StackElement>::pop()
{
  if (myTop >= 0)    // Preserve stack invariant
    myTop--;
  else
    cerr << "*** Stack is empty -- can't remove a value ***\n";
}

template <class StackElement>
void Stack<StackElement>::replace(StackElement oldItem, StackElement newItem)
{
	for(int i = 0; i <= myTop; i++)
	{
		if (myArray[i] == oldItem)
			myArray[i] = newItem;
	}
}

template <class StackElement>
bool Stack<StackElement>::identical(Stack &stack)
{
	bool same;
	for(int i = 0; i < myTop; i++)
	{
		if (myArray[i] == stack.myArray[i])
			same = true;
        else
			same = false;
	}
	return same;
}
//---------------------------------Friend Functions--------------------------
template <class StackElement>
StackElement top2(Stack<StackElement>  &stack)
{
	if (stack.myTop >= 0)
    	return (stack.myArray[stack.myTop]);
  	cerr << "*** Stack is empty -- returning 'garbage value' ***\n";
  		return stack.myArray[STACK_CAPACITY -1];
}

template<class StackElement>
void replace2(Stack<StackElement> &stack, StackElement oldItem, 
               StackElement newItem)
{
	for(int i = 0; i <= stack.myTop; i++)
	{
		if (stack.myArray[i] == oldItem)
			stack.myArray[i] = newItem;
	}
}

template<class StackElement>
bool identical2(Stack<StackElement> &stack1, Stack<StackElement> &stack2)
{
	bool same;
	for(int i = 0; i <= STACK_CAPACITY; i++)
	{
		if (stack1.myArray[i] == stack2.myArray[i])
			same = true;
        else
			same = false;
	}
	return same;
}
//---------------------------Non-member Functions -----------------------
template<class StackElement>
StackElement top3(Stack<StackElement> &stack)
{
	StackElement temp;
	temp = stack.top();
	return temp;
}

template <class StackElement>
void replace3(Stack <StackElement> &stack, StackElement olditem, StackElement newitem)
{
     Stack<StackElement> tempStack;
     StackElement temp;


     while(!stack.empty())
     {
          temp = stack.top();
          if(temp==olditem)
               temp = newitem;
		  stack.pop();	
          tempStack.push(temp);
     }

     while(!tempStack.empty())
     {
          temp = tempStack.top();
		  tempStack.pop();
          stack.push(temp);
     }
    

}

template<class StackElement>
bool identical3(Stack<StackElement> &stack1, Stack<StackElement> &stack2)
{
	Stack<StackElement> tempStack;
	StackElement temp;
	bool same = true;
	while(!stack1.empty() && !stack2.empty())
	{
	 	if (stack1.top()==stack2.top())
		{
			temp = stack1.top();
			stack1.pop();
			stack2.pop();
			tempStack.push(temp);
		}
		else
		{
			same = false;
			break;
        }
	}
	same = same && stack1.empty() && stack2.empty();
	while(!tempStack.empty())
	{
		temp = tempStack.top();
	 	stack1.push(temp);
		stack2.push(temp);
		tempStack.pop();
		
	}
	return same;
}

template<class StackElement>
int size(Stack<StackElement> &stack)
{
	Stack<StackElement> tempStack;
    StackElement temp;
	
	int num = 0;
	while(!stack.empty())
	{
		temp = stack.top();
		stack.pop();
		tempStack.push(temp);
		num++;
	}
	
	while(!tempStack.empty())
	{
	 	temp = tempStack.top();
		tempStack.pop();
		stack.push(temp);		
	}
	return num;
}
#endif
























