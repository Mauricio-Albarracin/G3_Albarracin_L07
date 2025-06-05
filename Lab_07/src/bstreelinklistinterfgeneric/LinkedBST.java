package bstreelinklistinterfgeneric;

import Exceptions.*;
import bstreeInterface.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    // Clase interna que representa un nodo del árbol
    class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this(data, null, null); 
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    protected Node root; // raíz del árbol

    public LinkedBST() {
        this.root = null;
    }

    // Inserta un nuevo elemento en el árbol
    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    // Inserción recursiva
    private Node insert(Node actual, E data) throws ItemDuplicated {
        if (actual == null) {
            return new Node(data); // Caso base: nodo nuevo
        }

        int compara = data.compareTo(actual.data); // Comparar con el nodo actual
        if (compara < 0) {
            actual.left = insert(actual.left, data); // Insertar a la izquierda
        } else if (compara > 0) {
            actual.right = insert(actual.right, data); // Insertar a la derecha
        } else {
            throw new ItemDuplicated("Elemento duplicado: " + data); // Ya existe
        }

        return actual;
    }

    // Busca un elemento en el árbol
    public E search(E data) throws ItemNoFound {
        return search(root, data);
    }

    // Búsqueda recursiva
    private E search(Node actual, E data) throws ItemNoFound {
        if (actual == null) {
            throw new ItemNoFound("Elemento no encontrado: " + data);
        }

        int compara = data.compareTo(actual.data);
        if (compara < 0) {
            return search(actual.left, data);
        } else if (compara > 0) {
            return search(actual.right, data);
        } else {
            return actual.data; // Encontrado
        }
    }

    // Elimina un elemento del árbol
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }
        root = delete(root, data);
    }

    // Eliminación recursiva
    private Node delete(Node actual, E data) {
        if (actual == null) {
            return null;
        }

        int compara = data.compareTo(actual.data);
        if (compara < 0) {
            actual.left = delete(actual.left, data);
        } else if (compara > 0) {
            actual.right = delete(actual.right, data);
        } else {
            // Nodo encontrado
            if (actual.left == null && actual.right == null) {
                return null; // Caso 1: sin hijos
            }
            if (actual.left == null) {
                return actual.right; // Caso 2: un hijo (derecho)
            } else if (actual.right == null) {
                return actual.left; // Caso 2: un hijo (izquierdo)
            }
            // Caso 3: dos hijos
            E min = findMin(actual.right); // Buscar mínimo en subárbol derecho
            actual.data = min;
            actual.right = delete(actual.right, min); // Eliminar duplicado
        }
        return actual;
    }

    // Encuentra el menor valor desde un nodo
    private E findMin(Node actual) {
        while (actual.left != null) {
            actual = actual.left;
        }
        return actual.data;
    }

    // Representación del árbol en inorden (ordenado)
    public String toString() {
        return toString(root).trim();
    }

    private String toString(Node actual) {
        if (actual == null) {
            return "";
        }
        return toString(actual.left) + actual.data + " " + toString(actual.right);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    // Recorrido inorden con mensajes de depuración
    public void recorrerInOrder() {
        inOrder(root);
    }

    private void inOrder(Node actual) {
        System.out.println("Recorrer el subárbol izquierdo en in-orden.");

        if (actual == null) {
            System.out.println("Vacío");
            return;
        }

        inOrder(actual.left);
        System.out.println("Visitar la raíz: " + actual.data);
        System.out.println("Recorrer el subárbol derecho en in-orden.");
        inOrder(actual.right);
    }

    // Recorrido preorden
    public void recorrerPreOrder() {
        preOrder(root);
    }

    private void preOrder(Node actual) {
        if (actual == null) {
            System.out.println("Vacío");
            return;
        }

        System.out.println("Visitar la raíz: " + actual.data);
        System.out.println("Recorrer el subárbol izquierdo en pre-orden.");
        preOrder(actual.left);
        System.out.println("Recorrer el subárbol derecho en pre-orden.");
        preOrder(actual.right);
    }

    // Recorrido postorden
    public void recorrerPostOrder() {
        postOrder(root);
    }

    private void postOrder(Node actual) {
        System.out.println("Recorrer el subárbol izquierdo en postorden.");
        if (actual == null) {
            System.out.println("Vacío");
            return;
        }

        postOrder(actual.left);
        System.out.println("Recorrer el subárbol derecho en postorden.");
        postOrder(actual.right);
        System.out.println("Visitar la raiz: " + actual.data);
    }

    // Encuentra el menor valor desde un nodo dado
    private E findMinNode(Node node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("Subárbol nulo. No se puede encontrar el mínimo.");
        }

        Node current = node;
        while (current.left != null) {
            current = current.left;
        }

        return search(current.data); // Validación usando búsqueda
    }

    // Encuentra el mayor valor desde un nodo dado
    private E findMaxNode(Node node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound("Subárbol nulo. No se puede encontrar el máximo.");
        }

        Node current = node;
        while (current.right != null) {
            current = current.right;
        }

        return search(current.data); // Validación usando búsqueda
    }

    // Permite obtener el menor valor desde un nodo con cierto valor
    public E obtenerMinimoDesde(E data) throws ItemNoFound {
        Node node = buscarNodo(root, data);
        if (node == null) {
            throw new ItemNoFound("No se encontró la raíz para buscar el mínimo.");
        }
        return findMinNode(node);
    }

    // Permite obtener el mayor valor desde un nodo con cierto valor
    public E obtenerMaximoDesde(E data) throws ItemNoFound {
        Node node = buscarNodo(root, data);
        if (node == null) {
            throw new ItemNoFound("No se encontró la raíz para buscar el máximo.");
        }
        return findMaxNode(node);
    }

    // Busca y devuelve el nodo que contiene el dato especificado
    private Node buscarNodo(Node actual, E data) {
        if (actual == null) return null;
        int comparar = data.compareTo(actual.data); 
        if (comparar == 0) return actual;
        else if (comparar < 0) return buscarNodo(actual.left, data);
        else return buscarNodo(actual.right, data);
    }
}
