/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private int id;
    private String nombre;
    private String director;
    private int año;    
    private boolean existencia;
    private String clasificacion;
    private String pais;
    public Filtro(){
    }
    
    public Filtro(int id,String nombre, int año, String director,String clasificacion,String pais, boolean existencia)
    {
        this.id=id;
        this.pais=pais;
        this.año=año;
        this.director=director;
        this.existencia=existencia;
        this.nombre=nombre;
        this.clasificacion=clasificacion;
        
    }
       
    public Filtro(String nombre, int año, String director, String clasificacion, boolean existencia) {
        this.año=año; 
        this.director=director;
        this.existencia=existencia;
        this.nombre=nombre;
        this.clasificacion=clasificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public boolean getExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    
}

