package source.finalschoolfx.controllers.discipline;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;
import source.finalschoolfx.repository.TeacherRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddDisciplineController extends BaseChangeController<Discipline, DisciplineRepository> implements Initializable {
    @FXML
    private Button submitButton;
    @FXML
    private TextField nameTextField;

    public AddDisciplineController(Discipline entity, DisciplineRepository repository) {
        super(entity, repository);
    }


    @Override
    protected void performSubmit() throws IOException, WindowedException {
        if (nameTextField.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле имени");
        entity.setDisciplineName(nameTextField.getText());
        repository.insert(entity);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
