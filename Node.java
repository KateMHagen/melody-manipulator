package PP3;

public class Node {

	private Object item;
	private Node next;
	
	public Node(Object o) {
		item = o;
		next = null;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public Node getNext() {
		return next;
	}
	
	public Object getItem() {
		return item;
	}
	
	public void setItem(Object o) {
		item = o;
	}
	
	public boolean hasNext() {
		return (next != null);
	}
	
	
}