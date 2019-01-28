package com.example.vaadindemo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vaadindemo.model.Employee;
import com.example.vaadindemo.model.QEmployee;
import com.example.vaadindemo.repository.EmployeeRepository;
import com.example.vaadindemo.searchparam.EmployeeSearchParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Service
@Transactional("primaryTransactionManager")
public class EmployeeDataService implements DataService<Employee, EmployeeSearchParams>{
	
	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public Page<Employee> findAll(EmployeeSearchParams searchParams, Pageable pageable) {
		
		return repository.findAll(getPredicate(searchParams), pageable);
	}

	@Override
	public Page<Employee> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Override
	public Long count(EmployeeSearchParams searchParams) {
		return repository.count(getPredicate(searchParams));
	}
	
	private Predicate getPredicate(EmployeeSearchParams searchParams) {

		BooleanBuilder builder = new BooleanBuilder();

		if (StringUtils.isNotBlank(searchParams.getAll())) {
			builder.and(QEmployee.employee.firstname.containsIgnoreCase(searchParams.getAll()));
			builder.and(QEmployee.employee.lastname.containsIgnoreCase(searchParams.getAll()));
		}
		
		if (StringUtils.isNotBlank(searchParams.getJob())) {
			builder.and(QEmployee.employee.job.containsIgnoreCase(searchParams.getJob()));
		}
		
		return builder;
	}
	
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

}
