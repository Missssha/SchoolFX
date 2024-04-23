package source.finalschoolfx.controllers.teacher;

import source.finalschoolfx.controllers.base.BaseDeleteController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.StudClass;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;
import source.finalschoolfx.repository.StudClassRepository;

public class DeleteStudClassInTeacherController extends BaseDeleteController<StudClass, StudClassRepository> {

    private final Teacher teacher;
    public DeleteStudClassInTeacherController(StudClass entity, StudClassRepository repository, Teacher teacher) {
        super(entity, repository);
        this.teacher = teacher;
    }

    @Override
    protected void delete() {
        teacher.getStudClasses().remove(entity);
        entity.getTeachers().remove(teacher);
        repository.update(entity);
    }
}
