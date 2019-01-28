package com.example.vaadindemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Player {
	
	public Player() {}
	public Player(Long id, String firstname, String lastname, Integer skilllevel) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.setSkilllevel(skilllevel);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstname;

	private String lastname;
	
	private Integer skilllevel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getSkilllevel() {
		return skilllevel;
	}
	public void setSkilllevel(Integer skilllevel) {
		this.skilllevel = skilllevel;
	}
}
