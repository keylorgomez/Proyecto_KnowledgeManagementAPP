package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Proyecto {
    /**
     * Atributos de la clase
     */
    public int idProyecto;

    public String numeroProyecto;
    public String categoria;

    public String nombre;

    public String fechaCreacion;

    public String ultimaModificacion;

    public String repositorio;

    public int idCarpeta;

    public int idUsuario;

    /**
     * Constructores que recibem todos los atributos de la clase como parametros
     * @param idProyecto
     * @param nombre
     * @param categoria
     * @param fechaCreacion
     * @param ultimaModificacion
     * @param repositorio
     * @param idCarpeta
     * @param idUsuario
     */

    public Proyecto(int idProyecto, String nombre , String categoria, String fechaCreacion, String ultimaModificacion, String repositorio, int idCarpeta, int idUsuario) {
        this.idProyecto = idProyecto;
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.idCarpeta = idCarpeta;
        this.idUsuario = idUsuario;
    }

    public Proyecto(String nombre,String categoria,  String fechaCreacion, String ultimaModificacion, String repositorio) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
    }

    public Proyecto(String nombre,String categoria,  String fechaCreacion, String ultimaModificacion, String repositorio, int idUsuario) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.repositorio = repositorio;
        this.idUsuario = idUsuario;
    }

    public Proyecto(String nombre,String categoria, String fechaCreacion,String ultimaModificacion,String repositorio,int idUsuario,String numero) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proyecto proyecto = (Proyecto) o;
        return idProyecto == proyecto.idProyecto && idCarpeta == proyecto.idCarpeta && idUsuario == proyecto.idUsuario && Objects.equals(numeroProyecto, proyecto.numeroProyecto) && Objects.equals(categoria, proyecto.categoria) && Objects.equals(nombre, proyecto.nombre) && Objects.equals(fechaCreacion, proyecto.fechaCreacion) && Objects.equals(ultimaModificacion, proyecto.ultimaModificacion) && Objects.equals(repositorio, proyecto.repositorio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProyecto, numeroProyecto, categoria, nombre, fechaCreacion, ultimaModificacion, repositorio, idCarpeta, idUsuario);
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProyecto=" + idProyecto +
                ", numeroProyecto='" + numeroProyecto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", ultimaModificacion=" + ultimaModificacion +
                ", repositorio='" + repositorio + '\'' +
                ", idCarpeta=" + idCarpeta +
                ", idUsuario=" + idUsuario +
                '}';
    }
}