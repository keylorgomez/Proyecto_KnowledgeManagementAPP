package Controlador.Database;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {

    private Connection connection;
    private String usuario="root";
    private String password="achaves2912";
    private String servidor="localhost";
    private String puerto="3306";
    private String nombreDB="proyectopoo";
    private String url="jdbc:mysql://"+servidor+":"+puerto+"/"+nombreDB+"?serverTimezone=UTC";
    private String driver="com.mysql.cj.jdbc.Driver";

    public Conexion(){
        try {
            Class.forName(driver);
            connection=DriverManager.getConnection(url,usuario,password);
            if (connection!=null){
                System.out.println("Conexion realiada con éxito");
            }
        }catch (Exception e){
            System.err.println("Ocorrió un error en la conexión");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();

        }
    }

}
