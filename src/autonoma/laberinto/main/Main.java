package autonoma.Laberinto.main;

import javax.swing.*;
import autonoma.Laberinto.views.MenuPrincipal;
import java.awt.Image;

/**
 * Clase principal que inicia la aplicación y configura la ventana principal del juego.
 * Se encarga de establecer el look and feel del sistema y crear la interfaz inicial.
 * 
 * @author Salomé
 */
public class Main {
    /**
     * Punto de entrada principal para la aplicación.
     * Configura la interfaz gráfica y muestra el menú principal.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        try {
            // Configurar el look and feel del sistema operativo
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo configurar el look and feel: " + e.getMessage());
        }

        // Ejecutar en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Configurar la ventana principal
            JFrame ventana = new JFrame("Escape del Laberinto");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(640, 680);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);

            // Establecer icono de la ventana
            try {
                Image icono = new ImageIcon(Main.class.getResource("/laberinto.png")).getImage();
                ventana.setIconImage(icono);
            } catch (Exception e) {
                System.err.println("No se pudo cargar el icono: " + e.getMessage());
            }

            // Mostrar el menú principal
            MenuPrincipal menu = new MenuPrincipal(ventana);
            ventana.setContentPane(menu);
            ventana.setVisible(true);
        });
    }
}