package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
	public SPLT() {
		this.size = 0;
	} 
	public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
		return root;
	}
	
	public void insert(String s) {
		if(size==0){
			root=new BST_Node(s);
			size++;
		}
		else{
			if(!contains(s)){
				BST_Node splay=root.insertNode(s);
				root=splay;
				size++;
			}
		}
		
	}

	@Override
	public void remove(String s) {
		if(size!=0){
			if(contains(s)){
				BST_Node left=root.left;
				BST_Node right=root.right;
				if(left!=null){
					left.parent=null;
					left.findMax();
					left.right=right;
					if(right!=null){
						right.parent=left;
					}
					root=left;
				}
				else{
					if(right!=null){
						root=right;
						right.parent=null;
					}
					else{
						root=null;
					}
				}
				size--;
			}
		}
	}
	@Override
	public String findMin() {
		if(size==0)
			return "There is no tree";
		else{
			BST_Node min=root.findMin();
			root=min;
			return min.data;
		}
	}

	@Override
	public String findMax() {
		if(size==0)
			return "There is no tree";
		else{
			BST_Node max=root.findMax();
			root=max;
			return max.data;
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
		else{
			
			Boolean ans=root.containsNode(s);
			if(root.parent!=null){
				while(root.parent!=null){
					root=root.parent;
				}
			}
			return ans;
		}
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