package com.stc.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.demo.model.domain.DocumentType;


@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
	 Optional<DocumentType> findByDocumentTypeName(String documentTypeName); 
	
}
