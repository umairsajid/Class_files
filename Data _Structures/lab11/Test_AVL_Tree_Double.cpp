/** Program to test binary tree class */

#include <string>
#include <iostream>
#include <fstream>
#include "Binary_Search_Tree.h"
#include "AVL_Tree.h"
#include "pre_order_traversal.h"

using namespace std;

int main() 
{
  Binary_Search_Tree<double>* the_tree = NULL;
    the_tree = new AVL_Tree<double>; 

  double value;
  while (cin >> value) {
    if (value == 0.0) break;
    the_tree->insert(value);
    pre_order_traversal(*the_tree, cout, 0);
  }
  cin.clear();
  while (cin >> value) {
    if (value == 0.0) break;
    the_tree->erase(value);
    pre_order_traversal(*the_tree, cout, 0);
  }

  cin.get() ;
}


  
