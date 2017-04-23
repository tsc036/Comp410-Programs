package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node parent;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public boolean containsNode(String s){
	  if(s.compareTo(data)<0){
		  if(left==null){
			  splay(this);
			  return false;
		  }
		  else
			  return left.containsNode(s);
	  }
	  else if(s.compareTo(data)>0){
		  if(right==null){
			  splay(this);
			  return false;
		  }
		  else
			  return right.containsNode(s);
	  }
	  splay(this);
	  return true;
		  
  }
  public BST_Node insertNode(String s){
	 if(s.compareTo(data)<0){
		  if(left==null){
			  left=new BST_Node(s);
			  left.parent=this;
			  BST_Node leftNode=left;
			  splay(left);
			  return leftNode;
		  }
		  else{
			  return left.insertNode(s);
		  }
	  }
	  else if(s.compareTo(data)>0){
		  if(right==null){
			  right=new BST_Node(s);
			  right.parent=this;
			  BST_Node rightNode=right;
			  splay(right);
			  return rightNode;
		  }
		  else{
			  return right.insertNode(s);
		  }
	  }
	 return null;
  }
  public boolean removeNode(String s){ 
	  if(containsNode(s)){
		  BST_Node hold=findNode(s);
		  Boolean condition=s.compareTo(hold.parent.data)<0;
		  if(hold.left==null&&hold.right==null){
			  if(s.compareTo(hold.parent.data)<0)
				  hold.parent.left=null;
			  else
				  hold.parent.right=null;
		  }
		  else if(hold.left==null){
			  if(condition){
				  hold.parent.left=hold.right;
			  }
			  else{
				  hold.parent.right=hold.right;
			  }
			  hold.right.parent=hold.parent;
		  }
		  else if(hold.right==null){
			  if(condition){
				  hold.parent.left=hold.left;
			  }
			  else{
				  hold.parent.right=hold.left;
			  }
			  hold.left.parent=hold.parent;
		  }
		  else{
			  BST_Node rightMin=hold.right.findMin();
			  removeNode(rightMin.data);
			  rightMin.left=hold.left;
			  hold.left.parent=rightMin;
			  rightMin.right=hold.right;
			  if(hold.right!=null)
			  {
				  hold.right.parent=rightMin;
			  }
			  if(condition){
				  hold.parent.left=rightMin;
			  }
			  else{
				  hold.parent.right=rightMin;
			  }
			  rightMin.parent=hold.parent;
		  }
		  return true;
	  }
	  return false;
  }
  public BST_Node findMin(){ 
	  if(left==null){
		  splay(this);
		  return this;
	  }
	  return left.findMin();
  }
  public BST_Node findMax(){ 
	  if(right==null){
		  splay(this);
		  return this;
	  }
	  return right.findMax();
  }
  public int getHeight(){
	  int rightHeight=-1;
	  int leftHeight=-1;
	  if(right!=null)
		  rightHeight=right.getHeight();
	  if(left!=null)
		  leftHeight=left.getHeight();
	  if(leftHeight>rightHeight)
		  return leftHeight+1;
	  else
		  return rightHeight+1;
		  
		  
  }
  private BST_Node findNode(String s){
	  if(s.compareTo(data)<0)
		  return left.findNode(s);
	  else if(s.compareTo(data)>0)
		  return right.findNode(s);
	  return this;
  }
  private void splay(BST_Node toSplay){
	  while (toSplay.parent!=null){
		   try{
			   if(toSplay.data.compareTo(toSplay.parent.data)<0){
				   if(toSplay.parent.data.compareTo(toSplay.parent.parent.data)<0){
					   zigZigLeft(toSplay);
				   }
				   else{
					   zigZagRight(toSplay);
				   }
			   }
			   else{
				   if(toSplay.parent.data.compareTo(toSplay.parent.parent.data)<0){
					   zigZagLeft(toSplay);
				   }
				   else{
					   zigZigRight(toSplay);
				   }
			   }
			   
		   }
		   catch(NullPointerException e){
			   if(toSplay.data.compareTo(toSplay.parent.data)<0){
				   rotateRight(toSplay);
			   }
			   else{
				   rotateLeft(toSplay);
			   }
		   }
	  }
  }
  private void grandCheck(BST_Node toSplay,BST_Node toSplayGreat){
	  if(toSplayGreat!=null){
		  if(toSplayGreat.data.compareTo(toSplay.data)<0){
			  toSplayGreat.right=toSplay;
		  }
		  else{
			  toSplayGreat.left=toSplay;
		  }
	  }
	  toSplay.parent=toSplayGreat;
  }
  private void zigZagLeft(BST_Node toSplay){
	  BST_Node toSplayRight=toSplay.right;
	  BST_Node toSplayLeft=toSplay.left;
	  BST_Node toSplayParent=toSplay.parent;
	  BST_Node toSplayGrand=toSplayParent.parent;
	  BST_Node toSplayGreat=toSplayGrand.parent;
	  toSplay.left=toSplayParent;
	  toSplay.right=toSplayGrand;
	  toSplayParent.parent=toSplay;
	  toSplayGrand.parent=toSplay;
	  toSplayGrand.left=toSplayRight;
	  if(toSplayRight!=null)
		  toSplayRight.parent=toSplayGrand;
	  toSplayParent.right=toSplayLeft;
	  if(toSplayLeft!=null)
		  toSplayLeft.parent=toSplayParent;
	  grandCheck(toSplay,toSplayGreat);
  }
  private void zigZagRight(BST_Node toSplay){
	  BST_Node toSplayRight=toSplay.right;
	  BST_Node toSplayLeft=toSplay.left;
	  BST_Node toSplayParent=toSplay.parent;
	  BST_Node toSplayGrand=toSplayParent.parent;
	  BST_Node toSplayGreat=toSplayGrand.parent;
	  toSplay.left=toSplayGrand;
	  toSplayGrand.parent=toSplay;
	  toSplay.right=toSplayParent;
	  toSplayParent.parent=toSplay;
	  toSplayGrand.right=toSplayLeft;
	  if(toSplayLeft!=null)
		  toSplayLeft.parent=toSplayGrand;
	  toSplayParent.left=toSplayRight;
	  if(toSplayRight!=null)
		  toSplayRight.parent=toSplayParent;
	  grandCheck(toSplay,toSplayGreat);
  }
  private void zigZigRight(BST_Node toSplay){
	  BST_Node toSplayLeft=toSplay.left;
	  BST_Node toSplayParent=toSplay.parent;
	  BST_Node toSplayParentLeft=toSplayParent.left;
	  BST_Node toSplayGrand=toSplayParent.parent;
	  BST_Node toSplayGreat=toSplayGrand.parent;
	  toSplay.left=toSplayParent;
	  toSplayParent.parent=toSplay;
	  toSplayParent.left=toSplayGrand;
	  toSplayGrand.parent=toSplayParent;
	  toSplayParent.right=toSplayLeft;
	  if(toSplayLeft!=null)
		  toSplayLeft.parent=toSplayParent;
	  toSplayGrand.right=toSplayParentLeft;
	  if(toSplayParentLeft!=null)
		  toSplayParentLeft.parent=toSplayGrand;
	  grandCheck(toSplay,toSplayGreat);
  }
  private void zigZigLeft(BST_Node toSplay){
	  BST_Node toSplayRight=toSplay.right;
	  BST_Node toSplayParent=toSplay.parent;
	  BST_Node toSplayParentRight=toSplayParent.right;
	  BST_Node toSplayGrand=toSplayParent.parent;
	  BST_Node toSplayGreat=toSplayGrand.parent;
	  toSplay.right=toSplayParent;
	  toSplayParent.parent=toSplay;
	  toSplayParent.right=toSplayGrand;
	  toSplayGrand.parent=toSplayParent;
	  toSplayParent.left=toSplayRight;
	  if(toSplayRight!=null)
		  toSplayRight.parent=toSplayParent;
	  toSplayGrand.left=toSplayParentRight;
	  if(toSplayParentRight!=null)
		  toSplayParentRight.parent=toSplayGrand;
	  grandCheck(toSplay,toSplayGreat);
  }
  private void rotateRight(BST_Node toSplay){
	  BST_Node toSplayRight=toSplay.right;
	  BST_Node toSplayParent=toSplay.parent;
	  toSplay.right=toSplayParent;
	  toSplayParent.parent=toSplay;
	  toSplayParent.left=toSplayRight;
	  try{
		  toSplayRight.parent=toSplayParent;
	  }
	  catch(NullPointerException e){
		  
	  }
	  toSplay.parent=null;
  }
  private void rotateLeft(BST_Node toSplay){
	  BST_Node toSplayLeft=toSplay.left;
	  BST_Node toSplayParent=toSplay.parent;
	  toSplay.left=toSplayParent;
	  toSplayParent.parent=toSplay;
	  toSplayParent.right=toSplayLeft;
	  try{
		  toSplayLeft.parent=toSplayParent;
	  }
	  catch(NullPointerException e){
		  
	  }
	  toSplay.parent=null;
  }
  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}