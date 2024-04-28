-- the file runs if application.properties file have spring.jpa.hibernate.ddl-auto=create
-- For Dynamic Values you can follow UserDataLoader.java in com.rajprints.data package
-- https://stackoverflow.com/a/65272395 - HOW TO INSERT PLAIN PASSWORD FOR BASIC AUTH - if your plaintext password is abc123ABC you will have to store it in database as {noop}abc123ABC

-- INSERT INTO `rajprints_db`.`user` (`account_non_expired`, `account_non_locked`, `credentials_non_expired`, `enabled`, `id`, `email`, `first_name`, `last_name`, `password`, `username`) VALUES (b'1', b'1', b'1', b'1', '1', 'demo@gmail.com', 'Demo', 'User', '{noop}abc123ABC', 'user1');
-- INSERT INTO `rajprints_db`.`user_roles` (`User_id`, `Roles`) VALUES (1, 'ROLE_ADMIN');
