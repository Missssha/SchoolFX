package source.finalschoolfx.controllers.student;

import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.controllers.base.BaseDeleteController;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.repository.StudentRepository;

public class DeleteStudentController extends BaseDeleteController<Student, StudentRepository> {

    public DeleteStudentController(Student entity, StudentRepository repository) {
        super(entity, repository);
    }

    @Override
    protected void delete() {
        if (entity.getStudClass() != null) {
            entity.getStudClass().getListOfStudents().remove(entity);
            entity.setStudClass(null);
        }
        repository.delete(entity);
    }

}
