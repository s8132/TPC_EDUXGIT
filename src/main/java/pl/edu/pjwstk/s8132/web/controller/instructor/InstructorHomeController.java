package pl.edu.pjwstk.s8132.web.controller.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.pjwstk.s8132.domain.subject.Subject;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.object.UserDetails;
import pl.edu.pjwstk.s8132.store.persistence.dao.subject.SubjectDao;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorHomeController {

    @Autowired private SubjectDao subjectDao;

    @RequestMapping("/home")
    public String homeHandle(){
        return "instructor/home2";
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public @ResponseBody List<Subject> getSubjects(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile userProfile = userDetails.getUserProfile();
        return subjectDao.getInstructorSubjects(userProfile);
    }
}
