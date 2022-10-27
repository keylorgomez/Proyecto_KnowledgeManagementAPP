package modelo;


public class UsuarioGestor extends Usuario {
    /**
     * Clase hija de usuario que permite tener un usuario gestor con sus atributos heredados
     * @param nombre
     * @param apellido
     * @param edad
     * @param fechaNacimiento
     * @param email
     * @param password
     * @param foto
     * @param rolLider
     * @param usuarioId
     */
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

