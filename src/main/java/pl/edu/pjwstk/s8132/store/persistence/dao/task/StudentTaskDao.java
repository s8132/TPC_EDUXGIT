package pl.edu.pjwstk.s8132.store.persistence.dao.task;

import pl.edu.pjwstk.s8132.domain.task.StudentTask;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDao;

import java.util.List;

public interface StudentTaskDao extends CommonDao<StudentTask>{
    List<StudentTask> getByTask(Long id);
    List<StudentTask> getByStudent(Long id);
    StudentTask getByTaskAndStudent(Long taskId, UserProfile student);
    StudentTask getByRepo(String repo);
}
