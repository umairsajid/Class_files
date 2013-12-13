//Header File Binary Tree –
//Header File Binary Tree - NON recursive traversal
#ifndef H_binaryTree
#define H_binaryTree

#include <iostream>
#include <stack>

using namespace std;

     //Definition of the node
template<class elemType>
struct nodeType
{
   elemType info;
   nodeType<elemType> *llink;
   nodeType<elemType> *rlink;
};

template<class elemType>
class binaryTreeType;


template<class elemType>
class PostorderTreeEnumerator
{
public:
PostorderTreeEnumerator(const binaryTreeType<elemType> &otherTree);

PostorderTreeEnumerator();

elemType nextElement();

bool hasMoreElements();

void slideLeft(nodeType<elemType> &otherNode);

void reset();

//friend class binaryTreeType<elemType>;

private:
stack<nodeType<elemType>* > st;


};

template<class elemType>
PostorderTreeEnumerator<elemType>::PostorderTreeEnumerator(const binaryTreeType<elemType> &otherTree)
{
    slideLeft(otherTree.root);
}

template<class elemType>
PostorderTreeEnumerator<elemType>::PostorderTreeEnumerator()
{

};

template<class elemType>
elemType PostorderTreeEnumerator<elemType>::nextElement()
{
    nodeType<elemType> *current, parent;
    st.pop(&current);

    if (!st.isEmpty())
    {
       st.pop(parent);
       if(parent -> rlink != current)
        slideleft(parent -> rlink);
    }
}

template<class elemType>
bool PostorderTreeEnumerator<elemType>::hasMoreElements()
{
    return st.isEmpty();
}

template<class elemType>
void PostorderTreeEnumerator<elemType>::slideLeft(nodeType<elemType> &otherNode)
{
  nodeType<elemType> *current;
  current = this->root;

  while(current != NULL)
  {
    st.push(current);
    current = current->llink;
  }
  if(st.isEmpty())
    return;
  st.pop(current);
  if(current->rlink != NULL)
    slideleft(current->rlink);
}

template<class elemType>
void PostorderTreeEnumerator<elemType>::reset()
{

}

    //Definition of the class
template <class elemType>
class binaryTreeType
{
public:
    const binaryTreeType<elemType>& operator=
                 (const binaryTreeType<elemType>&);
      //Overload the assignment operator.
    bool isEmpty();
      //Function to determine if the binary tree is empty.
      //Postcondition: Returns true if the binary tree is empty;
      // otherwise, returns false.
    void inorderTraversal();
      //Function to do an inorder traversal of the binary tree.
      //Postcondition: The nodes of the binary tree are output
      // in the inorder sequence.
    void preorderTraversal();
      //Function to do a preorder traversal of the binary tree.
      //Postcondition: The nodes of the binary tree are output
      // in the preorder sequence.
    void postorderTraversal();
      //Function to do a postorder traversal of the binary tree.
      //Postcondition: The nodes of the binary tree are output
      // in the postorder sequence.

    int treeHeight();
      //Function to deteremine the height of the binary tree.
      //Postcondition: The height of the binary tree is returned.
    int treeNodeCount();
      //Function to determine the number of nodes in the
      //binary tree.
      //Postcondition: The number of nodes in the binary tree
      // is returned.
    int treeLeavesCount();
      //Function to determine the number of leaves in the
      //binary tree.
      //Postcondition: The number of leaves in the binary tree
      // is returned.
    void destroyTree();
      //Deallocates the memory space occupied by the binary tree.
      //Postcondition: root = NULL;

void nonRecursiveInTraversal();
void nonRecursivePreTraversal();
void nonRecursivePostTraversal();

    binaryTreeType(const binaryTreeType<elemType>& otherTree);
      //copy constructor

    binaryTreeType();
      //default constructor

    ~binaryTreeType();
      //destructor

void createTree1();

void inorderTraversal(void (*visit) (elemType&));
//Function to do an inorder traversal of the binary tree.
//The parameter visit, which is a function, specifies
//the action to be taken at each node.

PostorderTreeEnumerator<elemType> getPostorderEnumerator();

friend class PostorderTreeEnumerator<elemType>;


protected:
    nodeType<elemType> *root;

private:
    void copyTree(nodeType<elemType>* &copiedTreeRoot,
                  nodeType<elemType>* otherTreeRoot);
      //Function to make a copy of the binary tree to
      //which otherTreeRoot points.
      //Postcondition: The pointer copiedTreeRoot points to
      // the root of the copied binary tree.

    void destroy(nodeType<elemType>* &p);
      //Function to destroy the binary tree to which p points.
      //Postcondition: The nodes of the binary tree to which
      // p points are deallocated.
      // p = NULL.

