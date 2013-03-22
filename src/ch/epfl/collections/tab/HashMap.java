package ch.epfl.collections.tab;

/**
 * Classe représentant une table associative avec la technique de hachage
 * @author Gaylor Bosson
 *
 * @param <K> clé de la valeur
 * @param <V> valeur
 */
public class HashMap<K, V> implements Map<K, V>{
	@SuppressWarnings("unchecked")
	private Entry<K, V>[] table = (Entry<K, V>[]) new Entry[20];
	private int size = 0;
	
	/**
	 * Ajoute un élément
	 * @param key Clé associée à la valeur
	 * @param value La valeur que l'on veut ajouter
	 */
	@Override
	public void put(K key, V value){
		int h = Math.abs(key.hashCode()) % table.length; // Calcule de l'index de la clé
		
		Entry<K, V> n = table[h];
		while(n != null && !n.key.equals(key)){
			n = n.next;
		}
		
		if(n == null){ // S'il n'existe pas, on l'ajoute
			table[h] = new Entry<K, V>(table[h], key, value);
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
		Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newSize]; // Le nouveau tableau
		
		for(int i = 0; i < table.length; i++){ // Parcours l'ancien tableau
			Entry<K, V> n = table[i];
			while(n != null){
				int h = Math.abs(n.key.hashCode()) % newTable.length; // nouvel indice
				newTable[h] = new Entry<K, V>(newTable[h], n.key, n.value); // On crée la nouvelle Entry
				n = n.next;
			}
		}
		
		table = newTable;
	}

	/**
	 * Retourne vrai si la clé existe
	 * @param key La clé à contrôler
	 * @return boolean contenant la réponse
	 */
	@Override
	public boolean containsKey(K key){
		Entry<K, V> n = entryForKey(key);
		
		return (n != null);
	}
	
	/**
	 * Test le vide
	 * @return vrai si le tableau est vide
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Retourne la taille
	 * @return Integer contenant la taille
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Supprime l'élément s'il existe
	 * @param key La clé de l'élément à supprimer
	 */
	@Override
	public void remove(K key) {
		int h = Math.abs(key.hashCode()) % table.length;
		
		Entry<K, V> n = table[h];
		Entry<K, V> last = null;
		while(n != null && !n.key.equals(key)){
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
	
	/**
	 * Retourne la valeur selon la clé
	 * @param key La clé de l'Entry recherchée
	 * @return la valeur trouvée
	 */
	public V get(K key){
		Entry<K, V> n = entryForKey(key);
		
		return (n!=null) ? n.value : null;
	}
	
	/**
	 * Retourne une Entry selon la clé donnée
	 * @param key La clé
	 * @return Entry
	 */
	public Entry<K, V> entryForKey(K key){
		int h = Math.abs(key.hashCode()) % table.length;
		
		Entry<K, V> n = table[h];
		while(n != null && !n.key.equals(key)){
			n = n.next;
		}
		
		return n;
	}
	
	/**
	 * Classe représentant les noeuds de l'ensemble (tableau associatif)
	 * @author Gaylor
	 *
	 * @param <K> clé
	 * @param <V> valeur
	 */
	private static class Entry<K, V>{
		public Entry<K, V> next;
		public final K key;
		public V value;
		
		public Entry(Entry<K, V> next, K key, V value){
			this.next = next;
			this.key = key;
			this.value = value;
		}
	}
}
