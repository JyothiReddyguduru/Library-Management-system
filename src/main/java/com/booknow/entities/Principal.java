package com.booknow.entities;

import java.io.Serializable;
import java.util.List;

import org.hibernate.usertype.UserType;
import org.springframework.security.core.GrantedAuthority;

import com.booknow.Views.BaseView;
import com.fasterxml.jackson.annotation.JsonView;


public class Principal implements Serializable {

		private static final long serialVersionUID = 1L;

		@JsonView(BaseView.class)
		private String fullName;

		@JsonView(BaseView.class)
		private UserType userType;

		private List<GrantedAuthority> authorities;

		@JsonView(BaseView.class)
		private String username;

		@JsonView(BaseView.class)
		private String department;

		public List<GrantedAuthority> getAuthorities() {
			return authorities;
		}

		public void setAuthorities(List<GrantedAuthority> authorities) {
			this.authorities = authorities;
		}
 
}
