package controlador.database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private Connection connection;
    private String usuario="root";
    private String password="123456";
    private String servidor="localhost";
    private String puerto="3306";
    private String nombreDB="db_proyectopoo";
    private String url="jdbc:mysql://"+servidor+":"+puerto+"/"+nombreDB+"?serverTimezone=UTC";
    private String driver="com.mysql.cj.jdbc.Driver";

    public Conexion(){
        try {
            Class.forName(driver);
            connection=DriverManager.getConnection(url,usuario,password);
            if (connection!=null){
                System.out.println("Conexión realizada con éxito");
            }
        }catch (Exception e){
            System.err.println("Ocurrió un error en la conexión");
            System.err.println("Mensaje del error: "+e.getMessage());
            System.err.println("Detalle del error: ");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
