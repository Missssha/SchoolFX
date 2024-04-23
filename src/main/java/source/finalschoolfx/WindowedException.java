package source.finalschoolfx;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class WindowedException extends Exception {
    @FXML
    private Label errorMessage;
    @FXML
    private Button confirmButton;
    public WindowedException(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(HelloApplication.class.getResource("exception-view.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ошибка!");
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);
        dialogStage.setMaximized(false);
        errorMessage.setText("Вы не выбрали ни одного элемента!");
        dialogStage.show();
    }

    public void onButtonClick() {
        Stage stage = (Stage)confirmButton.getScene().getWindow();
        stage.close();
    }
}
