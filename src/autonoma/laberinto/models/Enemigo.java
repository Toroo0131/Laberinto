
package autonoma.laberinto.models;

import java.awt.Image;
import java.awt.Point;

/**
 * * Representa un enemigo en el juego que puede moverse y perseguir al jugador.
 * Extiende la clase Personaje y añade comportamientos específicos de enemigos.
 * @author Salome C
 */
public class Enemigo extends Personaje{
     /**
     * Crea un nuevo enemigo en la posición especificada.
     * 
     * @param posicion Punto inicial donde aparecerá el enemigo
     * @param imagen Imagen que representa visualmente al enemigo
     */
    public Enemigo(Point posicion, Image imagen) {
        super(posicion, imagen);
    }
    /**
     * Mueve al enemigo en la dirección especificada.
     * 
     * @param direccion Dirección del movimiento ("UP", "DOWN", "LEFT" o "RIGHT")
     */
    @Override
    public void mover(String direccion) {
        int x = posicion.x;
        int y = posicion.y;

        switch (direccion) {
            case "UP": y--; break;
            case "DOWN": y++; break;
            case "LEFT": x--; break;
            case "RIGHT": x++; break;
        }

        posicion.setLocation(x, y);
    }
}
