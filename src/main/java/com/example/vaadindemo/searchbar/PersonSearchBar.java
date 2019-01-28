package com.example.vaadindemo.searchbar;

import com.example.vaadindemo.model.Person;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PersonSearchBar extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;

	private TextField allTextField = new TextField("All");

	private final ConfigurableFilterDataProvider<Person, Void, PersonSearchParams> configurableFilterDataProvider;
	
	public PersonSearchBar(PersonSearchParams personSearchParams, ConfigurableFilterDataProvider<Person, Void, PersonSearchParams> configurableFilterDataProvider) {
		
		this.configurableFilterDataProvider = configurableFilterDataProvider;
		
		Binder<PersonSearchParams> binder = new Binder<>(PersonSearchParams.class);

		this.addComponent(allTextField);
		binder.forField(allTextField).withNullRepresentation("").bind("all");
		binder.setBean(personSearchParams);
		
		binder.addValueChangeListener(new ValueChangeListener<PersonSearchParams>() {

			@Override
			public void valueChange(ValueChangeEvent<PersonSearchParams> event) {
				PersonSearchBar.this.configurableFilterDataProvider.setFilter(personSearchParams);
			}
		});
	}
}
