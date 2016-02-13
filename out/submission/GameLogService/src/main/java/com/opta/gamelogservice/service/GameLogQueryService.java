package com.opta.gamelogservice.service;

import java.io.IOException;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service
public class GameLogQueryService {

	@Autowired
	private MongoDbFactory mongo;
	
	@Value("${db.collections.name}")
	private String colName;

	
	/**
	 * <p>Retrieve all events related to a given match/game id 
	 * and write them in JSON into the given Writer</p>
	 * 
	 * @param matchId The game_id for which retrieve all related events
	 * @param writer Where to write the events in JSON format 
	 * @return The number of events written
	 * @throws IOException
	 */
	public long getEventsByMatchId(Long matchId, Writer writer) throws IOException {
		DB db = mongo.getDb();
		DBCollection gameCollection = db.getCollection(colName);
		DBCursor allGameEvents = gameCollection.find(new BasicDBObject("game_id", matchId));
		long written = 0;
		try {
			while(allGameEvents.hasNext()){
				if(written == 0) {
					writer.write("{\"fixture_id\":" + matchId + ",\"events\":[");
				}
				if(written > 0){
					writer.write(",");
				}
				DBObject event = allGameEvents.next();
				event.removeField("_id");				
				writer.write(event.toString());
				written++;
			}
			
			if(written > 0){
				writer.write("]}");
			}
			
			return written;
		} finally {
			allGameEvents.close();
		}
	}

}
