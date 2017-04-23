package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	if(size==0){
		root=new BST_Node(s);
	}
	else if(root.containsNode(s)||s==null){
		return false;
	}
	else{
		root.insertNode(s);
	}
	size++;
	return true;
	
}

@Override
public boolean remove(String s) {
	if(size==0){
		return false;
	}
	else if(root.data.equals(s)){
		if(size==1){
			root=null;
		}
		else{
			BST_Node rightMin=root.getRight().findMin();
			root.removeNode(rightMin.getData());
			rightMin.left=root.left;
			rightMin.right=root.right;
			if(root.left!=null)
				root.left.parent=rightMin;
			if(root.right!=null)
				root.right.parent=rightMin;
			root=rightMin;
		}
		size--;
		return true;		
	}
	else if(root.containsNode(s)){
		root.removeNode(s);
		size--;
		return true;
	}
	return false;
}

@Override
public String findMin() {
	if(size==0)
		return "There is no tree";
	else{
		return root.findMin().getData();
	}
}

@Override
public String findMax() {
	if(size==0)
		return "There is no tree";
	else{
		return root.findMax().getData();
	}
}

@Override
public boolean empty() {
	return size==0;
}

@Override
public boolean contains(String s) {
	if(size==0)
		return false;
	else
		return root.containsNode(s);
}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	if(size==0)
		return 0;
	return root.getHeight();
}

}