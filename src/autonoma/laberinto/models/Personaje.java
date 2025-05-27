
package autonoma.laberinto.models;

import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Salomé C
 */
public class Personaje {
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
}
