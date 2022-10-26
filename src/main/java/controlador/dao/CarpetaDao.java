package controlador.dao;

import controlador.database.Conexion;
import javafx.stage.Stage;
import modelo.Carpeta;
import modelo.Proyecto;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarpetaDao {
    private Conexion obtenerConexion;
    Proyecto proyecto= new Proyecto();
    Usuario usuario=new Usuario();

    public CarpetaDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarCarpeta(Carpeta carpeta){
        try {
            String SQL="insert into carpeta(investigacion,media,idProyecto)"+
                    "values(?,?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,carpeta.getInvestigacion());
            sentencia.setString(2, carpeta.getMedia());
            sentencia.setInt(3,carpeta.getIdProyecto());

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
    public int getProyectoId(String numero) throws SQLException {
        Connection connection=this.obtenerConexion.getConnection();
        String SQLidProyecto = "SELECT idProyecto FROM proyecto WHERE numeroProyecto = " + "'" + numero + "'";
        PreparedStatement sentencia2 = connection.prepareStatement(SQLidProyecto);
        ResultSet rs = sentencia2.executeQuery();

        int idProyecto=0;
        if (rs.next()) {
            idProyecto = rs.getInt("idProyecto");
        }
        return idProyecto;
    }

}
