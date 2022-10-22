package com.horaaventura.horaaventura.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Investigacion {
    private int InvestigacionId;
    private String nombreAutor;
    private LocalDate fechaInicio;
    private LocalDate fechaModificacion;
    private String titulo;
    private ArrayList<String> subtitulos;
    private ArrayList<String> textos;
    private ArrayList<Media> maedia;


    //contructor con todos los parametros
    public Investigacion(int investigacionId, String nombreAutor, LocalDate fechaInicio, LocalDate fechaModificacion, String titulo, ArrayList<String> subtitulos, ArrayList<String> textos, ArrayList<Media> maedia) {
        InvestigacionId = investigacionId;
        this.nombreAutor = nombreAutor;
        this.fechaInicio = fechaInicio;
        this.fechaModificacion = fechaModificacion;
        this.titulo = titulo;
        this.subtitulos = subtitulos;
        this.textos = textos;
        this.maedia = maedia;
    }
    //constructor generico
    public Investigacion(){

    }


}
