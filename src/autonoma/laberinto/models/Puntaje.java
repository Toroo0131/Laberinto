/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.Laberinto.models;

import java.io.*;
import java.util.*;

/**
 * Maneja el sistema de puntuaciones del juego.
 * Permite guardar y cargar puntajes desde un archivo.
 * 
 * @author Salo
 */
public class Puntaje {
    /** Nombre del jugador asociado al puntaje */
    private String nombre;
    /** Cantidad de puntos obtenidos */
    private int puntos;

    /**
     * Crea un nuevo registro de puntaje.
     * 
     * @param nombre Nombre del jugador
     * @param puntos Puntos obtenidos
     */
    public Puntaje(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    /**
     * Guarda un puntaje en el archivo de records.
     * 
     * @param p Puntaje a guardar
     */
    public static void guardarPuntaje(Puntaje p) {
        try (PrintWriter out = new PrintWriter(new FileWriter("puntajes.txt", true))) {
            out.println(p.nombre + "," + p.puntos);
        } catch (IOException e) {
            System.err.println("Error al guardar puntaje: " + e.getMessage());
        }
    }

    /**
     * Carga todos los puntajes guardados desde el archivo.
     * 
     * @return Lista de puntajes ordenados de mayor a menor
     */
    public static List<Puntaje> cargarPuntajes() {
        List<Puntaje> lista = new ArrayList<>();
        File archivo = new File("puntajes.txt");
        
        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    try {
                        lista.add(new Puntaje(partes[0], Integer.parseInt(partes[1])));
                    } catch (NumberFormatException e) {
                        System.err.println("Formato inválido en línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer puntajes: " + e.getMessage());
        }
        
        return lista;
    }

    /**
     * Obtiene el nombre del jugador asociado al puntaje.
     * 
     * @return Nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cantidad de puntos.
     * 
     * @return Puntos obtenidos
     */
    public int getPuntos() {
        return puntos;
    }
}