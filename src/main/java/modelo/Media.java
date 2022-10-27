package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Media {
    /**
     * Atributos de la clase Media
     */
    private String nombreArchivo;
    private String autor;
    private LocalDate fechaCreacion;
    private int mediaId;

    /**
     * Constructor que recibe todos los parametros de entrada
     * @param nombreArchivo
     * @param autor
     * @param fechaCreacion
     * @param mediaId
     */


    public Media(String nombreArchivo, String autor, LocalDate fechaCreacion, int mediaId) {
        this.nombreArchivo = nombreArchivo;
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.mediaId = mediaId;
    }



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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return mediaId == media.mediaId && Objects.equals(nombreArchivo, media.nombreArchivo) && Objects.equals(autor, media.autor) && Objects.equals(fechaCreacion, media.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreArchivo, autor, fechaCreacion, mediaId);
    }




    @Override
    public String toString() {
        return "Media{" +
                "nombreArchivo='" + nombreArchivo + '\'' +
                ", autor='" + autor + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", mediaId=" + mediaId +
                '}';
    }
}
