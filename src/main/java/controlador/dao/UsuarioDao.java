package controlador.dao;

import controlador.database.Conexion;
import modelo.Usuario;

import java.sql.*;

public class UsuarioDao {
    private Conexion obtenerConexion;

    public UsuarioDao(){
        this.obtenerConexion=new Conexion();
    }
    public boolean registrarUsuario(Usuario usuario){
        try {
            String SQL="insert into usuarios(nombre,apellido,edad,fechaNacimiento," +
                    "email,contrasenna,foto,tipoUsuario)" +
                    "values(?,?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);

            sentencia.setString(1,usuario.getNombre());
            sentencia.setString(2,usuario.getApellido());
            sentencia.setInt(3,usuario.getEdad());
            sentencia.setString(4, usuario.getFechaNacimiento());
            sentencia.setString(5, usuario.getEmail());
            sentencia.setString(6, usuario.getPassword());
            sentencia.setString(7, usuario.getFoto());
            sentencia.setString(8,usuario.getTipoUsuario());

            sentencia.executeUpdate();
            sentencia.close();
            return true;

        }catch (Exception e){
            System.err.println("Ocurrió un error al registrar el usuario");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }
    }

    public Boolean verificarUsuario(String email, String contrasenna) throws SQLException, SQLException {
        boolean error = true;
        Conexion connectNow = new Conexion();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "select count(1) from usuarios where email = " + "'" + email + "'" + " and contrasenna = " + "'" + contrasenna + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {

                } else {
                    error = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            e.getCause();
        }
        Conexion.closeConnection(connectDB);
        return error;
    }
    public boolean ValidarUsuarioRegistrado(String email) throws SQLException {
        boolean usurioExistente=false;
        Conexion connectNow = new Conexion();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "select count(1) from usuarios where email = " + "'" + email + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    usurioExistente=true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            e.getCause();
        }
        Conexion.closeConnection(connectDB);
        return usurioExistente;
    }
    public String getTipoUsuario(String email) throws SQLException {
        Connection connection=this.obtenerConexion.getConnection();
        String SQLidUser = "SELECT tipoUsuario FROM usuarios WHERE email = " + "'" + email + "'";
        PreparedStatement sentencia2 = connection.prepareStatement(SQLidUser);
        ResultSet rs = sentencia2.executeQuery();

        String tipoUsuario="";
        if (rs.next()) {
            tipoUsuario = rs.getString("tipoUsuario");
        }
        return tipoUsuario;
    }
}













