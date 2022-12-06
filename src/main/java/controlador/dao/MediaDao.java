package controlador.dao;

import controlador.database.Conexion;
import modelo.Media;
import modelo.Proyecto;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaDao {
    private Conexion obtenerConexion;

    public MediaDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarMedia(Media media, int idUsuario){
        try {
            String SQL="insert into media(nombreArchivo,autor,fechaCreacion, idUsuario)"+
                    "values(?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,media.getNombreArchivo());
            sentencia.setString(2, media.getAutor());
            sentencia.setString(3,String.valueOf(media.getFechaCreacion()));
            sentencia.setInt(4,idUsuario);


            sentencia.executeUpdate();
            sentencia.close();
            return true;
        }catch (Exception e){
            System.err.println("Ocurrió un error al registrar la media");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }

    }
    public int UsuarioMasMedia() throws SQLException {
        Connection connection=this.obtenerConexion.getConnection();
        String SQLMasMedia = "select idUsuario from media group by idUsuario order by count(idUsuario) desc limit 1";
        PreparedStatement sentencia2 = connection.prepareStatement(SQLMasMedia);
        ResultSet rs = sentencia2.executeQuery();

        int idUsuario=0;
        if (rs.next()) {
            idUsuario = rs.getInt("idUsuario");
        }
        return idUsuario;
    }
}
