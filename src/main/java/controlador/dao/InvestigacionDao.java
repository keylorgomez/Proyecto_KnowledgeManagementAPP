package controlador.dao;

import controlador.database.Conexion;
import modelo.Investigacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public List<Investigacion>listarInvestigacionGestor(){
        List<Investigacion> listaInvestigacion= new ArrayList<>();
        try{
            String SQL="select idInvestigacion,titulo,tema,categoria,subtitulo,fechaInicio,fechaModificacion from investigacion";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);
            ResultSet data=sentencia.executeQuery();
            while (data.next()==true){
                Investigacion investigacion=new Investigacion();
                investigacion.setIdInvestigacion(data.getInt(1));
                investigacion.setTituloInvestigacion(data.getString(2));
                investigacion.setTema(data.getString(3));
                investigacion.setCategoriaInvestigacion(data.getString(4));
                investigacion.setSubTitulo1(data.getString(5));
                investigacion.setFechaInicio(data.getString(6));
                investigacion.setFechaModificacion(data.getString(7));

                listaInvestigacion.add(investigacion);

            }
            data.close();
            sentencia.close();

        }catch (Exception e){
            System.err.println("Ocurrió un error al listar las investigaciones");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
        }
        return listaInvestigacion;
    }

}
