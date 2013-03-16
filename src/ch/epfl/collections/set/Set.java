package ch.epfl.collections.set;

/**
 * Interface for basic sets.
 * @author Michel Schinz
 */
public interface Set<E> extends Iterable<E>{
    /**
     * Check whether the set is empty.
     * @return <code>true</code> if and only if the set is empty.
     */
    public boolean isEmpty();

    /**
     * Get the number of elements in the set.
     * @return the number of elements in the set.
     */
    public int size();

    /**
     * Add an element to the set.
     * @param newElem the element to add
     */
    public void add(E newElem);

    /**
     * Remove the given element from the set, or do nothing if the set does not contain the element.
     * @param elem the element to remove.
     */
    public void remove(E elem);

    /**
     * Test whether the set contains the given element.
     * @param elem the element to look for.
     * @return <code>true</code> if and only if the set contains <code>elem</code>
     */
    public boolean contains(E elem);
    
}
