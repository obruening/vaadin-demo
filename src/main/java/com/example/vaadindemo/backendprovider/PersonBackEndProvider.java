package com.example.vaadindemo.backendprovider;

import com.example.vaadindemo.model.Person;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.example.vaadindemo.service.PersonDataService;

public class PersonBackEndProvider extends BackEndProvider<Person, PersonSearchParams, PersonDataService> {

	public PersonBackEndProvider(PersonDataService dataService) {
		super(dataService);
	}

	private static final long serialVersionUID = 1L;
	
}
