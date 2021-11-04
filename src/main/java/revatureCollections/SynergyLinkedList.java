package revatureCollections;

public class SynergyLinkedList <E> {

	//Start and end of linked list
	private Node<E> head, tail;
	private int count;


	//TODO
	// [ ] !!!!!!COMMENTS!!!!!!
	// [x] Node
	// [x] Head Tail
	// [x] Count
	// [x] adders
	// [x] 		addFirst
	// [x] 		addLast
	// [x] 		add@index
	// [x] removers
	// [x] 		removeFirst
	// [x] 		removeLast
	// [x] 		remove@index
	// [x] getters
	// [x] 		getHead
	// [x] 		getTail
	// [x] 		get@index
	// [x] Overrides
	// [x] 		toString
	// [x] 		equals

	//public functions

	//Getters

	//Gets value at head
	public E getHead(){
		// return null if head is null
		if (head  == null) return null;
		// return value at head node
		return head.getValue();
	}
	//Gets value at tail
	public E getTail(){
		// Check if tail is null
		if (tail  == null) return null;
		// return value at tail
		return tail.getValue();
	}
	//Gets Count value
	public int getCount() {
		return count;
	}
	//Gets value of node at index (Raises index out of bounds)
	public E getAtIndex(int index){
		//get value of node at index
		return getNodeAt(index).getValue();
	}

	//		Adds

	// Adds new node a head with data type E and value val
	public void addFirst(E val){
		// Create new node of Datatype E and Value val
		Node<E> node = new Node<>(val);
		//Set current head to new nodes next
		node.setNext(head);
		//Set head to new node
		if (node.getNext() == null) tail = node;
		head = node;
		count++;
	} //add to head
	public void addLast(E val){
		if (tail == null) addFirst(val);
		//make new node with value
		Node<E> node = new Node<>(val);
		//Set new node to point to null
		node.setNext(null);
		//Set current last node to point to new node
		tail.setNext(node);
		//Set tail to point to new node
		tail = node;
		count++;
	} // add at tail
	public void addAt(int index, E value) {
		if (index == 0) {
			addFirst(value);
			return;
		}
		if (index == count - 1){
			addLast(value);
			return;
		}
		Node<E> beforeNode = getNodeAt(index-1);
		Node<E> newNode = new Node<>(value);
		newNode.setNext(beforeNode.getNext());
		beforeNode.setNext(newNode);
		count++;


	} //add at index

	//		Removes
	public void removeFirst(){
		//Set head to new node
		head = head.getNext();
		count--;
	} // remove head
	public void removeLast(){
		if (count == 1) {
			removeFirst();
			return;
		}
		Node<E> newLast = getNodeAt(count-2);
		newLast.setNext(null);
		tail = newLast;
		count--;
	} // remove tail
	public void removeAt(int index){
		if (index == 0){
			removeFirst();
			return;
		}
		Node<E> beforeNode = getNodeAt(index-1);
		Node<E> toBeRemoved = getNodeAt(index);
		beforeNode.setNext(toBeRemoved.getNext());
		if (beforeNode.getNext() == null) tail = beforeNode;
		count--;
	} // remove at index


	//		Overrides

	public E[] toArray(){
		if (head == null)return (E[]) new Object[]{};
		E[] out = (E[]) new Object[this.getCount()];
		Node<E> node = head;
		int i = 0;
		while (node != null)  {
			out[i++] = node.getValue();
			node = node.getNext();
		}
		return out;





	}




	@Override
	public String toString() {
		if (head == null) return "null";
		StringBuilder stringBuilder = new StringBuilder("{");
		Node<E> node = head;
		while (node.getNext() != null) {
			stringBuilder.append(node.toString()).append(", ");
			node = node.getNext();
		}
		stringBuilder.append(node.toString()).append("}");
		return stringBuilder.toString();

	}


	public boolean equals(SynergyLinkedList<E> linkedList) {
		Node<E> ours = head;
		Node<E> theirs = linkedList.head;
		while (true){
			if (ours == null && theirs != null) return false;
			else if (theirs == null && ours != null) return false;
			else if (theirs == null)return true;
			else if (!(theirs.getValue().equals(ours.getValue()))) return false;
			ours = ours.getNext();
			theirs = theirs.getNext();

		}
	}

	//Private helper functions
	private Node<E> getHeadNode(){
		return head;
	}
	private Node<E> getTailNode(){
		return tail;
	}
	private Node<E> getNodeAt(int loc) {
		if (loc < 0 || loc >= count) throw new IndexOutOfBoundsException();
		int i = 0;
		Node<E> out = head;
		while (out != null){
			if (i == loc) return out;
			i++;
			out=out.getNext();
		}
		return null;
	}


	//Node Class Holder
	private class Node <E>{
		// Next node in the Linked List (null if tail)
		private Node<E> next;
		// Value
		private E value;
		public Node() {

		}
		public Node(E value) {
			this.value = value;
		}

		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value.toString();
		}

	}

	public SynergyLinkedList() {
		this.count = 0;
		this.head = null;
		this.tail = null;
	}
}