package com.opta.gamelogservice.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.opta.gamelogservice.service.GameLogQueryService;

@Controller
@RequestMapping("/api/matches")
public class MatchResource {

	@Autowired
	private GameLogQueryService gameLogQueryService;

	@RequestMapping(value = "/{matchId}", method=RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public void getMatchEventsStream(@PathVariable Long matchId, HttpServletResponse response) throws IOException{
		
		response.setStatus( HttpServletResponse.SC_OK );
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		long eventsWritten = gameLogQueryService.getEventsByMatchId(matchId, response.getWriter());
		
		if(eventsWritten == 0){
			response.setStatus( HttpServletResponse.SC_NOT_FOUND );
		}
		
	}
}
