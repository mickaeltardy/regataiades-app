package org.mtdev.regataiades.model;

import java.util.List;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.mtdev.regataiades.view.Views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Data
@Table(name = "events")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected int id;

	@Column(name = "type")
	protected int type;

	@Column(name = "sort")
	protected int sort;

	@Column(name = "eventId")
	protected String eventId;

	@Column(name = "eventCategory")
	@JsonView(Views.Public.class)
	protected String eventCategory;

	@Column(name = "time")
	protected String time;

	@Column(name = "status")
	protected int status;

	@Column(name = "boatCategory")
	protected String boatCategory;

	@JsonView(Views.Internal.class)
	@OneToMany(cascade = CascadeType.ALL,
	fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "fk_event_id")
	protected List<Result> results;

}
