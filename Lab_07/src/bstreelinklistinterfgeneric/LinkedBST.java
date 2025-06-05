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

    //private Node root;
    protected Node root;

    public LinkedBST() {
        this.root = null;
    }

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
            if (actual.left == null && actual.right == null) {
                return null;
            }
            if (actual.left == null) {
                return actual.right;
            } else if (actual.right == null) {
                return actual.left;
            }
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

    // MÉTODO DE RECORRIDO IN-ORDER CON MENSAJES
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

    // MÉTODO DE RECORRIDO PRE-ORDER CON MENSAJES
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

    // Método público que inicia el recorrido post-orden
    public void recorrerPostOrder() {
        postOrder(root);
    }

    // Método privado recursivo que realiza el recorrido post-orden con mensajes
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
}
