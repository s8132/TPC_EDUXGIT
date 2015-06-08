package pl.edu.pjwstk.s8132.domain.subject;

import pl.edu.pjwstk.s8132.domain.Entity;

public class StudentSubject extends Entity{

    private Long studentId;
    private Long subjectId;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
