package com.stc.demo.model.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="files")
public class Files {
 
 @Id	
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column(name="files_binary")
    private byte[] filesBinary;
    @OneToOne
	private Item items;
    
    private String extenstion;
    
    private Long size;
}
