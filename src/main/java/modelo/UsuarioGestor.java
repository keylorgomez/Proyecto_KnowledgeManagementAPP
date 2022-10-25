package modelo;


public class UsuarioGestor extends Usuario {
    public UsuarioGestor(String nombre, String apellido, int edad, String fechaNacimiento, String email, String password, String foto, boolean rolLider, int usuarioId) {
        super(nombre, apellido, edad, fechaNacimiento, email, password, foto, rolLider, usuarioId);
        this.gestor = gestor;
    }

    private boolean gestor;

    public boolean isGestor() {
        return gestor;
    }

    public void setGestor(boolean gestor) {
        this.gestor = gestor;
    }

}

