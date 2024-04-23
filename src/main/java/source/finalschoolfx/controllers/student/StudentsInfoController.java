package source.finalschoolfx.controllers.student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import source.finalschoolfx.WindowedException;
import source.finalschoolfx.XMLPro;
import source.finalschoolfx.controllers.base.BaseTableController;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.repository.StudentRepository;
import source.finalschoolfx.transformed.TransformedStudent;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class StudentsInfoController extends BaseTableController<TransformedStudent, StudentRepository> {

    public Button saveStudentsTable;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addStudentWindow;

    @FXML
    private TableColumn<TransformedStudent, Integer> ageColumn;

    @FXML
    private Button back;
    @FXML
    private Button deleteItem;

    @FXML
    private Button deleteStudentWindow;

    @FXML
    private TableColumn<TransformedStudent, String> fullnameColumn;

    @FXML
    private TableColumn<TransformedStudent, Integer> idColumn;

    @FXML
    private TableColumn<TransformedStudent, String> phoneNumberColumn;

    @FXML
    private Button redactStudentWindow;

    @FXML
    private TableColumn<TransformedStudent, String> studClassColumn;
    @FXML
    private TableColumn<TransformedStudent, String> ratingColumn;

    @FXML
    private TableView<TransformedStudent> table;
    private File myfile = null;
    private Window mainStage;


    @FXML
    void back() {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }


    @FXML
    void showAddStudent() throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(HelloApplication.class.getResource("FXML/addStudent.fxml"));
//        AnchorPane page = loader.load();
//        Stage dialogStage = new Stage();
//        dialogStage.setTitle("Добавление ученика");
//        Scene scene = new Scene(page);
//        dialogStage.setScene(scene);
//        dialogStage.setMinHeight(600);
//        dialogStage.setMinWidth(800);
//        dialogStage.showAndWait();
//        updateTable(new StudentDAO().all(Student.class));
    }


    @FXML
    void saveXML() throws IOException, JAXBException, JRException {

        String fileName = "students.xml";
        List<Student> list = repository.all();
        List<TransformedStudent> transformedStudentList = new ArrayList<>();

        list.forEach(student -> {
            transformedStudentList.add(new TransformedStudent(student));
        });

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLPro.class, TransformedStudent.class);
        XMLPro xmlPro = new XMLPro();
        xmlPro.setStudentList(transformedStudentList);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(xmlPro, writer);


        String resultpath = "StudentsInSchool.pdf";
        String datasource = "students.xml";
        String template = "FinalFinalFinalReport.jrxml";
        String xpath = "/students/student";

        JRDataSource ds = new JRXmlDataSource(datasource, xpath);
        JasperReport jasperReport = JasperCompileManager.compileReport(template);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), ds);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, resultpath);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.exportReport();


//        myfile = new File("students.xml");
//        if (myfile == null) {
//            DirectoryChooser Directorychooser = new DirectoryChooser();
//            Directorychooser.setTitle("Выберите место хранения файла!");
//            File selecteddir = Directorychooser.showDialog(mainStage);
//            if (selecteddir != null) {
//                List<File> lf = Arrays.asList(Objects.requireNonNull(selecteddir.listFiles()));
//                Pattern pat = Pattern.compile("SXPM" + ".*" + ".xml");
//                int num = 0;
//                for (File file : lf) {
//                    Matcher fname = pat.matcher(file.getName());
//                    if (((Matcher) fname).find()) num = num + 1;
//                }
//                File supfile = new File(selecteddir + "\\SXPM" + (num + 1) + ".xml");
//                try {
//                    JAXBContext context = JAXBContext
//                            .newInstance(XMLPro.class);
//                    Marshaller m = context.createMarshaller();
//                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//                    List<Student> list = HelloApplication.getEntityManagerDAO().all(Student.class);
//                    List<TransformedStudent> transformedStudentList = new ArrayList<>();
//
//                    for (int i = 0; i < list.size(); i++) {
//                        TransformedStudent transformedStudent = new TransformedStudent(list.get(i));
//                        transformedStudentList.add(transformedStudent);
//                    }
////                    XMLPro wrapper = new XMLPro();
////                    wrapper.setStudentList(students);
//
//                    m.marshal(transformedStudentList, supfile);
//
//                    myfile = supfile;
//                } catch (Exception ex) {
//                    }
//                }
//            }
//            else{
//                try {
//                    JAXBContext context = JAXBContext
//                            .newInstance(XMLPro.class);
//                    Marshaller m = context.createMarshaller();
//                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
////                    XMLPro wrapper = new XMLPro();
////                    wrapper.setStudentList(students);
//                    List<Student> list = HelloApplication.getEntityManagerDAO().all(Student.class);
//                    List<TransformedStudent> transformedStudentList = new ArrayList<>();
//
//                    for (int i = 0; i < list.size(); i++) {
//                        TransformedStudent transformedStudent = new TransformedStudent(list.get(i));
//                        transformedStudentList.add(transformedStudent);
//                    }
//
//                    m.marshal(transformedStudentList, myfile);
//                } catch (Exception ex) {
//                }
//
//        }

    }


    @Override
    protected void performInitialize() {

        repository = new StudentRepository(new Student());
        try {
            saveXML();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void initTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        studClassColumn.setCellValueFactory(new PropertyValueFactory<>("studClass"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
    }

    @Override
    protected void performEdit() throws IOException {
        openWindow("FXML/editStudent.fxml", "Редактирование ученика", new EditStudentController(
                table.getSelectionModel().getSelectedItem().getStudent(), repository
        ));
    }

    @Override
    public void performAdd() throws IOException {
        openWindow("FXML/addStudent.fxml", "Добавление ученика", new AddStudentController(
                new Student(), repository
        ));
    }

    @Override
    protected List<TransformedStudent> performUpdateTable() {
        List<TransformedStudent> transformedStudents = new ArrayList<>();
        for (Student student : repository.all()) transformedStudents.add(new TransformedStudent(student));
        return transformedStudents;
    }

    @Override
    protected void performDelete() throws IOException, WindowedException {
        openWindow("FXML/deleteStudent.fxml", "Подтверждение", new DeleteStudentController(
                table.getSelectionModel().getSelectedItem().getStudent(), repository
        ));
    }
}
