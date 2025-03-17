package com.spring.json_date_converter.getconvert;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.json_date_converter.common.Utils;

public class ConvertDateByPattern {

	Utils utils;

	private ObjectMapper mapper = new ObjectMapper();

	private final Pattern JSON_OBJECT_PATTERN = Pattern.compile("\"([^\"]+)\"\\s*:\\s*\\{");

	public ConvertDateByPattern(String[] formats, String targetFormat) {
		utils = new Utils(formats, targetFormat);
	}

	public String GetConvertDateByPattern(String jsonString) {
		return updateJson(jsonString);
	}

	public String updateJson(String jsonString) {
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			Map<String, JSONObject> jsonKeys = new LinkedHashMap<>();

			extractJsonObjects(jsonObject, "", jsonKeys);

			for (String path : jsonKeys.keySet()) {
				updateJsonValue(jsonObject, path);
			}

			return mapper.writeValueAsString(jsonKeys);
		} catch (Exception e) {
			return e.toString();
		}
	}

	public void extractJsonObjects(JSONObject jsonObject, String parentPath, Map<String, JSONObject> keys) {
		Matcher matcher = JSON_OBJECT_PATTERN.matcher(jsonObject.toString());
		while (matcher.find()) {
			String key = matcher.group(1);
			String fullPath = parentPath.isEmpty() ? key : parentPath + "." + key;

			if (jsonObject.has(key) && jsonObject.get(key) instanceof JSONObject) {
				keys.put(fullPath, jsonObject.getJSONObject(key));
				extractJsonObjects(jsonObject.getJSONObject(key), fullPath, keys);
			}
		}
	}

	public void updateJsonValue(JSONObject jsonObject, String jsonPath) {
		String[] keys = jsonPath.split("\\.");
		JSONObject currentObject = jsonObject;

		for (int i = 0; i < keys.length - 1; i++) {
			String key = keys[i];

			currentObject = currentObject.getJSONObject(key);
		}
		Map<String, String> datestr = utils.extractDateFields(currentObject.toString());
		for (String datestr1 : datestr.keySet()) {
			if (!currentObject.optString(datestr1).isBlank()) {
				currentObject.put(datestr1, utils.getDateString(currentObject.optString(datestr1)));
			}

		}
	}

}
