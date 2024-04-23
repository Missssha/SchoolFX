package source.finalschoolfx.controllers.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import source.finalschoolfx.WindowedException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class BaseTableController<M, R> extends BaseController implements Initializable {
    @FXML
    protected TableView<M> table;
    @FXML
    protected Button deleteItem;
    @FXML
    protected Button editItem;
    @FXML
    protected Button addItem;

    protected R repository;

    protected final ObservableList<M> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        performInitialize();
        bootTable();
    }

    protected abstract void performInitialize();

    public void onAddItemClick() throws IOException {
        performAdd();
        updateTable();
    }
    @FXML
    public void onDeleteItemClick() throws IOException, WindowedException {
        if (table.getSelectionModel().getSelectedItem() == null)
            throw new WindowedException("Вы не выбрали ни одного элемента");
        performDelete();
        updateTable();
    }
    @FXML
    public void onEditItemClick() throws IOException, WindowedException {
        if (table.getSelectionModel().getSelectedItem() == null)
            throw new WindowedException("Вы не выбрали ни одного элемента");
        performEdit();
        updateTable();
    }

    protected void updateTable() {
        observableList.clear();
        List<M> data = performUpdateTable();
        observableList.addAll(data);
        table.setItems(observableList);
    }

    protected void bootTable() {
        initTable();
        updateTable();
    }

    abstract protected void initTable();

    abstract protected void performEdit() throws IOException;

    abstract public void performAdd() throws IOException;

    abstract protected List<M> performUpdateTable();

    abstract protected void performDelete() throws IOException, WindowedException, WindowedException;


}
