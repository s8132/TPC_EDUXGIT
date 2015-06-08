package pl.edu.pjwstk.s8132.store.persistence.dao.task;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.s8132.domain.task.StudentTask;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentTaskDaoImpl extends CommonDaoImpl<StudentTask> implements StudentTaskDao{

    public List<StudentTask> getByTask(Long id) {
        return getSqlSession().selectList(prepareStatement("getByTask"), id);
    }

    public List<StudentTask> getByStudent(Long id) {
        return getSqlSession().selectList(prepareStatement("getByStudent"), id);
    }

    public StudentTask getByTaskAndStudent(Long taskId, UserProfile student) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("task", taskId);
        params.put("student", student.getId());
        return getSqlSession().selectOne(prepareStatement("getByTaskAndStudent"), params);
    }

    public StudentTask getByRepo(String repo) {
        return getSqlSession().selectOne(prepareStatement("getByRepository"), repo);
    }
}
