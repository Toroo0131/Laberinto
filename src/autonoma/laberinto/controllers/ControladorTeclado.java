/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.Laberinto.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import autonoma.Laberinto.models.Laberinto;
import autonoma.Laberinto.views.PantallaJuego;
import autonoma.laberinto.models.Jugador;

/**
 * Controlador que maneja las entradas del teclado para mover al jugador.
 * Traduce las pulsaciones de teclas en movimientos del jugador.
 * 
 * @author Salo
 */
public class ControladorTeclado extends KeyAdapter {
    /** Jugador controlado por el usuario */
    private Jugador jugador;
    /** Laberinto donde se desarrolla el juego */
    private Laberinto laberinto;
    /** Pantalla de juego donde se renderiza */
    private PantallaJuego pantalla;

    /**
     * Constructor que inicializa el controlador de teclado.
     * 
     * @param jugador Jugador que será controlado
     * @param laberinto Laberinto donde se mueve el jugador
     * @param pantalla Pantalla que necesita ser actualizada
     */
    public ControladorTeclado(Jugador jugador, Laberinto laberinto, PantallaJuego pantalla) {
        this.jugador = jugador;
        this.laberinto = laberinto;
        this.pantalla = pantalla;
    }

    /**
     * Método invocado cuando se presiona una tecla. Maneja el movimiento del jugador.
     * 
     * @param e Evento de teclado que contiene información sobre la tecla presionada
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int x = jugador.getPosicion().x;
        int y = jugador.getPosicion().y;

        String direccion = null;

        // Mapear teclas a direcciones
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                direccion = "UP";
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                direccion = "DOWN";
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                direccion = "LEFT";
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                direccion = "RIGHT";
                break;
        }

        if (direccion != null) {
            int nx = x;
            int ny = y;
            switch (direccion) {
                case "UP": ny = y - 1; break;
                case "DOWN": ny = y + 1; break;
                case "LEFT": nx = x - 1; break;
                case "RIGHT": nx = x + 1; break;
            }

            // Verificar que el movimiento sea válido
            if (nx >= 0 && ny >= 0 && ny < laberinto.getMapa().length && nx < laberinto.getMapa()[0].length) {
                int tipoCelda = laberinto.getMapa()[ny][nx];
                if (tipoCelda != 1) { // 1 representa una pared
                    jugador.setPosicion(new java.awt.Point(nx, ny));
                    pantalla.repaint();
                }
            }
        }
    }
}