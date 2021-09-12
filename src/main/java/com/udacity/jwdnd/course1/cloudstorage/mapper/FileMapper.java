package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

/**
 * Interface to map File data object to the database. Consists of sql commands to create/access/modify FILES table in database.
 */

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    File getFile(String filename);

    /** To select file names for listing files of a particular user **/
    @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
    String[] getFileListingsForUser(Integer userId);

    /** To insert new file details for a particular user, primary key is auto-generated **/
    @Insert("INSERT INTO FILES (fileid, filename, contenttype, filesize, userid, filedata) VALUES (null, #{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    /** To delete files with the filename **/
    @Delete("DELETE FROM FILES WHERE filename = #{filename}")
    void deleteFile(String filename);


}
