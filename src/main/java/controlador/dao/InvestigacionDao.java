package controlador.dao;

import controlador.database.Conexion;
import modelo.Investigacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class InvestigacionDao {
    private Conexion obtenerConexion;

    public InvestigacionDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarInvestigacion (Investigacion investigacion){
        try{
            String SQL="insert into investigacion(fechaModificacion,fechaInicio,categoria,tema, nombreAutor,titulo,subtitulo)"+
                    "values(?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,investigacion.getFechaModificacion());
            sentencia.setString(2,investigacion.getFechaInicio());
            sentencia.setString(3,investigacion.getCategoriaInvestigacion());
            sentencia.setString(4,investigacion.getTema());
            sentencia.setString(5,investigacion.getAutor());
            sentencia.setString(6,investigacion.getTituloInvestigacion());
            sentencia.setString(7,investigacion.getSubTitulo1());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        }catch (Exception e){
            System.err.println("Ocurrió un error al registrar la investigacion");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }
    }// find e registrar conexion

    public List<Investigacion>listarInvestigacion(){
        List<Investigacion> listaInvestigacion= new ArrayList<>();
        try{
            // aqui tambien va un id de investigacion.
            //String SQL="select "
        }catch (Exception e){

        }
        return listaInvestigacion;
    }

}// fin de Clase Investigacion Dao
