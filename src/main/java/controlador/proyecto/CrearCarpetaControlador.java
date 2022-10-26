package controlador.proyecto;

import controlador.dao.CarpetaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import modelo.Carpeta;
import modelo.Proyecto;

import java.time.LocalDate;

public class CrearCarpetaControlador {
    @FXML
    private TextField txtinvestigacion;
    @FXML
    private TextField txtmedia;

    private Carpeta carpeta;
    private CarpetaDao carpetaDao;

    public CrearCarpetaControlador() {
        carpeta = new Carpeta();
        carpetaDao = new CarpetaDao();
    }

    @FXML public void crearCarpeta(){

        carpeta.setInvestigacion(txtinvestigacion.getText());
        carpeta.setMedia(txtmedia.getText());

        String investigacion=carpeta.getInvestigacion();
        String media=carpeta.getInvestigacion();

        ValidarCamposCarpeta(investigacion, media);
    }

    public boolean ValidarCamposCarpeta(String linkInvestigacion, String linkMedia){
        //int IDUsuario=LoginControlador.UserIdActivo;
        boolean rsp=true;

        if(linkInvestigacion.isEmpty() || linkMedia.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error debido a espacios en blanco");
            alert.showAndWait();
            rsp=false;
            return rsp;
        }else {
            carpeta=new Carpeta(linkInvestigacion, linkMedia);
            rsp= carpetaDao.registrarCarpeta(carpeta);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Se registró correctamente la carpeta al proyecto");
            alert.initStyle(StageStyle.UTILITY);
            alert.showAndWait();
            //limpiarCampos();
            return rsp;
        }
    }
}
