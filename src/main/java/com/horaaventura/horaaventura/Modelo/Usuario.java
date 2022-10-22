package com.horaaventura.horaaventura.Modelo;

import java.time.LocalDate;

public class Usuario {
    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaNacimiento;
    private String email;
    private String password;
    private String foto;
    private boolean rolLider;
    private  int usuarioId;

    //Constructor que recibe todos los parametros


    public Usuario(String nombre, String apellido, int edad, LocalDate fechaNacimiento, String email, String password, String foto, boolean rolLider, int usuarioId) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.rolLider = rolLider;
        this.usuarioId = usuarioId;
    }


}
