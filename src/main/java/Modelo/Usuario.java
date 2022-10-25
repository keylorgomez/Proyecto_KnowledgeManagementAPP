package Modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {
    private  int usuarioId;
    private String nombre;
    private String apellido;
    private int edad;
    private String fechaNacimiento;
    private String email;
    private String password;
    private String foto;
    private boolean rolLider;


    //Constructor que recibe todos los parametros


    public Usuario(String nombre, String apellido, int edad, String fechaNacimiento, String email, String password, String foto, boolean rolLider, int usuarioId) {
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

    //Constructor sin tipo de rol

    public Usuario(String nombre, String apellido, int edad, String fechaNacimiento, String email, String password, String foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.foto = foto;
    }

    public Usuario() {
    }
//Getters and Setters


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isRolLider() {
        return rolLider;
    }

    public void setRolLider(boolean rolLider) {
        this.rolLider = rolLider;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    //Método equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return edad == usuario.edad && rolLider == usuario.rolLider && usuarioId == usuario.usuarioId && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido, usuario.apellido) && Objects.equals(fechaNacimiento, usuario.fechaNacimiento) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(foto, usuario.foto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, edad, fechaNacimiento, email, password, foto, rolLider, usuarioId);
    }

    //ToString
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", fechaNacimiento=" + fechaNacimiento +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", foto='" + foto + '\'' +
                ", rolLider=" + rolLider +
                ", usuarioId=" + usuarioId +
                '}';
    }
}
