package com.ff.logisticsmanangement.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*
 * here locations from an address is converted into lattitudes and longitudes using nominatim api
 */
public class GeocodingHelper {

	public double[] getLatitudeAndLongitude(String address) {

		JSONObject loadingLocation;
		try {
			loadingLocation = geocodeAddress(address);

			double lat = Double.parseDouble((String) loadingLocation.get("lat"));
			double lon = Double.parseDouble((String) loadingLocation.get("lon"));

			double coordinates[] = { lat, lon };
			return coordinates;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static JSONObject geocodeAddress(String address) throws ParseException {
		/*
		 * RestTemplate is used to connect to the third party api i.e nominatim api
		 * 
		 * address is passed as a query string in the api uri
		 */
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://nominatim.openstreetmap.org/search?q=" + address + "&format=json";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String responseBody = responseEntity.getBody();

		/*
		 * json-simple is used to parse the json responseBody into an array containing lattitude and longitude
		 */
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(responseBody);
		
		/*
		 * address contains local area i.e is street name and district 
		 * local area is checked first , if this failed district is checked
		 */
		if (jsonArray.size() > 0) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(0); 
			return (JSONObject) jsonObject;
		}
		else {
			String[] split = address.split(" ");
			if(split.length == 1) {
				throw new IndexOutOfBoundsException("Address not valid. Please try with correct credentials");
			}
			return geocodeAddress(split[1]);
		}
		

	}

}
