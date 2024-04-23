package source.finalschoolfx.controllers.student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.repository.StudClassRepository;
import source.finalschoolfx.repository.StudentRepository;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddStudentController extends BaseChangeController<Student, StudentRepository> implements Initializable {
    @FXML
    private ComboBox<String> comboBoxClass;
    @FXML
    private TextField phone_number_field;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField work_period_field;
    @FXML
    private TextField rating_field;

    @FXML
    private TextField age_field;
    private final StudClassRepository studClassRepository = new StudClassRepository(new StudClass());

    public AddStudentController(Student entity, StudentRepository repository) {
        super(entity, repository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (StudClass studClass : studClassRepository.all()) {
            comboBoxClass.getItems().add(studClass.getClassname());
        }
    }

    @Override
    public void performSubmit() throws IOException, WindowedException {
        if (nameTextField.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле имени");
        if (age_field.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле возраста");
        if (phone_number_field.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле номера телефона");
        if (rating_field.getText().isEmpty()) throw new WindowedException("Вы не заполнили поле рейтинга");
        entity.setFullname(nameTextField.getText());
        entity.setAge(Integer.parseInt(age_field.getText()));
        entity.setPhoneNumber(phone_number_field.getText());
        entity.setRating(rating_field.getText());
        for (StudClass studClass : studClassRepository.all()) {
            if (studClass.getClassname().equalsIgnoreCase(comboBoxClass.getSelectionModel().getSelectedItem())) {
                entity.setStudClass(studClass);
                studClass.getListOfStudents().add(entity);
            }
        }
        repository.insert(entity);
    }

}
