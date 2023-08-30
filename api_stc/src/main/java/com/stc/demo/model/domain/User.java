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
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId")
	private int userId;
    @Column(name="first_name",nullable = false)
	private String firstName;
	@Column(name="last_name",nullable = false)
	private String LastName;
	@Column(name="user_email",unique = true,nullable = false)
	private String userEmail;
	
	private String password;
    @ManyToMany
	    @JoinTable(
	    name = "user_group",
	    joinColumns=@JoinColumn(name="user_id"),
	    inverseJoinColumns = @JoinColumn(name="group_id")
	    )
	    private Set<Group> userGroup;
}
