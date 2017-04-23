package DiGraph_A5;
import java.util.HashMap;
import java.util.HashSet;

public class Node {
	private long idNum;
	private String name;
	private HashMap<String,Edge> edges;
	private HashSet<String> ids;
	private int inEdges;
	public Node(long idNum, String name) {
		this.idNum = idNum;
		this.name = name;
		this.edges = new HashMap<String,Edge>();
		ids=new HashSet<String>();
		this.inEdges = 0;
	}
	public long getIdNum() {
		return idNum;
	}
	public void setListIdNum(long idNum) {
		this.idNum = idNum;
	}
	public String getName() {
		return name;
	}
	public void setListName(String name) {
		this.name = name;
	}
	public HashMap<String,Edge> getEdges() {
		return edges;
	}
	public HashSet<String> getIds() {
		return ids;
	}
	public void setIds(HashSet<String> destinations) {
		this.ids = destinations;
	}
	public void setListEdges(HashMap<String,Edge> edges) {
		this.edges = edges;
	}
	public int getInEdges() {
		return inEdges;
	}
	public void setInEdges(int inEdges) {
		this.inEdges = inEdges;
	}
	public void incrementInEdges(){
		inEdges++;
	}
	public void decrementInEdges(){
		inEdges--;
	}
	
}
