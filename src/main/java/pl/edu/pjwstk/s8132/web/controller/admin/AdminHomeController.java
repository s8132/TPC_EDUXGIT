package pl.edu.pjwstk.s8132.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @RequestMapping("/home")
    public String homeHandle(){
        return "admin/home2";
    }
}
