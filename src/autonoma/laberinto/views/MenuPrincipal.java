/**
 * Pantalla principal del menú del juego con opciones para jugar, ver instrucciones, etc.
 * 
 * @author Salomé
 */
package autonoma.Laberinto.views;

import javax.swing.*;
import java.awt.*;
import autonoma.Laberinto.models.Puntaje;
import java.util.List;

public class MenuPrincipal extends JPanel {
    private JFrame ventana;
    

    public MenuPrincipal(JFrame ventana) {
        this.ventana = ventana;
        configurarInterfaz();
    }

    private void configurarInterfaz() {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(30, 30, 30));

        JLabel titulo = new JLabel("ESCAPE DEL LABERINTO");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(255, 215, 0));

        JButton btnJugar = crearBoton("Jugar", new Color(0, 170, 228));
        JButton btnInstrucciones = crearBoton("Instrucciones", new Color(0, 170, 228));
        JButton btnPuntajes = crearBoton("Puntajes", new Color(0, 170, 228));
        JButton btnSalir = crearBoton("Salir", new Color(0, 170, 228));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.gridx = 0;

        gbc.gridy = 0;
        this.add(titulo, gbc);
        
        gbc.gridy++;
        this.add(btnJugar, gbc);
        
        gbc.gridy++;
        this.add(btnInstrucciones, gbc);
        
        gbc.gridy++;
        this.add(btnPuntajes, gbc);
        
        gbc.gridy++;
        this.add(btnSalir, gbc);

        btnJugar.addActionListener(e -> iniciarJuego());
        btnInstrucciones.addActionListener(e -> mostrarInstrucciones());
        btnPuntajes.addActionListener(e -> mostrarPuntajes());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setBackground(color);
        boton.setForeground(Color.BLUE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    private void iniciarJuego() {
        String nombreJugador = JOptionPane.showInputDialog(ventana, 
            "Ingresa tu nombre:", 
            "Nuevo Jugador", 
            JOptionPane.PLAIN_MESSAGE);
        
        if (nombreJugador != null && !nombreJugador.trim().isEmpty()) {
            PantallaJuego panelJuego = new PantallaJuego(ventana, nombreJugador.trim());
            ventana.setContentPane(panelJuego);
            ventana.revalidate();
            panelJuego.requestFocusInWindow();
        } else {
            JOptionPane.showMessageDialog(ventana, 
                "Debes ingresar un nombre para jugar", 
                "Nombre requerido", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarInstrucciones() {
        JTextArea instrucciones = new JTextArea(
            "INSTRUCCIONES:\n\n" +
            "• Usa las teclas WASD o flechas para moverte\n" +
            "• Recoge la llave amarilla para abrir la puerta\n" +
            "• Llega a la puerta para escapar\n" +
            "• Evita a los enemigos\n" +
            "• Tienes 2 minutos para escapar\n\n" +
            "PUNTUACIÓN:\n" +
            "• Llave: +50 puntos\n" +
            "• Escapar: +100 puntos\n" +
            "• Tiempo restante: +2 pts/segundo"
        );
        instrucciones.setFont(new Font("Arial", Font.PLAIN, 16));
        instrucciones.setEditable(false);
        instrucciones.setBackground(new Color(240, 240, 240));
        instrucciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JOptionPane.showMessageDialog(ventana, 
            new JScrollPane(instrucciones), 
            "Cómo Jugar", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarPuntajes() {
        List<Puntaje> puntajes = Puntaje.cargarPuntajes();
        JTextArea areaPuntajes = new JTextArea();
        areaPuntajes.setFont(new Font("Monospaced", Font.BOLD, 14));
        areaPuntajes.setEditable(false);
        
        if (puntajes.isEmpty()) {
            areaPuntajes.setText("Aún no hay puntajes registrados.\n¡Sé el primero!");
        } else {
            puntajes.sort((p1, p2) -> Integer.compare(p2.getPuntos(), p1.getPuntos()));
            
            StringBuilder sb = new StringBuilder();
            sb.append("🏆 TOP 10 PUNTAJES GLOBALES🏆\n\n");
            
            int limite = Math.min(puntajes.size(), 10);
            for (int i = 0; i < limite; i++) {
                Puntaje p = puntajes.get(i);
                sb.append(String.format("%2d. %-15s %5d pts\n", 
                    i+1, p.getNombre(), p.getPuntos()));
            }
            
            areaPuntajes.setText(sb.toString());
        }
        
        JOptionPane.showMessageDialog(ventana, 
            new JScrollPane(areaPuntajes), 
            "Mejores Puntajes", 
            JOptionPane.PLAIN_MESSAGE);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
