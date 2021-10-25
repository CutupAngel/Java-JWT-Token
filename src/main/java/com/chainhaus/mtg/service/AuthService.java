package com.chainhaus.mtg.service;

import com.chainhaus.mtg.configuration.TokenProvider;
import com.chainhaus.mtg.entity.MenuLink;
import com.chainhaus.mtg.entity.Role;
import com.chainhaus.mtg.entity.User;
import com.chainhaus.mtg.model.AuthToken;
import com.chainhaus.mtg.model.CustomUserDetails;
import com.chainhaus.mtg.model.UserModel;
import com.chainhaus.mtg.repository.RoleRepository;
import com.chainhaus.mtg.repository.UserRepository;
import com.chainhaus.mtg.util.AuthUtil;
import com.chainhaus.mtg.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Asad Sarwar on 11/06/2020.
 */
@Service
public class AuthService{

    private static final Logger logger = LoggerFactory.getLogger("AuthService");

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public AuthToken authenticate(UserModel userModel) throws AuthenticationException{

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userModel.getUserName(),
                            userModel.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            return new AuthToken(token, ((CustomUserDetails) authentication.getPrincipal()).getUserId());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public UserModel getUserDetails(){

        UserModel userDetails = new UserModel();
        User user = userRepository.findByUserName(AuthUtil.getLoggedInUserName());

        userDetails.setUserName(user.getUserName());
        userDetails.setFirstName(user.getFirstName());
        userDetails.setLastName(user.getLastName());
        userDetails.setEmail(user.getEmail());


        List<Long> addedMenu = new LinkedList<>();
        for(Role role : user.getRoles()){
            userDetails.addRole(role.getTitle());
            for(MenuLink menuLink : role.getMenuLinks().stream().sorted(Comparator.comparingLong(MenuLink::getId)).collect(Collectors.toList()) ){
                if (!addedMenu.contains(menuLink.getId())) {
                    userDetails.adddMenuLink(menuLink.getId(), menuLink.getParentMenu() != null ? menuLink.getParentMenu().getId() : null, menuLink.getLable(), "", menuLink.getRouterLink(), menuLink.getIconType(), menuLink.getIconName(), menuLink.getToggle(), menuLink.getVisible());
                }
                addedMenu.add(menuLink.getId());
            }
        }


        return userDetails;
    }

    public boolean register(UserModel userModel){
        try{
            Long count = userRepository.count();
            User user = new User();

            user.setUserName(userModel.getUserName());
            user.setPassword(bcryptEncoder.encode(userModel.getPassword()));
            //dummy values
            user.setEmail(userModel.getUserName().concat("@mtg.com"));
            user.setFirstName("User ".concat(count+""));
            user.setLastName("User - ".concat(count+""));
            user.setActive(Boolean.TRUE);
            user.setCreatedBy(1l);
            user.setCreatedDate(Util.getCurrentTimeStamp());

            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByTitle("USER"));

            user.setRoles(roles);

            userRepository.save(user);

            return true;
        }catch (Exception e){
            logger.error("Unable to save user: " + e.getMessage(), e);
            return false;
        }

    }


}
