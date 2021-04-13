package com.relationtest.dbRelation.repository;

import com.relationtest.dbRelation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRpository extends JpaRepository<User, Long> {
}
