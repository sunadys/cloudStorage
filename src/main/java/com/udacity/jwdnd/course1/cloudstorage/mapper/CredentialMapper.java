package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

/**
 * Interface to map Credential data object to the database. Consists of sql commands to create/access/modify NOTES table in database.
 */

@Mapper
public interface CredentialMapper {

    /** To select credentials for a particular user **/
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    Credential[] getCredentialListings(Integer userId);

    /** To insert new credential details for a particular user, primary key is auto-generated. **/
    @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid) VALUES (null, #{siteUrl}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    /** To select credential using the credential id **/
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredential(Integer credentialId);

    /** To delete credentials using the credential id **/
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredential(Integer credentialId);

    /** To modify existing credentials using credential id **/
    @Update("UPDATE CREDENTIALS SET url = #{url}, key = #{key}, password = #{password}, username = #{newUsername} WHERE credentialid = #{credentialId} ")
    void updateCredential(Integer credentialId, String newUsername, String url, String key, String password);
    
}
