package ch.zli.mm.athlete.service;




import ch.zli.mm.athlete.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @version 09.12.2020
 */

@Service
public class UserServiceSecurity implements UserDetailsService {

    @Autowired
    private DBManager DBMANAGER;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User searchUser = DBMANAGER.findUser(username);
        if(searchUser == null) {
            throw new UsernameNotFoundException("User was not found");
        }

        return new org.springframework.security.core.userdetails.User(searchUser.getEmail(), searchUser.getPassword(), new ArrayList<>());
    }
}
