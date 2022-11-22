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
                    "ultimaModificacion,repositorio,idUsuario, numeroProyecto)"+
                    "values(?,?,?,?,?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,proyecto.getNombre());
            sentencia.setString(2, proyecto.getCategoria());
            sentencia.setString(3,String.valueOf(proyecto.getFechaCreacion()));
            sentencia.setString(4,String.valueOf(proyecto.getUltimaModificacion()));
            sentencia.setString(5, proyecto.getRepositorio());
            sentencia.setInt(6,proyecto.getIdUsuario());
            sentencia.setString(7,proyecto.getNumeroProyecto());

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

    public boolean registrarProyectoxusuario(int usuarioId, int proyectoId){
        try {
            String SQL = "insert into proyectoxusuario(idProyecto, idUsuario)" +
                    "values(?,?)";
            Connection connection = this.obtenerConexion.getConnection();
            PreparedStatement sentencia = connection.prepareStatement(SQL);

            sentencia.setInt(1, proyectoId);
            sentencia.setInt(2, usuarioId);

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
    public List<Proyecto> listarProyectosGestor(){
        List<Proyecto> listaProyecto=new ArrayList<>();
        try {
            String SQL="select idProyecto, nombre,categoria,numeroProyecto,repositorio,fechaCreacion,ultimaModificacion from proyecto";
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
            String SQL="select proyectoxusuario.idProyecto, proyecto.nombre, proyecto.categoria, proyecto.numeroProyecto, proyecto.repositorio, proyecto.fechaCreacion, proyecto.ultimaModificacion from proyecto, proyectoxusuario  WHERE proyecto.idProyecto=proyectoxusuario.idProyecto and proyectoxusuario.idUsuario=" + idUsuario;
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
    public  boolean eliminarProyecto(int idProyecto){
        try {
            String SQL="delete from proyecto where idProyecto=?";
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
        }
    }

}