package source.finalschoolfx.controllers.base;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import source.finalschoolfx.WindowedException;

import java.io.IOException;

abstract public class BaseChangeController<M, R> {
    @FXML
    protected Button submitButton;
    @FXML
    protected TextField nameTextField;
    protected M entity;
    protected R repository;

    public BaseChangeController(M entity, R repository) {
        this.entity = entity;
        this.repository = repository;
    }
    @FXML
    public void onSubmitButtonClick() throws WindowedException, IOException {
        performSubmit();
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    };



    protected abstract void performSubmit() throws IOException, WindowedException;
}
