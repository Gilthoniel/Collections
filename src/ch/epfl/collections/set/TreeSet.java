package ch.epfl.collections.set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TreeSet<E extends Comparable<E>> implements Set<E> {
    private static int size;
    private Tree<E> root;
    
    public TreeSet(){
    	root = new Leaf<E>();
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
    public void add(E newElem) {
        root = root.add(newElem);
    }

    @Override
    public void remove(E elem) {
        root = root.remove(elem);
    }

    @Override
    public boolean contains(E elem) {
        return root.contains(elem);
    }
    
    // Iterator
    public Iterator<E> iterator(){
    	final LinkedList<Node<E>> liste = new LinkedList<Node<E>>();
    	if(!(root.isEmpty())){
    		liste.push((Node<E>)root);
        	int i = 0;
        	while(!(liste.get(i).smaller.isEmpty())){
        		liste.push((Node<E>) liste.get(0).smaller);
        	}
    	}
    	
    	return new Iterator<E>(){
    		private LinkedList<Node<E>> nodes = liste;
    		
    		public boolean hasNext(){
    			if(nodes.isEmpty())
    				return false;
    			else
    				return true;
    		}
    		
    		public E next(){
    			Node<E> lastNode = nodes.pop();
    			
    			if(lastNode == null){
    				throw new NoSuchElementException("Pas d'éléments suivant");
    			}
    			
    			if(!(lastNode.bigger.isEmpty())){
    				nodes.push((Node<E>) lastNode.bigger);
    			}
    			
    			return lastNode.value;
    		}
    		
    		public void remove(){
    			throw new UnsupportedOperationException("Méthode non-implémentée");
    		}
    	};
    }

    
    // Tree
    
    private static interface Tree<E extends Comparable<E>> {
        public boolean isEmpty();
        public int size();
        public Tree<E> add(E newElem);
        public Tree<E> remove(E elem);
        public boolean contains(E elem);
        public E getMin();
    }
    
    // TODO: Implement Leaf and Node classes as inner classes
    private static class Leaf<E extends Comparable<E>> implements Tree<E>{
    	
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
    	 * Retournela valeur minimale d'un arbre
    	 */
    	public E getMin(){
    		return null;
    	}
    	
    	public Tree<E> add(E elem){
    		size++;
    		return new Node<E>(new Leaf<E>(), elem, new Leaf<E>());
    	}
    	
    	public Tree<E> remove(E elem){
    		return this;
    	}
    	
    	public boolean contains(E elem){
    		return false;
    	}
    };
    
    private static class Node<E extends Comparable<E>> implements Tree<E>{
    	private final E value;
    	private Tree<E> smaller, bigger;
    	
    	public Node(Tree<E> s, E v, Tree<E> b){
    		smaller = s;
    		bigger = b;
    		value = v;
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
    	public E getMin(){
    		if(smaller.isEmpty())
    			return value;
    		else
    			return smaller.getMin();
    	}
    	
    	/**
    	 * Fonction d'insertion
    	 * @param: Integer élément voulant être ajouté
    	 * @return: La node qui suit dans l'arbre de recherche
    	 */
    	public Tree<E> add(E elem){
    		if(elem.compareTo(value) < 0)
    			smaller = smaller.add(elem);
    		else if(elem.compareTo(value) > 0)
    			bigger = bigger.add(elem);
    		// Sinon l'élément existe déjà
    		
    		return this; // retourne la node pour l'algorithme récursif
    	}
    	
    	/**
    	 * Fonction de recherche
    	 * @param: Un entier à rechercher
    	 * @return: vrai ou faux
    	 */
    	public boolean contains(E elem){
    		if(elem.compareTo(value) < 0)
    			return smaller.contains(elem);
    		else if(elem.compareTo(value) > 0)
    			return bigger.contains(elem);
    		else
    			return true;
    	}
    	
    	/**
    	 * Supprime l'élément
    	 * @param: élément à supprimer
    	 * @return: node suivante
    	 */
    	public Tree<E> remove(E elem){
    		if(elem.compareTo(value) < 0){
    			smaller = smaller.remove(elem);
    			return this;
    		}
    		else if(elem.compareTo(value) > 0){
    			bigger = bigger.remove(elem);
    			return this;
    		}
    		else{
    			if(smaller.isEmpty() && bigger.isEmpty()){
    				size--;
    				return new Leaf<E>();
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
    				E minValue = bigger.getMin();
    				remove(minValue);
    				return new Node<E>(smaller, minValue, bigger);
    			}
    		}
    	}
    };

}
