package org.mtdev.regataiades.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected int id;

	@ManyToOne
	@JoinColumn(name = "fk_user_id")
	protected User user;

	@Column(name = "name")
	protected String name;

	@Column(name = "zipCode")
	protected String zipCode;

	@Column(name = "address")
	protected String address;

	@Column(name = "city")
	protected String city;

	@Column(name = "country")
	protected String country;

	@Column(name = "contactName")
	protected String contactName;

	@Column(name = "contactSurname")
	protected String contactSurname;

	@Column(name = "contactTelephone")
	protected String contactTelephone;

	@Column(name = "contactEmail")
	protected String contactEmail;

	@Column(name = "logo")
	protected String logo;

	@Column(name = "confirmed")
	protected int confirmed;

	@Column(name = "invited")
	protected boolean invited;

	@Column(name = "details")
	protected String details;
	
	@Column(name = "athletesNum")
	protected int athletesNum;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,
			CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "fk_team_id")
	protected Set<Crew> crews;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,
			CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "fk_team_id")
	protected Set<Coach> coaches;

}
