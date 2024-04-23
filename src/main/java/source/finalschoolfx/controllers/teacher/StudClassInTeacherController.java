package source.finalschoolfx.controllers.teacher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import source.finalschoolfx.controllers.studclass.StudClassesInfoController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;
import source.finalschoolfx.repository.StudClassRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudClassInTeacherController extends StudClassesInfoController {
    public Teacher teacher;
    @FXML
    private Button info;


    private final StudClassRepository studClassRepository = new StudClassRepository(new StudClass());
    public StudClassInTeacherController(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editItem.setVisible(false);
        info.setVisible(false);
        repository = new StudClassRepository(new StudClass());
        bootTable();
    }

    @Override
    protected List<StudClass> performUpdateTable() {
        List<StudClass> studClassList = new ArrayList<>();
        studClassRepository.all().forEach(studClass -> {
            if(studClass.getTeachers() != null && studClass.getTeachers().contains(teacher))
                studClassList.add(studClass);
        });
        return studClassList;
    }

    @Override
    public void performAdd() throws IOException {
        openWindow("FXML/addStudClassInTeacher.fxml", "Добавление", new AddStudClassInTeacherController(
                new StudClass(), repository, teacher
        ));
    }

    @Override
    public void performDelete() throws IOException {
        openWindow("FXML/deleteStudClass.fxml", "Удаление", new DeleteStudClassInTeacherController(
                table.getSelectionModel().getSelectedItem(), repository, teacher
        ));
    }
}
