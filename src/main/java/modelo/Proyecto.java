package modelo;

import java.util.Date;

public class Proyecto {
    public int idProyecto;

    public String categoria;

    public String nombre;

    public String fechaCreacion;

    public Date ultimaModificacion;

    public String repositorio;

    public int idCarpeta;

    public int idUsuario;

    public Proyecto(int idProyecto, String categoria, String nombre, String fechaCreacion, Date ultimaModificacion, String repositorio, int idCarpeta, int idUsuario) {
        this.idProyecto = idProyecto;
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.idCarpeta = idCarpeta;
        this.idUsuario = idUsuario;
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
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
