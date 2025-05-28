
package autonoma.Laberinto.threads;

/**
 * Hilo que maneja el cronómetro de tiempo restante en el juego.
 * Cuenta regresivamente desde un tiempo inicial y ejecuta una acción al finalizar.
 * 
 * @author Salo
 */
public class HiloCronometro extends Thread {
    /** Tiempo restante en segundos */
    private int tiempoRestante;
    /** Bandera que indica si el cronómetro está activo */
    private boolean activo = true;
    /** Acción a ejecutar cuando el tiempo se agota */
    private Runnable alFinalizar;

    /**
     * Crea un nuevo cronómetro con el tiempo especificado.
     * 
     * @param segundos Tiempo inicial en segundos
     * @param alFinalizar Acción a ejecutar al terminar el tiempo
     */
    public HiloCronometro(int segundos, Runnable alFinalizar) {
        this.tiempoRestante = segundos;
        this.alFinalizar = alFinalizar;
    }

    /**
     * Detiene el cronómetro antes de que finalice el tiempo.
     */
    public void detener() {
        activo = false;
    }

    /**
     * Obtiene el tiempo restante actual.
     * 
     * @return Segundos restantes
     */
    public int getTiempo() {
        return tiempoRestante;
    }

    /**
     * Ejecuta la cuenta regresiva del cronómetro.
     * Cuando el tiempo llega a cero, ejecuta la acción configurada.
     */
    @Override
    public void run() {
        try {
            while (activo && tiempoRestante > 0) {
                Thread.sleep(1000);
                tiempoRestante--;
            }

            if (activo && tiempoRestante <= 0) {
                alFinalizar.run();
            }
        } catch (InterruptedException e) {
            System.err.println("Cronómetro interrumpido: " + e.getMessage());
        }
    }
}
