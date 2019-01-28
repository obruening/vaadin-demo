package com.example.vaadindemo.searchbar;

import com.example.vaadindemo.model.Player;
import com.example.vaadindemo.searchparam.PlayerSearchParams;
import com.vaadin.data.Binder;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class PlayerSearchBar extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private TextField allTextField = new TextField("All");
	private TextField skilllevelTextField = new TextField("Skilllevel");

	private ConfigurableFilterDataProvider<Player, Void, PlayerSearchParams> configurableFilterDataProvider;

	public PlayerSearchBar(PlayerSearchParams playerSearchParams, ConfigurableFilterDataProvider<Player, Void, PlayerSearchParams> configurableFilterDataProvider) {
		
		this.configurableFilterDataProvider = configurableFilterDataProvider;
		Binder<PlayerSearchParams> binder = new Binder<>(PlayerSearchParams.class);

		this.addComponent(allTextField);
		this.addComponent(skilllevelTextField);
		
		binder.forField(allTextField).withNullRepresentation("").bind("all");
		binder.forField(skilllevelTextField).withNullRepresentation("").withConverter(new StringToIntegerConverter("fehler")).bind("skilllevel");
		binder.setBean(playerSearchParams);
		
		binder.addValueChangeListener(new ValueChangeListener<PlayerSearchParams>() {

			@Override
			public void valueChange(ValueChangeEvent<PlayerSearchParams> event) {
				PlayerSearchBar.this.configurableFilterDataProvider.setFilter(playerSearchParams);
			}
		});
	}
}
