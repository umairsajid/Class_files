#ifndef AVL_TREE_H
#define AVL_TREE_H

#include "BST_With_Rotate.h"
#include "AVLNode.h"

/** Definition of the AVL Binary Search Tree class.
    @param Item_Type The type of item to be stored in the tree
    Note: Item_Type must define the less-than operator as a 
    total ordering.
*/
template<typename Item_Type>
class AVL_Tree : public BST_With_Rotate<Item_Type> {
  
 public:
  // Constructor
  /** Construct an empty AVL_Tree */
  AVL_Tree() : BST_With_Rotate<Item_Type>() {}
  
  // Public Member Functions
  /** Insert an item into the tree by calling overridden insert function. 
      post: The item is in the tree.
      @param item The item to be inserted
      @return true only if the item was not already in the tree
  */
  virtual bool insert(const Item_Type& item) {
    return insert(this->root, item); }
  
  /*<exercise chapter="11" type="programming-project" number="5">*/
  /** Remove an item from the tree by calling overridden erase function. 
      post: The item is no longer in the tree.
      @param item The item to be removed
      @return true only if the item was in the tree
  */
  virtual bool erase(const Item_Type& item) {
    return erase(this->root, item); }
  /*</exercise>*/
  
 private:
  
  // Private member functions declarations
  /** Insert an item into the tree. Called by starter function (see
      listing 11.2) with this->root as first argument.
      post: The item is in the tree.
      @param local_root A reference to the current root
      @param item The item to be inserted
      @return true only if the item was not already in the
  */
  virtual bool insert(BTNode<Item_Type>*&, const Item_Type&);
  
  /** Correct a critical left balance condition
      pre:  local_root is the root of an AVL subtree that is
      critically heavy.
      post: balance is restored
      @param local_root is the root of the AVL subtree
      that needs rebalancing
  */
  void rebalance_left(BTNode<Item_Type>*& local_root);
  
  /** Correct a critical right balance condition
      pre:  local_root is the root of an AVL subtree that is
      critically heavy.
      post: balance is restored
      @param local_root is the root of the AVL subtree
      that needs rebalancing
  */
  void rebalance_right(BTNode<Item_Type>*& local_root);

  /*<exercise chapter="11" type="programming-project" number="5">*/
  /** Remove an item from the tree 
      @param local_root A reference to the current ro      
      @param item The item to be removed
      @return true only if the item was in the tree
      Post: The item is no longer in the tree.
  */
  virtual bool erase(BTNode<Item_Type>*& local_root, 
		     const Item_Type& item);
  
  /** Find a replacement for a node that is being deleted
      This function finds the right-most local root that 
      does not have a right child. The data in this local_root
      replaces the data in old_root. The pointer to local_root
      is then saved in old_root and local_root is replaced
      by its left child.
      @param old_root Reference to the pointer to old parent
      @param local_root Reference to the pointer to local root  
  */
  virtual void replace_parent(BTNode<Item_Type>*& old_root, 
		      BTNode<Item_Type>*& local_root);

  
  /** Correct a critical left balance condition due to erase
      pre:  local_root is the root of an AVL subtree that is
      critically heavy.
      post: balance is restored
      @param local_root is the root of the AVL subtree
      that needs rebalancing
  */
  void erase_rebalance_left(BTNode<Item_Type>*& local_root);
  
  /** Correct a critical right balance condition due to erase
      pre:  local_root is the root of an AVL subtree that is
      critically heavy.
      post: balance is restored
      @param local_root is the root of the AVL subtree
      that needs rebalancing
  */
  void erase_rebalance_right(BTNode<Item_Type>*& local_root);
  /*</exercise>*/

  // Data Fields
  /** A flag to indicate that the height of the tree has increased */
  bool increase;
  /*<exercise chapter="11" type="programming-project" number="5">*/
  /** A flag to indicate that the height of the tree has decreased */
  bool decrease;
  /*</exercise>*/

}; // End of AVL_Tree class definition

