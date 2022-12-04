package controlador.dao;

import controlador.database.Conexion;
import modelo.Proyecto;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDao {
    private Conexion obtenerConexion;
    Usuario usuario=new Usuario();

    public ProyectoDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarProyecto(Proyecto proyecto){
        try {
            String SQL="insert into proyecto(nombre,categoria,fechaCreacion," +
                    "ultimaModificacion,repositorio,idUsuario, numeroProyecto, mostrar)"+
                    "values(?,?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,proyecto.getNombre());
            sentencia.setString(2, proyecto.getCategoria());
            sentencia.setString(3,String.valueOf(proyecto.getFechaCreacion()));
            sentencia.setString(4,String.valueOf(proyecto.getUltimaModificacion()));
            sentencia.setString(5, proyecto.getRepositorio());
            sentencia.setInt(6,proyecto.getIdUsuario());
            sentencia.setString(7,proyecto.getNumeroProyecto());
            sentencia.setInt(8, proyecto.getMostrar());

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

    public boolean registrarProyectoxusuario(int usuarioId, int proyectoId, String tipo){
        try {
            String SQL = "insert into proyectoxusuario(idProyecto, idUsuario,tipo)" +
                    "values(?,?,?)";
            Connection connection = this.obtenerConexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setInt(1, proyectoId);
            sentencia.setInt(2, usuarioId);
            sentencia.setString(3,tipo);

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        }catch (Exception e){
            System.err.println("Ocurrió un error al registrar el usuario al proyecto");
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

    public int getEstatus(int idProyecto, int idproyectoModi) throws SQLException {
        Connection connection=this.obtenerConexion.getConnection();
        String SQLidUser = "SELECT estatus FROM proyectoModificado WHERE idProyecto = " + "'" + idProyecto + "'"+"and idproyectoModi="+"'" + idproyectoModi + "'";
        PreparedStatement sentencia2 = connection.prepareStatement(SQLidUser);
        ResultSet rs = sentencia2.executeQuery();

        int estatus=0;
        if (rs.next()) {
            estatus = rs.getInt("estatus");
        }
        return estatus;
    }

    public List<Proyecto> listarProyectosGestor(){
        List<Proyecto> listaProyecto=new ArrayList<>();
        try {
            String SQL="select idProyecto, nombre,categoria,numeroProyecto,repositorio,fechaCreacion,ultimaModificacion from proyecto where proyecto.mostrar=0";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);
            ResultSet data=sentencia.executeQuery();
            while (data.next()==true){
                Proyecto proyecto=new Proyecto();
                proyecto.setIdProyecto(data.getInt(1));
                proyecto.setNombre(data.getString(2));
                proyecto.setCategoria(data.getString(3));
                proyecto.setNumeroProyecto(data.getString(4));
                proyecto.setRepositorio(data.getString(5));
                proyecto.setFechaCreacion(data.getString(6));
                proyecto.setUltimaModificacion(data.getString(7));

                listaProyecto.add(proyecto);
            }
            data.close();
            sentencia.close();
        }catch (Exception e){
            System.err.println("Ocurrió un error al listar los proyectos");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
        }
        return listaProyecto;
    }
    public List<Proyecto> listarProyectosUsuarios(int idUsuario){
        List<Proyecto> listaProyecto=new ArrayList<>();
        try {
            String SQL="select proyectoxusuario.idProyecto, proyecto.nombre, proyecto.categoria, proyecto.numeroProyecto, proyecto.repositorio, proyecto.fechaCreacion, proyecto.ultimaModificacion from proyecto, proyectoxusuario  WHERE proyecto.idProyecto=proyectoxusuario.idProyecto and proyecto.mostrar=0  and proyectoxusuario.idUsuario=" + idUsuario;
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);
            ResultSet data=sentencia.executeQuery();
            while (data.next()==true){
                Proyecto proyecto=new Proyecto();
                proyecto.setIdProyecto(data.getInt(1));
                proyecto.setNombre(data.getString(2));
                proyecto.setCategoria(data.getString(3));
                proyecto.setNumeroProyecto(data.getString(4));
                proyecto.setRepositorio(data.getString(5));
                proyecto.setFechaCreacion(data.getString(6));
                proyecto.setUltimaModificacion(data.getString(7));

                listaProyecto.add(proyecto);
            }
            data.close();
            sentencia.close();
        }catch (Exception e){
            System.err.println("Ocurrió un error al listar los proyectos");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
        }
        return listaProyecto;
    }
    public List<Proyecto> listarProyectosTemporales(){
        List<Proyecto> listaProyectoTemporales=new ArrayList<>();
        try {
            String SQL="select idProyecto, nombre,categoria,numeroProyecto,repositorio,fechaCreacion,ultimaModificacion,estatus,idproyectoModi from proyectoModificado where estatus=0 or estatus=3";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);
            ResultSet data=sentencia.executeQuery();
            while (data.next()==true){
                Proyecto proyecto=new Proyecto();
                proyecto.setIdProyecto(data.getInt(1));
                proyecto.setNombre(data.getString(2));
                proyecto.setCategoria(data.getString(3));
                proyecto.setNumeroProyecto(data.getString(4));
                proyecto.setRepositorio(data.getString(5));
                proyecto.setFechaCreacion(data.getString(6));
                proyecto.setUltimaModificacion(data.getString(7));
                proyecto.setEstatus(data.getInt(8));
                proyecto.setIdProyectoModi(data.getInt(9));

                listaProyectoTemporales.add(proyecto);
            }
            data.close();
            sentencia.close();
        }catch (Exception e){
            System.err.println("Ocurrió un error al listar los proyectos");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
        }
        return listaProyectoTemporales;
    }

    public boolean editarProyecto(Proyecto proyecto){
        try {
            String SQL="update proyecto set nombre=?,categoria=?,numeroProyecto=?,repositorio=?,ultimaModificacion=? WHERE idProyecto=?";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);
            sentencia.setString(1,proyecto.getNombre());
            sentencia.setString(2,proyecto.getCategoria());
            sentencia.setString(3,proyecto.getNumeroProyecto());
            sentencia.setString(4,proyecto.getRepositorio());
            sentencia.setString(5,proyecto.getUltimaModificacion());

            sentencia.setInt(6,proyecto.getIdProyecto());

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

    public boolean ocultarProyecto(Proyecto proyecto){
        try {
            String SQL="update proyecto set nombre=?,categoria=?,numeroProyecto=?,repositorio=?,ultimaModificacion=?, mostrar=? WHERE idProyecto=?";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);
            sentencia.setString(1,proyecto.getNombre());
            sentencia.setString(2,proyecto.getCategoria());
            sentencia.setString(3,proyecto.getNumeroProyecto());
            sentencia.setString(4,proyecto.getRepositorio());
            sentencia.setString(5,proyecto.getUltimaModificacion());
            sentencia.setInt(6, proyecto.getMostrar());
            sentencia.setInt(7,proyecto.getIdProyecto());

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

    public boolean crearProyectoTemporal(Proyecto proyecto){
        try {
            String SQL="insert into proyectoModificado(idProyecto, nombre,categoria,fechaCreacion, numeroProyecto,repositorio,ultimaModificacion, estatus)"+
                    "values(?,?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);
            sentencia.setInt(1,proyecto.getIdProyecto());
            sentencia.setString(2,proyecto.getNombre());
            sentencia.setString(3,proyecto.getCategoria());
            sentencia.setString(4,proyecto.getFechaCreacion());
            sentencia.setString(5,proyecto.getNumeroProyecto());
            sentencia.setString(6,proyecto.getRepositorio());
            sentencia.setString(7,proyecto.getUltimaModificacion());
            sentencia.setInt(8, proyecto.getEstatus());
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

    public boolean editarProyectoTemporal(Proyecto proyecto){
        try {
            String SQL="update proyectoModificado set estatus=? WHERE idproyectoModi=? and idProyecto=? and nombre=? and categoria=? and numeroProyecto=? and repositorio=?";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia =connection.prepareStatement(SQL);

            sentencia.setInt(1,proyecto.getEstatus());
            sentencia.setInt(2,proyecto.getIdProyectoModi());
            sentencia.setInt(3,proyecto.getIdProyecto());
            sentencia.setString(4,proyecto.getNombre());
            sentencia.setString(5,proyecto.getCategoria());
            sentencia.setString(6,proyecto.getNumeroProyecto());
            sentencia.setString(7,proyecto.getRepositorio());

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


    /*public  boolean eliminarProyecto(int idProyecto){
        try {
            String SQL="select from proyecto where idProyecto=?";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia=connection.prepareStatement(SQL);

            sentencia.setInt(1,idProyecto);
            sentencia.executeUpdate();
            sentencia.close();
            return true;
        }catch (Exception e){
            System.err.println("Ocurrió un error al eliminar el proyecto");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
            return false;
        }*/


}