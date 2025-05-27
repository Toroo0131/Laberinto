
package autonoma.laberinto.models;

import java.awt.Image;
import java.awt.Point;

/**
 ** Representa al jugador principal del juego con sus propiedades y comportamientos.
 * Maneja el estado de la llave y reproduce sonidos al recogerla.
 * @author salit
 */
public class Jugador extends Personaje{
    /** Indica si el jugador ha recogido la llave */
    private boolean tieneLlave = false;

    /**
     * Crea un nuevo jugador en la posición especificada.
     * 
     * @param posicion Punto inicial donde aparecerá el jugador
     * @param imagen Imagen que representa visualmente al jugador
     */
    public Jugador(Point posicion, Image imagen) {
        super(posicion, imagen);
    }

    /**
     * Verifica si el jugador tiene la llave.
     * 
     * @return true si el jugador tiene la llave, false en caso contrario
     */
    public boolean tieneLlave() {
        return tieneLlave;
    }
    
    /**
     * Marca que el jugador ha recogido la llave 
     * 
     *
     */
    public void recogerLlave() {
        this.tieneLlave = true;
        
    }

    @Override
    public void mover(String direccion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Mueve al jugador en la dirección especificada.
     * 
     * @param direccion Dirección del movimiento ("UP", "DOWN", "LEFT" o "RIGHT")
     */

}
