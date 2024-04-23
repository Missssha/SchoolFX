package source.finalschoolfx.controllers.student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.repository.StudClassRepository;
import source.finalschoolfx.repository.StudentRepository;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditStudentController extends BaseChangeController<Student, StudentRepository> implements Initializable {
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
    private TextField rating_field;

    @FXML
    private TextField work_period_field;
    private StudClassRepository studClassRepository = new StudClassRepository(new StudClass());

    public EditStudentController(Student entity, StudentRepository repository) {
        super(entity, repository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studClassRepository.all().forEach(studClass -> {
            comboBoxClass.getItems().add(studClass.getClassname());
        });
        nameTextField.setText(entity.getFullname());
        age_field.setText(String.valueOf(entity.getAge()));
        rating_field.setText(entity.getRating());
        phone_number_field.setText(entity.getPhoneNumber());
        submitButton.setText("Сохранить");


    }

    @Override
    protected void performSubmit() {
        entity.setFullname(nameTextField.getText());
        for (StudClass studClass : studClassRepository.all()) {
            if (studClass.getClassname().equalsIgnoreCase(comboBoxClass.getSelectionModel().getSelectedItem())) {
                entity.getStudClass().getListOfStudents().remove(entity);
                entity.setStudClass(studClass);
                studClass.getListOfStudents().add(entity);
            }
        }
        entity.setRating(rating_field.getText());
        entity.setPhoneNumber(phone_number_field.getText());
        entity.setAge(Integer.parseInt(age_field.getText()));
        repository.update(entity);
    }
}
