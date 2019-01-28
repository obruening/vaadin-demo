package com.example.vaadindemo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaadindemo.backendprovider.BackEndProvider;
import com.example.vaadindemo.backendprovider.EmployeeBackEndProvider;
import com.example.vaadindemo.model.Employee;
import com.example.vaadindemo.searchbar.EmployeeSearchBar;
import com.example.vaadindemo.searchparam.EmployeeSearchParams;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.example.vaadindemo.service.EmployeeDataService;
import com.example.vaadindemo.util.Util;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

@Service
public class EmployeeFactory implements Factory {
	
	@Autowired 
	private EmployeeDataService employeeDataService;
	
	@Override
	public String getName() {
		return "Employee";
	}
	
	@Override
	public Component createSearchBar(PersonSearchParams searchParams, ConfigurableFilterDataProvider configurableFilterDataProvider) {
		return new EmployeeSearchBar((EmployeeSearchParams)searchParams, configurableFilterDataProvider);
	}
	
	@Override
	public EmployeeSearchParams cloneSearchParams(PersonSearchParams personSearchParams) {
		return new EmployeeSearchParams(personSearchParams);
	}
	
	@Override
	public BackEndProvider<Employee, EmployeeSearchParams, EmployeeDataService> createBackEndDataProvider() {
		return new EmployeeBackEndProvider(employeeDataService);
		
	}
	
	@Override
	public Grid<Employee> createGrid(ConfigurableFilterDataProvider configurableFilterDataProvider) {
		
		Grid<Employee> grid = new Grid<>(configurableFilterDataProvider);
		grid = Util.configGrid(grid);
		grid.addColumn(Employee::getId).setCaption("Meine Id");
		grid.addColumn(Employee::getFirstname).setCaption("Mein Firstname");
		grid.addColumn(Employee::getLastname).setCaption("Mein Lastname");
		grid.addColumn(Employee::getJob).setCaption("Mein Job");
		return grid;
	}
}
