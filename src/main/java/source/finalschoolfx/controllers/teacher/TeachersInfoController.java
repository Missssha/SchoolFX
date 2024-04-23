    package source.finalschoolfx.controllers.teacher;

    import java.io.IOException;
    import java.net.URL;
    import java.util.List;
    import java.util.ResourceBundle;

    import javafx.fxml.FXML;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextField;
    import javafx.stage.Stage;
    import source.finalschoolfx.HelloApplication;
    import source.finalschoolfx.WindowedException;
    import source.finalschoolfx.controllers.base.BaseTableController;
    import source.finalschoolfx.controllers.studclass.StudClassesInfoController;
    import source.finalschoolfx.controllers.studclass.StudentsInStudClassController;
    import source.finalschoolfx.controllers.student.AddStudentController;
    import source.finalschoolfx.controllers.student.DeleteStudentController;
    import source.finalschoolfx.controllers.student.EditStudentController;
    import source.finalschoolfx.models.Student;
    import source.finalschoolfx.models.Teacher;
    import javafx.scene.control.*;
    import source.finalschoolfx.repository.StudentRepository;
    import source.finalschoolfx.repository.TeacherRepository;


    public class TeachersInfoController extends BaseTableController<Teacher, TeacherRepository> {

        @FXML
        private ResourceBundle resources;
        @FXML
        private URL location;
        @FXML
        private TableColumn<Teacher, String> ageColumn;
        @FXML
        private Button deleteItem;
        @FXML
        private TableColumn<Teacher, String> fullnameColumn;
        @FXML
        private TableColumn<Teacher, Integer> idColumn;
        @FXML
        private TableColumn<Teacher, String> phoneNumberColumn;
        @FXML
        private TableColumn<Teacher, String> workPeriodColumn;
        @FXML
        private TableView<Teacher> table;
        @FXML
        private TextField textField;
        @FXML
        private Button addItem;
        @FXML
        private TextField age_field;
        @FXML
        private Button back;
        @FXML
        private TextField disciplineList_field;
        @FXML
        private TextField fullname_field;
        @FXML
        private TextField phone_number_field;
        @FXML
        private TextField studClassList_field;
        @FXML
        private TextField work_period_field;
        @FXML
        private ComboBox<String> comboBoxDisc;
        @FXML
        private ComboBox<String> comboBoxClass;
        @FXML
        private Button deleteTeacherButton;





        @FXML
        void back() {
            Stage stage = (Stage) back.getScene().getWindow();
            stage.close();
        }


        @Override
        protected void performInitialize() {
            repository = new TeacherRepository(new Teacher());
        }

        @Override
        protected void initTable() {
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            workPeriodColumn.setCellValueFactory(new PropertyValueFactory<>("workPeriod"));

        }

        @Override
        protected void performEdit() throws IOException {
            openWindow("FXML/editTeacher.fxml", "Редактирование учителя", new EditTeacherController(
                    table.getSelectionModel().getSelectedItem(), repository
            ));
        }

        @Override
        public void performAdd() throws IOException {
            openWindow("FXML/addTeacher.fxml", "Добавление учителя", new AddTeacherController(
                    new Teacher(), repository
            ));
        }

        @Override
        protected List<Teacher> performUpdateTable() {
            return repository.all();
        }

        @Override
        protected void performDelete() throws IOException, WindowedException {
            openWindow("FXML/deleteTeacher.fxml", "Подтверждение", new DeleteTeacherController(
                    table.getSelectionModel().getSelectedItem(), repository
            ));
        }
        public void showDisciplinesInTeacher() throws IOException, WindowedException {
            if ((table.getSelectionModel().getSelectedItem()) == null)
                throw new WindowedException("Вы не выбрали ни одного элемента");
            openWindow("FXML/disciplinesInfo.fxml", "Информация о предметах", new DisciplinesInTeacherController(
                    table.getSelectionModel().getSelectedItem())
            );
        }
        public void showStudClassesInTeacher() throws IOException, WindowedException {
            if ((table.getSelectionModel().getSelectedItem()) == null)
                throw new WindowedException("Вы не выбрали ни одного элемента");
            openWindow("FXML/studClassesInfo.fxml", "Информация о классах", new StudClassInTeacherController(
                    table.getSelectionModel().getSelectedItem())
            );
        }


    //    @FXML
    //    void saveXML() throws JAXBException, IOException, DocumentException {
    //
    //        String fileName = "teachers.xml";
    //        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin");
    //        EntityManager em = emf.createEntityManager();
    //        List<Student> list = em.createQuery("SELECT g FROM Teacher g WHERE id >= 0").getResultList();
    //        List<TransformedStudent> transformedStudentList = new ArrayList<>();
    //
    //        for (int i = 0; i < list.size(); i++) {
    //            TransformedStudent transformedStudent = new TransformedStudent(list.get(i));
    //            //transformedStudentList.getTransformedStudentList().add(transformedStudent);
    //            transformedStudentList.add(transformedStudent);
    //        }
    //
    //        JAXBContext jaxbContext = JAXBContext.newInstance(List.class);
    //        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    //        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    //        jaxbMarshaller.marshal(transformedStudentList, new File(fileName));
    //
    //    }
    }
