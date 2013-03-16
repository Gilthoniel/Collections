package ch.epfl.collections.set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashSet<E> implements Set<E>{
	@SuppressWarnings("unchecked")
	private Node<E>[] table = (Node<E>[]) new Node[20];
	private int size = 0;
	
	/**
	 * Fonction qui ajoute un élément
	 * @author Gaylor
	 * @param E e : élément à ajouter
	 */
	@Override
	public void add(E e){
		int h = Math.abs(e.hashCode()) % table.length;
		
		Node<E> n = table[h];
		while(n != null && !n.equals(e)){
			n = n.next;
		}
		
		if(n == null){ // S'il n'existe pas, on l'ajoute
			table[h] = new Node<E>(table[h], e);
			size++;
		} // sinon, on ne fait rien puisque chaque élément est unique
		
		// Si nécessaire, rehachage!
		if((size/(double)table.length < 0.5 || size/(double)table.length > 1) && size > 10)
			rehachage();
	}
	
	/**
	 * Recalcule la taille du tableau
	 */
	private void rehachage() {
		int newSize = (int) (size*(4/3.0));
		@SuppressWarnings("unchecked")
		Node<E>[] newTable = (Node<E>[]) new Node[newSize];
		
		for(Iterator<E> i = iterator(); i.hasNext();){
			E elem = i.next();
			int h = Math.abs(elem.hashCode()) % newSize;
			
			newTable[h] = new Node<E>(newTable[h], elem);
		}
		
		table = newTable;
	}

	/**
	 * @author Gaylor
	 * Cette Fonction retourne vrai si l'élément existe
	 * @param E e : élément à contrôler
	 */
	@Override
	public boolean contains(E e){
		int h = Math.abs(e.hashCode()) % table.length;
		
		Node<E> n = table[h];
		while(n != null && !n.elem.equals(e)){
			n = n.next;
		}
		
		return (n != null);
	}
	
	/**
	 * @author Gaylor
	 * Fonction retourne qui vrai si l'ensemble est vide
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * @author Gaylor
	 * Retourne la taille
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * @author Gaylor
	 * Supprime l'élément s'il existe
	 * @param E e : élément à supprimer
	 */
	@Override
	public void remove(E e) {
		int h = Math.abs(e.hashCode()) % table.length;
		
		Node<E> n = table[h];
		Node<E> last = null;
		while(n != null && !n.elem.equals(e)){
			last = n;
			n = n.next;
		}
		
		if(last == null && n.next == null){
			table[h] = null;
			size--;
		}
		else if(last == null && n.next != null){
			table[h] = n.next;
			size--;
		}
		else if(n != null){
			last.next = n.next;
			size--;
		}
		
		// Si nécessaire, rehachage!
		if((size/(double)table.length < 0.5 || size/(double)table.length > 1) && size > 10)
			rehachage();
	};
	
	private static class Node<E>{
		public Node<E> next;
		public final E elem;
		
		public Node(Node<E> next, E elem){
			this.next = next;
			this.elem = elem;
		}
	}

	@Override
	public Iterator<E> iterator() {
		final LinkedList<E> liste = new LinkedList<E>();
		for(int i = 0; i < table.length; i++){
			Node<E> node = table[i];
			while(node != null){
				liste.push(node.elem);
				node = node.next;
			}
		}
		
		return new Iterator<E>(){

			@Override
			public boolean hasNext() {
				return liste.size() > 0;
			}

			@Override
			public E next() {
				if(liste.size() > 0)
					return liste.pop();
				else
					throw new NoSuchElementException("Pas d'autres éléments");
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Méthode non-implémentée");
			}
			
		};
	}
}
