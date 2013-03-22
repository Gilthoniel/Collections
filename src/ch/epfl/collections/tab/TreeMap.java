package ch.epfl.collections.tab;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    private static int size;
    private Tree<K, V> root;
    
    public TreeMap(){
    	root = new Leaf<K, V>();
    	size = 0;
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public int size() {
        return root.size();
    }

    @Override
    public void put(K key, V value) {
        root = root.put(key, value);
    }

    @Override
    public void remove(K key) {
        root = root.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return root.contains(key);
    }
    
    @Override
    public V get(K key){
    	return root.get(key);
    }
    
    // Tree
    
    private static interface Tree<K extends Comparable<K>, V> {
        public boolean isEmpty();
        public int size();
        public Tree<K, V> put(K key, V value);
        public Tree<K, V> remove(K key);
        public boolean contains(K key);
        public K getMin();
        public Node<K, V> nodeForKey(K key);
        public V get(K key);
    }
    
    // TODO: Implement Leaf and Node classes as inner classes
    private static class Leaf<K extends Comparable<K>, V> implements Tree<K, V>{
    	
    	/**
    	 * Retourne vrai si la taille est 0
    	 */
    	public boolean isEmpty(){
    		return true;
    	}
    	
    	/**
    	 * Retourne la taille
    	 */
    	public int size(){
    		return size;
    	}
    	
    	/**
    	 * Retourne la valeur minimale d'un arbre
    	 */
    	public K getMin(){
    		return null;
    	}
    	
    	public Tree<K, V> put(K key, V value){
    		size++;
    		return new Node<K, V>(new Leaf<K, V>(), key, value, new Leaf<K, V>());
    	}
    	
    	public Tree<K, V> remove(K elem){
    		return this;
    	}
    	
    	public boolean contains(K elem){
    		return false;
    	}
    	
    	public Node<K, V> nodeForKey(K key){
    		return null;
    	}
    	
    	public V get(K key){
    		return null;
    	}
    };
    
    private static class Node<K extends Comparable<K>, V> implements Tree<K, V>{
    	private final K nodeKey;
    	private Tree<K, V> smaller, bigger;
    	private V value;
    	
    	public Node(Tree<K, V> s, K key, V value, Tree<K, V> b){
    		smaller = s;
    		bigger = b;
    		nodeKey = key;
    		this.value = value;
    	}
    	
    	/**
    	 * Retourne la taille
    	 */
    	public int size(){
    		return size;
    	}
    	
    	/**
    	 * Retourne vrai si la taille est 0
    	 */
    	public boolean isEmpty(){
    		return false;
    	}
    	
    	/**
    	 * Retourne la valeur minimale d'un arbre
    	 */
    	public K getMin(){
    		if(smaller.isEmpty())
    			return nodeKey;
    		else
    			return smaller.getMin();
    	}
    	
    	/**
    	 * Fonction d'insertion
    	 * @param: Integer élément voulant être ajouté
    	 * @return: La node qui suit dans l'arbre de recherche
    	 */
    	public Tree<K, V> put(K key, V value){
    		if(key.compareTo(nodeKey) < 0)
    			smaller = smaller.put(key, value);
    		else if(key.compareTo(nodeKey) > 0)
    			bigger = bigger.put(key, value);
    		// Sinon l'élément existe déjà
    		
    		return this; // retourne la node pour l'algorithme récursif
    	}
    	
    	/**
    	 * Fonction de recherche
    	 * @param: Un entier à rechercher
    	 * @return: vrai ou faux
    	 */
    	public boolean contains(K key){
    		if(key.compareTo(nodeKey) < 0)
    			return smaller.contains(key);
    		else if(key.compareTo(nodeKey) > 0)
    			return bigger.contains(key);
    		else
    			return true;
    	}
    	
    	/**
    	 * Retourne la valeur selon la clé
    	 */
    	public V get(K key){
    		return nodeForKey(key).value;
    	}
    	
    	/**
    	 * Retourne la node si c'est la bonne, sinon renvoie la suite de l'arbre
    	 */
    	public Node<K, V> nodeForKey(K key){
    		if(key.compareTo(nodeKey) < 0)
    			return smaller.nodeForKey(key);
    		else if(key.compareTo(nodeKey) > 0)
    			return bigger.nodeForKey(key);
    		else
    			return this;
    	}
    	
    	/**
    	 * Supprime l'élément
    	 * @param: élément à supprimer
    	 * @return: node suivante
    	 */
    	public Tree<K, V> remove(K key){
    		if(key.compareTo(nodeKey) < 0){
    			smaller = smaller.remove(key);
    			return this;
    		}
    		else if(key.compareTo(nodeKey) > 0){
    			bigger = bigger.remove(key);
    			return this;
    		}
    		else{
    			if(smaller.isEmpty() && bigger.isEmpty()){
    				size--;
    				return new Leaf<K, V>();
    			}
    			else if(smaller.isEmpty()){
    				size--;
    				return bigger;
    			}
    			else if(bigger.isEmpty()){
    				size--;
    				return smaller;
    			}
    			else{
    				K minValue = bigger.getMin();
    				remove(minValue);
    				return new Node<K, V>(smaller, minValue, nodeForKey(minValue).value, bigger);
    			}
    		}
    	}
    };

}
