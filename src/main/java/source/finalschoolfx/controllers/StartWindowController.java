package source.finalschoolfx.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.controllers.base.BaseController;
import source.finalschoolfx.controllers.discipline.DisciplinesInfoController;
import source.finalschoolfx.controllers.studclass.StudClassesInfoController;
import source.finalschoolfx.controllers.student.StudentsInfoController;
import source.finalschoolfx.controllers.teacher.TeachersInfoController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartWindowController extends BaseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button listOfClasses;

    @FXML
    private Button listOfDisciplines;

    @FXML
    private Button listOfStudents;

    @FXML
    private Button listOfTeachers;



    @FXML
    public void showTeachersInfo() throws IOException {
        openWindow("FXML/teachersInfo.fxml", "Список учителей", new TeachersInfoController());
    }
    @FXML
    public void showStudentsInfo() throws IOException {
        openWindow("FXML/studentsInfo.fxml", "Список студентов", new StudentsInfoController());
    }

    @FXML
    public void showDisciplinesInfo() throws IOException {
        openWindow("FXML/disciplinesInfo.fxml", "Список предметов", new DisciplinesInfoController());
    }

    @FXML
    public void showStudClassesInfo() throws IOException {
        openWindow("FXML/studClassesInfo.fxml", "Список предметов", new StudClassesInfoController());
    }
}
