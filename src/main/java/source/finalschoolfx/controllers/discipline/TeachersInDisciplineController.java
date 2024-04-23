package source.finalschoolfx.controllers.discipline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import source.finalschoolfx.controllers.teacher.TeachersInfoController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.StudClassRepository;
import source.finalschoolfx.repository.TeacherRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeachersInDisciplineController extends TeachersInfoController {
    @FXML
    private Button editItem1;
    @FXML
    private Button editItem2;
    @FXML
    private Button addItem;
    @FXML
    private Button deleteItem;

    public Discipline discipline;

    private final TeacherRepository teacherRepository = new TeacherRepository(new Teacher());
    public TeachersInDisciplineController(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editItem.setVisible(false);
        editItem1.setVisible(false);
        editItem2.setVisible(false);
        addItem.setVisible(false);
        deleteItem.setVisible(false);
        bootTable();
    }

    @Override
    protected List<Teacher> performUpdateTable() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.all().forEach(teacher -> {
            if(teacher.getDisciplines() != null && teacher.getDisciplines().contains(discipline))
                teachers.add(teacher);
        });
        return teachers;
    }


}
