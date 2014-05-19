package org.p7h.storm.sentimentanalysis.utils;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.io.Files;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by 078831 on 5/15/2014.
 */
public class GoogleMapsLookup {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleMapsLookup.class);
    private static final String GOOGLE_MAPS_URL = "https://maps.googleapis.com/maps/api/geocode/json?sensor=false&result_type=administrative_area_level_2&location_type=APPROXIMATE&key=";

    public static void main(String[] args) {
        double latitude = 51.6884939;
        double longitude= -4.1554822;
        Optional<String> state = reverseGeocodeFromLatLong(latitude, longitude);
        if(state.isPresent()) {
            LOGGER.info("State==>{}", state.get());
        } else {
            LOGGER.info("Could not find State!!");
        }
    }

    public final static Optional<String> reverseGeocodeFromLatLong(final double latitude, final double longitude) {
        final StringBuilder googleMapsURL = new StringBuilder();
        googleMapsURL
                .append(GOOGLE_MAPS_URL)
                .append(Constants.GOOGLE_MAPS_API_KEY_VALUE)
                .append("&latlng=")
                .append(latitude)
                .append(",")
                .append(longitude);
		//LOGGER.debug("GoogleMapsURL==>{}", googleMapsURL.toString());
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        try {
            final URL url = new URL(googleMapsURL.toString());
            httpURLConnection = (HttpURLConnection)url.openConnection();

            if(HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()){
                inputStream = httpURLConnection.getInputStream();
                return getStateFromJSONResponse(inputStream);
            }
        } catch (final Throwable throwable) {
            LOGGER.error(throwable.getMessage(), throwable);
            throwable.printStackTrace();
        } finally{
            if(null != inputStream) {
                try {
                    inputStream.close();
                } catch (final IOException ioException) {
                    LOGGER.error(ioException.getMessage(), ioException);
                    ioException.printStackTrace();
                }
            }
            httpURLConnection = null;
        }
        return Optional.absent();
    }

    private final static Optional<String> getStateFromJSONResponse(InputStream inputStream) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
//            final Map<String,Object> googleResponse = (Map<String, Object>) mapper.readValue(new File
//             ("D:/GoogleMaps_JSON_Response.json"), Map.class);
            final Map<String,Object> googleResponse = (Map<String, Object>) mapper.readValue(inputStream, Map.class);
            if(Constants.GOOGLE_MAPS_RESPONSE_OK.equalsIgnoreCase((String)googleResponse.get("status"))) {
                final List<Map<String, Object>> results = (List<Map<String, Object>>) googleResponse.get("results");
                if(results != null && results.size() > 0){
                    final String resources = (String) results.get(0).get("formatted_address");
                    return Optional.of(resources.replace(", UK", ""));
                }
            }
        } catch (final IOException ioException) {
            LOGGER.error(ioException.getMessage(), ioException);
            ioException.printStackTrace();
        }
        return Optional.absent();
    }
}

/*
https://maps.googleapis.com/maps/api/geocode/json?latlng=51.6884939,-4.1554822&sensor=false&result_type=administrative_area_level_2&key=<YOUR_GMAPS_API_KEY>
{
    "results": [
        {
            "address_components": [
                {
                    "long_name": "Carmarthenshire",
                    "short_name": "Carmarthenshire",
                    "types": [
                        "administrative_area_level_2",
                        "political"
                    ]
                },
                {
                    "long_name": "Wales",
                    "short_name": "Wales",
                    "types": [
                        "administrative_area_level_1",
                        "political"
                    ]
                },
                {
                    "long_name": "United Kingdom",
                    "short_name": "GB",
                    "types": [
                        "country",
                        "political"
                    ]
                }
            ],
            "formatted_address": "Carmarthenshire, UK",
            "geometry": {
                "bounds": {
                    "northeast": {
                        "lat": 52.1423962,
                        "lng": -3.6471249
                    },
                    "southwest": {
                        "lat": 51.6547722,
                        "lng": -4.723076
                    }
                },
                "location": {
                    "lat": 51.8598535,
                    "lng": -4.2608531
                },
                "location_type": "APPROXIMATE",
                "viewport": {
                    "northeast": {
                        "lat": 52.1423962,
                        "lng": -3.6471249
                    },
                    "southwest": {
                        "lat": 51.6547722,
                        "lng": -4.723076
                    }
                }
            },
            "types": [
                "administrative_area_level_2",
                "political"
            ]
        }
    ],
    "status": "OK"
}
 */