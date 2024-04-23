package source.finalschoolfx.controllers.teacher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.StudClassRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudClassInTeacherController extends BaseChangeController<StudClass, StudClassRepository> implements Initializable {
    private final Teacher teacher;
    @FXML
    public ComboBox<String> comboClass;
    public AddStudClassInTeacherController(StudClass entity, StudClassRepository repository, Teacher teacher) {
        super(entity, repository);
        this.teacher = teacher;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository.all().forEach(studClass -> {
            comboClass.getItems().add(studClass.getClassname());
        });
    }

    @Override
    protected void performSubmit() throws IOException, WindowedException {
        repository.all().forEach(studClass -> {
            if(studClass.getClassname().equalsIgnoreCase(comboClass.getSelectionModel().getSelectedItem())) {
                studClass.getTeachers().add(teacher);
                teacher.getStudClasses().add(studClass);
                repository.update(studClass);
            }
        });
    }
}
