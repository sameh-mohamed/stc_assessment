package com.stc.demo.model.enums;

public enum FileTypes {
    SPACE("Space"),
    FOLDER("Folder"),
    FILE("File");
	
	public final String val;
	
	private FileTypes(String val) {
		this.val=val;
	}
}
