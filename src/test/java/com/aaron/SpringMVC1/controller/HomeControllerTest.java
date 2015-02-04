package com.aaron.SpringMVC1.controller;

import static com.aaron.SpringMVC1.controller.HomeController.*;
import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.aaron.SpringMVC1.domain.Spittle;
import com.aaron.SpringMVC1.service.SpitterService;

public class HomeControllerTest {  
  @Test
  public void shouldDisplayRecentSpittles() {
    List<Spittle> expectedSpittles = 
      asList(new Spittle(), new Spittle(), new Spittle());
    
    SpitterService spitterService = mock(SpitterService.class);

    when(spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE)).
        thenReturn(expectedSpittles);
    
    HomeController controller = 
                   new HomeController(spitterService);
    
    HashMap<String, Object> model = new HashMap<String, Object>();
    String viewName = controller.showHomePage(model);
    
    assertEquals("home", viewName);

    assertSame(expectedSpittles, model.get("spittles")); 
    verify(spitterService).getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE);
  }
}
