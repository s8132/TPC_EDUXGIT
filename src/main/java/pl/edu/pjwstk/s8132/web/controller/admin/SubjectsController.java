package pl.edu.pjwstk.s8132.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.s8132.domain.subject.StudentSubject;
import pl.edu.pjwstk.s8132.domain.subject.Subject;
import pl.edu.pjwstk.s8132.store.persistence.dao.subject.StudentSubjectDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.subject.SubjectDao;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class SubjectsController {

    @Autowired private SubjectDao subjectDao;
    @Autowired private StudentSubjectDao studentSubjectDao;

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public List getSubjects(){
        return subjectDao.getAll();
    }

    @RequestMapping(value = "/subjects/{id}", method = RequestMethod.GET)
    public Subject getSubject(@PathVariable("id") Long id){
        return subjectDao.getById(id);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveSubject(@RequestBody Subject subject){
        subjectDao.save(subject);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity updateSubject(@RequestBody Subject subject){
        subjectDao.update(subject);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subjects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubject(@PathVariable("id") Long id){
        subjectDao.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subjects/{id}/save/users", method = RequestMethod.POST)
    public ResponseEntity saveUsers(@PathVariable("id") Long id, @RequestBody Long[] users){
        for(Long user: users){
            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setStudentId(user);
            studentSubject.setSubjectId(id);

            studentSubjectDao.save(studentSubject);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subjects/{id}/lose/users", method = RequestMethod.POST)
    public ResponseEntity loseUsers(@PathVariable("id") Long id, @RequestBody Long[] users){
        for(Long user: users){
            StudentSubject studentSubject = studentSubjectDao.getByStudentAndSubject(user, id);
            studentSubjectDao.delete(studentSubject);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
