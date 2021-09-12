package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a cloud storage application developed to store files, notes and credentials with login security and data persistence.
 * Sources used as reference are listed below:
 * 1. https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html
 * 2. https://www.w3schools.com/java/java_files.asp
 * 3. https://www.w3schools.com/howto/howto_css_modals.asp
 * 4. https://dzone.com/articles/automated-testing-with-junit-and-selenium-for-brow
 * 5. https://github.com/paul-reiners/super-duper-drive
 */


@SpringBootApplication
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}

}
