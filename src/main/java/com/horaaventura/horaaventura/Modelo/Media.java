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

    //getters and setters

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }


}
