package pl.edu.pjwstk.s8132.web.controller;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.pjwstk.s8132.domain.user.UserAuthority;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.object.UserDetails;
import pl.edu.pjwstk.s8132.store.persistence.dao.user.UserProfileDao;

import java.util.UUID;

@Controller
@RequestMapping("/connect")
public class ConnectAccountController {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.scope}")
    private String scope;

    @Autowired private UserProfileDao userProfileDao;

    @RequestMapping("/login")
    public String githubLoginHandle(ModelMap modelMap){
        modelMap.put("clientId", clientId);
        modelMap.put("scope", scope);
        modelMap.put("state", UUID.randomUUID().toString());

        return "connect/login";
    }

    @RequestMapping("/token")
    public String githubTokenHandle(@RequestParam(value = "code") String code, RedirectAttributes redirectAttributes) throws UnirestException{
        JsonNode node = Unirest.post("https://github.com/login/oauth/access_token").header("accept", "application/json").
                field("client_id", clientId).field("client_secret", clientSecret).field("code", code).asJson().getBody();

        String accessToken = node.getObject().getString("access_token");
        System.out.println("\n access_token: " + accessToken);

        redirectAttributes.addAttribute("access_token", accessToken);
        return "redirect:/connect/user";
    }


    @RequestMapping("/user")
    public String userHandle(@RequestParam("access_token") String accessToken) throws UnirestException {
        JsonNode userNode = Unirest.get("https://api.github.com/user").header("accept", "application/json").
                queryString("access_token", accessToken).asJson().getBody();

        String login = userNode.getObject().getString("login");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile userProfile = userDetails.getUserProfile();
        userProfile.setGithubAccount(login);
        userProfileDao.update(userProfile);

        if(userProfile.getAuthority().equals(UserAuthority.ROLE_STUDENT)){
            return "redirect:/student/home";
        }else if(userProfile.getAuthority().equals(UserAuthority.ROLE_INSTRUCTOR)){
            return "redirect:/instructor/home";
        }else{
            throw new RuntimeException("User has roles which are not supported");
        }
    }
}
