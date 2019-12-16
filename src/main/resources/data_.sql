 INSERT INTO "USER" VALUES
(114,'ADMIN', 'boy-7.png', 'uuu@uuu.pl', 'uuu', '{bcrypt}$2a$10$I0uWuhWjubbkUMRlfi.SA.zis2SKMl5CZmxQ9APT3W2evp7TLLwgq', 'uuu'),
(203,'ADMIN', 'default.png', '1eee@interia.pl', '1eee', '{bcrypt}$2a$10$9TYp3C5y1nt1uKil8lL53e2jm.MN0HwM8MpiipWErGMF26lBIX73m', '1eee');

INSERT INTO "PROJECT" ("ID","DESCRIPTION",
    "PROJECT_NAME" ,
    "USER_ID") VALUES
(1,'make drawings', 'documentation', 114);



INSERT INTO "SPRINT" ( "ID","FINISH_DATE",
    "PLANED_STORY_POINTS",
    "START_DATE") VALUES
(3,'2019-11-08', 11, '2019-11-01');

INSERT INTO "TASK" ("ID", "DESCRIPTION",
    "NAME",
    "PROGRESS",
    "STORY_POINTS",
    "WEIGHT",
    "PROJECT_ID",
    "SPRINT_ID",
    "USER_ID") VALUES
(5,'make upper structure', 'upper structure', 0, 2, 3, 1, 3, 203); 

INSERT INTO "PRODUCT_FEATURE" ("ID","NAME","DESCRIPTION","IMAGE_PATH") VALUES
(11,'5-th wheel heigth','distance from ground to king pin plate','default.png');

INSERT INTO "PRODUCT_FEATURE" ("ID","NAME","DESCRIPTION","IMAGE_PATH") VALUES
(12,'Axle','producer of the axle','default.png');

INSERT INTO "PRODUCT_FEATURE" ("ID","NAME","DESCRIPTION","IMAGE_PATH") VALUES
(13,'Body Type','','default.png');

INSERT INTO "FEATURE" ("ID","NAME","DESCRIPTION","PRICE","IMAGE_PATH") VALUES
(5,'1250','5-th wheel height',55.1,'default.png');

INSERT INTO "FEATURE" ("ID","NAME","DESCRIPTION","PRICE","IMAGE_PATH") VALUES
(14,'VAN','body type',355.1,'default.png');

INSERT INTO "FEATURE" ("ID","NAME","DESCRIPTION","PRICE","IMAGE_PATH") VALUES
(12,'BPW Drum','axles producer',255.1,'default.png');

INSERT INTO "FEATURE" ("ID","NAME","DESCRIPTION","PRICE","IMAGE_PATH") VALUES
(13,'SAF Drum','axles producer',255.1,'default.png');
INSERT INTO "FEATURE" ("ID","NAME","DESCRIPTION","PRICE","IMAGE_PATH") VALUES
(15,'Jost Disk','axles producer',255.1,'default.png');

