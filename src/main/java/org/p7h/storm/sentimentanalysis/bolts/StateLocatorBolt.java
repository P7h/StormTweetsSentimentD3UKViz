package org.p7h.storm.sentimentanalysis.bolts;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.google.common.base.Optional;
import org.p7h.storm.sentimentanalysis.utils.Constants;
import org.p7h.storm.sentimentanalysis.utils.GoogleMapsLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.GeoLocation;
import twitter4j.Status;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Gets the location of tweet by all 3 means and then fwds the State code with the tweet to the next Bolt.
 * There are three different objects within a tweet that we can use to determine it’s origin.
 * This Class utilizes all the three of them and prioritizes in the following order [high to low]:
 *  1. The coordinates object
 *  2. The place object
 *  3. The user object
 *
 * @author - Prashanth Babu
 */
public final class StateLocatorBolt extends BaseRichBolt {
	private static final Logger LOGGER = LoggerFactory.getLogger(StateLocatorBolt.class);
	private static final long serialVersionUID = 335100368035784611L;
    private OutputCollector _outputCollector;

	public StateLocatorBolt() {
		//No op
	}

	@Override
	public final void prepare(final Map map, final TopologyContext topologyContext,
	                          final OutputCollector outputCollector) {
		this._outputCollector = outputCollector;

		final Properties properties = new Properties();
		try {
			properties.load(StateLocatorBolt.class.getClassLoader()
					                .getResourceAsStream(Constants.CONFIG_PROPERTIES_FILE));
		} catch (final IOException ioException) {
			//Should not occur. If it does, we cant continue. So exiting the program!
			LOGGER.error(ioException.getMessage(), ioException);
			System.exit(1);
		}
		//Bolt reads the Google Maps API Value and stores the same to GOOGLE_MAPS_API_KEY_VALUE of Constants.java so that the Bolt can use it.
		//For the lack of time I am using this Constant or else using a good Design Pattern, this can be fine-tuned.
		//For Google Maps Key: https://google-developers.appspot.com/maps/documentation/geocoding/#api_key
		Constants.GOOGLE_MAPS_API_KEY_VALUE = properties.getProperty(Constants.GOOGLE_MAPS_API_KEY);
	}

	@Override
	public final void declareOutputFields(final OutputFieldsDeclarer outputFieldsDeclarer) {
		//Emit the state and also the complete tweet to the next Bolt.
		outputFieldsDeclarer.declare(new Fields("state", "tweet"));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final void execute(final Tuple input) {
		final Status status = (Status) input.getValueByField("tweet");
		final Optional<String> stateOptional = getStateFromTweet(status);
		if(stateOptional.isPresent()) {
			final String state = stateOptional.get();
			//Emit the state and also the complete tweet to the next Bolt.
			this._outputCollector.emit(new Values(state, status));
		}
	}

    private static final String[] states = new String[] {"GBR-2001", "GBR-2002", "GBR-2003", "GBR-2004", "GBR-2005",
            "GBR-2006", "GBR-2007", "GBR-2008", "GBR-2009", "GBR-2010", "GBR-2011", "GBR-2012", "GBR-2013", "GBR-2014", "GBR-2015", "GBR-2016", "GBR-2017", "GBR-2018", "GBR-2019", "GBR-2020", "GBR-2021", "GBR-2022", "GBR-2023", "GBR-2024", "GBR-2025", "GBR-2026", "GBR-2028", "GBR-2029", "GBR-2030", "GBR-2031", "GBR-2032", "GBR-2033", "GBR-2034", "GBR-2035"};
    static Random random = new Random();

	/**
	 * Tries to get the State of the tweet by checking first GeoLocation Object, then Place Object and finally User Object.
	 *
	 * @param status -- Status Object.
	 * @return State of the Tweet.
	 */
	private final Optional<String> getStateFromTweet(final Status status) {
		String state = getStateFromTweetGeoLocation(status);
        // String state = states[random.nextInt(states.length)];

		if(null == state) {
			//LOGGER.info("Skipping invalid State: {}.", state);
			return Optional.absent();
		}
		//LOGGER.debug("State:{}", state);
		return Optional.of(state);
	}

	/**
	 * Retrieves the State from GeoLocation Object of the Tweet.
	 * This is considered as the primary and correct value for the State of the tweet.
	 * Can also try: http://www.fcc.gov/developers/census-block-conversions-api
	 *
	 * @param status -- Status Object.
	 * @return State of tweet.
	 */
	private final String getStateFromTweetGeoLocation(final Status status) {
		String state = null;
		final double latitude;
		final double longitude;
		final GeoLocation geoLocation = status.getGeoLocation();
		if (null != geoLocation) {
			latitude = geoLocation.getLatitude();
			longitude = geoLocation.getLongitude();
//			LOGGER.debug("LatLng for Google Maps:{} and {}", latitude, longitude);
			final Optional<String> stateGeoOptional = GoogleMapsLookup.reverseGeocodeFromLatLong(latitude, longitude);
			if(stateGeoOptional.isPresent()){
				final String stateFromGeoLocation = stateGeoOptional.get();
				//LOGGER.debug("State from GoogleMaps:{}", stateFromGeoLocation);
                state = Constants.MAP_STATE_CODE_NAME.getOrDefault(stateFromGeoLocation, null);
			}
		}
		return state;
	}
}