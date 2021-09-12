package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Service bean for file-related functions. Contains methods to fetch filenames belonging to a particular user,
 * add new file, delete files and fetch particular file by id.
 */

@Service
public class FilesService {
    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FilesService(FileMapper fileMapper,UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    /** Method to fetch filenames for a particular user by user id **/
    public String[] getFileListings(Integer userId){
        return fileMapper.getFileListingsForUser(userId);
    }

    /** Method to add new file to the database **/
    public void addFile(MultipartFile multipartFile, String username) throws IOException {
        InputStream fis = multipartFile.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        // Reading nRead number of bytes from the fis input stream and storing it in a buffer output stream
        while ((nRead = fis.read(data, 0, data.length)) != -1){
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] fileData = buffer.toByteArray(); // file data in a byte array
        String filename = multipartFile.getOriginalFilename(); // fetching filename
        String contentType  = multipartFile.getContentType(); // fetching file content type
        String fileSize = String.valueOf(multipartFile.getSize()); // fetching file size
        Integer userId = userMapper.getUser(username).getUserId();
        File file = new File(0, filename, contentType, fileSize, userId, fileData);
        fileMapper.insert(file); // creating new file
    }

    /** Method to fetch files by filename **/
    public File getFile(String filename){
         return fileMapper.getFile(filename);
    }

    /** Method to delete files by filename **/
    public void deleteFile(String filename){
        fileMapper.deleteFile(filename);
    }

}
