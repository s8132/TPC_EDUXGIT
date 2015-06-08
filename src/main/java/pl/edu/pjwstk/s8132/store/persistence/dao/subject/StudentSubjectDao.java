package pl.edu.pjwstk.s8132.store.persistence.dao.subject;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.s8132.domain.subject.StudentSubject;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDao;

import java.util.List;

public interface StudentSubjectDao extends CommonDao<StudentSubject>{
    StudentSubject getByStudentAndSubject(Long studentId, Long subjectId);
}
