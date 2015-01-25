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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "athletes")
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Athlete {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	protected int id;

	@Column(name = "name")
	protected String name;

	@Column(name = "surname")
	protected String surname;

	@Column(name = "licence")
	protected String licence;

	@Column(name = "sex")
	protected String sex;

	@Column(name = "captain")
	protected boolean captain;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_crew_id")
	protected Crew crew;

	

	

}
