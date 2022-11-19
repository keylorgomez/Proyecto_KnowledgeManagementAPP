package controlador.proyecto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CrearInvestigacionController {
    private static CrearCarpetaController carpeta;
    private static DocumentoController documento;
    @FXML
    private Button btnGuardarInvestigacion;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtCategoria;

    @FXML
    private TextField txtContenido1;

    @FXML
    private TextField txtContenido2;

    @FXML
    private TextField txtRuta;

    @FXML
    private TextField txtSubTitulo;

    @FXML
    private TextField txtTema;

    @FXML
    private TextField txtTitulo;

    public CrearInvestigacionController() {
    }

    @FXML
    void crearInvestigacion(ActionEvent event) {
        String ruta = txtRuta.getText();
        String categoria = txtCategoria.getText();
        String tema = txtTema.getText();
        String autor = txtAutor.getText();
        String titulo = txtTitulo.getText();
        String contenido1 = txtContenido1.getText();
        String subTitulo = txtSubTitulo.getText();
        String contenido2 = txtContenido2.getText();

        String ultimaModificacion = LocalDate.now().toString();
        String fechaCreacion = LocalDate.now().toString();

        carpeta.crearCarpeta(ruta, titulo);
        String nuevaRuta =ruta+"\\"+titulo;
        documento.crearDocumento(ultimaModificacion, fechaCreacion, nuevaRuta, categoria,tema,autor,titulo,contenido1,subTitulo,contenido2);
        carpeta.crearCarpeta(nuevaRuta, "Media");

    }
    /*

    ==> Se puede borrar, solo es para pruebas sin frontend

public static void crearInvestigacion(String ruta, String categoria, String tema, String autor,
                                      String titulo, String contenido1, String subTitulo, String contenido2) {

    String ultimaModificacion = LocalDate.now().toString();
    String fechaCreacion = LocalDate.now().toString();

    CrearCarpetaController.crearCarpeta(ruta, titulo);

    String nuevaRuta =ruta+"\\"+titulo;
    DocumentoController.crearDocumento(ultimaModificacion,fechaCreacion, nuevaRuta, categoria,tema,autor,titulo,contenido1,subTitulo,contenido2);
    CrearCarpetaController.crearCarpeta(nuevaRuta, "Media");

    System.out.println("Investigacion creada en la fecha: "+fechaCreacion.toString());
    System.out.println("Investigacion creada en la fecha: "+ultimaModificacion.toString());
}
 */

}

