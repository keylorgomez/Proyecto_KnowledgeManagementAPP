package controlador.proyecto;

import modelo.Usuario;
import controlador.dao.UsuarioDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.StageStyle;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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

    @FXML private Button btnbuscarFotoRegistro;
    @FXML private Button btnRegistro;
    private UsuarioDao usuarioDao;

    public RegistroUsuarioControlador() {
        usuario = new Usuario("","",0,"","","","");
        labelRegistro = new Label();
        usuario = new Usuario();
        usuarioDao = new UsuarioDao();
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

    @FXML public void registrarUsuario() throws SQLException {
        usuario.setNombre(txtnombreRegistro.getText());
        usuario.setApellido(txtapellidoRegistro.getText());
        usuario.setFechaNacimiento(nacimientoRegistro.getValue().toString());
        usuario.setEmail(txtemailRegistro.getText());
        usuario.setPassword(txtcontrasenna.getText());
        usuario.setFoto(txtLinkFoto.getText());

        String nombreUsuario = usuario.getNombre();
        String apellidoUsuario = usuario.getApellido();
        String fechaNacimientoUsuario = usuario.getFechaNacimiento();
        String emailUsuario = usuario.getEmail();
        String passwordUsuario = usuario.getPassword();
        String fotoUsuario = usuario.getFoto();
        ValidarCamposRegistro(nombreUsuario, apellidoUsuario, fechaNacimientoUsuario, emailUsuario, passwordUsuario, fotoUsuario);

    }

    public boolean ValidarCamposRegistro(String nombre, String apellido, String fecha, String email, String contrasenna, String foto) throws SQLException {
        boolean rsp= true;
        int edad= calculoEdad(fecha);

        if(nombre.isEmpty() || apellido.isEmpty() ||email.isEmpty() || contrasenna.isEmpty() || (validarUserandContra(email, contrasenna)== false)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
             return rsp = false;
        } else {

                usuario = new Usuario(nombre, apellido, edad, fecha, email, contrasenna, foto);
                rsp= usuarioDao.registrarUsuario(usuario);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Se registró correctamente el usuario");
                alert.initStyle(StageStyle.UTILITY);
                alert.showAndWait();
                limpiarCampos();
                return rsp;
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
        txtLinkFoto.setText("");

    }

}

