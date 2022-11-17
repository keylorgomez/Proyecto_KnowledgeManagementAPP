package modelo;

import java.util.Objects;

public class Usuario {
    /**
     * Se crea la clase usuario con sus debidos atributos
     */
    private  int usuarioId;
    private String nombre;
    private String apellido;
    private int edad;
    private String fechaNacimiento;
    private String email;
    private String password;
    private String foto;
    private  String tipoUsuario;

    public Usuario(int usuarioId, String nombre, String apellido, int edad, String fechaNacimiento, String email, String password, String foto, String tipoUsuario) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.tipoUsuario = tipoUsuario;
    }
    public Usuario(String nombre, String apellido, int edad, String fechaNacimiento, String email, String password, String foto, String tipoUsuario) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.tipoUsuario = tipoUsuario;
    }
    public Usuario(){

    }

    //Constructor sin tipo de rol


    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return usuarioId == usuario.usuarioId && edad == usuario.edad && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellido, usuario.apellido) && Objects.equals(fechaNacimiento, usuario.fechaNacimiento) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(foto, usuario.foto) && Objects.equals(tipoUsuario, usuario.tipoUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, nombre, apellido, edad, fechaNacimiento, email, password, foto, tipoUsuario);
    }

    /**
     * Se genera el toString de la clase para verificar e imprimir por consola
     * @return el objeto como una String
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "usuarioId=" + usuarioId +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", foto='" + foto + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
}
