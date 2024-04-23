package source.finalschoolfx.controllers.teacher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseChangeController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;
import source.finalschoolfx.repository.TeacherRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddDisciplineInTeacherController extends BaseChangeController<Discipline, DisciplineRepository> implements Initializable {
    private final Teacher teacher;
    @FXML
    public ComboBox<String> comboDisc;
    public AddDisciplineInTeacherController(Discipline entity, DisciplineRepository repository, Teacher teacher) {
        super(entity, repository);
        this.teacher = teacher;
    }

    @Override
    protected void performSubmit() throws IOException, WindowedException {
        repository.all().forEach(discipline -> {
            if(discipline.getDisciplineName().equalsIgnoreCase(comboDisc.getSelectionModel().getSelectedItem())) {
                discipline.getTeachers().add(teacher);
                teacher.getDisciplines().add(discipline);
                repository.update(discipline);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository.all().forEach(discipline -> {
            comboDisc.getItems().add(discipline.getDisciplineName());
        });
    }
}
