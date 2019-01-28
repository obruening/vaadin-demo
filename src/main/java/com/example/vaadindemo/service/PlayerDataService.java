package com.example.vaadindemo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vaadindemo.model.Player;
import com.example.vaadindemo.model.QPlayer;
import com.example.vaadindemo.repository.PlayerRepository;
import com.example.vaadindemo.searchparam.PlayerSearchParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Service
@Transactional("primaryTransactionManager")
public class PlayerDataService implements DataService<Player, PlayerSearchParams>{
	
	@Autowired
	private PlayerRepository repository;
	
	@Override
	public Page<Player> findAll(PlayerSearchParams searchParams, Pageable pageable) {
		
		return repository.findAll(getPredicate(searchParams), pageable);
	}

	@Override
	public Page<Player> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@Override
	public Long count(PlayerSearchParams searchParams) {
		return repository.count(getPredicate(searchParams));
	}
	
	private Predicate getPredicate(PlayerSearchParams searchParams) {

		BooleanBuilder builder = new BooleanBuilder();

		if (StringUtils.isNotBlank(searchParams.getAll())) {
			builder.and(QPlayer.player.firstname.containsIgnoreCase(searchParams.getAll()));
			builder.and(QPlayer.player.lastname.containsIgnoreCase(searchParams.getAll()));
		}
		
		if (searchParams.getSkilllevel() != null) {
			builder.and(QPlayer.player.skilllevel.eq(searchParams.getSkilllevel()));
		}
		
		return builder;
	}
	
	public Player save(Player player) {
		return repository.save(player);
	}

}
