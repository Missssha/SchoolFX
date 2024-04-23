package source.finalschoolfx.controllers.base;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import source.finalschoolfx.HelloApplication;


import java.io.IOException;

abstract public class BaseController {

    public void openWindow(String view, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloApplication.class.getResource(view));
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.showAndWait();
    }

    public <T> void openWindow(String view, String title, T controller) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(HelloApplication.class.getResource(view));
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        dialogStage.showAndWait();

    }

}
