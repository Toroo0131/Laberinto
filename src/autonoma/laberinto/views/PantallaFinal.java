/**
 * Pantalla que se muestra al finalizar el juego, mostrando el resultado y puntaje.
 * 
 * @author Salomé  C
 */
package autonoma.Laberinto.views;

import javax.swing.*;
import java.awt.*;
import autonoma.Laberinto.models.Puntaje;

public class PantallaFinal extends JPanel {
    private final String nombreJugador;

    public PantallaFinal(JFrame ventana, boolean ganaste, int puntaje, String nombreJugador) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(20, 20, 20));
        this.nombreJugador = nombreJugador;

        String mensaje = ganaste ? "\u00a1HAS ESCAPADO!" : "\u00a1TE ATRAPARON!";
        Color colorMensaje = ganaste ? new Color(100, 255, 100) : new Color(255, 100, 100);

        JLabel lblTitulo = new JLabel(mensaje, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 42));
        lblTitulo.setForeground(colorMensaje);

        JLabel lblPuntaje = new JLabel("Puntuaci\u00f3n: " + puntaje, SwingConstants.CENTER);
        lblPuntaje.setFont(new Font("Arial", Font.BOLD, 28));
        lblPuntaje.setForeground(new Color(255, 215, 0));

        JButton btnMenu = new JButton("Volver al Men\u00fa Principal");
        btnMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnMenu.setBackground(new Color(70, 130, 180));
        btnMenu.setForeground(Color.BLUE);
        btnMenu.setFocusPainted(false);
        btnMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnMenu.addActionListener(e -> {
            MenuPrincipal menu = new MenuPrincipal(ventana);
            ventana.setContentPane(menu);
            ventana.revalidate();
        });

        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 20));
        centro.setBackground(new Color(20, 20, 20));
        centro.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        centro.add(lblTitulo);
        centro.add(lblPuntaje);

        if (ganaste && nombreJugador != null && !nombreJugador.trim().isEmpty()) {
            Puntaje.guardarPuntaje(new Puntaje(nombreJugador.trim(), puntaje));
        }

        this.add(centro, BorderLayout.CENTER);
        this.add(btnMenu, BorderLayout.SOUTH);

        // Mostrar esta pantalla inmediatamente
        ventana.setContentPane(this);
        ventana.revalidate();
        ventana.repaint();
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
