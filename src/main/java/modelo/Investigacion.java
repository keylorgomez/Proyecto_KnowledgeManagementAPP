package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Investigacion {
    /**
     * Atributos de la clase Investigacion
     */
    private int idInvestigacion;
    private String fechaModificacion;
    private String fechaInicio;
    private String CategoriaInvestigacion;
    private String tema;
    private String autor;
    private String tituloInvestigacion;
    private String contenido1;
    private String subTitulo1;
    private String contenido2;
    private int mostrar;

    private int estatus;

    public Investigacion() {
    }

    public Investigacion(String fechaModificacion, String fechaInicio, String categoriaInvestigacion, String tema, String autor, String tituloInvestigacion, String subTitulo1, int mostrar, int estatus) {
        this.fechaModificacion = fechaModificacion;
        this.fechaInicio = fechaInicio;
        CategoriaInvestigacion = categoriaInvestigacion;
        this.tema = tema;
        this.autor = autor;
        this.tituloInvestigacion = tituloInvestigacion;
        this.subTitulo1 = subTitulo1;
        this.mostrar= mostrar;
        this.estatus= estatus;
    }

    public Investigacion(String fechaModificacion, String fechaInicio, String categoriaInvestigacion, String tema, String autor, String tituloInvestigacion, String contenido1, String subTitulo1, String contenido2) {
        this.fechaModificacion = fechaModificacion;
        this.fechaInicio = fechaInicio;
        CategoriaInvestigacion = categoriaInvestigacion;
        this.tema = tema;
        this.autor = autor;
        this.tituloInvestigacion = tituloInvestigacion;
        this.contenido1 = contenido1;
        this.subTitulo1 = subTitulo1;
        this.contenido2 = contenido2;
    }

    public Investigacion(int idInvestigacion, String fechaModificacion, String fechaInicio, String categoriaInvestigacion, String tema, String autor, String tituloInvestigacion, String contenido1, String subTitulo1, String contenido2) {
        this.idInvestigacion = idInvestigacion;
        this.fechaModificacion = fechaModificacion;
        this.fechaInicio = fechaInicio;
        CategoriaInvestigacion = categoriaInvestigacion;
        this.tema = tema;
        this.autor = autor;
        this.tituloInvestigacion = tituloInvestigacion;
        this.contenido1 = contenido1;
        this.subTitulo1 = subTitulo1;
        this.contenido2 = contenido2;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdInvestigacion() {
        return idInvestigacion;
    }

    public void setIdInvestigacion(int idInvestigacion) {
        this.idInvestigacion = idInvestigacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCategoriaInvestigacion() {
        return CategoriaInvestigacion;
    }

    public void setCategoriaInvestigacion(String categoriaInvestigacion) {
        CategoriaInvestigacion = categoriaInvestigacion;
    }

    public int getMostrar() {
        return mostrar;
    }

    public void setMostrar(int mostrar) {
        this.mostrar = mostrar;
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

    public String getTituloInvestigacion() {
        return tituloInvestigacion;
    }

    public void setTituloInvestigacion(String tituloInvestigacion) {
        this.tituloInvestigacion = tituloInvestigacion;
    }

    public String getContenido1() {
        return contenido1;
    }

    public void setContenido1(String contenido1) {
        this.contenido1 = contenido1;
    }

    public String getSubTitulo1() {
        return subTitulo1;
    }

    public void setSubTitulo1(String subTitulo1) {
        this.subTitulo1 = subTitulo1;
    }

    public String getContenido2() {
        return contenido2;
    }

    public void setContenido2(String contenido2) {
        this.contenido2 = contenido2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investigacion that = (Investigacion) o;
        return idInvestigacion == that.idInvestigacion && Objects.equals(fechaModificacion, that.fechaModificacion) && Objects.equals(fechaInicio, that.fechaInicio) && Objects.equals(CategoriaInvestigacion, that.CategoriaInvestigacion) && Objects.equals(tema, that.tema) && Objects.equals(autor, that.autor) && Objects.equals(tituloInvestigacion, that.tituloInvestigacion) && Objects.equals(contenido1, that.contenido1) && Objects.equals(subTitulo1, that.subTitulo1) && Objects.equals(contenido2, that.contenido2);
    }

    @Override
    public String toString() {
        return "Investigacion{" +
                "idInvestigacion=" + idInvestigacion +
                ", fechaModificacion='" + fechaModificacion + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", CategoriaInvestigacion='" + CategoriaInvestigacion + '\'' +
                ", tema='" + tema + '\'' +
                ", autor='" + autor + '\'' +
                ", tituloInvestigacion='" + tituloInvestigacion + '\'' +
                ", contenido1='" + contenido1 + '\'' +
                ", subTitulo1='" + subTitulo1 + '\'' +
                ", contenido2='" + contenido2 + '\'' +
                '}';
    }
}// fin de clase
