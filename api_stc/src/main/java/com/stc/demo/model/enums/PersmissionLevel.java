package com.stc.demo.model.enums;

public enum PersmissionLevel {
	READ("Read"),
	WRITE("Write"),
	EDIT("Edit"),
	Download("Download"),
	DELETE("Delete");
	
	public final String val;
	private PersmissionLevel(String val) {
		this.val=val;
	}
}
