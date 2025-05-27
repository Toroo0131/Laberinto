/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.Laberinto.models;

import java.awt.Image;
import java.awt.Point;

/**
 * Representa una llave que el jugador puede recoger en el laberinto.
 * Extiende ObjetoJuego y define el comportamiento al ser activada.
 * 
 * @author Salo
 */
public class Llave extends ObjetoJuego {

    /**
     * Crea una nueva llave en la posición especificada.
     * 
     * @param posicion Punto donde se ubicará la llave
     * @param imagen Imagen que representa visualmente la llave
     */
    public Llave(Point posicion, Image imagen) {
        super(posicion, imagen);
    }

    /**
     * Activa la llave cuando el jugador la recoge.
     * Marca que el jugador tiene la llave para abrir la puerta.
     * 
     * @param jugador Jugador que recoge la llave
     */
    @Override
    public void activar(Jugador jugador) {
        System.out.println("¡Llave recogida!");
        // La lógica de recoger la llave se maneja en ControladorJuego
    }
}