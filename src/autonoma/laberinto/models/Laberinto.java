/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.Laberinto.models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representa el laberinto del juego con su estructura y lógica de generación.
 * Contiene el mapa, posiciones importantes y métodos para validar movimientos.
 * 
 * @author Salo
 */
public class Laberinto {
    /** Matriz que representa el mapa del laberinto (0=libre, 1=pared, 2=llave, 3=puerta) */
    private int[][] mapa;
    /** Ancho del laberinto en celdas */
    private int ancho;
    /** Alto del laberinto en celdas */
    private int alto;
    /** Posición inicial del jugador */
    private Point posicionInicial;
    /** Posición de la puerta de salida */
    private Point posicionPuerta;

    /**
     * Crea un nuevo laberinto con las dimensiones especificadas.
     * 
     * @param ancho Ancho del laberinto en celdas
     * @param alto Alto del laberinto en celdas
     */
    public Laberinto(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.mapa = new int[alto][ancho];
        generarLaberintoValido();
    }

    /**
     * Genera un laberinto válido con un camino desde el inicio hasta la puerta.
     */
    private void generarLaberintoValido() {
        Random rand = new Random();
        
        // Inicializar todo como pared
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                mapa[y][x] = 1;
            }
        }

        // Establecer posiciones importantes
        posicionInicial = new Point(1, 1);
        posicionPuerta = new Point(ancho - 2, alto - 2);
        
        // Crear camino principal
        crearCamino(posicionInicial, posicionPuerta);

        // Colocar la llave en un punto aleatorio del camino
        Point posicionLlave = obtenerPuntoCaminoValido(rand);
        mapa[posicionLlave.y][posicionLlave.x] = 2;

        // Colocar la puerta
        mapa[posicionPuerta.y][posicionPuerta.x] = 3;

        // Añadir variabilidad al laberinto
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                if (mapa[y][x] == 1 && rand.nextInt(3) == 0) {
                    mapa[y][x] = 0; // Convertir algunas paredes en pasillos
                }
            }
        }
    }

    /**
     * Crea un camino desde un punto inicial hasta un punto final.
     * 
     * @param inicio Punto de inicio del camino
     * @param fin Punto final del camino
     */
    private void crearCamino(Point inicio, Point fin) {
        Point actual = new Point(inicio);
        Random rand = new Random();

        while (!actual.equals(fin)) {
            mapa[actual.y][actual.x] = 0; // Hacer pasillo
            
            // Mover hacia la puerta con cierta aleatoriedad
            if (rand.nextBoolean() || actual.x == fin.x) {
                actual.y += Integer.compare(fin.y, actual.y);
            } else {
                actual.x += Integer.compare(fin.x, actual.x);
            }
        }
        mapa[fin.y][fin.x] = 0; // Asegurar que la puerta esté en pasillo
    }

    /**
     * Obtiene un punto válido del camino para colocar la llave.
     * 
     * @param rand Generador de números aleatorios
     * @return Punto válido para colocar la llave
     */
    private Point obtenerPuntoCaminoValido(Random rand) {
        List<Point> puntosValidos = new ArrayList<>();
        
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                if (mapa[y][x] == 0 && !new Point(x, y).equals(posicionInicial)) {
                    puntosValidos.add(new Point(x, y));
                }
            }
        }
        
        return puntosValidos.isEmpty() ? new Point(ancho / 2, alto / 2) : 
               puntosValidos.get(rand.nextInt(puntosValidos.size()));
    }

    /**
     * Obtiene la matriz que representa el mapa del laberinto.
     * 
     * @return Matriz del laberinto
     */
    public int[][] getMapa() {
        return mapa;
    }

    /**
     * Verifica si una posición es válida para moverse.
     * 
     * @param x Coordenada X de la posición
     * @param y Coordenada Y de la posición
     * @return true si la posición es válida, false si es una pared o está fuera de límites
     */
    public boolean esPosicionValida(int x, int y) {
        return x >= 0 && y >= 0 && y < mapa.length && x < mapa[0].length && mapa[y][x] != 1;
    }

    /**
     * Obtiene la posición inicial del jugador.
     * 
     * @return Punto de inicio del jugador
     */
    public Point getPosicionInicial() {
        return posicionInicial;
    }

    /**
     * Obtiene la posición de la puerta de salida.
     * 
     * @return Punto donde está la puerta
     */
    public Point getPosicionPuerta() {
        return posicionPuerta;
    }
}