package ch.epfl.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Double-linked circular implementation of the StringList interface.
 * 
 * The class manages and keeps track of SLLNode elements which contain the
 * list data.
 * 
 * @see SLLNode
 * @see SLLIterator
 * @see LinkedList<T>Test
 *
 */
public class LinkedList<T> implements List<T> {
	/**
	 * Main reference to the list of SLLNode elements. Tail of the list
	 * can always be found trough the head.prev 
	 */
    private SLLNode<T> head = null;
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
     * Adds new element to the list.
     * 
     * Creates a new SLLNode and updates the references of existing list elements
     * to position the new item at the tail position of the list.
     */
    @Override
    public void add(T newElem) {
        final SLLNode<T> newNode = new SLLNode<T>(newElem);
        if (size == 0) {	// in case of the empty list
            assert head == null;
            head = newNode;
            newNode.next = newNode.prev = newNode;
        } else {			// if the list is not empty, find the tail and update references
            assert head != null;
            SLLNode<T> tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
        size += 1;
    }

    /**
     * Removes the item from the list.
     * 
     * When removing item N, the method updates the .next reference of item N-1
     * and the .prev reference of the item N+1.
     */
    @Override
    public void remove(int index) {
        SLLNode<T> nodeToRemove = nodeAtIndex(index);
        nodeToRemove.prev.next = nodeToRemove.next;
        nodeToRemove.next.prev = nodeToRemove.prev;
        if(index == 0){
        	head = nodeToRemove.next;
        }
        if (--size == 0)
            head = null;
    }

    @Override
    public T get(int index) {
        return nodeAtIndex(index).value;
    }

    @Override
    public void set(int index, T elem) {
        nodeAtIndex(index).value = elem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){					// Classe anonyme LLIterator
        	private SLLNode<T> nextNode = head;
            private int nbLeft = size;

            @Override
            public boolean hasNext() {
                return nbLeft > 0;
            }

            @Override
            public T next() {
            	if(nbLeft > 0){
        	        T elem = nextNode.value;
        	        nextNode = nextNode.next;
        	        --nbLeft;
        	        return elem;
            	}
            	else
            		throw new NoSuchElementException("Fin de Liste");
            }
            
            public void remove(){
            	if(nbLeft == size)
            		throw new IllegalStateException("next() n'a pas été appelé!");
            	else{
            		nextNode.prev.prev.next = nextNode;
            		nextNode.prev = nextNode.prev.prev;
            		if(nbLeft+1 == size)
            			head = nextNode.prev;
            		size--;
            	}
            		
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("[");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            b.append(it.next());
            if (it.hasNext())
                b.append(",");
        }
        b.append("]");
        return b.toString();
    }

    /**
     * Internal method for accessing items by iterating N times from the head node.
     * 
     * @param index Index of the element to be returned
     * @return SLLNode at the requested position
     * @throws IndexOutOfBoundsException if index is greater then the number of elements in
     * 		   the list.
     */
    private SLLNode<T> nodeAtIndex(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("invalid index: "+ index +" (must be in [0;"+ (size() - 1) + "])");

        SLLNode<T> n = head;
        for (int i = 0; i < index; ++i)
            n = n.next;
        return n;
    }
    
    /* CLASSE IMBRIQUEE */
    /**
     * Double-linked list element. Used in LinkedList<T>.
     *
     * @see LinkedList<T>
     */
    private static class SLLNode<T> {
        T value;
        /**
         * References to the previous and next elements of the list. 
         */
        SLLNode<T> prev = null, next = null;

        public SLLNode(T value) {
            this.value = value;
        }
    }
}


