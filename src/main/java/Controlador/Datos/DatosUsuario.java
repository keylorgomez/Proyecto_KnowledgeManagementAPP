package Controlador.Datos;

import Controlador.Database.Conexion;
import Modelo.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatosUsuario {
    Usuario usuario;
    boolean userNuevo;
    public void insertarUsser(Usuario usuario) throws SQLException {
        userNuevo = true;
        Conexion connection= new Conexion();
        Connection connectDB = Conexion.getConnection();

        String insertInfo = "insert into usuarios(nombre, apellido,edad, fechaNacimiento, email, password,foto) values ('" + usuario.getNombre() + "','" + usuario.getApellido() + "','" + usuario.getEdad() + "','" + usuario.getFechaNacimiento() + "','" + usuario.getEmail() + "','" + usuario.getPassword() +"','"+usuario.getFoto() +"')";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertInfo);
        } catch (Exception e) {
            e.printStackTrace();

        }

        Conexion.closeConnection(connectDB);

    }
}
