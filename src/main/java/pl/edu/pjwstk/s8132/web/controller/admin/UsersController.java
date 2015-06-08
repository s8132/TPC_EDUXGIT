package pl.edu.pjwstk.s8132.web.controller.admin;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.s8132.domain.user.UserAuthority;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.object.UserDetails;
import pl.edu.pjwstk.s8132.store.persistence.dao.user.UserProfileDao;


import java.util.List;

@RestController
@RequestMapping("/admin")
public class UsersController {

    @Autowired private UserProfileDao userProfileDao;
    @Autowired private MapperFacade mapper;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserProfileJSON> getAllUsers(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile userProfile = userDetails.getUserProfile();
        return mapper.mapAsList(userProfileDao.getAllWithoutLogged(userProfile.getId()), UserProfileJSON.class);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity createUser(@RequestBody UserProfileJSON userProfileJSON){
        UserProfile userProfile = mapper.map(userProfileJSON, UserProfile.class);
        userProfileDao.save(userProfile);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity updateUser(@RequestBody UserProfileJSON userProfileJSON){
        UserProfile userProfile = mapper.map(userProfileJSON, UserProfile.class);
        userProfileDao.update(userProfile);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userProfileDao.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public List<UserProfileJSON> getTeachers(){
        return mapper.mapAsList(userProfileDao.getByAuthority(UserAuthority.ROLE_INSTRUCTOR), UserProfileJSON.class);
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public List<UserProfileJSON> getStudents(){
        return mapper.mapAsList(userProfileDao.getByAuthority(UserAuthority.ROLE_STUDENT), UserProfileJSON.class);
    }

    @RequestMapping(value = "/students/{subjectId}/saved/subject", method = RequestMethod.GET)
    public List<UserProfileJSON> getSavedUser(@PathVariable("subjectId") Long subjectId){
        return mapper.mapAsList(userProfileDao.getSavedSubject(subjectId), UserProfileJSON.class);
    }

    @RequestMapping(value = "/students/{subjectId}/unsaved/subject", method = RequestMethod.GET)
    public List<UserProfileJSON> getUnsavedUser(@PathVariable("subjectId") Long subjectId){
        return mapper.mapAsList(userProfileDao.getUnsavedSubject(subjectId), UserProfileJSON.class);
    }
}
