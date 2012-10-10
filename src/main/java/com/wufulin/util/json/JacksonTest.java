package com.wufulin.util.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JacksonTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
		
		String userJson="{\"name\" : { \"first\" : \"Joe\", \"last\" : \"Sixpack\" },"+
					     "\"gender\" : \"MALE\"," +
					     "\"verified\" : false," +
					     "\"userImage\":\"Rm9vYmFyIQ==\"}";
		
		// Data Binding
		ObjectMapper mapper=new ObjectMapper();
		User user=mapper.readValue(userJson.getBytes(), User.class);
		System.out.println(user.getUserImage());
//		mapper.writeValue(new File("user.json"), user);
		
		// Tree Model
		ObjectMapper m=new ObjectMapper();
		JsonNode rootNode=m.readValue(userJson.getBytes(), JsonNode.class);
		JsonNode nameNode=rootNode.path("name");
		String lastName=nameNode.path("last").textValue();
		if(!"xmler".equalsIgnoreCase(lastName)){
			((ObjectNode)nameNode).put("last","Jsoner");
		}
		m.writeValue(new File("user.json"), rootNode);
		
		
	}
}
