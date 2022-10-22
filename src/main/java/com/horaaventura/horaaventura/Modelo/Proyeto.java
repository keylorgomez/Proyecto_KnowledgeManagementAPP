package com.horaaventura.horaaventura.Modelo;

import java.util.ArrayList;
import java.util.Date;

public class Proyeto {
    public int idProyecto;

    public String categoria;

    public String nombre;

    public Date fechaCreacion;

    public Date ultimaModificacion;

    public ArrayList<Media> media;

    public ArrayList<Usuario> colaboradores;

    public String repositorio;

    public Proyeto(int idProyecto, String categoria, String nombre, Date fechaCreacion, Date ultimaModificacion, ArrayList<Media> media, ArrayList<Usuario> colaboradores, String repositorio) {
        this.idProyecto = idProyecto;
        this.categoria = categoria;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.ultimaModificacion = ultimaModificacion;
        this.media = media;
        this.colaboradores = colaboradores;
        this.repositorio = repositorio;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }

    public ArrayList<Usuario> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(ArrayList<Usuario> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }
}
