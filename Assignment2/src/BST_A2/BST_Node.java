package BST_A2;

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
		  if(left==null)
			  return false;
		  else
			  return left.containsNode(s);
	  }
	  else if(s.compareTo(data)>0){
		  if(right==null)
			  return false;
		  else
			  return right.containsNode(s);
	  }
	  return true;
		  
  }
  public boolean insertNode(String s){
	 if(s.compareTo(data)<0){
		  if(left==null){
			  left=new BST_Node(s);
			  left.parent=this;
			  return true;
		  }
		  else{
			  return left.insertNode(s);
		  }
	  }
	  else if(s.compareTo(data)>0){
		  if(right==null){
			  right=new BST_Node(s);
			  right.parent=this;
			  return true;
		  }
		  else{
			  return right.insertNode(s);
		  }
	  }
	  return false;
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
	  if(left==null)
		  return this;
	  return left.findMin();
  }
  public BST_Node findMax(){ 
	  if(right==null)
		  return this;
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
  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }
}