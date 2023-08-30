package com.stc.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.demo.model.domain.Files;
import com.stc.demo.model.domain.Item;

@Repository
public interface FilesRepository extends JpaRepository<Files, Integer>{
    
	  Optional<Files> findByItems(Item item);
}
