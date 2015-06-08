package pl.edu.pjwstk.s8132.store.persistence.dao.user;

import org.springframework.stereotype.Repository;
import pl.edu.pjwstk.s8132.domain.user.UserAuthority;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.CommonDaoImpl;

import java.util.List;

@Repository
public class UserProfileDaoImpl extends CommonDaoImpl<UserProfile> implements UserProfileDao{

    public UserProfile getByEmail(String email) {
        return getSqlSession().selectOne(prepareStatement("getByEmail"), email);
    }

    public List<UserProfile> getAll() {
        return getSqlSession().selectList(prepareStatement("getAll"));
    }

    public List<UserProfile> getAllWithoutLogged(Long id) {
        return getSqlSession().selectList(prepareStatement("getAllWithoutLogged"), id);
    }

    public List<UserProfile> getByAuthority(UserAuthority authority) {
        return getSqlSession().selectList(prepareStatement("getByAuthority"), authority.toString());
    }

    public List<UserProfile> getSavedSubject(Long id) {
        return getSqlSession().selectList(prepareStatement("getSavedSubject"), id);
    }

    public List<UserProfile> getUnsavedSubject(Long id) {
        return getSqlSession().selectList(prepareStatement("getUnsavedSubject"), id);
    }

    public UserProfile getByGitHubAccount(String account) {
        return getSqlSession().selectOne(prepareStatement("getByGitHubAccount"), account);
    }
}
