package com.example.vaadindemo.factory;

import com.example.vaadindemo.backendprovider.BackEndProvider;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.example.vaadindemo.service.DataService;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

public interface Factory {

	String getName();
	
	<E, S extends PersonSearchParams, Q, C extends ConfigurableFilterDataProvider<E, Q, S>> Grid<E> createGrid(C configurableFilterDataProvider);
	
	Component createSearchBar(PersonSearchParams searchParams, ConfigurableFilterDataProvider configurableFilterDataProvider);
	
	PersonSearchParams cloneSearchParams(PersonSearchParams personSearchParams);
	
	<E, S extends PersonSearchParams, D extends DataService<E, S>> BackEndProvider<?, PersonSearchParams, DataService<?, PersonSearchParams>> createBackEndDataProvider();
}
