
public class DLList<T> implements DLListADT<T> {
	private DLNode<T> front;
	private DLNode<T> rear;
	private int count;
	
	public DLList() {
		this.front = null;
		this.rear = null;
		this.count = 0;
	}
	
	public void insert(T dataItem, int value) {
		DLNode<T> newNode = new DLNode<T>(dataItem, value);
		
		if(isEmpty()) {
			this.front = newNode;
			this.rear = newNode;
		}
		else {
			rear.setNext(newNode);
			newNode.setPrevious(rear);
			rear = newNode;
		}
		count++;
	}
	
	public int getDataValue(T dataItem) throws InvalidDataItemException {
		DLNode<T> current = front;
		
		boolean found = false;
		while (found == false) {
			if(current == null) {
				throw new InvalidDataItemException("There are no nodes with that dataItem.");
			}
			if(dataItem.equals(current.getData())) {
				found = true;
			}
			if (found == false) current = current.getNext();
		}
		return current.getValue();
	}
	
	
	//changeValue
	public void changeValue(T dataitem, int newValue) throws InvalidDataItemException {
		DLNode<T> curr = front;
		boolean changed = false;
		
		while (curr != null) {
			if (curr.getData().equals(dataitem)) {
				curr.setValue(newValue);
				changed = true;
				break;
			}
			curr = curr.getNext();
		}
		if (changed == false) {
			throw new InvalidDataItemException("Item not in list");
		}
	}
	
	public T getSmallest() throws EmptyListException{
		if (isEmpty()) {
			throw new EmptyListException("There can't be a smallest node in an empty list.");
		}
		
		T smallData = front.getData();
		int smallVal = front.getValue();
		DLNode<T> smallest = front;
		DLNode<T> current = front;
		
		while (current != null) {
			if (current.getValue() < smallVal) {
				smallest = current;
				smallVal = smallest.getValue();
				smallData = smallest.getData();
			}
			current = current.getNext();
		}
		
		//removing
		if (smallest == front && smallest == rear) {
			smallest = null;
		}
		else if (smallest == front) {
			front = smallest.getNext();
			front.setPrevious(null);
		}
		else if (smallest == rear) {
			rear = smallest.getPrevious();
			rear.setNext(null);
		}
		else {
			smallest.getPrevious().setNext(smallest.getNext());
			smallest.getNext().setPrevious(smallest.getPrevious());
		}
		count--;
		
		return smallData;
	}


	
	public boolean isEmpty() {
		if (count == 0) return true;
		else return false;
	}
	
	public int size() {
		return count;
	}
	
	public String toString() {
		String result = "List:";
		DLNode<T> current = front;
		
		while (current != null) {
			result += " "+current.getData().toString()+","+current.getValue()+";";
			current = current.getNext();
		}
		return result;
	}
	
}
