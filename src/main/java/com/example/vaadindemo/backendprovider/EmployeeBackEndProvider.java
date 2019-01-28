package com.example.vaadindemo.backendprovider;

import com.example.vaadindemo.model.Employee;
import com.example.vaadindemo.searchparam.EmployeeSearchParams;
import com.example.vaadindemo.service.EmployeeDataService;

public class EmployeeBackEndProvider extends BackEndProvider<Employee, EmployeeSearchParams, EmployeeDataService> {

	public EmployeeBackEndProvider(EmployeeDataService dataService) {
		super(dataService);
	}

	private static final long serialVersionUID = 1L;
	
}