    void inorder(nodeType<elemType> *p);
      //Function to do an inorder traversal of the binary
      //tree to which p points.
      //Postcondition: The nodes of the binary tree to which p
      // points are output in the inorder sequence.
    void preorder(nodeType<elemType> *p);
      //Function to do a preorder traversal of the binary
      //tree to which p points.
      //Postcondition: The nodes of the binary tree to which p
      // points are output in the preorder sequence.
    void postorder(nodeType<elemType> *p);
      //Function to do a postorder traversal of the binary
      //tree to which p points.
      //Postcondition: The nodes of the binary tree to which p
      // points are output in the postorder sequence.

    int height(nodeType<elemType> *p);
      //Function to determine the height of the binary tree
      //to which p points.
      //Postcondition: The height of the binary tree to which p
      // points is returned.

    int max(int x, int y);
      //Function to determine the larger of x and y.
      //Postcondition: The larger of x and y is returned.

    int nodeCount(nodeType<elemType> *p);
      //Function to determine the number of nodes in the binary
      //tree to which p points.
      //Postcondition: The number of nodes in the binary tree
      // to which p points is returned.

    int leavesCount(nodeType<elemType> *p);
      //Function to determine the number of leaves in the binary
      //tree to which p points.
      //Postcondition: The number of nodes in the binary tree
      // to which p points is returned.

void inorder(nodeType<elemType> *p, void (*visit) (elemType&));
//Function to do an inorder traversal of the binary
//tree, starting at the node specified by the parameter p.
//The parameter visit, which is a function, specifies the
//action to be taken at each node.

PostorderTreeEnumerator<elemType> *postTreeEnum;
};



template<class elemType>
PostorderTreeEnumerator<elemType> binaryTreeType<elemType>::getPostorderEnumerator()
{
return postTreeEnum;
}

//Definition of member functions

template<class elemType>
binaryTreeType<elemType>::binaryTreeType()
{
root = NULL;
}

template<class elemType>
bool binaryTreeType<elemType>::isEmpty()
{
return (root == NULL);
}

template<class elemType>
void binaryTreeType<elemType>::inorderTraversal()
{
inorder(root);
}

template<class elemType>
void binaryTreeType<elemType>::preorderTraversal()
{
preorder(root);
}

template<class elemType>
void binaryTreeType<elemType>::postorderTraversal()
{
postorder(root);
}

template<class elemType>
int binaryTreeType<elemType>::treeHeight()
{
return height(root);
}

template<class elemType>
int binaryTreeType<elemType>::treeNodeCount()
{
return nodeCount(root);
}

template<class elemType>
int binaryTreeType<elemType>::treeLeavesCount()
{
return leavesCount(root);
}

template <class elemType>
void binaryTreeType<elemType>::copyTree
                      (nodeType<elemType>* &copiedTreeRoot,
nodeType<elemType>* otherTreeRoot)
{
if(otherTreeRoot == NULL)
copiedTreeRoot = NULL;
else
{
copiedTreeRoot = new nodeType<elemType>;
copiedTreeRoot->info = otherTreeRoot->info;
copyTree(copiedTreeRoot->llink, otherTreeRoot->llink);
copyTree(copiedTreeRoot->rlink, otherTreeRoot->rlink);
}
} //end copyTree

template<class elemType>
void binaryTreeType<elemType>::inorder(nodeType<elemType> *p)
{
if(p != NULL)
{
inorder(p->llink);
cout<<p->info<<" ";
inorder(p->rlink);
}
}

template<class elemType>
void binaryTreeType<elemType>::preorder(nodeType<elemType> *p)
{
if(p != NULL)
{
cout<<p->info<<" ";
preorder(p->llink);
preorder(p->rlink);
}
}

template<class elemType>
void binaryTreeType<elemType>::postorder(nodeType<elemType> *p)
{
if(p != NULL)
{
postorder(p->llink);
postorder(p->rlink);
cout<<p->info<<" ";
}
}

   //Overload the assignment operator
template<class elemType>
const binaryTreeType<elemType>& binaryTreeType<elemType>::
           operator=(const binaryTreeType<elemType>& otherTree)
{
     
if(this != &otherTree) //avoid self-copy
{
if(root != NULL) //if the binary tree is not empty,
//destroy the binary tree
destroy(root);

if(otherTree.root == NULL) //otherTree is empty
root = NULL;
else
copyTree(root, otherTree.root);
}//end else

   return *this;
}

template <class elemType>
void binaryTreeType<elemType>::destroy(nodeType<elemType>* &p)
{
if(p != NULL)
{
destroy(p->llink);
destroy(p->rlink);
delete p;
p = NULL;
}
}

template <class elemType>
void binaryTreeType<elemType>::destroyTree()
{
destroy(root);
}

