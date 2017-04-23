package DiGraph_A5;

public class Edge {
	private long idNum;
	private String source,destination,label;
	private long weight;
	public Edge(long id,String s,String d,String l){
		idNum=id;
		source=s;
		destination=d;
		label=l;
		weight=1;
	}
	public Edge(long id,String s,String d,String l,long w){
		idNum=id;
		source=s;
		destination=d;
		label=l;
		weight=w;
	}
	public long getIdNum() {
		return idNum;
	}
	public void setIdNum(long idNum) {
		this.idNum = idNum;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public long getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public boolean equals(Object obj) {
			return ((Edge)obj).getDestination().equals(destination)&&((Edge)obj).getSource().equals(source);
	}
	public int hashCode(){
		return (int)idNum;
	}
	
}
