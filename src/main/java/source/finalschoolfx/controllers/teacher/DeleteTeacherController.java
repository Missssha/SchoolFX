package source.finalschoolfx.controllers.teacher;

import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.controllers.base.BaseDeleteController;
import source.finalschoolfx.models.Student;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.StudentRepository;
import source.finalschoolfx.repository.TeacherRepository;

public class DeleteTeacherController extends BaseDeleteController<Teacher, TeacherRepository> {
    public DeleteTeacherController(Teacher entity, TeacherRepository repository) {
        super(entity, repository);
    }

    @Override
    protected void delete() {
        if (entity.getDisciplines() != null) {
            entity.getDisciplines().forEach(discipline -> discipline.getTeachers().remove(entity));
            entity.getDisciplines().clear();
        }
        if (entity.getStudClasses() != null) {
            entity.getStudClasses().forEach(studClass -> studClass.getTeachers().remove(entity));
            entity.getStudClasses().clear();
        }
        repository.delete(entity);
    }
}
