#include "binaryTree.h"
#include <iostream>

using namespace std;

int main()
{
cout<<"Cannot build the tree without the insert function."<<endl ;

binaryTreeType<char> bt ;
//this still needs to be fixed

bt.createTree1() ;

PostorderTreeEnumerator<char> p;

PostorderTreeEnumerator<char> pote(bt);

bt.preorderTraversal() ;
cout << endl ;
bt.inorderTraversal();
cout << endl ;
bt.postorderTraversal() ;
cout << endl ;

cout<<bt.treeNodeCount()<<endl;
cout<<bt.treeLeavesCount()<<endl;


return 0;
}