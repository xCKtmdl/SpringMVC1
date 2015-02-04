package com.aaron.SpringMVC1.persistence;

import java.util.List;

import com.aaron.SpringMVC1.domain.*;

public interface SpitterDao {
	void addSpitter(Spitter spitter);

	void saveSpitter(Spitter spitter);

	Spitter getSpitterById(long id);

	List<Spittle> getRecentSpittle(int count);

	void saveSpittle(Spittle spittle);

	List<Spittle> getSpittlesForSpitter(Spitter spitter);

	Spitter getSpitterByUsername(String username);

	void deleteSpittle(long id);

	Spittle getSpittleById(long id);

	List<Spitter> findAllSpitters();
}
