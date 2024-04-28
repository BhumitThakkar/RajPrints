package com.rajprints.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajprints.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String adminemail);
}
