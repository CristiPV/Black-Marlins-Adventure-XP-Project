package com.blackmarlins.adventurexp.repository;

import com.blackmarlins.adventurexp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
