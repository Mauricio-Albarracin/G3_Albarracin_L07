package bstreelinklistinterfgeneric;

import javax.swing.*;

import Exceptions.ItemDuplicated;

import java.awt.*;

// Clase para dibujar un árbol binario de búsqueda y mostrar los recorridos
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

    // Método para dibujar el árbol
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarNodo(g, arbol.root, getWidth() / 2, 30, getWidth() / 4);

        // Dibujar recorridos
        g.setColor(Color.BLUE);
        g.drawString("Recorrido In-Order: " + recorridoInOrder, 20, getHeight() - 30);

        g.setColor(Color.RED);
        g.drawString("Recorrido Pre-Order: " + recorridoPreOrder, 20, getHeight() - 50);

        g.setColor(Color.BLACK);
        g.drawString("Recorrido Post-Order: " + recorridoPostOrder, 20, getHeight() - 70);
    }

    // Dibujar nodo y conexiones
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

    // Recorridos
    private String generarInOrder(LinkedBST<E>.Node nodo) {
        if (nodo == null) return "";
        return generarInOrder(nodo.left) + nodo.data + " " + generarInOrder(nodo.right);
    }

    private String generarPreOrder(LinkedBST<E>.Node nodo) {
        if (nodo == null) return "";
        return nodo.data + " " + generarPreOrder(nodo.left) + generarPreOrder(nodo.right);
    }

    private String generarPostOrder(LinkedBST<E>.Node nodo) {
        if (nodo == null) return "";
        return generarPostOrder(nodo.left) + generarPostOrder(nodo.right) + nodo.data + " ";
    }
    
    // Main
    public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();
        
        try {
            
            // Insertar nodos
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(60);
            arbol.insert(80);
        } catch (ItemDuplicated e) {
            e.printStackTrace(); // O mostrar un JOptionPane si prefieres
        }
              
        // Crear y mostrar la ventana con el árbol primero
        JFrame ventana = new JFrame("Árbol Binario de Búsqueda + Recorridos");
        Prueba<Integer> panel = new Prueba<>(arbol);
        ventana.add(panel);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        // Usar SwingUtilities.invokeLater para mostrar el JOptionPane después
        SwingUtilities.invokeLater(() -> {
            try {
                String entrada = JOptionPane.showInputDialog(ventana,
                        "Ingresa el valor del nodo base para calcular mínimo y máximo:");
                if (entrada != null) {
                    int desde = Integer.parseInt(entrada);

                    int min = arbol.obtenerMinimoDesde(desde);
                    int max = arbol.obtenerMaximoDesde(desde);

                    JOptionPane.showMessageDialog(ventana,
                            "Desde el nodo " + desde +
                                    ":\n  ➤ Mínimo: " + min +
                                    "\n  ➤ Máximo: " + max,
                            "Resultados de Búsqueda de Mínimo y Máximo",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(ventana,
                        "Error: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
