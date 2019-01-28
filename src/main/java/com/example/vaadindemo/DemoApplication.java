package com.example.vaadindemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.vaadindemo.backendprovider.BackEndProvider;
import com.example.vaadindemo.factory.Factory;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.example.vaadindemo.service.DataService;
import com.vaadin.annotations.Theme;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Theme("valo")
	@SpringUI(path = "")
	public static class VaadinUI extends UI {
		
		@Autowired
		private List<Factory> services;
		
		private Factory selectedService;
		private PersonSearchParams searchParams = new PersonSearchParams();
		
		private VerticalLayout searchBarContainer = new VerticalLayout();
		private VerticalLayout gridContainer = new VerticalLayout();
		
		
		VerticalLayout verticalLayout = new VerticalLayout();
		
		private void switchEntities(Factory selectedService) {
			
			searchParams = selectedService.cloneSearchParams(searchParams);
			BackEndProvider<?, PersonSearchParams, DataService<?, PersonSearchParams>> backEndProvider = selectedService.createBackEndDataProvider();
			ConfigurableFilterDataProvider<?, Void, PersonSearchParams> configurableFilterDataProvider = backEndProvider.withConfigurableFilter();
			configurableFilterDataProvider.setFilter(searchParams);
			Grid<?> grid = selectedService.createGrid(configurableFilterDataProvider);
			Component searchBar = selectedService.createSearchBar(searchParams, configurableFilterDataProvider);
			gridContainer.removeAllComponents();
			gridContainer.addComponent(grid);
			searchBarContainer.removeAllComponents();
			searchBarContainer.addComponent(searchBar);
		}

		@Override
		protected void init(VaadinRequest request) {
			
			selectedService = services.get(1);
			switchEntities(selectedService);
			
			
			Button personButton = new Button("Person");
			Button playerButton = new Button("Player");
			Button employeeButton = new Button("Employee");
			
			personButton.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {

					selectedService = services.get(1);
					switchEntities(selectedService);
				}
			});

			playerButton.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					
					selectedService = services.get(2);
					switchEntities(selectedService);
				}
			});
			
			employeeButton.addClickListener(new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					
					selectedService = services.get(0);
					switchEntities(selectedService);
				}
			});
 
			verticalLayout.addComponent(new HorizontalLayout(personButton, playerButton, employeeButton));
			verticalLayout.addComponent(searchBarContainer);
			verticalLayout.addComponent(gridContainer);
			setContent(verticalLayout);
		}
	}
}
