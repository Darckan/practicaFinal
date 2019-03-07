/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author darck
 */
public class Pelicula {
    
    private int id_pelicula;
    private String nombre;
    private int id_director;
    private int costes;

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelidula) {
        this.id_pelicula = id_pelidula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_director() {
        return id_director;
    }

    public void setId_director(int id_director) {
        this.id_director = id_director;
    }

    public int getCostes() {
        return costes;
    }

    public void setCostes(int costes) {
        this.costes = costes;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "id_pelicula=" + id_pelicula + ", nombre=" + nombre + ", id_director=" + id_director + ", costes=" + costes + '}';
    }
    
    
}
