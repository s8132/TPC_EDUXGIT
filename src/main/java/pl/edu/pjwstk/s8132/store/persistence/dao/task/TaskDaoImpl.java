package pl.edu.pjwstk.s8132.store.persistence.dao.task;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.s8132.domain.task.Task;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDaoImpl;

import java.util.List;

@Repository
public class TaskDaoImpl extends CommonDaoImpl<Task> implements TaskDao{

    public List<Task> getBySubject(Long subjectId) {
        return getSqlSession().selectList(prepareStatement("getBySubject"), subjectId);
    }
}
