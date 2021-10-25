package com.chainhaus.mtg.service;

import com.chainhaus.mtg.repository.MenuLinkRepository;
import com.chainhaus.mtg.repository.RoleRepository;
import com.chainhaus.mtg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Asad Sarwar on 14/06/2020.
 */
@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MenuLinkRepository menuLinkRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

}
