package com.chainhaus.mtg.service;

import com.chainhaus.mtg.entity.User;
import com.chainhaus.mtg.model.CustomUserDetails;
import com.chainhaus.mtg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Asad Sarwar on 17/06/2020.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
        return new CustomUserDetails(user.getId(), user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getActive(), getAuthority(user));

    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getTitle()));
        });
        return authorities;
    }

    //temp
    public void encryptPasswords() throws Exception{
        for(User user : userRepository.findAll()){
            if(user.getPassword().length() < 60){
                user.setPassword(bcryptEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }
        }
    }
}
