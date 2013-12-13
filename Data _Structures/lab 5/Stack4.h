// Stack4.h: header file for Stack ADT 
// Class is templated.

#include <iostream>

using namespace std;


template <class ItemType>
struct NodeType
{
	ItemType info ;
	NodeType  *next ;
} ;

template<class ItemType>
class StackType
{

	template <class T>
    friend ostream& operator<<(ostream& out, const StackType<T>& stk);
public:
    StackType();
    // Class constructor.
	StackType(StackType<ItemType> &st);
	// Class copy constructutor
    ~StackType();
    // Class destructor.
    void MakeEmpty();
    // Function:  Sets stack to an empty state.
    // Post: Stack is empty.
    bool IsFull() const;
    // Function: Determines whether the stack is full.
    // Pre:  Stack has been initialized.
    // Post: Function value = (stack is full)
    bool IsEmpty() const;
    // Function: Determines whether the stack is empty.
    // Pre:  Stack has been initialized.
    // Post: Function value = (stack is empty)
    void Push(ItemType item);
    // Function: Adds newItem to the top of the stack.
    // Pre:  Stack has been initialized and is not full.
    // Post: newItem is at the top of the stack.
    void Pop(ItemType& item);
    // Function: Removes top item from the stack and returns it in item.
    // Pre:  Stack has been initialized and is not empty.
    // Post: Top element has been removed from stack. item is a copy of the removed item.
	StackType<ItemType> &operator=(const StackType<ItemType> & original);
	int size();

private:
    NodeType<ItemType>* topPtr;
};

 
// Implementation file for Stack ADT.
// Class definition is in "Stack4.h".

// Class is templated; implementation is linked.


template <class ItemType>
StackType<ItemType>::StackType()
{
    topPtr = NULL;
};

template<class ItemType>
void StackType<ItemType>::Pop(ItemType& item)
{
    NodeType<ItemType>* tempPtr;
    tempPtr = topPtr;
    item = topPtr ->info;
    topPtr = topPtr->next;
    delete tempPtr;
}

template<class ItemType>
bool StackType<ItemType>::IsFull() const
{
    NodeType<ItemType>* ptr;
    ptr = new NodeType<ItemType>;
    if (ptr == NULL)
	    return true;
    else
    {
	    delete ptr;
	    return false;
    }
}

template <class ItemType>
void StackType<ItemType>::Push(ItemType newItem)
{
    NodeType<ItemType>* ptr;
    ptr = new NodeType<ItemType>;
    ptr->info = newItem;
    ptr->next = topPtr;
    topPtr = ptr;
}
template <class ItemType>
void StackType<ItemType>::MakeEmpty() 
{
    NodeType<ItemType>* tempPtr;

    while (topPtr != NULL)
    {
	    tempPtr = topPtr;
	    topPtr = topPtr->next;
	    delete tempPtr;
     }
}

template <class ItemType>
StackType<ItemType>::~StackType()
{
    NodeType<ItemType>* tempPtr;

    while (topPtr != NULL)
    {
	    tempPtr = topPtr;
	    topPtr = topPtr->next;
	    delete tempPtr;
     }
}

template <class ItemType>
bool StackType<ItemType>::IsEmpty() const
{
    return (topPtr == NULL);
}

template <class ItemType>
StackType<ItemType>::StackType(StackType<ItemType> &st)
{
     NodeType<ItemType> *ptrOrig, *ptrDest;
     ptrOrig = st.topPtr;
     if(ptrOrig == NULL)
     {
          this -> topPtr = NULL;
     }
     else
     {
          ptrDest = new NodeType<ItemType>;
          ptrDest -> info = ptrOrig -> info;
          this  -> topPtr = ptrDest;
          ptrOrig = ptrOrig ->  next;
     
          while(ptrOrig != NULL)
          {
            ptrDest -> next = new NodeType<ItemType>;
            ptrDest = ptrDest -> next;
            ptrDest -> info = ptrOrig -> info;
            ptrOrig = ptrOrig -> next;
          } 
     
     ptrDest -> next = NULL;
     }
}

//*** Definition of operator=
template <class ItemType>
StackType<ItemType> &StackType<ItemType>::operator=(const StackType<ItemType> & original)
{
		
	NodeType<ItemType> *ptrOrig, *ptrDest;

    if (this != &original)		 // check that not st = st
    {
		this -> MakeEmpty();		// destroy previous array
		ptrOrig = original.topPtr;  
		if(ptrOrig == NULL)
     {
          this -> topPtr = NULL;
     }
     else
     {
          ptrDest = new NodeType<ItemType>;
          ptrDest -> info = ptrOrig -> info;
          this  -> topPtr = ptrDest;
          ptrOrig = ptrOrig ->  next;
     
          while(ptrOrig != NULL)
          {
            ptrDest -> next = new NodeType<ItemType>;
            ptrDest = ptrDest -> next;
            ptrDest -> info = ptrOrig -> info;
            ptrOrig = ptrOrig -> next;
          } 
     
     ptrDest -> next = NULL;
     } 
		}
       return *this; 		// return reference to  this object
}

template <class ItemType>
ostream& operator<<(ostream& out, const StackType<ItemType>& stk)
{
	NodeType<ItemType> *tempPtr;
	ItemType item;
	tempPtr = stk.topPtr;
	while(tempPtr != NULL)
	{
		item = tempPtr -> info;
		out << item << " ";
		tempPtr = tempPtr -> next;
	}	
	return out;
}

template <class ItemType>
int StackType<ItemType>::size()
{
	NodeType<ItemType> *tempPtr;
	int size = 0;

	tempPtr = this -> topPtr;
	while(tempPtr != NULL)
	{
		tempPtr = tempPtr -> next;
		size++;	
	}
	return size;
}
