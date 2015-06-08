package pl.edu.pjwstk.s8132.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.object.WebSocketMessage;

import java.util.concurrent.Future;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired private SimpMessagingTemplate template;
    @Value("${google.api.key}") String googleKey;

    public void markEvent(UserProfile fromUser, UserProfile toUser, String subjectName, String taskName) {
        final String title = "Wystawiono ocenê dla " + taskName;
        final String message = "Twoja ocena zosta³a zmieniona dla zadania: " + taskName + " w przedmiocie: " + subjectName;

        sendToWebSocket(toUser.getEmail(), new WebSocketMessage(title, message));
        sendToGCM(toUser.getRid(), title, message);
    }

    public void setRepoEvent(UserProfile fromUser, UserProfile toUser, String subjectName, String taskName) {
        final String title = "U¿ytkownik " + fromUser.getEmail() + " ustawi³ repozytorium";
        final String message = "U¿ytkownik " + fromUser.getEmail() + " przypisa³ repozytorium do zadania " + taskName + " w przedmiocie: " + subjectName;

        sendToWebSocket(toUser.getEmail(), new WebSocketMessage(title, message));
        sendToGCM(toUser.getRid(), title, message);
    }


    private void sendToWebSocket(String user, WebSocketMessage message){
        template.convertAndSendToUser(user, "/notification", message);
    }

    private void sendToGCM(String rid, String title, String body){
        Future<HttpResponse<JsonNode>> future = Unirest.post("https://android.googleapis.com/gcm/send")
                .header("Authorization", "key=" + googleKey + "")
                .header("Content-Type", "application/json")
                .body("{\"data\":{\"title\": \"" + title + "\", \"body\": \"" + body + "\"}, \"registration_ids\": [ \"" + rid + "\"]}")
                .asJsonAsync(new Callback<JsonNode>() {
                    public void completed(HttpResponse<JsonNode> httpResponse) {

                    }

                    public void failed(UnirestException e) {

                    }

                    public void cancelled() {

                    }
                });
    }
}
