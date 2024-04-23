package source.finalschoolfx.controllers.discipline;

import source.finalschoolfx.HelloApplication;
import source.finalschoolfx.controllers.base.BaseDeleteController;
import source.finalschoolfx.models.Discipline;
import source.finalschoolfx.models.Teacher;
import source.finalschoolfx.repository.DisciplineRepository;
import source.finalschoolfx.repository.TeacherRepository;

public class DeleteDisciplineController extends BaseDeleteController<Discipline, DisciplineRepository> {
    public DeleteDisciplineController(Discipline entity, DisciplineRepository repository) {
        super(entity, repository);
    }

    @Override
    protected void delete() {
        if (entity.getTeachers() != null) {
            entity.getTeachers().forEach(teacher -> teacher.getDisciplines().remove(entity));
            entity.getTeachers().clear();
        }
        repository.delete(entity);
    }
}
