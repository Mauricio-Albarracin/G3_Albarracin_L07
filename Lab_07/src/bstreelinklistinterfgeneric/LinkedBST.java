package bstreelinklistinterfgeneric;

import Exceptions.*;
import bstreeInterface.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

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

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    // Insertar un elemento en el BST
    public void insert(E data) throws ItemDuplicated {
        root = insert(root, data);
    }

    private Node insert(Node actual, E data) throws ItemDuplicated {
        if (actual == null) {
            return new Node(data);
        }

        int compara = data.compareTo(actual.data);
        if (compara < 0) {
            actual.left = insert(actual.left, data);
        } else if (compara > 0) {
            actual.right = insert(actual.right, data);
        } else {
            throw new ItemDuplicated("Elemento duplicado: " + data);
        }

        return actual;
    }

    // Buscar un elemento en el BST
    public E search(E data) throws ItemNoFound {
        return search(root, data);
    }

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
            return actual.data;
        }
    }

    // Eliminar un elemento del BST
    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío");
        }
        root = delete(root, data);
    }

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
            // Caso 1: sin hijos
            if (actual.left == null && actual.right == null) {
                return null;
            }
            // Caso 2: un solo hijo
            if (actual.left == null) {
                return actual.right;
            } else if (actual.right == null) {
                return actual.left;
            }
            // Caso 3: dos hijos
            E min = findMin(actual.right);
            actual.data = min;
            actual.right = delete(actual.right, min);
        }
        return actual;  
    }

    private E findMin(Node actual) {
        while (actual.left != null) {
            actual = actual.left;
        }
        return actual.data;
    }

    // Mostrar el árbol en inorden como cadena (sin usar StringBuilder)
    public String toString() {
        return toString(root).trim();
    }

    private String toString(Node actual) {
        if (actual == null) {
            return "";
        }
        return toString(actual.left) + actual.data + " " + toString(actual.right);
    }

    // Verifica si el árbol está vacío
    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
