package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * Interface to map User data object to the database. Consists of sql commands to create/access USERS table in database.
 */


@Mapper
public interface UserMapper {

    /** To select user by using username **/
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    /** To select user by using userid **/
    @Select("SELECT * FROM USERS WHERE userid = #{userid}")
    User getUserById(Integer userid);

    /** To insert new user details into USERS table, primary key is auto-generated **/
    @Insert("INSERT INTO USERS (userId, username, salt, password, firstname, lastname) VALUES (null, #{username}, #{salt}, #{password}, #{firstName}, #{lastName} )")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);


}
