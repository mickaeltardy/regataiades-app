package org.mtdev.regataiades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected int id;

	@Column(name = "login")
	protected String login;

	@Column(name = "password")
	protected String password;

	@Column(name = "name")
	protected String name;

	@Column(name = "surname")
	protected String surname;

	@Column(name = "telephone")
	protected String telephone;
	

}
