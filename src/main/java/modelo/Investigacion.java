package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Investigacion {
    /**
     * Atributos de la clase Investigacion
     */
    private int InvestigacionId;
    private String nombreAutor;
    private LocalDate fechaInicio;
    private LocalDate fechaModificacion;
    private String titulo;
    private ArrayList<String> subtitulos;
    private ArrayList<String> textos;
    private ArrayList<Media> maedia;

    /**
     * Se crea un constrctor con parametros de entrada
     * @param investigacionId
     * @param nombreAutor
     * @param fechaInicio
     * @param fechaModificacion
     * @param titulo
     * @param subtitulos
     * @param textos
     * @param maedia
     */


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

    /**
     * Constructor generico
     */

    public Investigacion(){

    }


    public int getInvestigacionId() {
        return InvestigacionId;
    }

    public void setInvestigacionId(int investigacionId) {
        InvestigacionId = investigacionId;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getSubtitulos() {
        return subtitulos;
    }

    public void setSubtitulos(ArrayList<String> subtitulos) {
        this.subtitulos = subtitulos;
    }

    public ArrayList<String> getTextos() {
        return textos;
    }

    public void setTextos(ArrayList<String> textos) {
        this.textos = textos;
    }

    public ArrayList<Media> getMaedia() {
        return maedia;
    }

    public void setMaedia(ArrayList<Media> maedia) {
        this.maedia = maedia;
    }

    //equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investigacion that = (Investigacion) o;
        return InvestigacionId == that.InvestigacionId && Objects.equals(nombreAutor, that.nombreAutor) && Objects.equals(fechaInicio, that.fechaInicio) && Objects.equals(fechaModificacion, that.fechaModificacion) && Objects.equals(titulo, that.titulo) && Objects.equals(subtitulos, that.subtitulos) && Objects.equals(textos, that.textos) && Objects.equals(maedia, that.maedia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(InvestigacionId, nombreAutor, fechaInicio, fechaModificacion, titulo, subtitulos, textos, maedia);
    }

    //toString
    @Override
    public String toString() {
        return "Investigacion{" +
                "InvestigacionId=" + InvestigacionId +
                ", nombreAutor='" + nombreAutor + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaModificacion=" + fechaModificacion +
                ", titulo='" + titulo + '\'' +
                ", subtitulos=" + subtitulos +
                ", textos=" + textos +
                ", maedia=" + maedia +
                '}';
    }
}
