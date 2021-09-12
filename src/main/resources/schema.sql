CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(25),
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(25),
  lastname VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS FILES (
   fileid INT PRIMARY KEY auto_increment,
   filename VARCHAR,
   contenttype VARCHAR,
   filesize VARCHAR,
   userid INT,
   filedata BLOB,
   foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(25),
    notedescription VARCHAR (500),
    userid INT,
    foreign key (userid) references USERS(userid)
);


CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(200),
    username VARCHAR (50),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    foreign key (userid) references USERS(userid)
);