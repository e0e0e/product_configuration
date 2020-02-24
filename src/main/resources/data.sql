;
 Delete  FROM USER where username like 'g.szkop';
 Delete  FROM USER where EMAIL like 'g.szkop@wielton.com.pl';

 INSERT INTO USER(ID, AUTHORITIES, AVATAR, EMAIL, LOGIN, PASSWORD, USERNAME) VALUES (1000, 'ADMIN', 'boy-4.png', 'g.szkop@wielton.com.pl', 'g.szkop', '{bcrypt}$2a$10$1EWn3y1xVQDED4zEfH3xeerftZXSAww9571dU1ny54caaH4gTcMtm', 'gszkop');
