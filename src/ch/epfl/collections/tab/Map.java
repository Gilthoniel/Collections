package ch.epfl.collections.tab;

/**
 * A collection mapping keys to values.
 * 
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public interface Map<K, V> {
    /**
     * Returns <code>true</code> iff this map contains no key-value mappings.
     * 
     * @return <code>true</code> if this map contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Returns the number of key-value mappings in this map.
     * 
     * @return the number of key-value mappings in this map
     */
    int size();
    
    
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value
     * is replaced by the specified value. 
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    void put(K key, V value);
    
    
    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     */
    void remove(K key);
    
    /**
     * Returns the value to which the specified key is mapped, or <code>null</code>
     * if this map contains no mapping for the key.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or <code>null</code>
     *  if this map contains no mapping for the key.
     */
    V get(K key);
    
    /**
     * Returns <code>true</code> if this map contains a mapping for the specified key.
     * @param key key whose presence in this map is to be tested
     * @return <code>true</code> if this map contains a mapping for the specified key
     */
    boolean containsKey(K key);
}
