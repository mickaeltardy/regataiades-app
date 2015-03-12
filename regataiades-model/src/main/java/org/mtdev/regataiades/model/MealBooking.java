package org.mtdev.regataiades.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "meal_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MealBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	protected int id;

	@OneToOne
	@JoinColumn(name = "fk_user_id")
	protected User user;

	@Column(name = "saturday_noon")
	protected int saturdayNoon;

	@Column(name = "saturday_night")
	protected int saturdayNight;

	@Column(name = "sunday_noon")
	protected int sundayNoon;

}
