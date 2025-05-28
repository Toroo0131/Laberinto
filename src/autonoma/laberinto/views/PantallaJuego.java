
/**
 * Pantalla principal del juego donde se desarrolla la acción.
 * 
 * @author Salomé C
 */
package autonoma.Laberinto.views;

import autonoma.Laberinto.models.*;
import autonoma.Laberinto.threads.*;
import autonoma.Laberinto.controllers.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

public class PantallaJuego extends JPanel {
    private final JFrame ventana;
    private final Jugador jugador;
    private final Laberinto laberinto;
    private final ControladorJuego controladorJuego;
    private final int TAM_CELDA = 40;
    private final ArrayList<Enemigo> enemigos;
    private final ArrayList<HiloEnemigo> hilosEnemigos;
    private final HiloCronometro cronometro;
    private final int tiempoInicial = 120;
    
    private String mensaje = "";
    private long tiempoMensaje = 0;
    private int puntaje = 0;
    private boolean juegoActivo = true;
    
    private Image imgJugador, imgEnemigo, imgLlave, imgPuerta, imgPared, imgSuelo;

    public PantallaJuego(JFrame ventana, String nombreJugador) {
        this.ventana = ventana;
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        
        // Inicializar listas
        this.enemigos = new ArrayList<>();
        this.hilosEnemigos = new ArrayList<>();
        
        // Crear laberinto y jugador
        this.laberinto = new Laberinto(15, 15);
        this.jugador = new Jugador(new Point(laberinto.getPosicionInicial()), null);
        
        // Cargar imágenes
        cargarImagenes();
        
        // Crear controlador
        this.controladorJuego = new ControladorJuego(ventana, this, nombreJugador);
        this.addKeyListener(new ControladorTeclado(jugador, laberinto, this));
        
        // Crear enemigos
        crearEnemigos();
        
        // Configurar cronómetro
        this.cronometro = new HiloCronometro(tiempoInicial, () -> {
            mostrarMensaje("¡Se acabó el tiempo!");
            controladorJuego.verificarEstado();
        });
        this.cronometro.start();
        
        this.requestFocusInWindow();
    }

    private void cargarImagenes() {
        try {
            imgJugador = cargarImagen("/jugador.png");
            imgEnemigo = cargarImagen("/enemigo.png");
            imgLlave = cargarImagen("/llave.png");
            imgPuerta = cargarImagen("/puerta.png");
            imgPared = cargarImagen("/pared.png");
            imgSuelo = cargarImagen("/suelo.png");
        } catch (Exception e) {
            System.err.println("Error cargando imágenes: " + e.getMessage());
            // Usar colores como fallback
        }
    }

    private Image cargarImagen(String ruta) throws IOException {
        BufferedImage img = ImageIO.read(getClass().getResource(ruta));
        return img.getScaledInstance(TAM_CELDA, TAM_CELDA, Image.SCALE_SMOOTH);
    }

