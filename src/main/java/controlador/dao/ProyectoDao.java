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
                    "values(?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,proyecto.getNombre());
            sentencia.setString(2, proyecto.getCategoria());
            sentencia.setString(3,String.valueOf(proyecto.getFechaCreacion()));
            sentencia.setString(4,String.valueOf(proyecto.getUltimaModificacion()));
            sentencia.setString(5, proyecto.getRepositorio());
            sentencia.setInt(6,usuario.getUsuarioId());

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

        public int getUsuarioId(String email){
            Connection conexion=this.obtenerConexion.getConnection();
            String SQLid = "SELECT idUsuario FROM usuarios WHERE email = " + "'" + id + "'";
            PreparedStatement sentencia2 = conexion.prepareStatement(SQLid);
            ResultSet rs = sentencia2.executeQuery();

            int idVideo = 0;
            if (rs.next()) {
                idVideo = rs.getInt("videoId");
            }
            return idVideo;
        }



    }

}
