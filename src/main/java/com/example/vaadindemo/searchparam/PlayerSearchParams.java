package com.example.vaadindemo.searchparam;

public class PlayerSearchParams extends PersonSearchParams {
	
	public PlayerSearchParams() {};
	
	public PlayerSearchParams(PersonSearchParams personSearchParams) {
		super(personSearchParams);
	}

	public PlayerSearchParams(PlayerSearchParams searchParams) {
		super(searchParams);
		this.skilllevel = searchParams.getSkilllevel();
	}

	
	public Integer skilllevel;

	public Integer getSkilllevel() {
		return skilllevel;
	}

	public void setSkilllevel(Integer skilllevel) {
		this.skilllevel = skilllevel;
	}
}
