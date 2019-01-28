package com.example.vaadindemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.vaadindemo.searchparam.PersonSearchParams;

public interface DataService<E, S extends PersonSearchParams> {
	
	Page<E> findAll(S searchParams, Pageable pageable);
	
	public Page<E> findAll(Pageable pageable);
	
	public Long count(S searchParams); 

}
