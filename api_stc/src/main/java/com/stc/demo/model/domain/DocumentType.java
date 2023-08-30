package com.stc.demo.model.domain;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
@Entity
@Table()
public class DocumentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_type_id")
	private int documentTypeId ;
	@Column(name="document_type_name")
	private String documentTypeName;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documentType", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Item> items;

	
}
