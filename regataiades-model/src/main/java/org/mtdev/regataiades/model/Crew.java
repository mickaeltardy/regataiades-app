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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "crews")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crew {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected int id;

	@Column(name = "category")
	protected String category;

	@OneToMany(
	// mappedBy = "crew",
	fetch = FetchType.EAGER, cascade = { CascadeType.ALL, CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinColumn(name = "fk_crew_id")
	protected Set<Athlete> athletes;

}
