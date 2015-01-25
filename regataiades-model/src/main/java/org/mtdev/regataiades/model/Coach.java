package org.mtdev.regataiades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "coaches")
@Data
public class Coach {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_team_id")
	protected Team team;

	@Column(name = "name")
	protected String name;

	@Column(name = "surname")
	protected String surname;

}
