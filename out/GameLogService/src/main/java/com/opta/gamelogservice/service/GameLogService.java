package com.opta.gamelogservice.service;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


@Service
public class GameLogService {

	@Autowired
	private MongoDbFactory mongo;
	
	@Value("${db.collections.name}")
	private String colName;

	private boolean indexesCreated;
	
	/**
	 * Update a match with a new XML event
	 * @param eventFeed
	 */
	public void applyXMLEvent(String eventFeed) {
		
		// parses the XML into a JSONObject
		JSONObject eventJSON = XML.toJSONObject(eventFeed)
				.getJSONObject("RU50_EventFeed")
				.getJSONObject("Game");
		
		DB db = mongo.getDb();
		
		// Save the "<game>" section of the event into the Collection "game_{game_id}"
		DBCollection matchesCollection = db.getCollection(colName);
		createIndexes(matchesCollection);
		matchesCollection.insert( (DBObject)JSON.parse(eventJSON.toString()) );		
	}

	private void createIndexes(DBCollection matchesCollection) {
		if(!indexesCreated){
			matchesCollection.createIndex(new BasicDBObject("game_id", 1));
			matchesCollection.createIndex(new BasicDBObject("Event.id", 1), new BasicDBObject("unique", true));
			indexesCreated = true;
		}
	}

}
