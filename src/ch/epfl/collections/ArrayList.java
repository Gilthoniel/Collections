package ch.epfl.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the StringList interface based on a array.
 * 
 * @see StringList
 * @see StringArrayListTest
 * @see StringArrayListIterator
 *
 */
public class ArrayList<T> implements List<T> {
	/**
	 * Internal storage of the list elements.
	 */
	private Object[] array = new Object[1];
	
	/**
	 * Indicates the current number of filled locations in the
	 * internal array storage. 
	 */
	private int size = 0;

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds a new element to the list and expands the internal storage if more
	 * space is needed.
	 * 
	 * The internal container array can contain a allocated maximum number of elements.
	 * Since we need the flexibility to add an indefinite number of elements, the method
	 * will expand the internal storage when it detects that we have reached the end
	 * of available locations in the current array.
	 * 
	 * Observe that we are increasing the size member with a postfix operator.
	 */
	@Override
	public void add(T newElem) {
		if (size == array.length)
			array = Arrays.copyOf(array, array.length * 2);
		array[size++] = newElem;
	}

	/**
	 * Removes the element at a given location of the list.
	 * 
	 * The methods copies all the elements after the specified index to the same array,
	 * over-writting the element being removed. After that we erase the last element
	 * of the array by setting it to null, and decrease the size with a postfix operator. 
	 * 
	 *  @param index Index of the element being removed.
	 *  
	 */
	@Override
	public void remove(int index) {
		checkIndex(index);
		System.arraycopy(array, index + 1, array, index, size - 1 - index);
		array[--size] = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		checkIndex(index);
		return (T) array[index];
	}

	@Override
	public void set(int index, T elem) {
		checkIndex(index);
		array[index] = elem;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>(){
			private int pos = 0;

			public boolean hasNext() {
				return pos < size;
			}

			@SuppressWarnings("unchecked")
			public T next() {
				if(hasNext())
					return (T)array[pos++];
				else
					throw new NoSuchElementException("Pas de suivant");
			}
			
			public void remove(){
				if(pos == 0)
					throw new IllegalStateException("next() n'a pas été appelé!");
				else{
					System.arraycopy(array, pos, array, pos-1, size - 1 - pos - 1);
					array[--size] = null;
				}
			}
		};
	}

	@Override
	public String toString() {
	    StringBuilder b = new StringBuilder("[");
	    for (Iterator<T> i = iterator(); i.hasNext();) {
	    	b.append(i.next());
	        if (i.hasNext())
	            b.append(",");
	    }
	    b.append("]");
	    return b.toString();
	}
	
	private void checkIndex(int index) {
		if (! (0 <= index && index < size))
			throw new IndexOutOfBoundsException("invalid index: "+ index +" (must be in [0;"+ size +"[)");
	}
}


