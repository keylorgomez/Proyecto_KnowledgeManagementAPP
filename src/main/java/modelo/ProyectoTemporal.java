package modelo;

import java.util.Objects;

public class ProyectoTemporal {
    private int idProyecto;
    private String nombre;
    private String categoria;
    private String fechaCreacion;
    private String ultimaModificacion;
    private String repositorio;
    private String numeroProyecto;
    private int estatus;


    public ProyectoTemporal(int idProyecto, String nombre, String categoria, String fechaCreacion, String ultimaModificacion, String repositorio, String numeroProyecto, int estatus) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.numeroProyecto = numeroProyecto;
        this.estatus = estatus;
    }

    public ProyectoTemporal(String nombre, String categoria, String fechaCreacion, String ultimaModificacion, String repositorio, String numeroProyecto, int estatus) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.numeroProyecto = numeroProyecto;
        this.estatus = estatus;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(String ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    public String getNumeroProyecto() {
        return numeroProyecto;
    }

    public void setNumeroProyecto(String numeroProyecto) {
        this.numeroProyecto = numeroProyecto;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectoTemporal that = (ProyectoTemporal) o;
        return idProyecto == that.idProyecto && estatus == that.estatus && Objects.equals(nombre, that.nombre) && Objects.equals(categoria, that.categoria) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(ultimaModificacion, that.ultimaModificacion) && Objects.equals(repositorio, that.repositorio) && Objects.equals(numeroProyecto, that.numeroProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProyecto, nombre, categoria, fechaCreacion, ultimaModificacion, repositorio, numeroProyecto, estatus);
    }

    @Override
    public String toString() {
        return "proyectoTemporal{" +
                "idProyecto=" + idProyecto +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", ultimaModificacion='" + ultimaModificacion + '\'' +
                ", repositorio='" + repositorio + '\'' +
                ", numeroProyecto='" + numeroProyecto + '\'' +
                ", estatus=" + estatus +
                '}';
    }
}