// Implementation of member functions
template<typename Item_Type>
bool 
AVL_Tree<Item_Type>::insert(BTNode<Item_Type>*& local_root,
			    const Item_Type& item) {
  if (local_root == NULL) {
    local_root = new AVLNode<Item_Type>(item);
    increase = true;
    return true;
  }
  else {
    if (item < local_root->data) {
      bool return_value = insert(local_root->left, item);
      if (increase) {
	// Height of the left subtree has increased
	AVLNode<Item_Type>* AVL_local_root =
	  dynamic_cast<AVLNode<Item_Type>*>(local_root);
	switch (AVL_local_root->balance) {
	case AVLNode<Item_Type>::BALANCED :
	  // local root is now left heavy
	  AVL_local_root->balance = AVLNode<Item_Type>::LEFT_HEAVY;
	  break;
	case AVLNode<Item_Type>::RIGHT_HEAVY :
	  // local root is now right heavy
	  AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
	  // Overall height of local root remains the same
	  increase = false;
	  break;
	case AVLNode<Item_Type>::LEFT_HEAVY :
	  // local root is now critically unbalanced
	  rebalance_left(local_root);
	  increase = false;
	  break;
	} // End switch
      } // End if (increase)
      return return_value;
    }
    else if (local_root->data < item) {
      /*<exercise chapter="11" section="2" type="programming" number="2">*/
      bool return_value = 
	insert(local_root->right, item);
      if (increase) {
	AVLNode<Item_Type>* AVL_local_root =
	  dynamic_cast<AVLNode<Item_Type>*>(local_root);
	// Height of the right subtree has increased
	switch (AVL_local_root->balance) {
	case AVLNode<Item_Type>::BALANCED:
	  // local root is now right heavy
	  AVL_local_root->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
	  break;
	case AVLNode<Item_Type>::LEFT_HEAVY:
	  // local root is now balanced
	  AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
	  // Overall height of local root remains the same
	  increase = false;
	  break;
	case AVLNode<Item_Type>::RIGHT_HEAVY:
	  // local root is now critically balanced
	  rebalance_right(local_root);
	  increase = false;
	  break;
	}
      }
      return return_value;
      /*</exercise>*/
    }
    else {
      increase = false;
      return false;
    }
  }
}

template<typename Item_Type>
  void AVL_Tree<Item_Type>::rebalance_left(BTNode<Item_Type>*& local_root) {

  // Cast local_root to an AVLNode pointer
  AVLNode<Item_Type>* AVL_local_root =
    dynamic_cast<AVLNode<Item_Type>*>(local_root);
  // Obtain reference to the left child
  AVLNode<Item_Type>* left_child = 
    dynamic_cast<AVLNode<Item_Type>*>(local_root->left);
  // See whether left-right-heavy
  if (left_child->balance == AVLNode<Item_Type>::RIGHT_HEAVY) {
    // Obtain a reference to the left-right child
    AVLNode<Item_Type>* left_right_child = 
      dynamic_cast<AVLNode<Item_Type>*>(left_child->right);
    // Adjust the balances to be the new values after rotations are 
    // performed
    if (left_right_child->balance == AVLNode<Item_Type>::LEFT_HEAVY) {
      left_child->balance = AVLNode<Item_Type>::BALANCED;
      left_right_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
    } else if (left_right_child->balance 
               == AVLNode<Item_Type>::BALANCED) {
      left_child->balance = AVLNode<Item_Type>::BALANCED;
      left_right_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    } else {
      left_child->balance = AVLNode<Item_Type>::LEFT_HEAVY;
      left_right_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    }
    // Perform left rotation
    rotate_left(local_root->left);
  } else { // Left-Left case
    /* In this case the left child (the new root) and the
       local root (new right child) will both be balanced
       after the rotation.
    */
    left_child->balance = AVLNode<Item_Type>::BALANCED;
    AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
  }
  // Finally rotate right
  rotate_right(local_root);
}

