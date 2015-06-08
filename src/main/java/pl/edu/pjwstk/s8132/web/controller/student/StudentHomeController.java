package pl.edu.pjwstk.s8132.web.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentHomeController {

    @RequestMapping("/home")
    public String homeHandle(){
        return "student/home2";
    }
}
