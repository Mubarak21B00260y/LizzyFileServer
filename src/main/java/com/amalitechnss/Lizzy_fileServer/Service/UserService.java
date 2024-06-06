package com.amalitechnss.Lizzy_fileServer.Service;

import com.amalitechnss.Lizzy_fileServer.Entity.User;
import com.amalitechnss.Lizzy_fileServer.Model.UserDTO;

import java.io.IOException;

public interface UserService {


    User RegisterUser(UserDTO userDTO) throws IOException;


    void SaveVerificationTokensForUser( String token,User user);
}
