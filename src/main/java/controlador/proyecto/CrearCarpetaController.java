package controlador.proyecto;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Carpeta;

import java.io.File;

public class CrearCarpetaController {
    private static String rutaNombreCarpeta;
    @FXML
    public TextField txtRutaDeCarpeta;
    @FXML
    public TextField txtNombreDeCarpeta;


    public CrearCarpetaController() {
    }

    public CrearCarpetaController(TextField txtRutaDeCarpeta, TextField txtNombreDeCarpeta) {
        this.txtRutaDeCarpeta = txtRutaDeCarpeta;
        this.txtNombreDeCarpeta = txtNombreDeCarpeta;
    }

    public void crearCarpeta(){
        String ruta = txtRutaDeCarpeta.getText();
        String nombre = txtNombreDeCarpeta.getText();
        rutaNombreCarpeta = ruta+nombre;
        File crearCarpeta = new File(rutaNombreCarpeta);
        if (crearCarpeta.mkdir()){
            System.out.println("carpeta crada con exito");
        }else {
            System.out.println("no se pudo crear carpeta");
        }
    }

    public static void crearCarpeta(String ruta, String nombre){
        rutaNombreCarpeta = ruta+"\\"+nombre;
        File crearCarpeta = new File(rutaNombreCarpeta);
        if (crearCarpeta.mkdir()){
            System.out.println("carpeta crada con exito");
        }else {
            System.out.println("no se pudo crear carpeta");
        }
    }

}
