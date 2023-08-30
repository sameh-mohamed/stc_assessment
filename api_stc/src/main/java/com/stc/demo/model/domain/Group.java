package com.stc.demo.model.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="group_tbl")
public class Group {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name="group_id")
 private int groupId;
 
 @Column(name="group_name")
 private  String groupName;
 
 @Column(name="group_description")
 private String groupDescription;
 
 @ManyToMany(mappedBy = "userGroup")
 private Set<User> userGroup;
 
 @ManyToMany
    @JoinTable(
    name = "group_permission",
    joinColumns=@JoinColumn(name="group_id"),
    inverseJoinColumns = @JoinColumn(name="permission_id")
    )
    private Set<PersmissionEntity> groupPermission;
 
    @ManyToMany(mappedBy = "itemGroups")
    private Set<Item> item;
 
}
