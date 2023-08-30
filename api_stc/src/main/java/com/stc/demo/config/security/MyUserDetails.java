package com.stc.demo.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.stc.demo.model.domain.Group;
import com.stc.demo.model.domain.PersmissionEntity;
import com.stc.demo.model.domain.User;

public class MyUserDetails implements UserDetails {

	   private User user;
	     
	    public MyUserDetails(User user) {
	        this.user = user;
	    } 
	   
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 Set<SimpleGrantedAuthority> authorities =  new HashSet<>();
		for(Group group  : user.getUserGroup()) 
		{
		   authorities.add(new SimpleGrantedAuthority(group.getGroupName()));
		   for(PersmissionEntity permission :group.getGroupPermission()) {
			   authorities.add(new SimpleGrantedAuthority(group.getGroupName()+"_"+permission.getPermissionName()));
		   }			
		} 
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
