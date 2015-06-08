package pl.edu.pjwstk.s8132.web.controller.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instructor")
public class StudentsController {

    @Autowired private StudentTaskDao studentTaskDao;
    @Autowired private UserProfileDao userProfileDao;
    @Autowired private NotificationService notificationService;
    @Autowired private TaskDao taskDao;
    @Autowired private SubjectDao subjectDao;

    @RequestMapping(value = "/students/{taskId}/task", method = RequestMethod.GET)
    public List<StudentJSON> getStudentsByTask(@PathVariable("taskId") Long taskId){
        List<StudentJSON> studentJSONs = new ArrayList<StudentJSON>();

        for(StudentTask studentTask: studentTaskDao.getByTask(taskId)){
            studentJSONs.add(getStudentJson(studentTask));
        }

        return studentJSONs;
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public StudentJSON getStudent(@PathVariable("id") Long id){
        return getStudentJson(studentTaskDao.getById(id));
    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public ResponseEntity updateStudent(@RequestBody StudentJSON student){
        StudentTask studentTask = studentTaskDao.getById(student.getId());
        studentTask.setPoints(student.getPoints());
        studentTaskDao.update(studentTask);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile instructor = userDetails.getUserProfile();

        Task task = taskDao.getById(studentTask.getTaskId());
        Subject subject = subjectDao.getById(task.getSubjectId());
        UserProfile studentProfile = userProfileDao.getById(studentTask.getStudentId());

        notificationService.markEvent(instructor, studentProfile, subject.getName(), task.getName());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private StudentJSON getStudentJson(StudentTask studentTask){
        UserProfile userProfile = userProfileDao.getById(studentTask.getStudentId());

        StudentJSON studentJSON = new StudentJSON();
        studentJSON.setId(studentTask.getId());
        studentJSON.setFirstName(userProfile.getFirstName());
        studentJSON.setLastName(userProfile.getLastName());
        studentJSON.setEmail(userProfile.getEmail());
        studentJSON.setGithubAccount(userProfile.getGithubAccount());
        studentJSON.setPoints(studentTask.getPoints());
        studentJSON.setRepository(studentTask.getRepository());
        return studentJSON;
    }
}
