package com.horaaventura.horaaventura.Controlador.ControladorProyecto;

import com.horaaventura.horaaventura.Modelo.Usuario;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroUsuario {
    Usuario usuario;



    public boolean validarUsserandContra(String email, String password){
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
            return true;}

    }
}
