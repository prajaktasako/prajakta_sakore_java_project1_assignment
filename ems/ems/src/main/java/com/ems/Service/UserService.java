package com.ems.Service;

import com.ems.Entity.User;
import com.ems.Payload.Logindto;
import com.ems.Payload.Userdto;

public interface UserService {
    public User saveuser(Userdto userdto);

    public String verifylogin(Logindto logindto);
}
