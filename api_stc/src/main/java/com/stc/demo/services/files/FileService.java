package com.stc.demo.services.files;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stc.demo.model.domain.Group;
import com.stc.demo.model.domain.Item;
import com.stc.demo.model.dto.ViewResponseDTO;


@Service
public interface FileService  {
	public Boolean createSpace(Set<Group> groupName);
	public Boolean createFolder(Item parent);
	public Boolean uploadFile(MultipartFile file,Item parent) throws IOException;
	public List<ViewResponseDTO> viewDataByJPA (Integer itemId);
	public List<ViewResponseDTO> viewDataByNativeSqlQuery (Integer itemId);
	public List<ViewResponseDTO> viewDataByGraphql (Integer itemId);
	public byte[] downloadFile(Item itemId);
}
