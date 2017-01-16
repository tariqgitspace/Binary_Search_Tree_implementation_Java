class BST {
	public static void main(String args[]) {
		 Tree tr;
		 tr = new Tree(100);
		 tr.insert(50);
		 tr.insert(125);
		 tr.insert(150);
		 tr.insert(25);
		 tr.insert(75);
		 tr.insert(20);	
		 tr.insert(90); 
		 tr.delete(20);
		 tr.delete(20);
		 tr.delete(125);
		 tr.delete(150);
		 tr.delete(100);
		 tr.delete(50);
		 tr.delete(75);
		 tr.delete(25);
		 tr.delete(90);
		 System.gc();
		 
		 //System.out.println("Completed");
	}

	
}

class Tree { // Defines one node of a binary search tree
	
	public Tree(int n) {
		value = n;
		left = null;
		right = null;
	}
	public static int exist;
	
	public void insert(int n) {
		if (value == n)
			return;
		if (value < n)
			if (right == null)
				right = new Tree(n);
			else
				right.insert(n);
		else if (left == null)
			left = new Tree(n);
		else
			left.insert(n);
	}


	public void delete(int n)
	{
		exist =0;
		if(this.value==n)  //special handling of root node as mentioned in Video as Root Node cannot be deleted
		{
			if((this.right==null)&&(this.left==null))
			{
				System.out.println("unable to delete  " +n+ "---tree will become empty ");
				return;
			}
			if(this.right==null)  //if Right Node is Null 
			{
				//Finding maximum value from left so that this value is swapped with given node  
			    Tree MaxNodeFromLeft = maximumvaluefromleft(this.left);  
			    // Replace value of this node with that of maximum value found above  
			    this.value = MaxNodeFromLeft.value;  
			    // Now, delete this maximum found node
			    this.left = deleteNode(this.left, MaxNodeFromLeft.value); 
			}
			
			else      //if(this.left==null) OR Root has both nodes
			{
				// Finding minimum value from right so that this value is swaped with given node
			    Tree minNodeFromRight = minimumValuefromRight(this.right);  
			    // Replace value of this node with that of minimum value found above  
			    this.value = minNodeFromRight.value;  
			    // Now, delete that minimum found node
			    this.right = deleteNode(this.right, minNodeFromRight.value); 
			}
		
		}
		
		else
		{
			deleteNode(this, n);
		}
		
		if(exist==0)
		{
			System.out.println("This element is not found = "+n);
		}
		  
	}
	public static Tree deleteNode(Tree root,int value) 
	{  
		  if (root == null)  
			   return root;  
			  if (root.value > value) 
			  {  
			   root.left = deleteNode(root.left, value);  
			  } 
			  else if (root.value < value) 
			  {  
			   root.right = deleteNode(root.right, value);  
			  } 
			  else  //if element found
			  {  	exist =1;
				   // node has both left and Right 
				   if (root.left != null && root.right != null) 
				   {  

				    // Finding minimum value from right so that this value is swaped with given node
				    Tree minNodeFromRight = minimumValuefromRight(root.right);  
				    // Replace value of this node with that of minimum value found above  
				    root.value = minNodeFromRight.value;  
				    // Now, delete this minimum found node
				    root.right = deleteNode(root.right, minNodeFromRight.value);  
				   }  
				   
				   // if Right child of Node is null  
				   else if (root.left != null) 
				   {  
					Tree templeft = root;
				    root = root.left;  
				    templeft.left=null;  //Free pointers of this node to be deleted
				   } 
				   
				   // if left child of Node is null  
				   else if (root.right != null) 
				   {  
					Tree tempright = root;
				    root = root.right; 
				    tempright.right=null;  //Free pointers of this node to be deleted
				   }  
			   
				  // if Node is a leaf node 
				   else
				   {
					   root = null;
				   }
			  }  
			  return root;  
		} 
	
	
	public static Tree maximumvaluefromleft(Tree node)
	{
	    while(node.right != null){
	        node = node.right;
	    }
	    return node;
	} 
	
	public static Tree minimumValuefromRight(Tree node)
	{
	    while(node.left != null){
	        node = node.left;
	    }
	    return node;
	} 
	
	protected int value;
	protected Tree left;
	protected Tree right;
}