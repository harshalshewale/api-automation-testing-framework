package com.harshalshewale.apiautomationtestingframework.util;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;

public class JsonUtil {

	private static final Gson gson = new Gson();

	public static boolean isValidJson(String jsonInString) {
		try {
			gson.fromJson(jsonInString, Object.class);
		} catch (JsonSyntaxException ex) {
			return false;
		}
		return true;
	}

	public static String generateJsonWithDynamicData(String jsonInString, Map<String, String> data, boolean ignoreMissing)
			throws ParseException {

		isValidJson(jsonInString);

		JSONParser jsonParser = new JSONParser();
		Object jsonObject = jsonParser.parse(jsonInString);

		for (Map.Entry<String, String> entry : data.entrySet()) {

			try {
				if (JsonPath.read(jsonObject, entry.getKey()) != null) {
					JsonPath.parse(jsonObject).set(entry.getKey(), entry.getValue()).json();
				} else {
					System.out.println("Given path not found");
				}
			} catch (PathNotFoundException exception) {
				if (!ignoreMissing) {
					throw exception;
				}
			}

		}
		return jsonObject.toString();

	}
	

	public static String getJsonValue(String json, String jsonPath) throws ParseException, PathNotFoundException {
		isValidJson(json);
		JSONParser jsonParser = new JSONParser();
		Object jsonObject = jsonParser.parse(json);
		return JsonPath.read(jsonObject, jsonPath).toString();

	}

	public static String readJsonFile(String jsonFile) throws ParseException, IOException {
		System.out.println("I ma here");
		Object jsonObject = null;
		JSONParser jsonParser = new JSONParser();
		String filename = "src/main/resources/api/" + jsonFile;
		System.out.println("filename "+filename);
		FileReader reader = new FileReader(filename);
		jsonObject = jsonParser.parse(reader);
		return jsonObject.toString();
	}

	public static boolean jsonLenientCompare(String actualJson, String expectedJson, boolean modeOfCompare) {
		try {
			JSONAssert.assertEquals(expectedJson, actualJson, modeOfCompare);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public static String addNewJsonObject(String jsonInString, String jsonPathWithKeyName, String Value) throws ParseException {
		
		String json = null;
		
		if(Value.contains("{") || Value.contains("}") || Value.contains("[") || Value.contains("]")) {
			JSONParser jsonParser = new JSONParser();
			Object jsonObject = jsonParser.parse(Value);
			Configuration conf = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL)
					.addOptions(Option.SUPPRESS_EXCEPTIONS);
			DocumentContext documentContext = JsonPath.using(conf).parse(jsonInString);
			documentContext.set(JsonPath.compile(jsonPathWithKeyName), jsonObject);
			json = documentContext.jsonString();
		}else {
			Configuration conf = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL)
					.addOptions(Option.SUPPRESS_EXCEPTIONS);
			DocumentContext documentContext = JsonPath.using(conf).parse(jsonInString);
			documentContext.set(JsonPath.compile(jsonPathWithKeyName), Value);
			json = documentContext.jsonString();
			
		}
		return json;
	}

	public static String addNewJsonObject(String jsonInString, String jsonPathWithKeyName, Object Value) {
		String json = null;
		Configuration conf = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL)
				.addOptions(Option.SUPPRESS_EXCEPTIONS);
		DocumentContext documentContext = JsonPath.using(conf).parse(jsonInString);
		documentContext.set(JsonPath.compile(jsonPathWithKeyName), Value);
		json = documentContext.jsonString();
		return json;

	}

}
