package com.example.vaadindemo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaadindemo.backendprovider.BackEndProvider;
import com.example.vaadindemo.backendprovider.PlayerBackEndProvider;
import com.example.vaadindemo.model.Player;
import com.example.vaadindemo.searchbar.PlayerSearchBar;
import com.example.vaadindemo.searchparam.PersonSearchParams;
import com.example.vaadindemo.searchparam.PlayerSearchParams;
import com.example.vaadindemo.service.PlayerDataService;
import com.example.vaadindemo.util.Util;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;

@Service
public class PlayerFactory implements Factory {
	
	@Autowired 
	private PlayerDataService playerDataService;
	
	@Override
	public String getName() {
		return "Player";
	}
	
	@Override
	public Component createSearchBar(PersonSearchParams personSearchParams, ConfigurableFilterDataProvider configurableFilterDataProvider) {
		return new PlayerSearchBar((PlayerSearchParams)personSearchParams, configurableFilterDataProvider);
	}
	
	@Override
	public PersonSearchParams cloneSearchParams(PersonSearchParams personSearchParams) {
		return new PlayerSearchParams(personSearchParams);
	}
	
	@Override
	public BackEndProvider<Player, PlayerSearchParams, PlayerDataService> createBackEndDataProvider() {
		return new PlayerBackEndProvider(playerDataService);
	}

	@Override
	public Grid<Player> createGrid(ConfigurableFilterDataProvider configurableFilterDataProvider) {
		
		Grid<Player> grid = new Grid<>(configurableFilterDataProvider);
		grid = Util.configGrid(grid);
		grid.addColumn(Player::getId).setCaption("Meine Id");
		grid.addColumn(Player::getFirstname).setCaption("Mein Firstname");
		grid.addColumn(Player::getLastname).setCaption("Mein Lastname");
		grid.addColumn(Player::getSkilllevel).setCaption("Mein SkillLevel");
		return grid;
	}
}
