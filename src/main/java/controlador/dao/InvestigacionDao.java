package controlador.dao;

import controlador.database.Conexion;
import modelo.Investigacion;
import modelo.Proyecto;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InvestigacionDao {
    private Conexion obtenerConexion;
    Usuario usuario=new Usuario();

    public InvestigacionDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarInvestigacion(Investigacion investigacion){
        try {
            String SQL="insert into investigacion(fechaModificacion, fechaInicio, categoria,tema," +
                    "nombreAutor,titulo,subtitulo,contenido)"+
                    "values(?,?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1, investigacion.getFechaModificacion());
            sentencia.setString(2, investigacion.getFechaInicio());
            sentencia.setString(3, investigacion.getcategoriaInvestigacion());
            sentencia.setString(4, investigacion.getTema());
            sentencia.setString(5, investigacion.getAutor());
            sentencia.setString(6, investigacion.getTituloInvestigacion());
            sentencia.setString(7, investigacion.getSubtitulo());
            sentencia.setString(8, investigacion.getContenido());

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
