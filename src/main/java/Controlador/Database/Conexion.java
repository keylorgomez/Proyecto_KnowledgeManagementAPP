package Controlador.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase connexion que hace la connection a la base de datos
 */
public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/db_proyectopoo";
    private static final String USER = "root";
    private static final String PASS = "achaves2912";

    /**
     * Constructor de la clase
     */
    public Conexion() {
    }

    /**
     * Function que crea la conexion a la base de datos
     * @return devuelve los settings de la conexion
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    public static Connection getConnection() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(URL, USER, PASS);

        return connection;
    }

    /**
     * Function que ayuda a cerrar la declaration de la base de datos
     * @param statement recibe declaration de la base de datos
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    public static void closePreparedStatement(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    /**
     * Function que ayuda a cerrar la conexion con la base de datos
     * @param connection Recibe unos settings para la conexion
     * @throws SQLException genera una excepción si la conexion no es establecida con la base de datos
     */
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}









/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection connection;
    private String usuario="root";
    private String password="achaves2912";
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
}*/

