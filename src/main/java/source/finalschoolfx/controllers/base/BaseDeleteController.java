package source.finalschoolfx.controllers.base;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

abstract public class BaseDeleteController<M, R> {

    @FXML
    protected Button confirmButton;
    @FXML
    protected Button cancelButton;

    protected M entity;
    protected R repository;

    public BaseDeleteController(M entity, R repository) {
        this.entity = entity;
        this.repository = repository;
    }

    @FXML
    public void onConfirmButtonClick() {
        delete();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    abstract protected void delete();

    @FXML
    public void onCancelButtonClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
