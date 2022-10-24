package Controlador.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String usuario="root";
    private static final String password="123456";
    private static final String servidor="localhost";
    private static final String puerto="3306";
    private static final String nombreDB="db_proyectopoo";
    private static final String url="jdbc:mysql://"+servidor+":"+puerto+"/"+nombreDB+"?serverTimezone=UTC";
    private static final String driver="com.mysql.cj.jdbc.Driver";


    public Conexion(){

    }

    public static Connection getConnection() throws SQLException{
        Connection connection;
        connection = DriverManager.getConnection(url,usuario,password);
        return connection;
    }


    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
