
package autonoma.laberinto.models;

import java.awt.Image;
import java.awt.Point;

/**
 *Clase abstracta que representa un personaje genérico en el juego.
 * Define la estructura base para jugadores y enemigos.
 * @author Salomé C
 */
public abstract class Personaje {
    /** Posición actual del personaje */
    protected Point posicion;
    /** Imagen que representa visualmente al personaje */
    protected Image imagen;

    /**
     * Crea un nuevo personaje.
     * 
     * @param posicion Punto inicial del personaje
     * @param imagen Representación visual del personaje
     */
    public Personaje(Point posicion, Image imagen) {
        this.posicion = posicion;
        this.imagen = imagen;
    }

    /**
     * Obtiene la posición actual del personaje.
     * 
     * @return Punto con las coordenadas actuales
     */
    public Point getPosicion() {
        return posicion;
    }
    /**
     * Establece una nueva posición para el personaje.
     * 
     * @param nuevaPosicion Nuevas coordenadas del personaje
     */
    public void setPosicion(Point nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }

    /**
     * Obtiene la imagen del personaje.
     * 
     * @return Imagen que representa al personaje
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Método abstracto para mover al personaje en una dirección.
     * 
     * @param direccion Dirección del movimiento ("UP", "DOWN", "LEFT", "RIGHT")
     */
    public abstract void mover(String direccion);
}
