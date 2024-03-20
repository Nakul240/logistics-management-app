package com.ff.logisticsmanangement.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ff.logisticsmanangement.dto.EstimatesDto;
import com.ff.logisticsmanangement.util.GeocodingHelper;

@Service
public class DistanceAndDurationEstimationService {

	private GeocodingHelper helper = new GeocodingHelper();

	private String apiKey = "711a40d5-dbdd-4c5a-b564-d2b215bcda6a";

	private String point1Lat = "";
	private String point1Lon = "";
	private String point2Lat = "";
	private String point2Lon = "";

	private String urlTemplate = "https://graphhopper.com/api/1/route?point=%s,%s&point=%s,%s&calc_points=false&key=%s";

	public EstimatesDto getTheDistanceAndTime(String loadingAddress, String unloadingAddress) throws ParseException {

		double[] loadingAddressCoordinates = helper.getLatitudeAndLongitude(loadingAddress);

		point1Lat = String.valueOf(loadingAddressCoordinates[0]);
		point1Lon = String.valueOf(loadingAddressCoordinates[1]);

		double[] unloadingAddressCoordinates = helper.getLatitudeAndLongitude(unloadingAddress);

		point2Lat = String.valueOf(unloadingAddressCoordinates[0]);
		point2Lon = String.valueOf(unloadingAddressCoordinates[1]);

		String url = String.format(urlTemplate, point1Lat, point1Lon, point2Lat, point2Lon, apiKey);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String responseBody = responseEntity.getBody();

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
		JSONArray jsonArray = (JSONArray) jsonObject.get("paths");

		// Extract the first path object
		JSONObject pathObject = (JSONObject) jsonArray.get(0);

		double distance = (double) pathObject.get("distance");
		long time = (long) pathObject.get("time");

		EstimatesDto estimates = new EstimatesDto(distance, time);

		return estimates;
	}

}
