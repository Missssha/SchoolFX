package source.finalschoolfx.controllers.teacher;

import source.finalschoolfx.controllers.base.BaseDeleteController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;

public class DeleteDisciplineInTeacherController extends BaseDeleteController<Discipline, DisciplineRepository> {
    private final Teacher teacher;

    public DeleteDisciplineInTeacherController(Discipline entity, DisciplineRepository repository, Teacher teacher) {
        super(entity, repository);
         this.teacher = teacher;
    }

    @Override
    protected void delete() {
        teacher.getDisciplines().remove(entity);
        entity.getTeachers().remove(teacher);
        repository.update(entity);
    }
}
