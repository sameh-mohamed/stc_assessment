package com.stc.demo.services.files.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stc.demo.config.exception.enums.FileErrorMessages;
import com.stc.demo.model.domain.DocumentType;
import com.stc.demo.model.domain.Files;
import com.stc.demo.model.domain.Group;
import com.stc.demo.model.domain.Item;
import com.stc.demo.model.dto.ViewResponseDTO;
import com.stc.demo.model.enums.FileTypes;
import com.stc.demo.repository.DocumentTypeRepository;
import com.stc.demo.repository.FilesRepository;
import com.stc.demo.repository.GroupRepository;
import com.stc.demo.repository.ItemRepository;
import com.stc.demo.services.files.FileService;
import com.stc.demo.services.files.mapper.FileMapper;
import com.stc.demo.util.FileOperations;
import com.stc.demo.util.UtilImp;


@Service
public class FileServiceImp implements FileService{

	@Autowired FilesRepository filesrepository;
	@Autowired ItemRepository itemRepository;
	@Autowired DocumentTypeRepository  documentTypeRepository;
	@Autowired GroupRepository  groupRepository;
	@Autowired UtilImp   utilImp;
	@Autowired FileMapper fileMapper;
	

	// Api for create space and i depend on create one space 
	@Override
	public Boolean createSpace(Set<Group> groupName) {
		Item obj=new Item();
		Optional<DocumentType> ot=documentTypeRepository.findByDocumentTypeName(FileTypes.SPACE.val.toString());
		if(ot.isPresent()) {
			Long cnt=itemRepository.countByDocumentType(ot.get());
			obj.setItemName("Space"+(cnt+1)+"");
			obj.setCreationDate(utilImp.getNowDateUTC());
			if(groupName !=null && groupName.size() >0) {
			 obj.setItemGroups(groupName);
			}
			obj.setDocumentType(ot.get());
			obj.setParent(null);
			itemRepository.save(obj);
			return true;
		}else {
			throw new com.stc.demo.config.exception.CustomRunTimeException(FileErrorMessages.File_Not_Found.name(),FileErrorMessages.File_Not_Found.message);
		}
		
	}

	// Api for create any Folder and will be renamed based on it's order creation 
	// you can create any folder under space or under any other folder only 
	@Override
	public Boolean createFolder(Item parent) {
		Item obj=new Item();
		if(parent.getDocumentType().getDocumentTypeName().equals(FileTypes.FILE.val)==false) {
			Optional<DocumentType> ot=documentTypeRepository.findByDocumentTypeName(FileTypes.FOLDER.val.toString());
			if(ot.isPresent()) {
				Long cnt=itemRepository.countByDocumentType(ot.get());
				obj.setItemName("Folder-"+(cnt+1)+"");
			    obj.setCreationDate(utilImp.getNowDateUTC());	
				obj.setItemGroups(parent.getItemGroups());	
				obj.setDocumentType(ot.get());
				obj.setParent(parent);
				itemRepository.save(obj);
				return true;
			}else {
				throw new com.stc.demo.config.exception.CustomRunTimeException(FileErrorMessages.Type_Not_Found.name(),FileErrorMessages.Type_Not_Found.message);
			}
		}else {
			throw new com.stc.demo.config.exception.CustomRunTimeException(FileErrorMessages.Parent_Folder_Not_Found.name(),FileErrorMessages.Parent_Folder_Not_Found.message);
		}
		
		
	}

	// api to create file under space Direct or define folder to add this file under it
	// restricted parent must be space or folder only	
	@Override
	public Boolean uploadFile(MultipartFile file, Item parent) throws IOException {
		Item obj=new Item();	
		if(parent.getDocumentType().getDocumentTypeName().equals(FileTypes.FILE.val)==false) {
			Optional<DocumentType> ot=documentTypeRepository.findByDocumentTypeName(FileTypes.FILE.val.toString());
	        if(ot.isPresent()) {
	        	obj.setItemName(file.getOriginalFilename());
	        	obj.setCreationDate(utilImp.getNowDateUTC());
	        	obj.setDocumentType(ot.get());
	        	obj.setParent(parent);
	        	obj.setItemGroups(parent.getItemGroups());
	        	Item savedFile= itemRepository.save(obj);
	        	if(savedFile !=null) { 		
	        		Files fs=new Files();
	        		fs.setItems(savedFile);
	        		fs.setFilesBinary( FileOperations.compressFile(file.getBytes()) );
	        		fs.setSize(file.getSize());
	        		fs.setExtenstion(file.getContentType());
	        		filesrepository.save(fs);	
	        		return true;
	            	} else {
	        			throw new com.stc.demo.config.exception.CustomRunTimeException(FileErrorMessages.Error_In_Save_File.name(),FileErrorMessages.Error_In_Save_File.message);
	            	}	
	         }
		}else {
			throw new com.stc.demo.config.exception.CustomRunTimeException(FileErrorMessages.Parent_Folder_Not_Found.name(),FileErrorMessages.Parent_Folder_Not_Found.message);
		}		
		return true;
	}
	
	// View nested files or folder under parent, For ex .
	// may be parent is folder1 once click it will appear   foler 2  , newbook.pdf
	@Override
	public List<ViewResponseDTO> viewDataByJPA(Integer itemId ){
		List<ViewResponseDTO> response=new ArrayList<>();
		if(itemId != null) {
		Optional<Item> parentData=	itemRepository.findById(itemId);
		if(parentData.isPresent()) {
		response= itemRepository.items(parentData.get().getItemId()).stream().map(
		item -> fileMapper.mapItemMetaDataToViewResponseDto(item)).collect(Collectors.toList());
		}
		}
		return response;
	}

	// Download File 
	@Override
	public byte[] downloadFile(Item itemId) {
		if(itemId.getDocumentType().getDocumentTypeName().equals(FileTypes.FILE.val))
		{
			Optional<Files> row= filesrepository.findByItems(itemId);
			if(row.isPresent()) {
			byte[] reqFile=  FileOperations.decompressFile(row.get().getFilesBinary());
			return reqFile;
			}else {
				return null;
			}
		}else {
			return null;
		}	
	}

	
// *****************************************
// *****************************************
// ***********Extra************************* 
	
	
	// Api to view meta files  using Native Sql  Query 
	@Override
	public List<ViewResponseDTO> viewDataByNativeSqlQuery(Integer itemId) {
		List<ViewResponseDTO> response=new ArrayList<>();
		List<Map<String,Object>> getNativeQueryResult=itemRepository.itemsByNativeSQL(itemId);
		if(getNativeQueryResult !=null) {
			response=getNativeQueryResult.stream().map( item -> fileMapper.mapViewNativeSqlQueryToViewResponseDto(item)).collect(Collectors.toList());	
		}
		return response;
	}

	

	// Api to view meta files  using grapghql 
	@Override
	public List<ViewResponseDTO> viewDataByGraphql(Integer itemId) {
		return null;
	}
		
}
