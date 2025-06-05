package bstreelinklistinterfgeneric;

import javax.swing.*;
import java.awt.*;

// Clase para dibujar un árbol binario de búsqueda y mostrar los recorridos in-orden, pre-orden y post-orden.
public class Prueba<E extends Comparable<E>> extends JPanel {

    private final LinkedBST<E> arbol;
    private final String recorridoInOrder;
    private final String recorridoPreOrder;
    private final String recorridoPostOrder;

    // Constructor
    public Prueba(LinkedBST<E> arbol) {
        this.arbol = arbol;
        this.recorridoInOrder = generarInOrder(arbol.root);
        this.recorridoPreOrder = generarPreOrder(arbol.root);
        this.recorridoPostOrder = generarPostOrder(arbol.root);
    }

    // Método para dibujar el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarNodo(g, arbol.root, getWidth() / 2, 30, getWidth() / 4);

        // Dibujar los recorridos
        g.setColor(Color.BLUE);
        g.drawString("Recorrido In-Order: " + recorridoInOrder, 20, getHeight() - 30);

        g.setColor(Color.RED);
        g.drawString("Recorrido Pre-Order: " + recorridoPreOrder, 20, getHeight() - 50);

        g.setColor(Color.BLACK);
        g.drawString("Recorrido Post-Order: " + recorridoPostOrder, 20, getHeight() - 70);
    }

    // Dibuja recursivamente los nodos y conexiones
    private void dibujarNodo(Graphics g, LinkedBST<E>.Node nodo, int x, int y, int distancia) {
        if (nodo == null) return;

        g.setColor(Color.BLACK);
        g.fillOval(x - 15, y - 15, 30, 30);

        g.setColor(Color.WHITE);
        g.drawString(nodo.data.toString(), x - 5, y + 5);

        g.setColor(Color.BLACK);
        if (nodo.left != null) {
            g.drawLine(x, y, x - distancia, y + 50);
            dibujarNodo(g, nodo.left, x - distancia, y + 50, distancia / 2);
        }

        if (nodo.right != null) {
            g.drawLine(x, y, x + distancia, y + 50);
            dibujarNodo(g, nodo.right, x + distancia, y + 50, distancia / 2);
        }
    }

    // Recorrido In-Order
    private String generarInOrder(LinkedBST<E>.Node nodo) {
        if (nodo == null) return "";
        return generarInOrder(nodo.left) + nodo.data + " " + generarInOrder(nodo.right);
    }

    // Recorrido Pre-Order
    private String generarPreOrder(LinkedBST<E>.Node nodo) {
        if (nodo == null) return "";
        return nodo.data + " " + generarPreOrder(nodo.left) + generarPreOrder(nodo.right);
    }

    // Recorrido Post-Order
    private String generarPostOrder(LinkedBST<E>.Node nodo) {
        if (nodo == null) return "";
        return generarPostOrder(nodo.left) + generarPostOrder(nodo.right) + nodo.data + " ";
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        try {
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(60);
            arbol.insert(80);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame ventana = new JFrame("Árbol Binario de Búsqueda + Recorridos");
        Prueba<Integer> panel = new Prueba<>(arbol);
        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }
}
