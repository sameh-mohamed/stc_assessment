package com.stc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.demo.model.domain.PersmissionEntity;

@Repository
public interface PermissionRepository  extends JpaRepository<PersmissionEntity, Integer> {

}
