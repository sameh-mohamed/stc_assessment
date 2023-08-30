package com.stc.demo.model.dto;

import java.util.Set;

import com.stc.demo.model.domain.Group;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewResponseDTO {
   
	private Integer  itemId;
	private String   itemName;
	private String   DocumentType;
	private Set<Group> groups;
	private byte[] binaryFile;
	private Long size;
	private String extension;
	
}
