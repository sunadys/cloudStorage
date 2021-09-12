package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

/**
 * Interface to map Note data object to the database. Consists of sql commands to create/access/modify NOTES table in database.
 */


@Mapper
public interface NoteMapper {

    /** To select notes for a particular user **/
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    Note[] getNoteListingForUser(Integer userId);

    /** To insert new note details for a particular user, primary key is auto-generated **/
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTit},#{noteDesc},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    /** To select a note using note id **/
    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNote(Integer noteId);

    /** To delete a note using note id **/
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);

    /** To modify note using the note id **/
    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
    void updateNote(Integer noteId, String title, String description);

}
