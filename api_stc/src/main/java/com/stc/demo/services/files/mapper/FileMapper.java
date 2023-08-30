package com.stc.demo.services.files.mapper;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stc.demo.model.domain.Group;
import com.stc.demo.model.domain.Item;
import com.stc.demo.model.dto.ViewResponseDTO;
import com.stc.demo.repository.FilesRepository;
import com.stc.demo.repository.GroupRepository;

@Component
public class FileMapper {

	@Autowired FilesRepository   filesRepository;
	@Autowired GroupRepository   groupRepository;
	
	public ViewResponseDTO mapItemMetaDataToViewResponseDto(Item data) {
		ViewResponseDTO obj=new ViewResponseDTO();
		obj.setItemId(data.getItemId());
		obj.setDocumentType(data.getDocumentType().getDocumentTypeName());
		obj.setGroups(data.getItemGroups());
		obj.setItemName(data.getItemName());
		
//		if(data.getDocumentType().getDocumentTypeName().equals(FileTypes.FILE.val)) {
//			Optional<Files> row= filesRepository.findByItems(data);
//			if(row.isPresent()) {
//				obj.setBinaryFile(row.get().getFilesBinary());
//				obj.setSize(row.get().getSize());
//				obj.setExtension(row.get().getExtenstion());
//			}
//		}
		
		return obj;
	}
	
	public ViewResponseDTO mapViewNativeSqlQueryToViewResponseDto(Map<String,Object> data) {
		ViewResponseDTO obj=new ViewResponseDTO();
		obj.setItemId((int)data.get("item_id"));
		obj.setDocumentType(String.valueOf(data.get("document_type_name")));
		obj.setItemName(String.valueOf(data.get("item_name")));
		if(String.valueOf(data.get("groups")) !=null ) {
		  String[] listOfGroups=String.valueOf(data.get("groups")).split(",");
		  Set<Group> groups=new HashSet<>();
		  for(String str:listOfGroups) {
		  Optional<Group> gr=  groupRepository.findByGroupName(str);
			if(gr.isPresent()) {
				groups.add(gr.get());
			}
		  }   
		  obj.setGroups(groups);
		}
		
		return obj;
		}
 
}
