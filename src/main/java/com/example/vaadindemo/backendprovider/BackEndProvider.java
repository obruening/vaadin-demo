package com.example.vaadindemo.backendprovider;

import java.util.stream.Stream;

import org.springframework.data.domain.Page;

import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.example.vaadindemo.service.DataService;
import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;

public class BackEndProvider<ENTITY, SEARCHPARAMS extends PersonSearchParams, DATASERVICE extends DataService<ENTITY, SEARCHPARAMS>> extends AbstractBackEndDataProvider<ENTITY, SEARCHPARAMS> {

	private static final long serialVersionUID = 1L;
	
	private DATASERVICE dataService;

	public BackEndProvider(DATASERVICE dataService) {
		this.dataService = dataService;
	}

	@Override
	protected Stream<ENTITY> fetchFromBackEnd(Query<ENTITY, SEARCHPARAMS> query) {

		QueryPageable<ENTITY, SEARCHPARAMS> pageable = new QueryPageable<>(query);

		Page<ENTITY> result = dataService.findAll(query.getFilter().get(), pageable);
		
		return result.getContent().stream();
	}

	@Override
	protected int sizeInBackEnd(Query<ENTITY, SEARCHPARAMS> query) {

		return dataService.count(query.getFilter().get()).intValue();
	}
	
	
}
