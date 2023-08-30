package com.stc.demo.model.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="permission")
public class PersmissionEntity {


	
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int permissionId;
 @Column(name="permission_name")
 private String permissionName;
 
 @ManyToMany(mappedBy = "groupPermission")
 private Set<Group> group;
}
