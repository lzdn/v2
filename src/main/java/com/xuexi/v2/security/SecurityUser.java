package com.xuexi.v2.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.xuexi.v2.domain.Role;
import com.xuexi.v2.domain.User;

public class SecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityUser(User suser) {
		if (suser != null) {
			this.setUserId(suser.getUserId());
			this.setUsername(suser.getUsername());
			this.setPassword(suser.getPassword());
			this.setRoles(suser.getRoles());
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> userRoles = this.getRoles();

		if (userRoles != null) {
			for (Role role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
