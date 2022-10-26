package modelo;

import java.time.LocalDate;

public class Proyecto {
    public int idProyecto;

    public String numeroProyecto;
    public String categoria;

    public String nombre;

    public LocalDate fechaCreacion;

    public LocalDate ultimaModificacion;

    public String repositorio;

    public int idCarpeta;

    public int idUsuario;

    public Proyecto(int idProyecto, String nombre ,String categoria,  LocalDate fechaCreacion, LocalDate ultimaModificacion, String repositorio, int idCarpeta, int idUsuario) {
        this.idProyecto = idProyecto;
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.idCarpeta = idCarpeta;
        this.idUsuario = idUsuario;
    }

    public Proyecto(String nombre,String categoria,  LocalDate fechaCreacion, LocalDate ultimaModificacion, String repositorio) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
    }

    public Proyecto(String nombre,String categoria,  LocalDate fechaCreacion, LocalDate ultimaModificacion, String repositorio, int idUsuario) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.idUsuario = idUsuario;
    }

    public Proyecto(String nombre, String numero, String categoria, LocalDate fechaCreacion, LocalDate ultimaModificacion, String repositorio, int idUsuario) {
        this.categoria = categoria;
        this.numeroProyecto = numero;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.idUsuario = idUsuario;
    }

    public String getNumeroProyecto() {
        return numeroProyecto;
    }

    public void setNumeroProyecto(String numeroProyecto) {
        this.numeroProyecto = numeroProyecto;
    }

    public Proyecto() {
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(LocalDate ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    public int getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(int idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
