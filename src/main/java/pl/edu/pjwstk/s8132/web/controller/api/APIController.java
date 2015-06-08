package pl.edu.pjwstk.s8132.web.controller.api;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.s8132.domain.subject.SubjectStatus;
import pl.edu.pjwstk.s8132.store.persistence.dao.subject.SubjectDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.task.TaskDao;
import pl.edu.pjwstk.s8132.web.controller.api.json.SubjectJSON;
import pl.edu.pjwstk.s8132.web.controller.api.json.TaskJSON;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired private SubjectDao subjectDao;
    @Autowired private TaskDao taskDao;
    @Autowired private MapperFacade mapper;

    @ApiOperation(value = "Get all subjects", responseContainer = "List", response = SubjectJSON.class, notes = "Returns all subjects")
    @RequestMapping(value = "/subject", method = RequestMethod.GET)
    public List getAllSubjects(){
        List<SubjectJSON> subjectJSONList = mapper.mapAsList(subjectDao.getAll(), SubjectJSON.class);

        for(SubjectJSON subjectJSON: subjectJSONList){
            if(subjectJSON.getStatus().equals(SubjectStatus.ACTIVE)){
                subjectJSON.add(linkTo(methodOn(APIController.class).updateSubject(subjectJSON)).withRel("edit"));
            }else{
                subjectJSON.add(linkTo(methodOn(APIController.class).deleteSubject(subjectJSON.getSubjectId())).withRel("tasks"));
            }
            subjectJSON.add(linkTo(methodOn(APIController.class).getAllTask(subjectJSON.getSubjectId())).withRel("tasks"));
        }

        return subjectJSONList;
    }

    @ApiOperation(value = "Get tasks by subject id", responseContainer = "List", response = TaskJSON.class, notes = "Return all tasks in subject")
    @RequestMapping(value = "/{subjectId}/task", method = RequestMethod.GET, produces = "application/json")
    public List getAllTask(@ApiParam(value = "ID of subject", required = true, name = "subjectId", allowMultiple = false) @PathVariable("subjectId") Long subjectId){
        return mapper.mapAsList(taskDao.getBySubject(subjectId), TaskJSON.class);
    }

    @RequestMapping(value = "/subject", method = RequestMethod.POST)
    public ResponseEntity createSubject(@RequestBody SubjectJSON subjectJSON){
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subject", method = RequestMethod.PUT)
    public ResponseEntity updateSubject(@RequestBody SubjectJSON subjectJSON){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubject(@PathVariable("subjectId") Long subjectId){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/task", method = RequestMethod.PUT)
    public ResponseEntity updateTask(@RequestBody TaskJSON taskJSON){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/task/{taskId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTask(@PathVariable("taskId") Long taskId){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
