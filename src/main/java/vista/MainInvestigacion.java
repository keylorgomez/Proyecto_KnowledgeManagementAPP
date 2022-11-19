package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainInvestigacion extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Main para probar la pantalla de crear investigacion, hace carpetas y documentos
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Inicio.class.getResource("CrearInvestigacion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Knowledge Management APP");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

