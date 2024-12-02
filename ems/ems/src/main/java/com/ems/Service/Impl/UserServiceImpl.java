package com.ems.Service.Impl;

import com.ems.Entity.User;
import com.ems.Payload.Logindto;
import com.ems.Payload.Userdto;
import com.ems.Repository.UserRepository;
import com.ems.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Override
    public User saveuser(Userdto userdto) {
        User user = new User();
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setEmail(userdto.getEmail());
        user.setUsername(userdto.getUsername());
        user.setPassword(BCrypt.hashpw(userdto.getPassword(),BCrypt.gensalt(10)));
        user.setUserRole(userdto.getUserRole());
        User save = userRepository.save(user);
        return save;
    }

    @Override
    public String verifylogin(Logindto logindto) {
        Optional<User> byUsername = userRepository.findByUsername(logindto.getUsername());
        if(byUsername.isPresent()){
            User user = byUsername.get();
            if(BCrypt.checkpw(logindto.getPassword(),user.getPassword())){
                return jwtService.generateJWTToken(user);
            }
        }

        return null;
    }
}
