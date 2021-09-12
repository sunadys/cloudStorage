package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Service bean for user-related functions. Contains methods to check username availability,
 * create new users and fetch existing users. Uses methods from HashService for encoding passwords.
 */

@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    /** Method to check if given username is available and not already used **/
    public boolean isUsernameAvailable(String username){
        return userMapper.getUser(username) == null;
    }

    /** Method to create new user with encoded password **/
    public int createUser(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt); // generating random salt
        String encodedSalt = Base64.getEncoder().encodeToString(salt); // encoding salt
        String hashedPassword = hashService.getHashedValue(user.getPassword(),encodedSalt); //generating hashed password using encoded salt and given password
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    /** Method to fetch existing user using username **/
    public User getUser(String username){
        return userMapper.getUser(username);
    }
    /** Method to fetch existing user using user id **/
    public User getUser(Integer userId){
        return userMapper.getUserById(userId);
    }


}
