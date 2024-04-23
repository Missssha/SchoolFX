package source.finalschoolfx.controllers.studclass;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.student.StudentsInfoController;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.repository.StudentRepository;
import source.finalschoolfx.transformed.TransformedStudent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentsInStudClassController extends StudentsInfoController {
    @FXML
    private Button editItem1;
    @FXML
    private Button saveStudentsTable;
    @FXML
    private Button addItem;
    @FXML
    private Button deleteItem;

    private StudClass studClass;
    private final StudentRepository studentRepository = new StudentRepository(new Student());

    public StudentsInStudClassController(StudClass studClass) {
        this.studClass = studClass;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addItem.setVisible(false);
        deleteItem.setVisible(false);
        editItem.setVisible(false);
        saveStudentsTable.setVisible(false);
        bootTable();
    }

//    @Override
//    protected void performDelete() throws IOException, WindowedException {
//        Student studentx = table.getSelectionModel().getSelectedItem().getCargo();
//        openWindow("common/confirm-delete-view.fxml", "Подтвердить", new DeleteCargoFromRackController(cargo, cargoRepository));
//    }


    @Override
    protected List<TransformedStudent> performUpdateTable() {
        List<TransformedStudent> transformedStudents = new ArrayList<>();
        studentRepository.all().forEach(student -> {
            if(student.getStudClass() != null && student.getStudClass().equals(studClass))
                transformedStudents.add(new TransformedStudent(student));
        });
        return transformedStudents;
    }
}
