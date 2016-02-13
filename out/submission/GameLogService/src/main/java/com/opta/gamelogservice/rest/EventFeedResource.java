package com.opta.gamelogservice.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DuplicateKeyException;
import com.opta.gamelogservice.service.GameLogService;

@Controller
@RequestMapping("/api/events")
public class EventFeedResource {
	
	@Autowired
	private GameLogService gameLogService;

	@RequestMapping(value = "/inlinexml", method = RequestMethod.POST, 
			consumes = {MediaType.APPLICATION_XML_VALUE})
	public void logEvent(@RequestBody(required=true) String event, HttpServletResponse response){
		
		response.setStatus(HttpServletResponse.SC_OK);
		try {
			
			gameLogService.applyXMLEvent(event);
		
		} catch(JSONException je) {
			throw new BadRequestException("Unable to translate the XML event to JSON: " + je.getMessage());
		} catch(DuplicateKeyException dke){
			throw new BadRequestException("Game event already stored");
		}
		
	}
	
	
	// Used by clients that prefer HTTP file upload (RFC 1867)
	// NOTE: requires little more processing
	@RequestMapping(method = RequestMethod.POST, 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public void uploadEvent(
			@RequestParam(value = "file") MultipartFile file, 
			HttpServletResponse response) 
					throws UnsupportedEncodingException, IOException{
		
		response.setStatus(HttpServletResponse.SC_OK);	
		try {
			
			gameLogService.applyXMLEvent( new String(file.getBytes(), "UTF-8") );
		
		} catch(JSONException je) {
			throw new BadRequestException("Unable to translate the XML event to JSON: " + je.getMessage());
		} catch(DuplicateKeyException dke){
			throw new BadRequestException("Game event already stored");
		}
		
	}
	
	
}
