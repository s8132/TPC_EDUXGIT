package pl.edu.pjwstk.s8132.domain.task;

import pl.edu.pjwstk.s8132.domain.Entity;

public class StudentTask extends Entity{

    private String repository;
    private Float points;
    private Long studentId;
    private Long taskId;

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
