/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.Laberinto.models;

import java.awt.Image;
import java.awt.Point;

/**
 * Clase abstracta que representa un objeto genérico del juego.
 * Define la estructura base para todos los objetos interactuables.
 * 
 * @author Salo
 */
public abstract class ObjetoJuego {
    /** Posición del objeto en el laberinto */
    protected Point posicion;
    /** Imagen que representa visualmente el objeto */
    protected Image imagen;

    /**
     * Crea un nuevo objeto del juego.
     * 
     * @param posicion Punto inicial del objeto
     * @param imagen Representación visual del objeto
     */
    public ObjetoJuego(Point posicion, Image imagen) {
        this.posicion = posicion;
        this.imagen = imagen;
    }

    /**
     * Obtiene la posición actual del objeto.
     * 
     * @return Punto con las coordenadas del objeto
     */
    public Point getPosicion() {
        return posicion;
    }

    /**
     * Obtiene la imagen que representa el objeto.
     * 
     * @return Imagen del objeto
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Método abstracto que define qué ocurre cuando el jugador interactúa con el objeto.
     * 
     * @param jugador Jugador que activa el objeto
     */
    public abstract void activar(Jugador jugador);
}
