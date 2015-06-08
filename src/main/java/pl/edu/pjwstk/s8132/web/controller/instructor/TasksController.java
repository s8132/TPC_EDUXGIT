package pl.edu.pjwstk.s8132.web.controller.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.s8132.domain.subject.Subject;
import pl.edu.pjwstk.s8132.domain.task.StudentTask;
import pl.edu.pjwstk.s8132.domain.task.Task;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.subject.StudentSubjectDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.task.StudentTaskDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.task.TaskDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.user.UserProfileDao;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class TasksController {

    @Autowired private TaskDao taskDao;
    @Autowired private UserProfileDao userProfileDao;
    @Autowired private StudentSubjectDao studentSubjectDao;
    @Autowired private StudentTaskDao studentTaskDao;

    @RequestMapping(value = "/tasks/{subjectId}/subject", method = RequestMethod.GET)
    public List<Task> getTasks(@PathVariable("subjectId") Long subjectId){
        return taskDao.getBySubject(subjectId);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public Task getTask(@PathVariable("id") Long id) {
        return taskDao.getById(id);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ResponseEntity createSubject(@RequestBody Task task){
        taskDao.save(task);

        List<UserProfile> userProfiles = userProfileDao.getSavedSubject(task.getSubjectId());
        for(UserProfile profile: userProfiles){
            StudentTask studentTask = new StudentTask();
            studentTask.setStudentId(profile.getId());
            studentTask.setTaskId(task.getId());
            studentTask.setPoints(0.0F);
            studentTaskDao.save(studentTask);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    public ResponseEntity editSubject(@RequestBody Task task){
        taskDao.update(task);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubject(@PathVariable("id") Long id){
        taskDao.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
