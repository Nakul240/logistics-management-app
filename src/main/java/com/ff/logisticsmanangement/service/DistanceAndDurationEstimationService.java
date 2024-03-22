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

/*
 * Distance and Duration taken to travel between to address is obtained using graphhopper api
 * 
 * Address is converted into lattitude and longitude using nominatim api
 * 
 * @see GeocodingHelper class
 */

@Service
public class DistanceAndDurationEstimationService {

	private GeocodingHelper helper = new GeocodingHelper();

	/*
	 * apiKey of graphhopper api
	 */
	private String apiKey = "711a40d5-dbdd-4c5a-b564-d2b215bcda6a";

	private String point1Lat = "";
	private String point1Lon = "";
	private String point2Lat = "";
	private String point2Lon = "";

	private String urlTemplate = "https://graphhopper.com/api/1/route?point=%s,%s&point=%s,%s&calc_points=false&key=%s";

	public EstimatesDto getTheDistanceAndTime(String loadingAddress, String unloadingAddress) throws ParseException {

		/*
		 * lat and lon is obtain in a double array through the GeoCodingHelper class and stored as string
		 */
		double[] loadingAddressCoordinates = helper.getLatitudeAndLongitude(loadingAddress);

		point1Lat = String.valueOf(loadingAddressCoordinates[0]);
		point1Lon = String.valueOf(loadingAddressCoordinates[1]);

		double[] unloadingAddressCoordinates = helper.getLatitudeAndLongitude(unloadingAddress);

		point2Lat = String.valueOf(unloadingAddressCoordinates[0]);
		point2Lon = String.valueOf(unloadingAddressCoordinates[1]);

		String url = String.format(urlTemplate, point1Lat, point1Lon, point2Lat, point2Lon, apiKey);

		/*
		 * RestTemplate is used to connect to third party api i.e graphhopper api
		 */
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String responseBody = responseEntity.getBody();

		/*
		 *  reponseBody which is the json format is converted into Array format through json-simple parser
		 *  reponseBody example : {"hints":{"visited_nodes.sum":46,"visited_nodes.average":46.0},"info":{"copyrights":["GraphHopper","OpenStreetMap contributors"],"took":1,"road_data_timestamp":"2024-03-17T08:00:00Z"},"paths":[{"distance":17131.963,"weight":1186.78507,"time":929806,"transfers":0,"snapped_waypoints":"apaiAe`eoMglHvwU"}]}
		 */
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
		JSONArray jsonArray = (JSONArray) jsonObject.get("paths");

		/*
		 * paths object is obtained first which is an Array , paths contains distance and time 
		 * 
		 * get operation is done based on key 
		 */
		JSONObject pathObject = (JSONObject) jsonArray.get(0);

		/*
		 * from the paths array , distance and time is obtained 
		 */
		double distance = (double) pathObject.get("distance");
		long time = (long) pathObject.get("time");

		EstimatesDto estimates = new EstimatesDto();
		estimates.setDistance(distance);
		estimates.setTime(time);

		return estimates;
	}

}
