package pl.edu.pjwstk.s8132.service;

import pl.edu.pjwstk.s8132.domain.user.UserProfile;

public interface NotificationService {

    void markEvent(UserProfile fromUser, UserProfile toUser, String subjectName, String taskName);
    void setRepoEvent(UserProfile fromUser, UserProfile toUser, String subjectName, String taskName);

}
