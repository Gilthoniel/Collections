Cours de programmation du 2ème semestre à l'EPFL.
-------------------------------------------------

*Développement de la bibliothèque Java pour les collections. Des tests JUnit sont mis en place pour contrôler le code.*

1. Première étape, les listes !  
	* Création de StringList dérivé en deux sous-classe. ArrayList qui est constitué d'un tableau dynamique et LinkedList, qui est
	constitué de nodes.  
	* Ensuite, modification du code pour rendre les listes générique, de type List<T>.
2. Deuxième étape, les ensembles
	* Création d'une classe IntTreeSet pour prendre en main les ensemble
	* Généralisation de la classe pour tout type implémentant Comparable
	* Mise en place de l'itérateur
3. Troisième étape, les tableaux associatifs
	* Modifications des classes d'ensembles
	* Deux classes ainsi créées : TreeMap et HashMap