package controlador.dao;

import controlador.database.Conexion;
import javafx.stage.Stage;
import modelo.Carpeta;
import modelo.Proyecto;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CarpetaDao {
    private Conexion obtenerConexion;
    Proyecto proyecto= new Proyecto();
    Usuario usuario=new Usuario();

    public CarpetaDao(){
        this.obtenerConexion=new Conexion();
    }

    public boolean registrarCarpeta(Carpeta carpeta){
        try {
            String SQL="insert into carpeta(investigacion,media)"+
                    "values(?,?)";
            Connection connection=this.obtenerConexion.getConnection();
            PreparedStatement sentencia= connection.prepareStatement(SQL);

            sentencia.setString(1,carpeta.getInvestigacion());
            sentencia.setString(2, carpeta.getMedia());

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
