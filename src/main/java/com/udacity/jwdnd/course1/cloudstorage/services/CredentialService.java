package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

/**
 * Service bean for credential-related functions. Contains methods to fetch credentials belonging to a particular user,
 * add new credential, modify existing credentials, delete credentials and fetch particular credential by id.
 */

@Service
public class CredentialService {
    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
    }

    /** Method to fetch credentials for a particular user by user id **/
    public Credential[] getCredentialListings(Integer userId){
        return credentialMapper.getCredentialListings(userId);
    }

    /** Method to add new credential **/
    public void addCredential(String url, String username, String credentialUsername, String key, String password){
        Integer userId = userMapper.getUser(username).getUserId();
        Credential credential = new Credential(0,url,credentialUsername,password,key,userId);
        credentialMapper.insert(credential);
    }

    /** Method to fetch credentials by credential id **/
    public Credential getCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId);
    }

    /** Method to delete credentials by credential id **/
    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }

    /** Method to modify credentials by credential id **/
    public void updateCredential(Integer credentialId, String newUsername, String url, String key, String password) {
        credentialMapper.updateCredential(credentialId, newUsername, url, key, password);
    }


}
