package modelo;

import java.util.Objects;

public class Documento {
    private String rutaDocumento;
    private String categoria;
    private String tema;
    private String autor;
    private String titulo;
    private String contenido1;
    private String subTitulo;
    private String contenido2;


    public Documento() {
    }

    public Documento(String rutaDocumento, String titulo) {
        this.rutaDocumento = rutaDocumento;
        this.titulo = titulo;
    }

    public Documento(String rutaDocumento, String categoria, String tema, String autor, String titulo, String contenido1, String subTitulo, String contenido2) {
        this.rutaDocumento = rutaDocumento;
        this.titulo = titulo;
        this.categoria = categoria;
        this.tema = tema;
        this.autor = autor;
        this.contenido1 = contenido1;
        this.subTitulo = subTitulo;
        this.contenido2 = contenido2;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getContenido1() {
        return contenido1;
    }

    public void setContenido1(String contenido1) {
        this.contenido1 = contenido1;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
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
        Documento documento = (Documento) o;
        return Objects.equals(titulo, documento.titulo);
    }

    @Override
    public String toString() {
        return "Documento{" +
                "rutaDocumento='" + rutaDocumento + '\'' +
                ", titulo='" + titulo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", tema='" + tema + '\'' +
                ", autor='" + autor + '\'' +
                ", contenido1='" + contenido1 + '\'' +
                ", subTitulo='" + subTitulo + '\'' +
                ", contenido2='" + contenido2 + '\'' +
                '}';
    }
}
