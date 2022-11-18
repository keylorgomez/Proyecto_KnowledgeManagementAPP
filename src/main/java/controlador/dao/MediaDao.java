package controlador.dao;

import controlador.database.Conexion;
import modelo.Media;
import modelo.Proyecto;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MediaDao {
    private Conexion obtenerConexion;

    public MediaDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarMedia(Media media){
        try {
            String SQL="insert into media(nombreArchivo,autor,fechaCreacion)"+
                    "values(?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,media.getNombreArchivo());
            sentencia.setString(2, media.getAutor());
            sentencia.setString(3,String.valueOf(media.getFechaCreacion()));


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
}
