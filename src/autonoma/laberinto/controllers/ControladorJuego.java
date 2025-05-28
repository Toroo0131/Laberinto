package autonoma.Laberinto.controllers;

import autonoma.Laberinto.models.*;
import autonoma.Laberinto.views.*;
import java.awt.Point;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Controlador principal del juego que maneja la lógica del juego, las colisiones
 * y el estado del juego. Coordina las interacciones entre el modelo y la vista.
 * 
 * @author Salomé
 */
public class ControladorJuego {
    /** Jugador controlado por el usuario */
    private final Jugador jugador;
    /** Laberinto donde se desarrolla el juego */
    private final Laberinto laberinto;
    /** Ventana principal del juego */
    private final JFrame ventana;
    /** Pantalla donde se renderiza el juego */
    private final PantallaJuego pantallaJuego;
    /** Nombre del jugador */
    private final String nombreJugador;
    /** Indica si el juego está activo */
    private final boolean juegoActivo;
    /** Indica si el juego ha terminado */
    private boolean juegoTerminado = false;

    /**
     * Constructor que inicializa el controlador del juego.
     * 
     * @param ventana Ventana principal del juego
     * @param pantallaJuego Pantalla de juego donde se renderiza
     * @param nombreJugador Nombre del jugador
     */
    public ControladorJuego(JFrame ventana, PantallaJuego pantallaJuego, String nombreJugador) {
        this.ventana = ventana;
        this.pantallaJuego = pantallaJuego;
        this.jugador = pantallaJuego.getJugador();
        this.laberinto = pantallaJuego.getLaberinto();
        this.nombreJugador = nombreJugador;
        this.juegoActivo = true;
    }

    /**
     * Verifica el estado actual del juego, incluyendo colisiones y condiciones de victoria/derrota.
     * Se ejecuta continuamente durante el juego.
     */
    public void verificarEstado() {
        if (!juegoActivo || juegoTerminado) return;

        Point posicion = jugador.getPosicion();

        // Verificar colisión con enemigos primero
        if (verificarColisionEnemigos()) {
            return; // Si hay colisión con enemigo, salir
        }

        // Verificar límites del mapa
        if (posicion.y < 0 || posicion.y >= laberinto.getMapa().length || 
            posicion.x < 0 || posicion.x >= laberinto.getMapa()[0].length) {
            return;
        }

        int celda = laberinto.getMapa()[posicion.y][posicion.x];

        switch (celda) {
            case 2: // Llave
                manejarRecogerLlave(posicion);
                break;
            case 3: // Puerta
                manejarLlegadaPuerta();
                break;
        }
    }

    /**
     * Maneja la acción de recoger una llave en el laberinto.
     * 
     * @param posicion Posición donde se encuentra la llave
     */
    public void manejarRecogerLlave(Point posicion) {
        jugador.recogerLlave();

    // Cambiar la celda de llave por una celda vacía (por ejemplo, 0)
        laberinto.getMapa()[posicion.y][posicion.x] = 0;

    // Opcional: refrescar la pantalla para que se vea el cambio
        pantallaJuego.repaint();

    // Mensaje de confirmación
        pantallaJuego.mostrarMensaje("¡Has recogido la llave!");
    }



    /**
     * Maneja la acción de llegar a la puerta del laberinto.
     * Calcula la puntuación final y muestra la pantalla de victoria.
     */
    private void manejarLlegadaPuerta() {
        if (jugador.tieneLlave() && !juegoTerminado) {
            juegoTerminado = true;
            int puntosTiempo = pantallaJuego.getTiempoRestante();
            int puntosTotales = 100 + (puntosTiempo > 0 ? puntosTiempo * 2 : 0);

            pantallaJuego.sumarPuntos(puntosTotales);
            pantallaJuego.mostrarMensaje("\u00a1Escapaste! Ganaste " + puntosTotales + " puntos");
            pantallaJuego.detenerJuego();
            mostrarPantallaFinal(true);
        } else if (!juegoTerminado) {
            pantallaJuego.mostrarMensaje("\u00a1Necesitas la llave para salir!");
        }
    }

    /**
     * Verifica si el jugador ha colisionado con algún enemigo.
     * 
     * @return true si hay colisión con un enemigo, false en caso contrario
     */
    private boolean verificarColisionEnemigos() {
    List<Enemigo> enemigos = pantallaJuego.getEnemigos();
    Point posicionJugador = jugador.getPosicion();

        for (Enemigo enemigo : enemigos) {
            if (enemigo.getPosicion().equals(posicionJugador) && !juegoTerminado) {
                juegoTerminado = true;

                if (jugador.tieneLlave()) {
                pantallaJuego.sumarPuntos(100); // Por ejemplo, 100 puntos solo por tener la llave
                }

                pantallaJuego.mostrarMensaje("¡Un enemigo te atrapó! Has perdido");
                pantallaJuego.detenerJuego();
                mostrarPantallaFinal(false);
                return true;
            }
        }
        return false;
    }
    /**
     * Muestra la pantalla final del juego con el resultado.
     * 
     * @param gano Indica si el jugador ganó (true) o perdió (false)
     */
    private void mostrarPantallaFinal(boolean gano) {
        SwingUtilities.invokeLater(() -> {
            PantallaFinal pantallaFinal = new PantallaFinal(ventana, gano, pantallaJuego.getPuntaje(), nombreJugador);
            // setContentPane ya está llamado en el constructor de PantallaFinal
        });
    }
}