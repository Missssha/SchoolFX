package source.finalschoolfx.controllers.teacher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;
import source.finalschoolfx.repository.StudClassRepository;
import source.finalschoolfx.repository.TeacherRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditTeacherController extends BaseChangeController<Teacher, TeacherRepository> implements Initializable {
    @FXML
    private Button submitButton;

    @FXML
    private TextField age_field;

    @FXML
    private ComboBox<String> comboBoxClass;

    @FXML
    private ComboBox<String> comboBoxDisc;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phone_number_field;

    @FXML
    private TextField work_period_field;
    private StudClassRepository studClassRepository = new StudClassRepository(new StudClass());
    private DisciplineRepository disciplineRepository = new DisciplineRepository(new Discipline());
    private Teacher editableTeacher;

    public EditTeacherController(Teacher entity, TeacherRepository repository) {
        super(entity, repository);
    }

    @Override
    protected void performSubmit() throws IOException, WindowedException {
        entity.setFullname(nameTextField.getText());
        entity.setPhoneNumber(phone_number_field.getText());
        entity.setAge(Integer.parseInt(age_field.getText()));
        entity.setWorkPeriod(Integer.parseInt(work_period_field.getText()));
        repository.update(entity);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTextField.setText(entity.getFullname());
        age_field.setText(String.valueOf(entity.getAge()));
        phone_number_field.setText(entity.getPhoneNumber());
        work_period_field.setText(String.valueOf(entity.getWorkPeriod()));
        submitButton.setText("Сохранить");
    }
}
