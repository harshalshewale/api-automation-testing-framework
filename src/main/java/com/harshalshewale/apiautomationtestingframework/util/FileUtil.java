package com.harshalshewale.apiautomationtestingframework.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtil {

	public static String readFileFromResources(String filePath) throws IOException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(readFileFromResourcesAsStream(filePath), writer, "UTF-8");
		return writer.toString();
	}

	public static InputStream readFileFromResourcesAsStream(String filePath) throws IOException {

		InputStream inputStream = null;
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			inputStream = classloader.getResourceAsStream(filePath);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception " + e.getMessage());
		}

		return inputStream;
	}

	public static String readPropertyValues(String proprtyFilePath, String key) {

		String keyValue = null;
		Properties prop = new Properties();
		try {
			prop.load(readFileFromResourcesAsStream(proprtyFilePath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		keyValue = prop.getProperty(key);
		return keyValue;
	}

	public static String getURL(String environment, String key) {
		String value = null;
		value = readPropertyValues("url-" + environment + ".properties", key);
		if (null == value) {
			value = readPropertyValues("url-dev.properties", key);
		}

		if (null != value) {
			return value.trim();
		} else {
			return null;
		}

	}
	
	public static String readJsonFile(String jsonFile) throws ParseException, IOException {
		Object jsonObject = null;
		JSONParser jsonParser = new JSONParser();
		String filename = "src/test/resources/api/" + jsonFile;
		FileReader reader = new FileReader(filename);
		jsonObject = jsonParser.parse(reader);
		return jsonObject.toString();
	}
}
