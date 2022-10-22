package com.horaaventura.horaaventura.Modelo;

import java.time.LocalDate;

public class Media {
    private String nombreArchivo;
    private String autor;
    private LocalDate fechaCreacion;
    private int mediaId;

    //Constructor con todos los parametros
    public Media(String nombreArchivo, String autor, LocalDate fechaCreacion, int mediaId) {
        this.nombreArchivo = nombreArchivo;
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.mediaId = mediaId;
    }
}
