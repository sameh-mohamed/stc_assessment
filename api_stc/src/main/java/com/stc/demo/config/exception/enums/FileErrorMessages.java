package com.stc.demo.config.exception.enums;

public enum FileErrorMessages {
	File_Not_Found("File not found"),
	Type_Not_Found("Type not found"),
	Error_In_Save_File("Error in save file"),
	Parent_Folder_Not_Found("Parent Folder not found");
	
	public final String message;
	
	private FileErrorMessages (String message) {
		this.message = message;
	}
}
