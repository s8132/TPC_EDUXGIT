package pl.edu.pjwstk.s8132.store.persistence.dao.subject;

import pl.edu.pjwstk.s8132.domain.subject.Subject;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDao;

import java.util.List;

public interface SubjectDao extends CommonDao<Subject>{

    List<Subject> getAll();
    List<Subject> getInstructorSubjects(UserProfile instructor);
    List<Subject> getStudentSubjects(UserProfile student);
}
