package com.example.vaadindemo.searchparam;

public class PersonSearchParams {
	
	private String all;
	
	public PersonSearchParams() {};
	
	public PersonSearchParams(PersonSearchParams personSearchParams) {
		this.all = personSearchParams.getAll();
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}
}
