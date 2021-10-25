package com.chainhaus.mtg.service;


import com.chainhaus.mtg.entity.Role;
import com.chainhaus.mtg.entity.User;
import com.chainhaus.mtg.exception.exception.FieldValueException;
import com.chainhaus.mtg.model.SelectModel;
import com.chainhaus.mtg.model.UserModel;
import com.chainhaus.mtg.repository.MenuLinkRepository;
import com.chainhaus.mtg.repository.RoleRepository;
import com.chainhaus.mtg.repository.UserRepository;
import com.chainhaus.mtg.util.AuthUtil;
import com.chainhaus.mtg.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuLinkRepository menuLinkRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(UserModel userModel){

        if(userRepository.findByUserName(userModel.getUserName()) != null){
            throw new FieldValueException(FieldValueException.DUPLICATE_VALUE,"User Name");
        }

        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setUserName(userModel.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));

        Set<Role> userRoles = new HashSet<>();

        for(String role : userModel.getRoles()){
            Role userRole = roleRepository.findByTitle(role);
            if(userRole == null){
                throw new FieldValueException(FieldValueException.INVALID_VALUE, "Role not found");
            }
            userRoles.add(userRole);
        }

        user.setRoles(userRoles);
        user.setActive(Boolean.TRUE);
        user.setCreatedDate(Util.getCurrentTimeStamp());
        user.setCreatedBy(AuthUtil.getLoggedInUserId());

        userRepository.save(user);

        return user;
    }

    public List<UserModel> getAllUsers() {

        List<UserModel> list = new LinkedList<>();

        for (User user: userRepository.findAll()) {

            UserModel userDetails = new UserModel();

            userDetails.setId(user.getId());
            userDetails.setUserName(user.getUserName());
            userDetails.setFirstName(user.getFirstName());
            userDetails.setLastName(user.getLastName());
            userDetails.setEmail(user.getEmail());
            userDetails.setActive(user.getActive());

            for(Role role : user.getRoles()){
                userDetails.addRole(role.getTitle());
            }

            list.add(userDetails);
        }
        return list;
    }

    public boolean deactivateUser(long user_id){
        User user = userRepository.findById(user_id).get();
        user.setActive(!user.getActive());
        userRepository.save(user);
        if (user.getActive()) {
            return true;
        } else {
            return false;
        }
    }

    public List<SelectModel> getUserRoles() {
        List<SelectModel> selectList = new LinkedList<>();
        for (Role role : roleRepository.findAll()) {
            selectList.add(new SelectModel(role.getId(), role.getTitle()));
        }
        return selectList;
    }

}
