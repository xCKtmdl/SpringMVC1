package com.aaron.SpringMVC1.service;

import java.util.List;

import com.aaron.SpringMVC1.domain.*;


public interface SpitterService {
  List<Spittle> getRecentSpittles(int count);
  void saveSpittle(Spittle spittle);
  
  void saveSpitter(Spitter spitter);
  Spitter getSpitter(long id);
  void startFollowing(Spitter follower, Spitter followee);

  List<Spittle> getSpittlesForSpitter(Spitter spitter);
  List<Spittle> getSpittlesForSpitter(String username);
  Spitter getSpitter(String username);
  
  Spittle getSpittleById(long id);
  void deleteSpittle(long id);
  
  List<Spitter> getAllSpitters();
}
