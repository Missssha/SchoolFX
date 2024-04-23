package source.finalschoolfx.controllers.discipline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.controllers.base.BaseTableController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.repository.DisciplineRepository;

import java.io.IOException;
import java.util.List;

public class DisciplinesInfoController extends BaseTableController<Discipline, DisciplineRepository> {

    @FXML
    private Button back;

    @FXML
    private Button addDisciplineWindow;

    @FXML
    private Button deleteDisciplineWindow;

    @FXML
    private TableColumn<Discipline, String> disciplineNameColumn;

    @FXML
    private TableColumn<Discipline, String> idColumn;
    @FXML
    private Button editItem1;





    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }


    @Override
    protected void performInitialize() {
        repository = new DisciplineRepository(new Discipline());
    }

    @Override
    protected void initTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        disciplineNameColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineName"));
    }


    @Override
    protected void performEdit() throws IOException {
        openWindow("FXML/editDiscipline.fxml", "Редактирование предмета", new EditDisciplineController(
                table.getSelectionModel().getSelectedItem(), repository
        ));
    }

    @Override
    public void performAdd() throws IOException {
        openWindow("FXML/addDiscipline.fxml", "Добавление предмета", new AddDisciplineController(
                new Discipline(), repository
        ));
    }

    @Override
    protected List<Discipline> performUpdateTable() {
        return repository.all();    }

    @Override
    protected void performDelete() throws IOException, WindowedException {
        openWindow("FXML/deleteDiscipline.fxml", "Удаление предмета", new DeleteDisciplineController(
                table.getSelectionModel().getSelectedItem(), repository
        ));
    }
    public void showTeacherInDiscipline() throws IOException, WindowedException {
        if ((table.getSelectionModel().getSelectedItem()) == null)
            throw new WindowedException("Вы не выбрали ни одного элемента");
        openWindow("FXML/teachersInfo.fxml", "Информация о предметах", new TeachersInDisciplineController(
                table.getSelectionModel().getSelectedItem())
        );
    }
}
