package com.booknow.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Identifiable {
	@JsonIgnore
	public Long getIdentifier();
}
