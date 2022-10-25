package controlador.proyecto;

import modelo.Usuario;
import vista.Inicio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML private TextField txtContrasenia;
    @FXML private TextField txtUsuario;
    @FXML private Label mensaje;


    public void validarLogin() throws SQLException {
        if (txtUsuario.getText() == "" || txtContrasenia.getText() == "")
            mensaje.setText("Datos incorrectos");
        else {
            mensaje.setText("Ingreso exitoso");
        }
    }


    public void IrRegistrarse(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("RegistroUsuario.fxml")));
        Stage window = (Stage) btnRegistrarse.getScene().getWindow();
        window.setScene(new Scene(root));
    }


}

