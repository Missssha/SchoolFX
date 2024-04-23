package source.finalschoolfx.controllers.studclass;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

public class AddStudClassController extends BaseChangeController<StudClass, StudClassRepository> implements Initializable {
    @FXML
    private Button submitButton;
    @FXML
    private TextField nameTextField;

    public AddStudClassController(StudClass entity, StudClassRepository repository) {
        super(entity, repository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    protected void performSubmit() throws IOException, WindowedException {
        if (nameTextField.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле имени");

        entity.setClassname(nameTextField.getText());

        repository.insert(entity);
    }
}
