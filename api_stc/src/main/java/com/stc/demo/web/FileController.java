package com.stc.demo.web;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stc.demo.model.domain.Group;
import com.stc.demo.model.domain.Item;
import com.stc.demo.services.files.FileService;


@RestController
@RequestMapping("/v1/stc/")
public class FileController {

	@Autowired FileService fileService;
	
	
	@PreAuthorize("hasAuthority('ADMIN_Read') or hasAuthority('ADMIN_Write') or hasAuthority('ADMIN_Edit') or hasAuthority('ADMIN_Delete') or hasAuthority('ADMIN_Download') ")
	@RequestMapping(method = RequestMethod.POST, path = "/action/create-space", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createSpace(@RequestBody Set<Group> obj) {
		return ResponseEntity.ok(fileService.createSpace(obj));
	}
	
	@PreAuthorize("hasAuthority('ADMIN_Read') or hasAuthority('ADMIN_Write') or hasAuthority('ADMIN_Edit') or hasAuthority('ADMIN_Delete') or hasAuthority('ADMIN_Download') ")
	@RequestMapping(method = RequestMethod.POST, path = "/action/create-folder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createFolder(@RequestBody Item parent) {
		return ResponseEntity.ok(fileService.createFolder(parent));
	}
	
	@PreAuthorize("hasAuthority('ADMIN_Read') or hasAuthority('ADMIN_Write') or hasAuthority('ADMIN_Edit') or hasAuthority('ADMIN_Delete') or hasAuthority('ADMIN_Download') ")
	@RequestMapping(method = RequestMethod.POST, path = "/action/create-file", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> uploadFile( MultipartFile file, @RequestBody Item parent) throws IOException  {
		return ResponseEntity.ok(fileService.uploadFile(file,parent));
	}
	
	@PreAuthorize("hasAuthority('ADMIN_Read') or hasAuthority('ADMIN_Write') or hasAuthority('ADMIN_Edit') or hasAuthority('ADMIN_Delete') or hasAuthority('ADMIN_Download')  or hasAuthority('USER_Read') ")
	@RequestMapping(method = RequestMethod.GET, path = "/action/view-jpa-files", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewDataByJPA(@RequestParam("itemId") Integer itemId ) throws IOException  {
		return ResponseEntity.ok(fileService.viewDataByJPA(itemId));
	}
	
	@PreAuthorize("hasAuthority('ADMIN_Read') or hasAuthority('ADMIN_Write') or hasAuthority('ADMIN_Edit') or hasAuthority('ADMIN_Delete') or hasAuthority('ADMIN_Download')  or hasAuthority('USER_Read') ")
	@RequestMapping(method = RequestMethod.GET, path = "/action/view-nativesql-files", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewDataByNativeSqlQuery(@RequestParam("itemId") Integer itemId ) throws IOException  {
		return ResponseEntity.ok(fileService.viewDataByNativeSqlQuery(itemId));
	}
	
	@PreAuthorize("hasAuthority('ADMIN_Read') or hasAuthority('ADMIN_Write') or hasAuthority('ADMIN_Edit') or hasAuthority('ADMIN_Delete') or hasAuthority('ADMIN_Download')  or hasAuthority('USER_Read') ")
	@RequestMapping(method = RequestMethod.GET, path = "/action/view-graphql-files", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewDataByGraphql(@RequestParam("itemId") Integer itemId ) throws IOException  {
		return ResponseEntity.ok(fileService.viewDataByGraphql(itemId));
	}
	
	@PreAuthorize("hasAuthority('ADMIN_Read') or hasAuthority('ADMIN_Write') or hasAuthority('ADMIN_Edit') or hasAuthority('ADMIN_Delete') or hasAuthority('ADMIN_Download')  or hasAuthority('USER_Read') ")
	@RequestMapping(method = RequestMethod.GET, path = "/action/download-file", consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8" })
	public ResponseEntity<?> downloadFile(@RequestBody Item item ) throws IOException  {
		byte[] arr=fileService.downloadFile(item);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/octet-stream")).body(arr);
	}
	
}
