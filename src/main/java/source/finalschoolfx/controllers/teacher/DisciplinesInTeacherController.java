    package source.finalschoolfx.controllers.teacher;

    import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import source.finalschoolfx.controllers.discipline.DisciplinesInfoController;
    import source.finalschoolfx.controllers.studclass.AddStudClassController;
    import source.finalschoolfx.models.Discipline;
    import source.finalschoolfx.models.StudClass;
    import source.finalschoolfx.models.Student;
    import source.finalschoolfx.models.Teacher;
    import source.finalschoolfx.repository.DisciplineRepository;
    import source.finalschoolfx.repository.StudentRepository;
    import source.finalschoolfx.transformed.TransformedStudent;

    import java.io.IOException;
    import java.net.URL;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.ResourceBundle;

    public class DisciplinesInTeacherController extends DisciplinesInfoController {

        public Teacher teacher;
        @FXML
        private Button editItem1;

        private final DisciplineRepository disciplineRepository = new DisciplineRepository(new Discipline());
        public DisciplinesInTeacherController(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            editItem.setVisible(false);
            editItem1.setVisible(false);
            repository = new DisciplineRepository(new Discipline());
            bootTable();
        }
        @Override
        protected List<Discipline> performUpdateTable() {
            List<Discipline> disciplines = new ArrayList<>();
            disciplineRepository.all().forEach(discipline -> {
                if(discipline.getTeachers() != null && discipline.getTeachers().contains(teacher))
                    disciplines.add(discipline);
            });
            return disciplines;
        }

        @Override
        public void performAdd() throws IOException {
            openWindow("FXML/addDisciplineInTeacher.fxml", "Добавление", new AddDisciplineInTeacherController(
                    new Discipline(), repository, teacher
            ));
        }

        @Override
        public void performDelete() throws IOException {
            openWindow("FXML/deleteDiscipline.fxml", "Удаление", new DeleteDisciplineInTeacherController(
                    table.getSelectionModel().getSelectedItem(), repository, teacher
            ));
        }

    }
