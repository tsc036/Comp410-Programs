/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node root;//this will be the entry point to your linked list (the head)
	public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
	    root=new Node(0); //Note that the root's data is not a true part of your data set!
	}
	  
	  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
	  
	public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
	    return root;
	}
	
	@Override
	public boolean insert(Node n, int index) {
		if(index>size())
			return false;
		else if(index==0)
		{
			n.next=root.getNext();
			root.next=n;
		}
		else
		{
			Node preInsert=get(index-1);
			n.next=preInsert.getNext();
			preInsert.next=n;
		}
		return true;
			
	}
	
	@Override
	public boolean remove(int index) {
		if(index>size()-1||index<0)
			return false;
		else
		{
			Node removedPrev=get(index-1);
			Node removed=removedPrev.getNext();
			removedPrev.next=removed.getNext();
			return true;
		}
	}
	
	@Override
	public Node get(int index) {
		if(index>size()-1)
			return null;
		else
		{
			Node holder=root;
			for(int i=0;i<=index;i++)
			{
				holder=holder.getNext();
			}
			return holder;
		}
		
		
			
	}
	
	@Override
	public int size() {
		int count=0;
		Node check=root.getNext();
		while(check!=null)
		{
			count++;
			check=check.getNext();
		}
		return count;
	}
	
	@Override
	public boolean isEmpty() {
		return size()==0;
	}
	
	@Override
	public void clear() {
		root.next=null;
		
		}
	}