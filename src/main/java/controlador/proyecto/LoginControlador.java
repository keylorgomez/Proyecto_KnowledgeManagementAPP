package controlador.proyecto;

import controlador.dao.ProyectoDao;
import controlador.dao.UsuarioDao;
import javafx.scene.control.*;
import modelo.Usuario;
import vista.Inicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginControlador {
    Usuario usuario;
    @FXML private Button btnIniciar;
    @FXML private Button btnRegistrarse;
    @FXML private ImageView imagLogo;
    @FXML private Label labelContrasenia;
    @FXML private Label labelRegistrese;
    @FXML private Label labelSesion;
    @FXML private Label labelUsuario;
    @FXML private PasswordField txtContrasenia;
    @FXML private TextField txtUsuario;
    @FXML private Label mensaje;
    private UsuarioDao usuarioDao;
    private ProyectoDao proyectoDao;
    public static int UserIdActivo=0;

    public LoginControlador() {
        usuario = new Usuario();
        usuarioDao = new UsuarioDao();
        proyectoDao=new ProyectoDao();
    }
    @FXML public void loginUsuario() throws SQLException, IOException {
        usuario.setEmail(txtUsuario.getText());
        usuario.setPassword(txtContrasenia.getText());

        String emailLogin = usuario.getEmail();
        String passwordLogin = usuario.getPassword();
        validarLogin(emailLogin, passwordLogin);
        UserIdActivo= proyectoDao.getUsuarioId(emailLogin);
    }
    public void validarLogin(String user, String password) throws SQLException, IOException {
        if (user.isEmpty()|| password.isEmpty())
            mensaje.setText("Datos incorrectos");
        else {
            realizarLogin(user, password);
        }
    }
    public void realizarLogin(String email, String contrasenna) throws SQLException, IOException {
        Boolean verificandoUsuario = usuarioDao.verificarUsuario(email, contrasenna);
        if (verificandoUsuario == true) {
            mensaje.setText("Ingresado con exito");
            ingresarApp();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a datos incorrectos");
            alert.showAndWait();
        }

    }
    public void ingresarApp() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("CrearProyecto.fxml")));
        Stage window = (Stage) btnIniciar.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    public void IrRegistrarse(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("RegistroUsuario.fxml")));
        Stage window = (Stage) btnRegistrarse.getScene().getWindow();
        window.setScene(new Scene(root));
    }


}

