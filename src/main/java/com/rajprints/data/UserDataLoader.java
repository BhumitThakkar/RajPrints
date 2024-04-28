package com.rajprints.data;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rajprints.helper.Constants;
import com.rajprints.model.User;

@Component
public class UserDataLoader implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	private UserRepository userRepo;

//	Comment to use Plain Text Password
    private PasswordEncoder passwordEncoder;

//	Comment to use Plain Text Password
    @Autowired
    public UserDataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
//	Uncomment to use Plain Text Password
//    @Autowired
//    public UserDataLoader(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }

    @Override
    public void run(String... args) throws Exception {
    	User user = userRepo.findByUsername(Constants.adminUsername);
    	if (user == null) {
        	user = userRepo.findByEmail(Constants.adminEmail);
    	}
    	if (user == null) {
            user = new User(Constants.adminEmail, Constants.adminUsername, passwordEncoder.encode(Constants.adminPassword), Constants.adminFirstName, Constants.adminLastName, User.Role.ROLE_ADMIN);
//			Uncomment to use Plain Text Password
//          user = new User(Constants.adminEmail, Constants.adminUsername, "{noop}"+Constants.adminPassword, Constants.adminFirstName, Constants.adminLastName, User.Role.ROLE_ADMIN);
            userRepo.save(user);
    	} else {
    		logger.info("Not loading default admin user from UserDataLoader because it already exists in DB");
    	}
    }
}
