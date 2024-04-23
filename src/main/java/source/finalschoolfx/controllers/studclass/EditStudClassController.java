package source.finalschoolfx.controllers.studclass;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.StudClassRepository;
import source.finalschoolfx.repository.TeacherRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditStudClassController extends BaseChangeController<StudClass, StudClassRepository> implements Initializable {
    @FXML
    private TextField nameTextField;
    @FXML
    private Button submitButton;


    public EditStudClassController(StudClass entity, StudClassRepository repository) {
        super(entity, repository);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTextField.setText(entity.getClassname());
        submitButton.setText("Сохранить");
    }

    @Override
    protected void performSubmit() throws IOException, WindowedException {
        entity.setClassname(nameTextField.getText());
        repository.update(entity);
    }
}
