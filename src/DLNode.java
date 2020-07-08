
public class DLNode<T> {
	
	private T dataItem;
	private DLNode<T> next;
	private DLNode<T> previous;
	private int value;
	
	public DLNode(T data, int value) {
		//initialize a node storing a given data and value
		this.dataItem = data;
		this.value = value;
		this.next = null;
		this.previous = null;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public T getData() {
		return this.dataItem;
	}
	
	public DLNode<T> getNext() {
		return this.next;
	}
	
	public DLNode<T> getPrevious() {
		return this.previous;
	}
	
	public void setData(T data) {
		this.dataItem = data;
	}
	
	public void setNext(DLNode<T> nextNode) {
		this.next = nextNode;
	}
	
	public void setPrevious(DLNode<T> previousNode) {
		this.previous = previousNode;
	}
	
	public void setValue(int nodeValue) {
		this.value = nodeValue;
	}
	
}