//copy constructor
template <class elemType>
binaryTreeType<elemType>::binaryTreeType
              (const binaryTreeType<elemType>& otherTree)
{
if(otherTree.root == NULL) //otherTree is empty
root = NULL;
else
copyTree(root, otherTree.root);
}

template <class elemType>
binaryTreeType<elemType>::~binaryTreeType()
{
destroy(root);
}

template<class elemType>
int binaryTreeType<elemType>::height(nodeType<elemType> *p)
{
if(p == NULL)
return 0;
else
return 1 + max(height(p->llink), height(p->rlink));
}

template<class elemType>
int binaryTreeType<elemType>::max(int x, int y)
{
if(x >= y)
return x;
else
return y;
}

template<class elemType>
int binaryTreeType<elemType>::nodeCount(nodeType<elemType> *p)
{
if(p == NULL)
return 0;
else
return 1 + nodeCount(p->llink) + nodeCount(p->rlink);
}

template<class elemType>
int binaryTreeType<elemType>::leavesCount(nodeType<elemType> *p)
{
if(p == NULL)
return 0;
else
{
if(p->llink == NULL && p->rlink == NULL)
return 1;
else
return leavesCount(p->llink) + leavesCount(p->rlink);
}
}

//NonRecursive Traversal algorithms

template <class elemType>
void binaryTreeType<elemType>::nonRecursiveInTraversal()
{
stack<nodeType<elemType>* > st;
nodeType<elemType> *current;
current = root;

while((current != NULL) || (!st.empty()))
    if (current != NULL)
  {
st.push(current);
current = current->llink;
}
else
   {
current = st.top();
st.pop();
cout<<current->info<<" ";
current = current->rlink;
}

cout<<endl;
}

template <class elemType>
void binaryTreeType<elemType>::nonRecursivePreTraversal()
{

stack<nodeType<elemType>*> st;
nodeType<elemType> *current;

current = root;

while((current != NULL) || (!st.empty()))
if(current != NULL)
{
cout<<current->info<<" ";
st.push(current);
current = current->llink;
}
else
{
current = st.top();
st.pop();
current = current->rlink;
}

cout<<endl;
}

template <class elemType>
void binaryTreeType<elemType>::nonRecursivePostTraversal()
{
cout<<"See lecture notes – Lab "<<endl;
}

// Traversal with visit functions

template <class elemType>
void binaryTreeType<elemType>::inorderTraversal
(void (*visit) (elemType& item))
{
inorder(root, *visit);
}

template <class elemType>
void binaryTreeType<elemType>::inorder(nodeType<elemType>* p,
void (*visit) (elemType& item))
{
if(p != NULL)
{
inorder(p->llink, *visit);
(*visit)(p->info);
inorder(p->rlink, *visit);
}
}

// create a char tree

template <class elemType>
void binaryTreeType<elemType>::createTree1()
{
nodeType<char>* tmp1, *tmp2, *tmp3, *tmp4 ;

tmp1 = new nodeType<elemType> ;
tmp1->info = 'I' ;
tmp1->llink = NULL ;
tmp1->rlink = NULL ;

tmp2 = new nodeType<elemType> ;
tmp2->info = 'E' ;
tmp2->llink = NULL ;
tmp2->rlink = tmp1 ;

tmp1 = new nodeType<elemType> ;
tmp1->info = 'J' ;
tmp1->llink = NULL ;
tmp1->rlink = NULL ;

tmp3 = new nodeType<elemType> ;
tmp3->info = 'F' ;
tmp3->llink = NULL ;
tmp3->rlink = tmp1 ;

tmp1 = new nodeType<elemType> ;
tmp1->info = 'C' ;
tmp1->llink = tmp2 ;
tmp1->rlink = tmp3 ;

tmp2 = new nodeType<elemType> ;
tmp2->info = 'K' ;
tmp2->llink = NULL ;
tmp2->rlink = NULL ;

tmp3 = new nodeType<elemType> ;
tmp3->info = 'L' ;
tmp3->llink = NULL ;
tmp3->rlink = NULL ;

tmp4 = new nodeType<elemType> ;
tmp4->info = 'H' ;
tmp4->llink = tmp2 ;
tmp4->rlink = tmp3 ;

tmp2 = new nodeType<elemType> ;
tmp2->info = 'G' ;
tmp2->llink = NULL ;
tmp2->rlink = NULL ;

tmp3 = new nodeType<elemType> ;
tmp3->info = 'D' ;
tmp3->llink = tmp2 ;
tmp3->rlink = tmp4 ;

tmp2 = new nodeType<elemType> ;
tmp2->info = 'B' ;
tmp2->llink = tmp1 ;
tmp2->rlink = tmp3 ;

tmp1 = new nodeType<elemType> ;
tmp1->info = 'A' ;
tmp1->llink = tmp2 ;
tmp1->rlink = NULL ;

root = (nodeType<elemType>*)tmp1 ;

}


#endif