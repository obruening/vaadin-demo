package com.example.vaadindemo.backendprovider;


import com.example.vaadindemo.model.Player;
import com.example.vaadindemo.searchparam.PlayerSearchParams;
import com.example.vaadindemo.service.PlayerDataService;

public class PlayerBackEndProvider extends BackEndProvider<Player, PlayerSearchParams, PlayerDataService> {

	public PlayerBackEndProvider(PlayerDataService dataService) {
		super(dataService);
	}

	private static final long serialVersionUID = 1L;
	
}