template<typename Item_Type>
void
AVL_Tree<Item_Type>::rebalance_right(BTNode<Item_Type>*& local_root) {
  /*<exercise chapter="11" section="2" type="programming" number="1">*/
  // Cast local_root to an AVLNode pointer
  AVLNode<Item_Type>* AVL_local_root =
    dynamic_cast<AVLNode<Item_Type>*>(local_root);
  // Obtain reference to the right_child
  AVLNode<Item_Type>* right_child = 
    dynamic_cast<AVLNode<Item_Type>*>(local_root->right);
  // See whether right-left heavy
  if (right_child->balance == AVLNode<Item_Type>::LEFT_HEAVY) {
    // Obtain a reference to the right-left child
    AVLNode<Item_Type>* right_left_child = 
      dynamic_cast<AVLNode<Item_Type>*>(right_child->left);
    // Adjust the balances to be the new values after rotations are performed
    if (right_left_child->balance == AVLNode<Item_Type>::RIGHT_HEAVY) {
      right_child->balance = AVLNode<Item_Type>::BALANCED;
      right_left_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::LEFT_HEAVY;
    } else if (right_left_child->balance == AVLNode<Item_Type>::BALANCED) {
      right_child->balance = AVLNode<Item_Type>::BALANCED;
      right_left_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    } else {
      right_child->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
      right_left_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    }
    // Perform right rotation
    rotate_right(local_root->right);
  } else { // Right-Right case
    /* In this case the right child (the new root) and the
       local root (new left child) will both be balanced
       after the rotation
    */
    right_child->balance = AVLNode<Item_Type>::BALANCED;
    AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
  }
  // Finally rotate left
  rotate_left(local_root);
  /*</exercise>*/
}

/*<exercise chapter="11" type="programming-project" number="5">*/
template<typename Item_Type>
bool AVL_Tree<Item_Type>::erase(BTNode<Item_Type>*& local_root,
					  const Item_Type& item) {
  if (local_root == NULL) {
    decrease = false;
    return false;
  } else {
    if (item < local_root->data) {
      bool return_value = 
	erase(local_root->left, item);
      if (decrease) {
	// Height of left sub-tree has decreased
	AVLNode<Item_Type>* AVL_local_root = 
	  dynamic_cast<AVLNode<Item_Type>*>(local_root);
	switch (AVL_local_root->balance) {
	case AVLNode<Item_Type>::BALANCED:
	  // local root is now right heavy
	  AVL_local_root->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
	  // Overall height of local root remains the same
	  decrease = false;
	  break;
	case AVLNode<Item_Type>::LEFT_HEAVY:
	  // local root is now balanced
	  AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
	  break;
	case AVLNode<Item_Type>::RIGHT_HEAVY:
	  // local root is critical
	  erase_rebalance_right(local_root);
	}
      }
      return return_value;
    }
    else if (local_root->data < item) {
      bool return_value = 
	erase(local_root->right, item);
      if (decrease) {
	AVLNode<Item_Type>* AVL_local_root =
	  dynamic_cast<AVLNode<Item_Type>*>(local_root);
	// Height of right sub-tree has decreased
	switch (AVL_local_root->balance) {
	case AVLNode<Item_Type>::BALANCED:
	  // local root is now left heavy
	  AVL_local_root->balance = AVLNode<Item_Type>::LEFT_HEAVY;
	  // Overall height of local root remains the same
	  decrease = false;
	  break;
	case AVLNode<Item_Type>::RIGHT_HEAVY:
	  // local root is now balanced
	  AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
	  break;
	case AVLNode<Item_Type>::LEFT_HEAVY:
	  // local root is critical
	  erase_rebalance_left(local_root);
	}
      }
      return return_value;
    }
    else { // Found item
      BTNode<Item_Type>* old_root = local_root;
      if (local_root->left == NULL) {
	local_root = local_root->right;
	decrease = true;
      } else if (local_root->right == NULL) {
	local_root = local_root->left;
	decrease = true;
      } else {
	replace_parent(old_root, old_root->left);
      }
      delete old_root;
      return true;
    }
  }
}

