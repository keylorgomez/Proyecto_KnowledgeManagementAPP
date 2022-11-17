package modelo;


public class UsuarioGestor extends Usuario {
    public UsuarioGestor(int usuarioId, String nombre, String apellido, int edad, String fechaNacimiento, String email, String password, String foto, String tipoUsuario) {
        super(usuarioId, nombre, apellido, edad, fechaNacimiento, email, password, foto, tipoUsuario);
    }

    public UsuarioGestor(String nombre, String apellido, int edad, String fechaNacimiento, String email, String password, String foto, String tipoUsuario) {
        super(nombre, apellido, edad, fechaNacimiento, email, password, foto, tipoUsuario);
    }

    public UsuarioGestor() {
    }

}

