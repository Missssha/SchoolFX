package source.finalschoolfx.controllers.teacher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;
import source.finalschoolfx.repository.StudClassRepository;
import source.finalschoolfx.repository.StudentRepository;
import source.finalschoolfx.repository.TeacherRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddTeacherController extends BaseChangeController<Teacher, TeacherRepository> implements Initializable {

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
    private final StudClassRepository studClassRepository = new StudClassRepository(new StudClass());
    private final DisciplineRepository disciplineRepository = new DisciplineRepository(new Discipline());

    public AddTeacherController(Teacher entity, TeacherRepository repository) {
        super(entity, repository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for (StudClass studClass : studClassRepository.all()) {
            comboBoxClass.getItems().add(studClass.getClassname());
        }
        for (Discipline discipline : disciplineRepository.all()) {
            comboBoxDisc.getItems().add(discipline.getDisciplineName());
        }
    }


    @Override
    protected void performSubmit() throws IOException, WindowedException {
        if (nameTextField.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле имени");
        if (age_field.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле возраста");
        if (phone_number_field.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле номера телефона");
        if (work_period_field.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле рабочего стажа");


        for (StudClass studClass : studClassRepository.all()) {
            if (studClass.getClassname().equalsIgnoreCase(comboBoxClass.getSelectionModel().getSelectedItem())) {
                entity.getStudClasses().add(studClass);
                studClass.getTeachers().add(entity);
            }
        }
        for (Discipline discipline : disciplineRepository.all()) {
            if (discipline.getDisciplineName().equalsIgnoreCase(comboBoxDisc.getSelectionModel().getSelectedItem())) {
                entity.getDisciplines().add(discipline);
                discipline.getTeachers().add(entity);
            }
        }
        entity.setFullname(nameTextField.getText());
        entity.setAge(Integer.parseInt(age_field.getText()));
        entity.setPhoneNumber(phone_number_field.getText());
        entity.setWorkPeriod(Integer.parseInt(work_period_field.getText()));

        repository.insert(entity);


    }


}