template<typename Item_Type>
void 
AVL_Tree<Item_Type>::replace_parent(BTNode<Item_Type>*& old_root,
				    BTNode<Item_Type>*& local_root) {
  if (local_root->right != NULL) {
    replace_parent(old_root, local_root->right);
    if (decrease) {
      AVLNode<Item_Type>* AVL_local_root =
	dynamic_cast<AVLNode<Item_Type>*>(local_root);
      switch (AVL_local_root->balance) {
      case AVLNode<Item_Type>::RIGHT_HEAVY:
	AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
	break;
      case AVLNode<Item_Type>::BALANCED:
	AVL_local_root->balance = AVLNode<Item_Type>::LEFT_HEAVY;
	decrease = false;
	break;
      case AVLNode<Item_Type>::LEFT_HEAVY:
	erase_rebalance_left(local_root);
	break;
      }
    }
  } else {
    old_root->data = local_root->data;
    old_root = local_root;
    local_root = local_root->left;
    decrease = true;
  }
}

template<typename Item_Type>
void
AVL_Tree<Item_Type>::erase_rebalance_left(BTNode<Item_Type>*& local_root) {
  // Cast local_root to AVLNode pointer
  AVLNode<Item_Type>* AVL_local_root =
    dynamic_cast<AVLNode<Item_Type>*>(local_root);
  // Obtain reference to the left_child
  AVLNode<Item_Type>* left_child = 
    dynamic_cast<AVLNode<Item_Type>*>(local_root->left);
  // See whether left-right heavy
  if (left_child->balance == AVLNode<Item_Type>::RIGHT_HEAVY) {
    // Obtain a reference to the left-right child
    AVLNode<Item_Type>* left_right_child = 
      dynamic_cast<AVLNode<Item_Type>*>(left_child->right);
    // Adjust the balances to be the new values after rotations are performed
    if (left_right_child->balance == AVLNode<Item_Type>::LEFT_HEAVY) {
      left_child->balance = AVLNode<Item_Type>::BALANCED;
      left_right_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
    } else if (left_right_child->balance == AVLNode<Item_Type>::BALANCED) {
      left_child->balance = AVLNode<Item_Type>::BALANCED;
      left_right_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    } else {
      left_child->balance = AVLNode<Item_Type>::LEFT_HEAVY;
      left_right_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    }
    // Perform left rotation
    rotate_left(local_root->left);
  } else { // Left-Left case
    if (left_child->balance == AVLNode<Item_Type>::BALANCED) {
      left_child->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
      AVL_local_root->balance = AVLNode<Item_Type>::LEFT_HEAVY;
    } else {
      left_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    }
  }
  // Finally rotate right
  rotate_right(local_root);
}

template<typename Item_Type>
void
AVL_Tree<Item_Type>::erase_rebalance_right(BTNode<Item_Type>*& local_root) {
  // Cast local_root to AVLNode pointer
  AVLNode<Item_Type>* AVL_local_root =
    dynamic_cast<AVLNode<Item_Type>*>(local_root);
  // Obtain reference to the right_child
  AVLNode<Item_Type>* right_child = 
    dynamic_cast<AVLNode<Item_Type>*>(local_root->right);
  // See whether right-left heavy
  if (right_child->balance == AVLNode<Item_Type>::LEFT_HEAVY) {
    // Obtain a reference to the right-left child
    AVLNode<Item_Type>* right_left_child = 
      dynamic_cast<AVLNode<Item_Type>*>(right_child->left);
    // Adjust the balances to be the new values after rotations are performed
    if (right_left_child->balance == AVLNode<Item_Type>::RIGHT_HEAVY) {
      right_child->balance = AVLNode<Item_Type>::BALANCED;
      right_left_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::LEFT_HEAVY;
    } else if (right_left_child->balance == AVLNode<Item_Type>::BALANCED) {
      right_child->balance = AVLNode<Item_Type>::BALANCED;
      right_left_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    } else {
      right_child->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
      right_left_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    }
    // Perform right rotation
    rotate_right(local_root->right);
  } else { // Right-Right case
    if (right_child->balance == AVLNode<Item_Type>::BALANCED) {
      right_child->balance = AVLNode<Item_Type>::LEFT_HEAVY;
      AVL_local_root->balance = AVLNode<Item_Type>::RIGHT_HEAVY;
    } else {
      right_child->balance = AVLNode<Item_Type>::BALANCED;
      AVL_local_root->balance = AVLNode<Item_Type>::BALANCED;
    }
  }
  // Finally rotate left
  rotate_left(local_root);
}
/*</exercise>*/

#endif
