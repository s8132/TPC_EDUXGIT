package pl.edu.pjwstk.s8132.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.edu.pjwstk.s8132.domain.user.UserProfile;
import pl.edu.pjwstk.s8132.store.persistence.dao.user.UserProfileDao;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired private UserProfileDao userProfileDao;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileDao.getByEmail(email);
        if(userProfile==null){
            throw new UsernameNotFoundException("User email: " + email + " not found");
        }

        pl.edu.pjwstk.s8132.object.UserDetails userDetails = new pl.edu.pjwstk.s8132.object.UserDetails();
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setEnabled(userProfile.getEnabled());
        userDetails.setPassword(userProfile.getPassword());
        userDetails.setPasswordExpired(false);
        userDetails.setUsername(userProfile.getEmail());
        userDetails.setUserProfile(userProfile);
        userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(userProfile.getAuthority().toString()));

        return userDetails;
    }
}
