package source.finalschoolfx.controllers.studclass;

import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.controllers.base.BaseDeleteController;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.repository.StudClassRepository;

public class DeleteStudClassController extends BaseDeleteController<StudClass, StudClassRepository> {
    public DeleteStudClassController(StudClass entity, StudClassRepository repository) {
        super(entity, repository);
    }

    @Override
    protected void delete() {
        if (entity.getTeachers() != null) {
            entity.getTeachers().forEach(teacher -> teacher.getStudClasses().remove(entity));
            entity.getTeachers().clear();
        }
        if (entity.getListOfStudents() != null) {
            entity.getListOfStudents().forEach(student -> student.setStudClass(null));
            repository.delete(entity);
        }
    }
}
