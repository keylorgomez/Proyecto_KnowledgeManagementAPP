package Controlador.Proyecto;

import Modelo.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroUsuarioControlador {
    Usuario usuario;
    @FXML private TextField txtnombreRegistro;
    @FXML private TextField txtapellidoRegistro;
    @FXML private DatePicker nacimientoRegistro;
    @FXML private TextField txtemailRegistro;
    @FXML private TextField txtcontrasenna;

    private Label labelFotoRegistro;
    @FXML private Label labelRegistro;

    @FXML private Button btnbuscarFotoRegistro;
    @FXML private Button btnRegistro;

    public RegistroUsuarioControlador() {
        usuario = new Usuario("","",0,null,"","","");
        labelRegistro = new Label();
    }

    public boolean validarUserandContra(String email, String password){
        Pattern Correo =null;
        Matcher ResultadoCorreo=null;

        Correo=Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");

        Pattern Contrasenna=null;
        Matcher ResultadoContra=null;

        Contrasenna=Pattern.compile( "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$");
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
            alert.setContentText("La contraseña debe contener de 4 a 8 caracteres. Además incluir mínimo una mayúscula y un número.");
            alert.showAndWait();
            return false;
        }else{
            return true;
        }

    }

    public void registrarUsuario(){
        usuario.setNombre(txtnombreRegistro.getText());
        usuario.setApellido(txtapellidoRegistro.getText());
        usuario.setFechaNacimiento(nacimientoRegistro.getValue());
        usuario.setEmail(txtemailRegistro.getText());
        usuario.setPassword(txtcontrasenna.getText());
        usuario.setFoto(labelFotoRegistro.getText());

        String nombreUsuario = usuario.getNombre();
        String apellidoUsuario = usuario.getApellido();
        LocalDate fechaNacimientoUsuario = usuario.getFechaNacimiento();
        String emailUsuario = usuario.getEmail();
        String passwordUsuario = usuario.getPassword();
        String fotoUsuario = usuario.getFoto();
    }

    public void ValidarCamposRegistro(String nombre, String apellido, LocalDate fecha, String email, String contrasenna, String foto){
        int edad= calculoEdad(fecha);
        if(nombre.isEmpty() || apellido.isEmpty() ||email.isEmpty() || contrasenna.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
        } else if(validarUserandContra(email, contrasenna)) {
            }else{
                labelRegistro.setText("Usuario registrado exitosamente");
                usuario = new Usuario(nombre, apellido, edad, fecha, email, contrasenna, foto);
                //UDI.insert(usuario);//envio de datos a la base de datos
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Succesfull");
                alert.setContentText("Usuario registrado exitosamente");
                alert.showAndWait();
            }
    }


    public int calculoEdad(LocalDate fechaNacimiento){
        LocalDate now = LocalDate.now();
        LocalDate born = LocalDate.of(fechaNacimiento.getYear(),fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
        Period period = Period.between(born,now);
        int edad= period.getYears();
        return edad;
    }
}

