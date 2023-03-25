INSERT INTO ROLES VALUES(1,'ROLE_ADMIN');
INSERT INTO ROLES VALUES(2,'ROLE_SECRETARY');
INSERT INTO ROLES VALUES(3,'ROLE_PATIENT');
INSERT INTO ROLES VALUES(4,'ROLE_DOCTOR');

INSERT INTO USERS VALUES(1,'root@gmail.com','$2a$12$UQBpJuYn9nhq/MDdWBgw.OmcTrcaDhve03L0Tf89iemyxoZRrByIy','root');
INSERT INTO USERS VALUES(2,'user1@gmail.com','$2a$12$UQBpJuYn9nhq/MDdWBgw.OmcTrcaDhve03L0Tf89iemyxoZRrByIy','user1');
INSERT INTO USERS VALUES(3,'user2@gmail.com','$2a$12$UQBpJuYn9nhq/MDdWBgw.OmcTrcaDhve03L0Tf89iemyxoZRrByIy','user2');
INSERT INTO USERS VALUES(4,'user3@gmail.com','$2a$12$UQBpJuYn9nhq/MDdWBgw.OmcTrcaDhve03L0Tf89iemyxoZRrByIy','user3');

INSERT INTO user_roles values(1,1);
INSERT INTO user_roles values(2,2);
INSERT INTO user_roles values(3,4);
INSERT INTO user_roles values(4,3);