package pl.edu.pjwstk.s8132.store.persistence.dao.subject;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.s8132.domain.subject.StudentSubject;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDaoImpl;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentSubjectDaoImpl extends CommonDaoImpl<StudentSubject> implements StudentSubjectDao{

    public StudentSubject getByStudentAndSubject(Long studentId, Long subjectId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("student", studentId);
        params.put("subject", subjectId);
        return getSqlSession().selectOne(prepareStatement("getByStudentAndSubject"), params);
    }

}
