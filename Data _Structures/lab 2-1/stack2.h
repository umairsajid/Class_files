//      File Stack2.h
//       ItemType : the class definition of the objects on the stack.
//       MAX_ITEMS: the maximum number of items on the stack. 
const int MAX_ITEMS = 128;
typedef int ItemType;
class StackType
{
friend void top2();
public:
    StackType();
    // Class constructor.
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
    // Post: Top element has been removed from stack.
    //       item is a copy of the removed item.
        void top() const;
        // Function: Returns copy of the last item put onto the stack
        // Pre: Stack is not empty
        // Post: Function value = copy of time at top of stack. Stack is not changed
private:
    int top;
    ItemType  items[MAX_ITEMS];  // items is a pointer to an item
};

//      File Stack2.cpp
// The function definitions for the non-templated, non-dynamic storage-
// allocation version of class StackType.

#include "Stack2.h"
StackType::StackType()
{
    top = -1;
}
void StackType::MakeEmpty()
{
    top = -1;
}
bool StackType::IsEmpty() const
{
    return (top == -1);
}
bool StackType::IsFull() const
{
    return (top == MAX_ITEMS-1);
}
void StackType::Push(ItemType newItem) 
{
    top++;
    items[top] = newItem;
}
void StackType::Pop(ItemType& item) 
{
    item = items[top];
    top--;
}
void StackType::Top() const
{
        return top;
}
void Top2(StackType* st)
{
        return st.top;

