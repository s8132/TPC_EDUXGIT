package pl.edu.pjwstk.s8132.web.controller.student;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.s8132.domain.subject.Subject;
import pl.edu.pjwstk.s8132.domain.task.StudentTask;
import pl.edu.pjwstk.s8132.domain.task.Task;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.object.UserDetails;
import pl.edu.pjwstk.s8132.service.NotificationService;
import pl.edu.pjwstk.s8132.store.persistence.dao.subject.SubjectDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.task.StudentTaskDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.task.TaskDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.user.UserProfileDao;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentSubjectTaskController {

    @Autowired private SubjectDao subjectDao;
    @Autowired private TaskDao taskDao;
    @Autowired private StudentTaskDao studentTaskDao;
    @Autowired private UserProfileDao userProfileDao;
    @Autowired private NotificationService notificationService;

    @RequestMapping(value = "/subjects", method = RequestMethod.GET, produces = "application/json")
    public List getSubjects(){
        return subjectDao.getStudentSubjects(getLoggedUser());
    }

    @RequestMapping(value = "/subjects/{subjectId}/tasks", method = RequestMethod.GET, produces = "application/json")
    public List getTasks(@PathVariable("subjectId") Long subjectId){
        return taskDao.getBySubject(subjectId);
    }

    @RequestMapping(value = "/{taskId}/tasks", method = RequestMethod.GET, produces = "application/json")
    public Object getTask(@PathVariable("taskId") Long taskId){
        Task task = taskDao.getById(taskId);
        StudentTask studentTask = studentTaskDao.getByTaskAndStudent(taskId, getLoggedUser());
        return getTaskJSON(task, studentTask);
    }

    @RequestMapping(value = "/{taskId}/tasks", method = RequestMethod.PUT)
    public ResponseEntity updateRepository(@PathVariable("taskId") Long taskId, @RequestBody TaskJSON taskJSON){
        StudentTask studentTask = studentTaskDao.getByTaskAndStudent(taskId, getLoggedUser());
        studentTask.setRepository(taskJSON.getRepository());
        studentTaskDao.update(studentTask);

        UserProfile student = userProfileDao.getById(studentTask.getStudentId());
        Task task = taskDao.getById(studentTask.getTaskId());
        Subject subject = subjectDao.getById(task.getSubjectId());
        UserProfile instructor = userProfileDao.getById(subject.getTeacherId());


        notificationService.setRepoEvent(student, instructor, subject.getName(), task.getName());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private TaskJSON getTaskJSON(Task task, StudentTask studentTask){
        TaskJSON taskJSON = new TaskJSON();
        taskJSON.setTaskId(task.getId());
        taskJSON.setName(task.getName());
        taskJSON.setDescription(task.getDescription());
        taskJSON.setStartDate(task.getStartDate());
        taskJSON.setStopDate(task.getStopDate());
        taskJSON.setMaxPoints(task.getMaxPoints());
        taskJSON.setStudentTaskId(studentTask.getId());
        taskJSON.setRepository(studentTask.getRepository());
        taskJSON.setPoints(studentTask.getPoints());
        taskJSON.setStudentId(studentTask.getStudentId());
        taskJSON.setGithubAccount(getLoggedUser().getGithubAccount());

        return taskJSON;
    }

    private UserProfile getLoggedUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUserProfile();
    }
}
