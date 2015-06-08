package pl.edu.pjwstk.s8132.store.persistence.dao.user;

import pl.edu.pjwstk.s8132.domain.user.UserAuthority;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDao;

import java.util.List;

public interface UserProfileDao extends CommonDao<UserProfile>{
    UserProfile getByEmail(String email);
    List<UserProfile> getAll();
    List<UserProfile> getAllWithoutLogged(Long id);
    List<UserProfile> getByAuthority(UserAuthority authority);
    List<UserProfile> getSavedSubject(Long id);
    List<UserProfile> getUnsavedSubject(Long id);
    UserProfile getByGitHubAccount(String account);
}
