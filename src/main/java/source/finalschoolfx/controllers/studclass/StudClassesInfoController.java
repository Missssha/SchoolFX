package source.finalschoolfx.controllers.studclass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseTableController;
import source.finalschoolfx.controllers.teacher.AddTeacherController;
import source.finalschoolfx.controllers.teacher.DeleteTeacherController;
import source.finalschoolfx.controllers.teacher.EditTeacherController;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.StudClassRepository;
import source.finalschoolfx.repository.StudentRepository;
import source.finalschoolfx.repository.TeacherRepository;

import java.io.IOException;
import java.util.List;

public class StudClassesInfoController extends BaseTableController<StudClass, StudClassRepository> {

    @FXML
    private Button addDisciplineWindow;
    @FXML
    private Label countStudentSchool;

    @FXML
    private Button back;

    @FXML
    private Button deleteDisciplineWindow;

    @FXML
    private TableColumn<StudClass, String> disciplineNameColumn;

    @FXML
    private TableColumn<StudClass, Integer> idColumn;
    @FXML
    private TableColumn<StudClass, Integer> CountStudentColumn;

    @FXML
    private Button redactClassWindow;

    private final StudentRepository studentRepository = new StudentRepository(new Student());

    



    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }


    @Override
    protected void performInitialize() {
        repository = new StudClassRepository(new StudClass());
    }

    @Override
    protected void initTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        disciplineNameColumn.setCellValueFactory(new PropertyValueFactory<>("classname"));
        CountStudentColumn.setCellValueFactory(new PropertyValueFactory<>("countOfStudents"));
        countStudentSchool.setText(String.valueOf(studentRepository.all().stream().count()));
//        countStudentSchool.setLabelFor(studentRepository.all().size());

    }

    @Override
    protected void performEdit() throws IOException {
        openWindow("FXML/editStudClass.fxml", "Редактирование класса", new EditStudClassController(
                table.getSelectionModel().getSelectedItem(), repository
        ));
    }

    @Override
    public void performAdd() throws IOException {
        openWindow("FXML/addStudClass.fxml", "Добавление класса", new AddStudClassController(
                new StudClass(), repository
        ));
    }

    public void showStudentsInClass() throws IOException, WindowedException {
        if ((table.getSelectionModel().getSelectedItem()) == null)
            throw new WindowedException("Вы не выбрали ни одного элемента");
        openWindow("FXML/studentsInfo.fxml", "Информация об учениках", new StudentsInStudClassController(
                table.getSelectionModel().getSelectedItem())
        );
    }

    @Override
    protected List<StudClass> performUpdateTable() {
        List<StudClass> classes = repository.all();
        for (StudClass clazz : classes) {
            clazz.countStudents();
        }
        return classes;
    }

    @Override
    protected void performDelete() throws IOException, WindowedException {
        openWindow("FXML/deleteStudClass.fxml", "Удаление класса", new DeleteStudClassController(
                table.getSelectionModel().getSelectedItem(), repository
        ));
    }
}
