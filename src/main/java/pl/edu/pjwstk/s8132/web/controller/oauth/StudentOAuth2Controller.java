package pl.edu.pjwstk.s8132.web.controller.oauth;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.object.UserDetails;
import pl.edu.pjwstk.s8132.store.persistence.dao.subject.SubjectDao;
import pl.edu.pjwstk.s8132.store.persistence.dao.user.UserProfileDao;

import java.util.List;

@RestController
@RequestMapping("/resources/student")
public class StudentOAuth2Controller {

    @Autowired private SubjectDao subjectDao;
    @Autowired private UserProfileDao userProfileDao;

    @RequestMapping(value = "/subject", method = RequestMethod.GET, produces = "application/json")
    public List getSubjects(Principal principal){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile userProfile = userDetails.getUserProfile();

        return subjectDao.getStudentSubjects(userProfile);
    }

    @RequestMapping(value = "/save/rid/{rid}", method = RequestMethod.PUT)
    public ResponseEntity saveRid(@PathVariable("rid") String rid){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile userProfile = userDetails.getUserProfile();
        userProfile.setRid(rid);

        userProfileDao.update(userProfile);

        return new ResponseEntity(HttpStatus.OK);
    }
}
