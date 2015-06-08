package pl.edu.pjwstk.s8132.web.controller;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.pjwstk.s8132.domain.user.UserAuthority;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.object.UserDetails;

import java.util.Locale;

@Controller
public class MainController {

    @RequestMapping("/")
    public String homeHandle(){
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String loginHandle(@RequestParam(value = "error", required = false) Integer error, ModelMap modelMap){
        modelMap.put("error", error != null);
        return (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) ? "home" : "redirect:/logged";
    }

    @RequestMapping("/security/error")
    public String securityErrorHandle(){
        return "error";
    }

    @RequestMapping("/logged")
    public String loggedHandle(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile userProfile = userDetails.getUserProfile();
        if(userProfile.getAuthority().equals(UserAuthority.ROLE_STUDENT)){
            return (userProfile.getGithubAccount()==null) ? "redirect:/connect/login" : "redirect:/student/home";
        }else if(userProfile.getAuthority().equals(UserAuthority.ROLE_INSTRUCTOR)){
            return (userProfile.getGithubAccount()==null) ? "redirect:/connect/login" : "redirect:/instructor/home";
        }else if(userProfile.getAuthority().equals(UserAuthority.ROLE_ADMIN)){
            return "redirect:/admin/home";
        }else{
            throw new RuntimeException("User has roles which are not supported");
        }
    }
}
