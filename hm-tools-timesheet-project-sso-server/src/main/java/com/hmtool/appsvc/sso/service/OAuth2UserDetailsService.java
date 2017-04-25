package com.hmtool.appsvc.sso.service;

import com.hmtool.appsvc.sso.DTO.UserDTO;
import com.hmtool.appsvc.sso.domain.User;
import com.hmtool.appsvc.sso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OAuth2UserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserDTO currentUser;

    public OAuth2UserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserCode(username);
        /*This step might or might not be needed depends on the strategy used to imeplement OAuth2 (Clients equal users or not)
        * In case of clients equals just clients literally, pass this along until it get to appUI. And use that as your context
        * credentials info
        * */
        currentUser.setUsername(username);
        return new org.springframework.security.core.userdetails.User(user.get().getUserCode(), user.get().getPassword(), new ArrayList<>());
    }
}
