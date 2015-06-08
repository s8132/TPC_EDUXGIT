package pl.edu.pjwstk.s8132.store.persistence.dao.subject;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.s8132.domain.subject.Subject;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDaoImpl;

import java.util.List;

@Repository
public class SubjectDaoImpl extends CommonDaoImpl<Subject> implements SubjectDao{

    public List<Subject> getAll() {
        return getSqlSession().selectList(prepareStatement("getAll"));
    }

    public List<Subject> getInstructorSubjects(UserProfile instructor) {
        return getSqlSession().selectList(prepareStatement("getInstructorSubjects"), instructor.getId());
    }

    public List<Subject> getStudentSubjects(UserProfile student) {
        return getSqlSession().selectList(prepareStatement("getStudentSubjects"), student.getId());
    }
}
