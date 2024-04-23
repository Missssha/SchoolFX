package source.finalschoolfx.controllers.discipline;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

public class EditDisciplineController extends BaseChangeController<Discipline, DisciplineRepository> implements Initializable {
    @FXML
    private Button submitButton;

    @FXML
    private TextField nameTextField;


    public EditDisciplineController(Discipline entity, DisciplineRepository repository) {
        super(entity, repository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTextField.setText(entity.getDisciplineName());
        submitButton.setText("Сохранить");
    }

    @Override
    protected void performSubmit() throws IOException, WindowedException {
        entity.setDisciplineName(nameTextField.getText());
        repository.update(entity);
    }
}
