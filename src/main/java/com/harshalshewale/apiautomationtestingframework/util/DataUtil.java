package com.harshalshewale.apiautomationtestingframework.util;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataUtil {
	
	public static Object readDataFile(String jsonFile) throws ParseException, IOException {
		Object jsonObject = null;
		JSONParser jsonParser = new JSONParser();
		String filename = "src/test/resources/data/" + jsonFile;
		FileReader reader = new FileReader(filename);
		jsonObject = jsonParser.parse(reader);
		return jsonObject;
	}

}
