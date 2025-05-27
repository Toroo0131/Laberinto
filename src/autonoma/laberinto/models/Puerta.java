/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.Laberinto.models;

import java.awt.Image;
import java.awt.Point;

/**
 * Representa la puerta de salida del laberinto.
 * El jugador necesita la llave para activarla y escapar.
 * 
 * @author Salo
 */
public class Puerta extends ObjetoJuego {

    /**
     * Crea una nueva puerta en la posición especificada.
     * 
     * @param posicion Punto donde se ubicará la puerta
     * @param imagen Imagen que representa visualmente la puerta
     */
    public Puerta(Point posicion, Image imagen) {
        super(posicion, imagen);
    }

    /**
     * Activa la puerta si el jugador tiene la llave.
     * La lógica completa se maneja en ControladorJuego.
     * 
     * @param jugador Jugador que intenta usar la puerta
     */
    @Override
    public void activar(Jugador jugador) {
        System.out.println("¡Puerta abierta!");
        // La verificación de la llave y lógica de escape se maneja en ControladorJuego
    }
}

