package com.example.vaadindemo.searchbar;

import com.example.vaadindemo.model.Employee;
import com.example.vaadindemo.searchparam.EmployeeSearchParams;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class EmployeeSearchBar extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private TextField allTextField = new TextField("All");
	private TextField jobTextField = new TextField("Job");

	private ConfigurableFilterDataProvider<Employee, Void, EmployeeSearchParams> configurableFilterDataProvider;

	public EmployeeSearchBar(EmployeeSearchParams employeeSearchParams, ConfigurableFilterDataProvider<Employee, Void, EmployeeSearchParams> configurableFilterDataProvider) {
		
		this.configurableFilterDataProvider = configurableFilterDataProvider;
		Binder<EmployeeSearchParams> binder = new Binder<>(EmployeeSearchParams.class);

		this.addComponent(allTextField);
		this.addComponent(jobTextField);
		
		binder.forField(allTextField).withNullRepresentation("").bind("all");
		binder.forField(jobTextField).withNullRepresentation("").bind("job");
		binder.setBean(employeeSearchParams);
		
		binder.addValueChangeListener(new ValueChangeListener<EmployeeSearchParams>() {

			@Override
			public void valueChange(ValueChangeEvent<EmployeeSearchParams> event) {
				EmployeeSearchBar.this.configurableFilterDataProvider.setFilter(employeeSearchParams);
			}
		});
	}
}
