package com.testProjects.dbRelations.repository;

import com.testProjects.dbRelations.models.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepo extends JpaRepository<Mission, Long> {

    List<Mission>findAllByUserId(Long userId);
}
