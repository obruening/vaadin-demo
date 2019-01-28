package com.example.vaadindemo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vaadindemo.model.Person;
import com.example.vaadindemo.model.QPerson;
import com.example.vaadindemo.repository.PersonRepository;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Service
@Transactional("primaryTransactionManager")
public class PersonDataService implements DataService<Person, PersonSearchParams> {
	
	@Autowired
	private PersonRepository repository;
	
	@Override
	public Page<Person> findAll(PersonSearchParams searchParams, Pageable pageable) {
		
		return repository.findAll(getPredicate(searchParams), pageable);
	}

	@Override
	public Page<Person> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Override
	public Long count(PersonSearchParams searchParams) {
		return repository.count(getPredicate(searchParams));
	}
	
	private Predicate getPredicate(PersonSearchParams searchParams) {

		BooleanBuilder builder = new BooleanBuilder();

		if (StringUtils.isNotBlank(searchParams.getAll())) {
			builder.and(QPerson.person.firstname.containsIgnoreCase(searchParams.getAll()));
			builder.and(QPerson.person.lastname.containsIgnoreCase(searchParams.getAll()));

		}
		return builder;
	}
	
	public Person save(Person person) {
		return repository.save(person);
	}
}
