package controlador.proyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Usuario;
import controlador.dao.UsuarioDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;
import vista.Inicio;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroUsuarioControlador {
    Usuario usuario;

    @FXML private TextField txtnombreRegistro;
    @FXML private TextField txtapellidoRegistro;
    @FXML private DatePicker nacimientoRegistro;
    @FXML private TextField txtemailRegistro;
    @FXML private TextField txtcontrasenna;

    @FXML private TextField txtLinkFoto;
    @FXML private Label labelRegistro;

    @FXML private ImageView fotoUsuario;
    @FXML private Button btnbuscarFotoRegistro;
    @FXML private Button btnRegistro;
    @FXML private Button btnVolver;
    private UsuarioDao usuarioDao;
    private String imagen;
    private String foto;

    /**
     * Se crea el constructor de la clase que inicializa las clases externas del mismo proyecto que se utilizaran
     */

    public RegistroUsuarioControlador() {
        usuario = new Usuario("","",0,"","","","","");
        labelRegistro = new Label();
        usuario = new Usuario();
        usuarioDao = new UsuarioDao();
    }

    /**
     * Funcion que valida los campos de usuario y contrasena y los recibe como parametro
     * @param email
     * @param password
     * @return
     */

    public boolean validarUserandContra(String email, String password){
        Pattern Correo =null;
        Matcher ResultadoCorreo=null;

        Correo=Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");

        Pattern Contrasenna=null;
        Matcher ResultadoContra=null;



        Contrasenna=Pattern.compile( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,8}$"); //CAMBIO REALIZADO
        ResultadoCorreo= Correo.matcher(email);
        ResultadoContra=Contrasenna.matcher(password);

        if(ResultadoCorreo.find()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Incorrecto");
            alert.setContentText("El formato de correo ingresado no es permitido");
            alert.showAndWait();
            return false;
        }else if(ResultadoContra.find()==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Incorrecto");
            alert.setContentText("La contraseña debe contener de 6 a 8 caracteres.\n" +
                    "Tambíen incluir almenos una mayúscula, una minúscula, un número y un caracter especial."); //CAMBIO
            alert.showAndWait();
            return false;
        }else{
            return true;
        }

    }

    @FXML public void registrarUsuario() throws SQLException {
        usuario.setNombre(txtnombreRegistro.getText());
        usuario.setApellido(txtapellidoRegistro.getText());
        usuario.setFechaNacimiento(nacimientoRegistro.getValue().toString());
        usuario.setEmail(txtemailRegistro.getText());
        usuario.setPassword(txtcontrasenna.getText());

        String nombreUsuario = usuario.getNombre();
        String apellidoUsuario = usuario.getApellido();
        String fechaNacimientoUsuario = usuario.getFechaNacimiento();
        String emailUsuario = usuario.getEmail();
        String passwordUsuario = usuario.getPassword();
        if(ValidarCamposRegistro(nombreUsuario, apellidoUsuario, fechaNacimientoUsuario, emailUsuario, passwordUsuario)==true){
            if (validarUserandContra(emailUsuario,passwordUsuario)==true){
                boolean verificanoNuevoUsuario=usuarioDao.ValidarUsuarioRegistrado(emailUsuario);
                if(verificanoNuevoUsuario==false){
                    boolean rsp= true;
                    int edad= calculoEdad(fechaNacimientoUsuario);
                    String tipoUsuario="Sin rol";
                    usuario = new Usuario(nombreUsuario, apellidoUsuario, edad, fechaNacimientoUsuario, emailUsuario, passwordUsuario, foto,tipoUsuario);
                    rsp= usuarioDao.registrarUsuario(usuario);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("Se registró correctamente el usuario");
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait();
                    limpiarCampos();
                    //return rsp;
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Ya se encuentra un usuario registrado con el mismo correo electrónico");
                    alert.showAndWait();
                }
            }
        }
    }

    public boolean ValidarCamposRegistro(String nombre, String apellido, String fecha, String email, String contrasenna) throws SQLException {
        if(nombre.isEmpty() || apellido.isEmpty() ||email.isEmpty() || contrasenna.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }


    public int calculoEdad(String fechaNacimiento){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = fechaNacimiento;
        //convert String to LocalDate
        LocalDate Nacimiento = LocalDate.parse(date, formatter);
        LocalDate now = LocalDate.now();
        LocalDate born = LocalDate.of(Nacimiento.getYear(),Nacimiento.getMonth(), Nacimiento.getDayOfMonth());
        Period period = Period.between(born,now);
        int edad= period.getYears();
        return edad;
    }

    private void limpiarCampos(){
        txtnombreRegistro.setText("");
        txtapellidoRegistro.setText("");
        nacimientoRegistro.setValue(null);
        txtemailRegistro.setText("");
        txtcontrasenna.setText("");
        fotoUsuario.setImage(null);


    }
    @FXML
    void regresarInicio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Inicio.class.getResource("Login.fxml")));
        Stage window = (Stage) btnVolver.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public String buscarFoto() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            // Obtener la imagen seleccionada
            File file = fileChooser.showOpenDialog(null);

            // Mostar la imagen
            if (file != null) {
                imagen = file.toString();
                fotoUsuario.setImage(new Image(file.toURI().toString()));
                foto = imagen;
                return foto;
            }else {
                String error ="Ocurre error con foto";
                return error;
            }

    }

}

