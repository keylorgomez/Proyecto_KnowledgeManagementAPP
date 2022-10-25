package controlador.dao;

import controlador.database.Conexion;
import modelo.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProyectoDao {
    private Conexion obtenerConexion;

    public ProyectoDao(){
        this.obtenerConexion=new Conexion();
    }
    public boolean registrarProyecto(Proyecto proyecto){
        try {
            String SQL="insert into proyecto(nombre,categoria,fechaCreacion," +
                    "ultimaModificacion,repositorio,idCarpeta,idUsuario)"+
                    "values(?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,proyecto.getNombre());
            sentencia.setString(2, proyecto.getCategoria());
            sentencia.setString(3,proyecto.getFechaCreacion());
            sentencia.setString(4,String.valueOf(proyecto.getUltimaModificacion()));
            sentencia.setString(5, proyecto.getRepositorio());
            sentencia.setInt(6,proyecto.getIdCarpeta());
            sentencia.setInt(7,proyecto.getIdUsuario());

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

}
