package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Investigacion {
    /**
     * Atributos de la clase Investigacion
     */
    private String tituloInvestigacion;
    private String CategoriaInvestigacion;
    private String tema;
    private String autor;
    private LocalDate fechaInicio;
    private LocalDate fechaModificacion;

    public Investigacion() {
    }

    public Investigacion(String tituloInvestigacion, String categoriaInvestigacion, String tema,
                         String autor, LocalDate fechaInicio, LocalDate fechaModificacion) {
        this.tituloInvestigacion = tituloInvestigacion;
        CategoriaInvestigacion = categoriaInvestigacion;
        this.tema = tema;
        this.autor = autor;
        this.fechaInicio = fechaInicio;
        this.fechaModificacion = fechaModificacion;
    }

    public String getTituloInvestigacion() {
        return tituloInvestigacion;
    }

    public void setTituloInvestigacion(String tituloInvestigacion) {
        this.tituloInvestigacion = tituloInvestigacion;
    }

    public String getCategoriaInvestigacion() {
        return CategoriaInvestigacion;
    }

    public void setCategoriaInvestigacion(String categoriaInvestigacion) {
        CategoriaInvestigacion = categoriaInvestigacion;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
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
                ", CategoriaInvestigacion='" + CategoriaInvestigacion + '\'' +
                ", tema='" + tema + '\'' +
                ", autor='" + autor + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
