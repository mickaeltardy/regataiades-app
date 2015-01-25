package org.mtdev.regataiades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "teams")
@Data
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected int id;

	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	protected User user;

	@Column(name = "name")
	protected String name;

	@Column(name = "telephone")
	protected String telephone;

	@Column(name = "address")
	protected String addresse;

	@Column(name = "city")
	protected String city;

	@Column(name = "country")
	protected String country;

	@Column(name = "contact_name")
	protected String contactName;

	@Column(name = "contact_surname")
	protected String contactSurname;

	@Column(name = "contact_telephone")
	protected String contactTelephone;

	@Column(name = "contact_email")
	protected String contactEmail;

	@Column(name = "logo")
	protected String logo;

	@Column(name = "confirmed")
	protected int confirmed;

}
