package controlador.proyecto;

import controlador.dao.CarpetaDao;
import controlador.dao.InvestigacionDao;
import controlador.dao.ProyectoDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Investigacion;
import modelo.Media;
import modelo.Proyecto;

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
    private InvestigacionDao investigacionDao;

    private Investigacion investigacion;

    private CarpetaDao carpetaDao;


    public CrearInvestigacionController() {
        investigacion = new Investigacion();
        investigacionDao=new InvestigacionDao();
        carpetaDao =new CarpetaDao();
    }

    public boolean ValidarCampos(String categoria,  String tema, String autor, String titulo, String subtitulo,String contenido){
        String fechaModificacion = LocalDate.now().toString();
        String fechaInicio = LocalDate.now().toString();

        boolean rsp=true;
        if(categoria.isEmpty()||tema.isEmpty()||autor.isEmpty()||titulo.isEmpty()||subtitulo.isEmpty()||contenido.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
            return rsp=false;
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Exito");
            alert.setContentText("Se registro exitosamente la media");
            alert.showAndWait();
            investigacion=new Investigacion(titulo, categoria, tema, autor, fechaInicio, fechaModificacion, subtitulo, contenido);
            rsp= investigacionDao.registrarInvestigacion(investigacion);
            return rsp=true;

        }
    }
    @FXML
    void crearInvestigacion(ActionEvent event) {
        String ruta = txtRuta.getText();
        String categoria = txtCategoria.getText();
        String tema = txtTema.getText();
        String autor = txtAutor.getText();
        String titulo = txtTitulo.getText();
        String subTitulo = txtSubTitulo.getText();
        String contenido1 = txtContenido1.getText();

        String ultimaModificacion = LocalDate.now().toString();
        String fechaCreacion = LocalDate.now().toString();

        carpeta.crearCarpeta(ruta, titulo);
        String nuevaRuta =ruta+"\\"+titulo;

        if(ValidarCampos(categoria, tema,autor, titulo, subTitulo, contenido1)==true){
            documento.crearDocumento(ultimaModificacion, fechaCreacion, nuevaRuta, categoria,tema,autor,titulo,contenido1,subTitulo);
            carpeta.crearCarpeta(nuevaRuta, "Media");

        }
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

