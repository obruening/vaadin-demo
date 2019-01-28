package com.example.vaadindemo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaadindemo.backendprovider.BackEndProvider;
import com.example.vaadindemo.backendprovider.PersonBackEndProvider;
import com.example.vaadindemo.model.Person;
import com.example.vaadindemo.searchbar.PersonSearchBar;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.example.vaadindemo.service.PersonDataService;
import com.example.vaadindemo.util.Util;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

@Service
public class PersonFactory implements Factory {
	
	@Autowired 
	private PersonDataService personDataService;
	
	@Override
	public String getName() {
		return "Person";
	}
	
	@Override
	public Component createSearchBar(PersonSearchParams personSearchParams, ConfigurableFilterDataProvider configurableFilterDataProvider) {
		return new PersonSearchBar(personSearchParams, configurableFilterDataProvider);
	}
	
	@Override
	public PersonSearchParams cloneSearchParams(PersonSearchParams personSearchParams) {
		return new PersonSearchParams(personSearchParams);
	}
	
	@Override
	public BackEndProvider<Person, PersonSearchParams, PersonDataService> createBackEndDataProvider() {
		return new PersonBackEndProvider(personDataService);
		
	}
	
	@Override
	public Grid<Person> createGrid(ConfigurableFilterDataProvider configurableFilterDataProvider) {
		
		Grid<Person> grid = new Grid<>(configurableFilterDataProvider);
		grid = Util.configGrid(grid);
		grid.addColumn(Person::getId).setCaption("Meine Id");
		grid.addColumn(Person::getFirstname).setCaption("Mein Firstname");
		grid.addColumn(Person::getLastname).setCaption("Mein Lastname");
		return grid;
	}

}
