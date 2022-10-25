package dao;

import Controlador.Database.Conexion;
import Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDao {
    private Conexion obtenerConexion;

    public UsuarioDao(){
        this.obtenerConexion=new Conexion();
    }
    public void registrarUsuario(Usuario usuario){
        try {
            String SQL="insert into usuarios(nombre,apellido,edad,fechaNacimiento," +
                    "email,contrasenna,foto)" +
                    "values(?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);

            sentencia.setString(1,usuario.getNombre());
            sentencia.setString(2,usuario.getApellido());
            sentencia.setInt(3,usuario.getEdad());
            sentencia.setString(4, usuario.getFechaNacimiento());
            sentencia.setString(5, usuario.getEmail());
            sentencia.setString(6, usuario.getPassword());
            sentencia.setString(7, usuario.getFoto());

        }catch (Exception e){
            System.err.println("Ocurrió un error al registrar el usuario");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();

        }


    }
}
