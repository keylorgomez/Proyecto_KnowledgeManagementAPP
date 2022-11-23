package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Investigacion {
    /**
     * Atributos de la clase Investigacion
     */

    private int idInvestigacion;
    private String tituloInvestigacion;
    private String categoriaInvestigacion;
    private String tema;
    private String autor;
    private String fechaInicio;
    private String fechaModificacion;

    private  String subtitulo;
    private String contenido;


    public Investigacion(int idInvestigacion, String tituloInvestigacion, String categoriaInvestigacion, String tema, String autor, String fechaInicio, String fechaModificacion, String subtitulo, String contenido) {
        this.idInvestigacion = idInvestigacion;
        this.tituloInvestigacion = tituloInvestigacion;
        this.categoriaInvestigacion = categoriaInvestigacion;
        this.tema = tema;
        this.autor = autor;
        this.fechaInicio = fechaInicio;
        this.fechaModificacion = fechaModificacion;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
    }

    public Investigacion(String tituloInvestigacion, String categoriaInvestigacion, String tema, String autor, String fechaInicio, String fechaModificacion, String subtitulo, String contenido) {
        this.tituloInvestigacion = tituloInvestigacion;
        this.categoriaInvestigacion = categoriaInvestigacion;
        this.tema = tema;
        this.autor = autor;
        this.fechaInicio = fechaInicio;
        this.fechaModificacion = fechaModificacion;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Investigacion() {
    }



    public int getIdInvestigacion() {
        return idInvestigacion;
    }

    public void setIdInvestigacion(int idInvestigacion) {
        this.idInvestigacion = idInvestigacion;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTituloInvestigacion() {
        return tituloInvestigacion;
    }

    public void setTituloInvestigacion(String tituloInvestigacion) {
        this.tituloInvestigacion = tituloInvestigacion;
    }

    public String getcategoriaInvestigacion() {
        return categoriaInvestigacion;
    }

    public void setcategoriaInvestigacion(String categoriaInvestigacion) {
        categoriaInvestigacion = categoriaInvestigacion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investigacion that = (Investigacion) o;
        return Objects.equals(tituloInvestigacion, that.tituloInvestigacion);
    }

    @Override
    public String toString() {
        return "Investigacion{" +
                "tituloInvestigacion='" + tituloInvestigacion + '\'' +
                ", CategoriaInvestigacion='" + categoriaInvestigacion + '\'' +
                ", tema='" + tema + '\'' +
                ", autor='" + autor + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
