package controlador.dao;

import controlador.database.Conexion;
import modelo.Proyecto;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProyectoDao {
    private Conexion obtenerConexion;
    Usuario usuario=new Usuario();

    public ProyectoDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarProyecto(Proyecto proyecto){
        try {
            String SQL="insert into proyecto(nombre,categoria,fechaCreacion," +
                    "ultimaModificacion,repositorio,idUsuario)"+
                    "values(?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,proyecto.getNombre());
            sentencia.setString(2, proyecto.getCategoria());
            sentencia.setString(3,String.valueOf(proyecto.getFechaCreacion()));
            sentencia.setString(4,String.valueOf(proyecto.getUltimaModificacion()));
            sentencia.setString(5, proyecto.getRepositorio());
            sentencia.setInt(6,proyecto.getIdUsuario());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        }catch (Exception e){
            System.err.println("Ocurrió un error al registrar el proyecto");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }

        }
        public int getUsuarioId(String email) throws SQLException {
            Connection connection=this.obtenerConexion.getConnection();
            String SQLidUser = "SELECT idUsuario FROM usuarios WHERE email = " + "'" + email + "'";
            PreparedStatement sentencia2 = connection.prepareStatement(SQLidUser);
            ResultSet rs = sentencia2.executeQuery();

            int idUsuario=0;
            if (rs.next()) {
                idUsuario = rs.getInt("idUsuario");
            }
            return idUsuario;
    }

}
