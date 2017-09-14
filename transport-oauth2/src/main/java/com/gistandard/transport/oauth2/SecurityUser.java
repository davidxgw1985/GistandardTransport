package com.gistandard.transport.oauth2;

import com.gistandard.transport.oauth2.service.OAuth2TokenService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *用户详细信息
 */
public class SecurityUser<T> implements UserDetails {

	private static final long serialVersionUID = -694318736321199793L;

	private Set<String> permissions;

	private T info;
	private String id ;
	private String name ;

	public SecurityUser(String id ,String name,T info) {
		this.id = id;
		this.name = name;
		this.info = info;

	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (this.getPermissions() == null) {
			this.permissions = new HashSet<String>();
		}

		permissions.add("read");
		permissions.add("write");
		permissions.add("trust");

		if (permissions != null) {
			for (String permission : permissions) {
				GrantedAuthority authority = new SimpleGrantedAuthority(
						permission);
				authorities.add(authority);
			}
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return OAuth2TokenService.DEFAULT_PASSWORD;
	}

	@Override
	public String getUsername() {
		return this.name;
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

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
