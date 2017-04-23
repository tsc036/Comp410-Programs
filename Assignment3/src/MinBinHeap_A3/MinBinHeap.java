package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000);//0th will be unused for simplicity                                            //of child/parent computations...
    size=0;                                         //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  public EntryPair[] getHeap() { 
    return this.array;
  }

  public void insert(EntryPair entry){
	// TODO Auto-generated method stub
	array[size+1]=entry;
	size++;
	bubbleUp(this,entry);
  }
  public void delMin(){
	  if(size!=0){
		  array[1]=array[size];
		  array[size]=null;
		  size--;
		  if(size>1){
			  if(size<3&&array[1].priority>array[2].priority){
				  reassign(array,1,2);
			  }
			  else{
				  bubbleDown(this,1);
			  }
		  }
	  }
	  
  }
  public EntryPair getMin() {
	return array[1];
  }
  public int size() {
	return size;
  }

  public void build(EntryPair[] entries) {
	  for(int i=0;i<entries.length;i++){
		  array[i+1]=entries[i];
		  if(entries[i]!=null)
			  size++;
	  }
	  if(size>1){
		  if(size>2){
			  bubbleDownBuild(this,size/2);
		  }
		  else{
			  reassignAdvance(0,this);
		  }
	  }
  }
  private void bubbleUp(MinBinHeap k,EntryPair temp){
	  int childIndex=k.size;
	  int parentIndex=childIndex/2;
	  while(temp.priority<k.array[parentIndex].priority&&childIndex>1)
	  {
		  k.array[childIndex]=k.array[parentIndex];
		  childIndex/=2;
		  parentIndex/=2;
	  }
	  k.array[childIndex]=temp;
  }
  private void bubbleDownBuild(MinBinHeap c, int parent){
	  int hold=0;
	  while(parent>0){
		  if(parent>1){
			  reassignAdvance(parent,c);
		  }
	  	else{
	  		hold=reassignAdvance(parent,c);
	  	}
		parent--;
	  }
	  if(hold>1&hold<size){
		  	if(c.size>hold*2+1){
		  		bubbleDown(c,hold);
		  	}
		  	else {
		  		reassignAdvance(hold,c);
		  	}
	  }
	  
  }
  private void bubbleDown(MinBinHeap c,int parent){
	  while(parent<=c.size/2){
		  parent=reassignAdvance(parent,c);
	  }
  }  
  private int reassign(EntryPair[] entries,int parent, int child){
	  EntryPair hold=entries[child];
	  entries[child]=entries[parent];
	  entries[parent]=hold;
	  return child;
  }
  private int reassignAdvance(int parent, MinBinHeap c){
	  int child1=parent*2;
	  int child2=child1+1;
	  EntryPair parentObject=c.array[parent];
	  EntryPair child1Object=c.array[child1];
	  EntryPair child2Object=c.array[child2];
	  if(c.size<child2){
		  if(parentObject.priority>child1Object.priority){
			  return reassign(c.array,parent,child1);
		  }
	  }
	  else{
		  if(parentObject.priority>child1Object.priority||parentObject.priority>child1Object.priority){
			  if(child1Object.priority<child2Object.priority){
				  return reassign(c.array,parent,child1);
			  }
			  else{
				  return reassign(c.array,parent,child2);
			  }
		  }
	  }
	  return c.size;
  }
}