package org.mtdev.regataiades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected int id;

	@Column(name = "crewId")
	protected int crewId;

	@Column(name = "crewName")
	protected String crewName;

	@Column(name = "time")
	protected String time;

	@Column(name = "timeDiff")
	protected String timeDiff;

	@Column(name = "rank")
	protected int rank;
	
	@Column(name = "line")
	protected int line;
	
	@Column(name = "sort")
	protected int sort;
	

}
