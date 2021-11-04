package revatureCollections;//import java.util.ArrayList;

/**
 * revatureCollections.VanquishList is an implementation of ArrayList
 * without using the collections library
 */

public class VanquishList{
	/**
	 * Size, Capacity, DEFAULT_SIZE, and elementData
	 * Size tracks the current size of the revatureCollections.VanquishList (tm)
	 *
	 * Capacity tracks the current max size of the array
	 * before we need to resize it.
	 *
	 * DEFAULT_SIZE is used to initialize the ArrayList
	 * making sure to give some space before needing to
	 * resize
	 *
	 * elementData is the actual storage of the objects,
	 * this is cast as an array of Objects, the size of
	 * it will be allocated as needed during run time.
	 */

	/**
	 * Initialization of the tracker of the elements in the list
	 */
	private int size = 0;
	public int getSize(){
		return this.size;
	}
	/**
	 * Assumed the original capacity unless specified otherwise
	 */
	private int capacity = 10;
	/**
	 * Initial static size of the array if one is not specified
	 */
	private static final int DEFAULT_SIZE = 10;
	Object[] elementData;

	/**
	 * Constructor with no Parameters, defaulted to be a list
	 * of size 10 with no elements stored and a size of 0
	 */
	public VanquishList(){
		this.elementData = new Object[DEFAULT_SIZE];
	}
	/**
	 * Constructor taking an int of the capacity the user
	 * requests for the size of the list. To be on the safe
	 * side. The list allocates twice as large as the given
	 * capacity.
	 * @param initialCapacity - Capacity given from the user
	 *                        This is multiplied by 2 before
	 *                        being used
	 */
	public VanquishList(int initialCapacity) {
		if (initialCapacity >= 0) {
			this.elementData = new Object[initialCapacity * 2];
			this.setCapacity(initialCapacity * 2);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * The .clone() function returns a copy of the list element
	 * for element. This makes a deep copy and adds each element
	 * of the original list to a new revatureCollections.VanquishList and returns
	 * the new list
	 *
	 * @return - The new list returned with each element and
	 * index the same. (NOTE This may lead to different *CAPACITY*
	 * variable)
	 */
	public Object clone(){
		VanquishList clone = new VanquishList(size);
		for(int x = 0; x < size; x++){
			clone.add(elementData[x]);
		}
		return clone;
	}

	/**
	 * setCapacity is used privately to change the max size
	 * variable of the revatureCollections.VanquishList without accessing the
	 * variable directly.
	 *
	 * @param capacity - This variable takes in the new capacity
	 *                 and acts as a setter
	 */
	private void setCapacity(int capacity){
		this.capacity = capacity;
	}

	/**
	 * Grow takes the current array and doubles the capacity
	 * all the elements in the previous array are copied to the
	 * new array which is twice as large (capacity) but the same
	 * number of elements
	 */
	private void grow(){
		this.capacity *= 2;
		Object[] newElementData = new Object[capacity * 2];
		for(int x = 0; x < size; x++){
			newElementData[x] = elementData[x];
		}
		this.elementData = newElementData;
	}

	/**
	 * Only 2 return scenarios
	 * @return true if size is 0, false if size is not 0
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * add function to take a new object and add
	 * that object to the end of the list. Check
	 * to see if the size is the same size as capacity,
	 * if it is then the Array needs to grow, then the
	 * object is placed at the end of the array (at index
	 * size) and size in incremented (in that order).
	 *
	 * @param o The object to the appended to the end
	 *          of the Array
	 */

	public void add(Object o){
		if (this.size >= capacity){
			this.grow();
		}
		this.elementData[size++] = o;
	}

	/**
	 * Adds each member of an input varArgs as a new member of the list.
	 * @param o The object array to be appended to the end of the array.
	 */

	public void appendArray(Object ...o){
		while (this.size+o.length >= capacity) {
			this.grow();
		}
		for (int i = 0; i < o.length; i++) {
			this.elementData[size++] = o[i];
		}

	}
	/**
	 * Overloaded add function to specify where to add the
	 * object in the array. Loops backwards through the array
	 * pushing each element back one UNTIL reaching where the
	 * new element needs to be, then ending after inserting the
	 * new element
	 *
	 * @param index - where in the array to place the new object
	 * @param o - the object to place in the array
	 */
	public void add(int index, Object o){
		if (size >= capacity){
			this.grow();
		}
		Object[] newElementData = new Object[this.capacity];
		size++;
		for(int x = size; x >= 0; --x){
			if (x > index) {
				newElementData[x] = this.elementData[x-1];
			}
			else if (x == index){
				newElementData[x] = o;
			}
			else {
				newElementData[x] = elementData[x];
			}
		}
		this.elementData = newElementData;
	}

	/**
	 * The get function returns the object at the specified index
	 *
	 * @param index - Where in the array the object returned is
	 * @return - Returns the element at the specified index
	 * @throws - Throws illegal argument if the index specified
	 * is outside the array's size
	 */
	public Object get(int index){
		if (index >= 0 && index < size){
			return elementData[index];
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	/**
	 * Set works as an overwrite function for the array that
	 * cannot increase the size of the array, and will ONLY
	 * overwrite an array index
	 *
	 * @param index - the element in the array that will be
	 *              overwritten
	 * @param o - the object to overwrite at the given index
	 *          *NOTE* This is assumed to be the same object
	 *          as the rest of the array
	 */
	public void set(int index, Object o){
		if (index >= 0 && index < size){
			elementData[index] = o;
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Simple contains function to check if an object exists
	 * in the array
	 *
	 * @param o - Object to search for in the array
	 * @return - true the object exists in the array
	 * @return - false the object does not exist in the array
	 */
	public boolean Contains(Object o){
		for(int x = 0; x < size; x++){
			if (elementData[x].equals(o)){
				return true;
			}
		}
		return false;
	}

	/**
	 * This returns in index of the first instance of an
	 * object in the array.
	 *
	 * @param o - the object to search for in the array
	 * @return - if found the function returns the index
	 * @return - if not found the function returns -1
	 *
	 */
	public int indexOf(Object o){
		for(int x = 0; x < size; x++){
			if (elementData[x].equals(o)){
				return x;
			}
		}
		return -1;
	}
	/**
	 * Remove saves the next array element to the current
	 * array element for every element at or after the given
	 * index then overwrites the last element with null to delete
	 * the duplicate element at the end to act as a removal and
	 * keeping array continuity.
	 *
	 * @param index - the index of the element to be removed and
	 *              specification of where the loop will start
	 */
	public void remove(int index){
		if (index >= size || index < 0){
			throw new IllegalArgumentException();
		}
		size--;
		for(int x = index; x < size; x++){
			elementData[x] = elementData[x + 1];
		}
		elementData[size] = null;
	}

	/**
	 * remove loops through the array to check for the first
	 * instance of an object, once the instance is found remove
	 * is called at that index to delete the element and the function
	 * ends.
	 *
	 * @param o - the object to search for in the array to be
	 *          deleted. This is only reliable if the data structure
	 *          used implements .equals(Object o) well
	 */
	public void remove(Object o){
		size--;
		for(int x = 0; x < size; x++){
			if(elementData[x].equals(o)){
				remove(x);
				break;
			}
		}
	}

	/**
	 * removeAll removes all instances of an object, the loop continues
	 * to run after the first instance of an object has been deleted.
	 *
	 * @param o - the object to search the array for and then delete
	 *          within the array.
	 */
	public void removeAll(Object o){
		for(int x = 0; x < size; x++){
			if(elementData[x].equals(o)){
				//First x is removed as position x, then x is decremented
				//because the new element shifted down could be another
				//instance of that element.
				remove(x--);
			}
		}
	}

	/**
	 * No-args removeAll removes all objects in the list.
	 */
	public void removeAll(){
		for(int x = 0; x < size; x++){
			remove(x--);
		}
	}
}