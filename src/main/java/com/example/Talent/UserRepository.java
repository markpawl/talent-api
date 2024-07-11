package com.example.Talent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	  // This method will automatically be translated to a JPQL query
	  User findByUsername(String username);
}
