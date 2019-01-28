package com.example.vaadindemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.vaadindemo.model.Employee;
import com.example.vaadindemo.model.Person;
import com.example.vaadindemo.model.Player;
import com.example.vaadindemo.service.EmployeeDataService;
import com.example.vaadindemo.service.PersonDataService;
import com.example.vaadindemo.service.PlayerDataService;

@Component
public class DemoApplicationCommandLineRunner implements CommandLineRunner {

	@Autowired
	private PersonDataService personDataService;
	
	@Autowired
	private PlayerDataService playerDataService;

	@Autowired
	private EmployeeDataService employeeDataService;

	@Override
	public void run(String... args) throws Exception {

		for (int i = 0; i < 100; i++) {
			personDataService.save(new Person(new Long(i), "person firstname " + i, "person lastname " + i));
		}
		
		for (int i = 0; i < 100; i++) {
			playerDataService.save(new Player(new Long(i), "player firstname " + i, "player lastname " + i, new Integer(i)));
		}

		for (int i = 0; i < 100; i++) {
			employeeDataService.save(new Employee(new Long(i), "employee firstname " + i, "employee lastname " + i, "employee job " + i));
		}

	}
}
