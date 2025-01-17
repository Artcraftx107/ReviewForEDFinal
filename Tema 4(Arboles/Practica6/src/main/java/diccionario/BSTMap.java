/*******************************
 * Copyright (C) [2024] [J.L. Triviño]
 *
 * Este programa es software libre: puedes redistribuirlo y/o modificarlo
 * bajo los términos de la Licencia Pública General de GNU según lo publicado
 * por la Free Software Foundation, ya sea la versión 3 de la Licencia, o
 * (a tu elección) cualquier versión posterior.
 *
 * Este programa se distribuye con la esperanza de que sea útil, pero
 * SIN NINGUNA GARANTÍA; ni siquiera la garantía implícita de
 * COMERCIABILIDAD o IDONEIDAD PARA UN PROPÓSITO PARTICULAR.
 * Consulta la Licencia Pública General de GNU para obtener más detalles.
 *
 * Deberías haber recibido una copia de la Licencia Pública General de GNU
 * junto con este programa. Si no es así, consulta <http://www.gnu.org/licenses/>.
 */

package diccionario;

import java.util.ArrayList;
import java.util.Set;

import java.util.HashSet;

public class BSTMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {
	public static final class BST<K extends Comparable<K>, V>{
		public K key;
		public V value;
		public BST<K,V> left, right;
		public int height;

		BST( K k, V v, BST<K, V> l, BST<K, V> r ){
			key = k;
			value = v;
			left = l;
			right = r;
		}

		BST( K k, V v ){
			key = k;
			value = v;
			left = null;
			right = null;
		}
	}

	private BST<K,V> bst; // Cabeza de la lista enlazada


	public BSTMap() {
		bst = null;
		size = 0;
	}



	@Override
	public V put(K key, V value) {
		V oldValue = null;

		// Completar
		oldValue = get(key);
		if(key == null){
			throw new NullPointerException();
		}
		bst = insert(bst,key,value);

		return oldValue;
	}

	private BST<K,V> insert(BST<K,V> root,K key, V value){
		if(root == null){
			size++;
			return new BST<>(key,value);
		}else if(key.compareTo(root.key) == 0){
			root.value = value;
		}else if(key.compareTo(root.key) > 0){
			root.right = insert(root.right, key, value);
		}else if(key.compareTo(root.key) < 0){
			root.left = insert(root.left, key, value);
		}

		root.height = 1+Math.max(calcularALtura(root.left),calcularALtura(root.right));

		return balance(root);
	}

	private BST<K, V> search(BST<K, V> root, K key) {
		if (root == null) {
			return null; // Si no encontramos el nodo, retornamos null.
		}


		if (key.compareTo(root.key) == 0) {
			return root; // Si la clave es menor, buscar en el subárbol izquierdo.
		} else if (key.compareTo(root.key) > 0) {
			return search(root.right, key); // Si la clave es mayor, buscar en el subárbol derecho.
		} else {
			return search(root.left,key); // Si la clave es igual, hemos encontrado el nodo y lo retornamos.
		}
	}




	@Override
	public boolean hasEntry(K key) {
		// Completar
		if(key == null){
			throw new NullPointerException();
		}

		return search(bst, key) != null;

	}



	@Override
	public V get(K key) {
		// Completar
		if(key == null){
			throw new NullPointerException();
		}

		BST<K,V> aux = search(bst,key);
		if(aux != null) {
			return aux.value;
		}else{
			return null;
		}
	}



	@Override
	public V remove(K key) {
		if (key == null) {
			throw new NullPointerException();
		}

		// Almacén para capturar el valor eliminado
		V oldValue = get(key);
		if(hasEntry(key)){
			bst = delete(bst, key);
			size--;
		}

		return oldValue; // Retorna el valor eliminado, o null si no existía
	}



	private BST<K, V> delete(BST<K, V> root, K key) {
		if (root == null) {
			return null; // Clave no encontrada, no hay nada que eliminar
		}

		if (key.compareTo(root.key) < 0) {
			root.left = delete(root.left, key);
		} else if (key.compareTo(root.key) > 0) {
			root.right = delete(root.right, key);
		} else { // Se encontró el nodo con la clave
			// Captura el valor antes de eliminarlo

			// Casos si tiene un hijo o ninguno
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			// Si tiene 2 hijos
			BST<K, V> min = root.right;
			while (min.left != null) {
				min = min.left;
			}
			root.key = min.key;
			root.value = min.value;
			root.right = delete(root.right, min.key);
		}

		root.height = 1+Math.max(calcularALtura(root.left),calcularALtura(root.right));

		return balance(root);
	}



	@Override
	public void clear() {
		bst = null;
		size = 0;
	}



	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> entrySet = new HashSet<>();
		// Completar
		ArrayList<Map.Entry<K, V>> aux = new ArrayList<>();
		inOrder(bst,aux);
		entrySet.addAll(aux); //Ponemos los elementos del arrayList aux en el set
		return entrySet;
	}



	@Override
	@SuppressWarnings("unchecked")
	protected Entry<K, V>[] toArray() {
		var array = new Entry[size]; // Usa el tamaño dinámico
		ArrayList<Entry<K, V>> aux = new ArrayList<>(); //Usamos un arrayList para facilitar el poner valores
		inOrder(bst, aux);
		array = aux.toArray(array);
		return array;
	}



	//Metodo para recorrer los valores del mas chico al mas grande e ir poniendolos an el ArrayList auxiliar
	private void inOrder(BST<K, V> root, ArrayList<Entry<K, V>> aux) {
		if (root != null) { // Condición base de la recursión
			inOrder(root.left, aux); // Subárbol izquierdo
			aux.add(new Entry<>(root.key, root.value)); // Nodo actual
			inOrder(root.right, aux); // Subárbol derecho
		}
	}

	private BST<K, V> balance(BST<K, V> root){
		if(balanceFactor(root) > 1 && balanceFactor(root.left) >= 0){
			root = rightRotation(root);
		}else if(balanceFactor(root) > 1 && balanceFactor(root.left) < 0){
			root.left = leftRotation(root.left);
			root = rightRotation(root);
		}else if(balanceFactor(root) < -1 && balanceFactor(root.right) <= 0){
			root = leftRotation(root);
		}else if(balanceFactor(root) < -1 && balanceFactor(root.right) > 0){ //RL
			root.right = rightRotation(root.right);
			root = leftRotation(root);
		}

		return root;
	}

	private BST<K, V> rightRotation(BST<K, V> root){
		BST<K, V> x = root;
		BST<K, V> y = x.left;
		x.left = y.right;
		y.right = x;

		x.height = 1+Math.max(calcularALtura(x.left),calcularALtura(x.right));
		y.height = 1+Math.max(calcularALtura(y.left),calcularALtura(y.right));

		return y;
	}

	private BST<K, V> leftRotation(BST<K, V> root){
		BST<K, V> x = root;
		BST<K, V> y = x.right;
		x.right = y.left;
		y.left = x;

		x.height = 1+Math.max(calcularALtura(x.left),calcularALtura(x.right));
		y.height = 1+Math.max(calcularALtura(y.left),calcularALtura(y.right));

		return y;
	}



	private int calcularALtura(BST<K, V> root) {
		if (root == null) return -1;
		return root.height;
	}

	private int balanceFactor(BST<K, V> root){
		if(root == null){
			return 0;
		}
		return calcularALtura(root.left)-calcularALtura(root.right);
	}


}