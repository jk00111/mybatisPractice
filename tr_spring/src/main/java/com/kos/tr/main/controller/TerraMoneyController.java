package com.kos.tr.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;

import org.bson.*;

@Controller
public class TerraMoneyController {
	
	@GetMapping("trMoneyForm")
	public String trMoneyForm() {
		
		
		return "terraMain/mainMoney";
		
	}
	
	@GetMapping(value="trMoneyMongoDB")
	@ResponseBody
	public String trMainMoney() {
		
		System.out.println("로그 >>>>>>>> " );
		String MongoDB_IP = "192.168.0.26";
		int MongoDB_PORT = 27017;
		String DB_NAME ="terra";
		
		MongoClient mongoClient = new MongoClient(new ServerAddress(MongoDB_IP, MongoDB_PORT));
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection collection = db.getCollection("terra");
		
		System.out.println("mongoDB 연결");
		DBCursor cursor = collection.find();
		String jsonResult = "";
		
		
		while (cursor.hasNext()) {
			jsonResult = cursor.next().toString();
			System.out.println(jsonResult);
		}
		
		System.out.println(jsonResult);
		
		return jsonResult;
		
	}
	
}
