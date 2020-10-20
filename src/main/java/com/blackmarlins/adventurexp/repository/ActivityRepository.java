package com.blackmarlins.adventurexp.repository;

import com.blackmarlins.adventurexp.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}