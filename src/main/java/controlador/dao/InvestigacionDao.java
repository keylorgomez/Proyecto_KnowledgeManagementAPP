package controlador.dao;

import controlador.database.Conexion;
import modelo.Investigacion;
import modelo.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvestigacionDao {
    private Conexion obtenerConexion;

    public InvestigacionDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarInvestigacion (Investigacion investigacion, int proyectoId, int usuarioId){
        try{
            String SQL="insert into investigacion(fechaModificacion,fechaInicio,categoria,tema, nombreAutor,titulo,subtitulo, idUsuario, idProyecto, mostrar)"+
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,investigacion.getFechaModificacion());
            sentencia.setString(2,investigacion.getFechaInicio());
            sentencia.setString(3,investigacion.getCategoriaInvestigacion());
            sentencia.setString(4,investigacion.getTema());
            sentencia.setString(5,investigacion.getAutor());
            sentencia.setString(6,investigacion.getTituloInvestigacion());
            sentencia.setString(7,investigacion.getSubTitulo1());
            sentencia.setInt(8, usuarioId);
            sentencia.setInt(9, proyectoId);
            sentencia.setInt(10,investigacion.getMostrar());
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


    public boolean registrarProyectoxinvestigacion(int proyectoId, int investigacionId, int usuarioId){
        try {
            String SQL = "insert into proyectoxinvestigacion(idProyecto, idInvestigacion, idUsuario)" +
                    "values(?,?,?)";
            Connection connection = this.obtenerConexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setInt(1, proyectoId);
            sentencia.setInt(2,investigacionId);
            sentencia.setInt(3, usuarioId);

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
    }

    public List<Investigacion>listarInvestigacionGestor(){
        List<Investigacion> listaInvestigacion= new ArrayList<>();
        try{
            String SQL="select idInvestigacion,titulo,tema,categoria,nombreAutor,subtitulo,fechaInicio,fechaModificacion from investigacion";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);
            ResultSet data=sentencia.executeQuery();
            while (data.next()==true){
                Investigacion investigacion=new Investigacion();
                investigacion.setIdInvestigacion(data.getInt(1));
                investigacion.setTituloInvestigacion(data.getString(2));
                investigacion.setTema(data.getString(3));
                investigacion.setCategoriaInvestigacion(data.getString(4));
                investigacion.setAutor(data.getString(5));
                investigacion.setSubTitulo1(data.getString(6));
                investigacion.setFechaInicio(data.getString(7));
                investigacion.setFechaModificacion(data.getString(8));

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
    public List<Investigacion> listarInvestigacionesUsuarios(int idUsuario){
        List<Investigacion> listaInvestigacion=new ArrayList<>();
        try {
            String SQL="select investigacion.idProyecto,investigacion.titulo, investigacion.tema,investigacion.categoria,  investigacion.nombreAutor,  investigacion.subtitulo, investigacion.fechaInicio,investigacion.fechaModificacion  from investigacion  WHERE  investigacion.mostrar=0  and investigacion.idUsuario=" + idUsuario;
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);
            ResultSet data=sentencia.executeQuery();
            while (data.next()==true){
                Investigacion investigacion= new Investigacion();
                investigacion.setIdInvestigacion(data.getInt(1));
                investigacion.setTituloInvestigacion(data.getString(2));
                investigacion.setTema(data.getString(3));
                investigacion.setCategoriaInvestigacion(data.getString(4));
                investigacion.setAutor(data.getString(5));
                investigacion.setSubTitulo1(data.getString(6));
                investigacion.setFechaInicio(data.getString(7));
                investigacion.setFechaModificacion(data.getString(8));


                listaInvestigacion.add(investigacion);
            }
            data.close();
            sentencia.close();
        }catch (Exception e){
            System.err.println("Ocurrió un error al listar los proyectos");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
        }
        return listaInvestigacion;
    }
    public boolean crearInvestigacionTemporal(Investigacion investigacion){
        try {
            String SQL="insert into investigacionModificado(idInvestigacion, fechaModificacion,fechaInicio,categoria, tema,nombreAutor,titulo, subtitulo,estatus)"+
                    "values(?,?,?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);
            sentencia.setInt(1,investigacion.getIdInvestigacion());
            sentencia.setString(2,investigacion.getFechaModificacion());
            sentencia.setString(3,investigacion.getFechaInicio());
            sentencia.setString(4,investigacion.getCategoriaInvestigacion());
            sentencia.setString(5,investigacion.getTema());
            sentencia.setString(6,investigacion.getAutor());
            sentencia.setString(7,investigacion.getTituloInvestigacion());
            sentencia.setString(8,investigacion.getSubTitulo1());
            sentencia.setInt(9, investigacion.getEstatus());
            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Ocurrió un error al editar el proyecto");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }
    }
    public List<Investigacion>listarInvestigacionesTemporales(){
        List<Investigacion> listaInvestigacionTemporales= new ArrayList<>();
        try{
            String SQL="select idInvestigacion,titulo,tema,categoria,nombreAutor,subtitulo,fechaInicio,fechaModificacion, estatus from investigacionModificado where estatus=0 or estatus=3";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);
            ResultSet data=sentencia.executeQuery();
            while (data.next()==true){
                Investigacion investigacion=new Investigacion();
                investigacion.setIdInvestigacion(data.getInt(1));
                investigacion.setTituloInvestigacion(data.getString(2));
                investigacion.setTema(data.getString(3));
                investigacion.setCategoriaInvestigacion(data.getString(4));
                investigacion.setAutor(data.getString(5));
                investigacion.setSubTitulo1(data.getString(6));
                investigacion.setFechaInicio(data.getString(7));
                investigacion.setFechaModificacion(data.getString(8));
                investigacion.setEstatus(data.getInt(9));

                listaInvestigacionTemporales.add(investigacion);

            }
            data.close();
            sentencia.close();

        }catch (Exception e){
            System.err.println("Ocurrió un error al listar las investigaciones");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
        }
        return listaInvestigacionTemporales;
    }

    public int getEstatus(int idInvestigacion) throws SQLException {
        Connection connection=this.obtenerConexion.getConnection();
        String SQLidUser = "SELECT estatus FROM investigacionModificado WHERE idInvestigacion = " + "'" + idInvestigacion + "'";
        PreparedStatement sentencia2 = connection.prepareStatement(SQLidUser);
        ResultSet rs = sentencia2.executeQuery();

        int estatus=0;
        if (rs.next()) {
            estatus = rs.getInt("estatus");
        }
        return estatus;
    }

    public boolean editarInvestigacion(Investigacion investigacion){
        try {
            String SQL="update investigacion set tema=?,categoria=?,nombreAutor=?,titulo=?,subtitulo=?,fechaModificacion=? WHERE idInvestigacion=?";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);
            sentencia.setString(1, investigacion.getTema());
            sentencia.setString(2,investigacion.getCategoriaInvestigacion());
            sentencia.setString(3,investigacion.getAutor());
            sentencia.setString(4, investigacion.getTituloInvestigacion());

            sentencia.setString(5, investigacion.getSubTitulo1());

            sentencia.setString(6, investigacion.getFechaModificacion());

            sentencia.setInt(7,investigacion.getIdInvestigacion());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Ocurrió un error al editar el investigacion");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }
    }
    public boolean ocultarInvestigacion(Investigacion investigacion){
        try {
            String SQL="update investigacion set tema=?,categoria=?,nombreAutor=?,titulo=?,subtitulo=?,ultimaModificacion=?, mostrar=? WHERE idInvestigacion=?";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);

            sentencia.setString(1, investigacion.getTema());
            sentencia.setString(2,investigacion.getCategoriaInvestigacion());
            sentencia.setString(3,investigacion.getAutor());
            sentencia.setString(4, investigacion.getTituloInvestigacion());
            sentencia.setString(5, investigacion.getSubTitulo1());
            sentencia.setString(6, investigacion.getFechaModificacion());
            sentencia.setInt(7, investigacion.getMostrar());
            sentencia.setInt(8,investigacion.getIdInvestigacion());

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Ocurrió un error al eliminar investigacion");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }
    }



    public boolean editarInvestigacionTemporal(Investigacion investigacion){
        try {
            String SQL="update investigacionModificado set estatus=? WHERE idInvestigacion=? and tema=? and categoria=? and nombreAutor=? and titulo=? and subtitulo=?";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);
            sentencia.setInt(1,investigacion.getEstatus());
            sentencia.setInt(2,investigacion.getIdInvestigacion());
            sentencia.setString(3, investigacion.getTema());
            sentencia.setString(4,investigacion.getCategoriaInvestigacion());
            sentencia.setString(5,investigacion.getAutor());
            sentencia.setString(6, investigacion.getTituloInvestigacion());
            sentencia.setString(7, investigacion.getSubTitulo1());
            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (Exception e) {
            System.err.println("Ocurrió un error al editar el proyecto");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }
    }

}