    private void crearEnemigos() {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            Point posicionEnemigo;
            int intentos = 0;
            do {
                int x = 3 + rand.nextInt(laberinto.getMapa()[0].length - 6);
                int y = 3 + rand.nextInt(laberinto.getMapa().length - 6);
                posicionEnemigo = new Point(x, y);
                intentos++;
                
                // Prevenir bucle infinito
                if (intentos > 100) {
                    posicionEnemigo = new Point(5, 5); // Posición por defecto
                    break;
                }
            } while (!laberinto.esPosicionValida(posicionEnemigo.x, posicionEnemigo.y) || 
                     posicionEnemigo.equals(jugador.getPosicion()));

            Enemigo enemigo = new Enemigo(posicionEnemigo, null);
            enemigos.add(enemigo);
            
            HiloEnemigo hilo = new HiloEnemigo(enemigo, laberinto, this, jugador);
            hilo.start();
            hilosEnemigos.add(hilo);
        }
    }

    public void mostrarMensaje(String msg) {
        this.mensaje = msg;
        this.tiempoMensaje = System.currentTimeMillis() + 3000;
        repaint();
    }

    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
        repaint();
    }

    public void detenerJuego() {
        this.juegoActivo = false;
        detenerHilosEnemigos();
        detenerCronometro();
    }

    public void detenerHilosEnemigos() {
        for (HiloEnemigo hilo : hilosEnemigos) {
            if (hilo != null) {
                hilo.detener();
            }
        }
    }

    public void detenerCronometro() {
        if (cronometro != null) {
            cronometro.detener();
        }
    }

    public List<Enemigo> getEnemigos() {
        return new ArrayList<>(enemigos);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getTiempoRestante() {
        return cronometro != null ? cronometro.getTiempo() : 0;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int[][] mapa = laberinto.getMapa();

        // Dibujar laberinto
        for (int y = 0; y < mapa.length; y++) {
            for (int x = 0; x < mapa[0].length; x++) {
                int celda = mapa[y][x];
                int px = x * TAM_CELDA;
                int py = y * TAM_CELDA;

                switch (celda) {
                    case 0: // Suelo
                        if (imgSuelo != null) {
                            g.drawImage(imgSuelo, px, py, this);
                        } else {
                            g.setColor(Color.LIGHT_GRAY);
                            g.fillRect(px, py, TAM_CELDA, TAM_CELDA);
                        }
                        break;
                    case 1: // Pared
                        if (imgPared != null) {
                            g.drawImage(imgPared, px, py, this);
                        } else {
                            g.setColor(Color.DARK_GRAY);
                            g.fillRect(px, py, TAM_CELDA, TAM_CELDA);
                        }
                        break;
                    case 2: // Llave
                        if (imgLlave != null) {
                            g.drawImage(imgLlave, px, py, this);
                        } else {
                            g.setColor(Color.YELLOW);
                            g.fillRect(px, py, TAM_CELDA, TAM_CELDA);
                        }
                        break;
                    case 3: // Puerta
                        if (imgPuerta != null) {
                            g.drawImage(imgPuerta, px, py, this);
                        } else {
                            g.setColor(Color.RED);
                            g.fillRect(px, py, TAM_CELDA, TAM_CELDA);
                        }
                        break;
                }
                
                g.setColor(Color.BLACK);
                g.drawRect(px, py, TAM_CELDA, TAM_CELDA);
            }
        }

        // Dibujar jugador
        Point pj = jugador.getPosicion();
        if (imgJugador != null) {
            g.drawImage(imgJugador, pj.x * TAM_CELDA, pj.y * TAM_CELDA, this);
        } else {
            g.setColor(Color.GREEN);
            g.fillOval(pj.x * TAM_CELDA, pj.y * TAM_CELDA, TAM_CELDA, TAM_CELDA);
        }

        // Dibujar enemigos
        for (Enemigo enemigo : enemigos) {
            Point pe = enemigo.getPosicion();
            if (imgEnemigo != null) {
                g.drawImage(imgEnemigo, pe.x * TAM_CELDA, pe.y * TAM_CELDA, this);
            } else {
                g.setColor(Color.BLUE);
                g.fillOval(pe.x * TAM_CELDA, pe.y * TAM_CELDA, TAM_CELDA, TAM_CELDA);
            }
        }

        // Dibujar HUD
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        
        String textoLlave = "Llave: " + (jugador.tieneLlave() ? "SÍ" : "NO");
        String textoTiempo = "Tiempo: " + getTiempoRestante() + "s";
        String textoPuntaje = "Puntos: " + puntaje;
        
        g.drawString(textoLlave, 10, 20);
        g.drawString(textoTiempo, 200, 20);
        g.drawString(textoPuntaje, 400, 20);

        // Mostrar mensaje temporal
        if (System.currentTimeMillis() < tiempoMensaje && !mensaje.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            FontMetrics fm = g.getFontMetrics();
            int msgWidth = fm.stringWidth(mensaje);
            
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRoundRect(getWidth()/2 - msgWidth/2 - 15, 45, msgWidth + 30, 40, 10, 10);
            
            g.setColor(Color.YELLOW);
            g.drawString(mensaje, getWidth()/2 - msgWidth/2, 75);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
        if (controladorJuego != null && juegoActivo) {
            controladorJuego.verificarEstado();
        }
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


