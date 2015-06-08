package pl.edu.pjwstk.s8132.store.persistence.dao.task;

import pl.edu.pjwstk.s8132.domain.task.Task;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDao;

import java.util.List;

public interface TaskDao extends CommonDao<Task>{
    List<Task> getBySubject(Long subjectId);
}
