package com.stc.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.demo.model.domain.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>{
    
	Optional<Group> findByGroupName(String groupName);
}
