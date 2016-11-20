package com.ustrzycki.domain;

public abstract class AddableToDb {
	
	Long id = null;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
