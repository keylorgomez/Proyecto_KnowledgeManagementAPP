package Controlador.Datos;

import Controlador.Database.Conexion;
import Modelo.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatosUsuario {
    boolean userNuevo;
    public void insertarUser(Usuario usuario) throws SQLException {
        userNuevo = true;
        Conexion connection= new Conexion();
        Connection connectDB = Conexion.getConnection();

        String insertInfo = "insert into usuarios(id,nombre, apellido,edad, fechaNacimiento, email, password,foto) values ('"+ 1+ usuario.getNombre() + "','" + usuario.getApellido() + "','" + usuario.getEdad() + "','" + usuario.getFechaNacimiento() + "','" + usuario.getEmail() + "','" + usuario.getPassword() +"','"+usuario.getFoto() +"')";
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertInfo);
        } catch (Exception e) {
            e.printStackTrace();

        }

        Conexion.closeConnection(connectDB);

    }
}
