package controlador.proyecto;

import controlador.dao.InvestigacionDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Investigacion;
import modelo.Proyecto;
import vista.Inicio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class BusquedaContenidoController {
    private InvestigacionDao investigacionDao;
    @FXML
    private Button btnBuscarPalabra;

    @FXML
    private TextField txtPalabraBuscar;
    @FXML private Label labelEncontrado;

    public BusquedaContenidoController() {
        investigacionDao= new InvestigacionDao();
    }

    @FXML public void buscarPalabra() throws IOException, SQLException {
        String palabra= txtPalabraBuscar.getText();

        if (palabra.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error, no hay palabra ingresada para hacer búsqueda");
            alert.showAndWait();
        }else{
            labelEncontrado.setText(investigacionDao.busqueda(palabra));
        }
        txtPalabraBuscar.setText("");
    }



    @FXML
    void BuscarPalabra(ActionEvent event) throws SQLException {

    }
}

