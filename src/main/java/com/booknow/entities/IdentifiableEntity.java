package com.booknow.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.booknow.Views.DefaultView;
import com.fasterxml.jackson.annotation.JsonView;


@MappedSuperclass
public abstract class IdentifiableEntity implements Serializable, Identifiable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DefaultView.class)
	@Column(name = "id")
	protected Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getIdentifier() {
		return id;
	}
}

