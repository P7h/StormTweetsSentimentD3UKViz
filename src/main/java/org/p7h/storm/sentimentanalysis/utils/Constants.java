package org.p7h.storm.sentimentanalysis.utils;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Constants used in this project.
 *
 * @author - Prashanth Babu
 */
public final class Constants {
	//Name of the Topology. Used while launching the LocalCluster
	public static final String TOPOLOGY_NAME = "SentimentAnalysis";

	//Properties file which has all the configurable parameters required for execution of this Topology.
	public static final String CONFIG_PROPERTIES_FILE = "config.properties";

	public static final String OAUTH_ACCESS_TOKEN = "OAUTH_ACCESS_TOKEN";
	public static final String OAUTH_ACCESS_TOKEN_SECRET = "OAUTH_ACCESS_TOKEN_SECRET";
	public static final String OAUTH_CONSUMER_KEY = "OAUTH_CONSUMER_KEY";
	public static final String OAUTH_CONSUMER_SECRET = "OAUTH_CONSUMER_SECRET";

	public static final String GOOGLE_MAPS_API_KEY = "GOOGLE_MAPS_API_KEY";

	//Bolt reads the Google Maps API Value and stores the same to GOOGLE_MAPS_API_KEY_VALUE of Constants.java so that it can be used for reverse geocoding.
	//For the lack of time I am using this Constant or else using a good Design Pattern, this can be fine-tuned.
	public static String GOOGLE_MAPS_API_KEY_VALUE = "GOOGLE_MAPS_API_KEY_VALUE";
	public static String GOOGLE_MAPS_RESPONSE_OK = "OK";

	//Sentiment scores of few words are present in this file.
	//For more info on this, please check: http://www2.imm.dtu.dk/pubdb/views/publication_details.php?id=6010
	public static final String AFINN_SENTIMENT_FILE_NAME = "AFINN-111.txt";

	//Codes of all the states of USA.
	//Used as a precautionary measure so that we can be completely sure that the State we got is indeed one of US States.
	public static final List<String> CONSOLIDATED_STATE_CODES = Lists.newArrayList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "MD", "MA", "MI", "MN", "MS", "MO", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY");

	//Map to hold the State code and its corresponding name.
	public static final Map<String, String> MAP_STATE_CODE_NAME = new HashMap<String, String>() {{
        put("East Ayrshire", "GBR-2001");
        put("East Dunbartonshire", "GBR-2002");
        put("East Renfrewshire", "GBR-2003");
        put("Glasgow", "GBR-2004");
        put("Inverclyde", "GBR-2005");
        put("North Ayshire", "GBR-2006");
        put("North Lanarkshire", "GBR-2007");
        put("Renfrewshire", "GBR-2008");
        put("South Ayrshire", "GBR-2009");
        put("South Lanarkshire", "GBR-2010");
        put("West Dunbartonshire", "GBR-2011");
        put("Aberdeen", "GBR-2012");
        put("Aberdeenshire", "GBR-2013");
        put("Moray", "GBR-2014");
        put("Falkirk", "GBR-2015");
        put("Stirling", "GBR-2016");
        put("Clackmannanshire", "GBR-2017");
        put("Perthshire and Kinross", "GBR-2018");
        put("Angus", "GBR-2019");
        put("Dundee", "GBR-2020");
        put("Fife", "GBR-2021");
        put("East Lothian", "GBR-2022");
        put("Edinburgh", "GBR-2023");
        put("Midlothian", "GBR-2024");
        put("West Lothian", "GBR-2025");
        put("Scottish Borders", "GBR-2026");
        put("Darlington", "GBR-2028");
        put("Durham", "GBR-2029");
        put("Hartlepool", "GBR-2030");
        put("Middlesbrough", "GBR-2031");
        put("Redcar and Cleveland", "GBR-2032");
        put("Stockton-on-Tees", "GBR-2033");
        put("Northumberland", "GBR-2034");
        put("Hampshire", "GBR-2035");
        put("Southampton", "GBR-2036");
        put("Milton Keynes", "GBR-2038");
        put("Gloucestershire", "GBR-2039");
        put("Buckinghamshire", "GBR-2040");
        put("Hertfordshire", "GBR-2042");
        put("Bath and North East Somerset", "GBR-2043");
        put("Bristol", "GBR-2044");
        put("North Somerset", "GBR-2045");
        put("South Gloucestershire", "GBR-2046");
        put("Somerset", "GBR-2047");
        put("Devon", "GBR-2048");
        put("Bournemouth", "GBR-2050");
        put("Dorset", "GBR-2051");
        put("Poole", "GBR-2052");
        put("Cambridgeshire", "GBR-2053");
        put("Leicestershire", "GBR-2054");
        put("Kingston upon Hull", "GBR-2055");
        put("North East Lincolnshire", "GBR-2056");
        put("North Lincolnshire", "GBR-2057");
        put("Derby", "GBR-2058");
        put("Derbyshire", "GBR-2059");
        put("Barking and Dagenham", "GBR-2060");
        put("Bexley", "GBR-2061");
        put("Brent", "GBR-2062");
        put("Bromley", "GBR-2063");
        put("Camden", "GBR-2064");
        put("Croydon", "GBR-2065");
        put("Ealing", "GBR-2066");
        put("Enfield", "GBR-2067");
        put("Greenwich", "GBR-2068");
        put("Hammersmith and Fulham", "GBR-2069");
        put("Hounslow", "GBR-2070");
        put("Islington", "GBR-2071");
        put("Kensington and Chelsea", "GBR-2072");
        put("Merton", "GBR-2073");
        put("Redbridge", "GBR-2074");
        put("Richmond upon Thames", "GBR-2075");
        put("Sutton", "GBR-2076");
        put("Tower Hamlets", "GBR-2077");
        put("Waltham Forest", "GBR-2078");
        put("Wandsworth", "GBR-2079");
        put("Westminster", "GBR-2080");
        put("Lincolnshire", "GBR-2081");
        put("Belfast", "GBR-2082");
        put("Derry", "GBR-2083");
        put("Omagh", "GBR-2084");
        put("Armagh", "GBR-2085");
        put("Newry and Mourne", "GBR-2086");
        put("Banbridge", "GBR-2087");
        put("Craigavon", "GBR-2088");
        put("Dungannon", "GBR-2089");
        put("Lisburn", "GBR-2090");
        put("Cookstown", "GBR-2091");
        put("Antrim", "GBR-2092");
        put("Magherafelt", "GBR-2093");
        put("Ballymena", "GBR-2094");
        put("Larne", "GBR-2095");
        put("Carrickfergus", "GBR-2096");
        put("Newtownabbey", "GBR-2097");
        put("North Down", "GBR-2098");
        put("Down", "GBR-2099");
        put("Coleraine", "GBR-2100");
        put("Ballymoney", "GBR-2101");
        put("Limavady", "GBR-2102");
        put("Castlereagh", "GBR-2103");
        put("Carmarthenshire", "GBR-2104");
        put("Ceredigion", "GBR-2105");
        put("Pembrokeshire", "GBR-2106");
        put("Halton", "GBR-2108");
        put("Cornwall", "GBR-2110");
        put("Powys", "GBR-2111");
        put("Bridgend", "GBR-2112");
        put("Caerphilly", "GBR-2113");
        put("Merthyr Tydfil", "GBR-2114");
        put("Rhondda", "GBR-2115");
        put("Cardiff", "GBR-2116");
        put("Vale of Glamorgan", "GBR-2117");
        put("Neath Port Talbot", "GBR-2118");
        put("Swansea", "GBR-2119");
        put("York", "GBR-2120");
        put("Telford and Wrekin", "GBR-2121");
        put("Blackburn with Darwen", "GBR-2122");
        put("Lancashire", "GBR-2123");
        put("East Riding of Yorkshire", "GBR-2124");
        put("Denbighshire", "GBR-2125");
        put("Flintshire", "GBR-2126");
        put("Wrexham", "GBR-2127");
        put("Anglesey", "GBR-2128");
        put("Conwy", "GBR-2129");
        put("Gwynedd", "GBR-2130");
        put("Blaenau Gwent", "GBR-2131");
        put("Monmouthshire", "GBR-2132");
        put("Newport", "GBR-2133");
        put("Torfaen", "GBR-2134");
        put("Strabane", "GBR-2135");
        put("Fermanagh", "GBR-2136");
        put("Ards", "GBR-2137");
        put("Dumfries and Galloway", "GBR-2138");
        put("Cumbria", "GBR-2139");
        put("North Yorkshire", "GBR-2140");
        put("Plymouth", "GBR-2141");
        put("Torbay", "GBR-2142");
        put("Essex", "GBR-2317");
        put("Suffolk", "GBR-2318");
        put("Norfolk", "GBR-2319");
        put("Brighton and Hove", "GBR-2674");
        put("Havering", "GBR-2675");
        put("Thurrock", "GBR-2676");
        put("East Sussex", "GBR-2677");
        put("Medway", "GBR-2678");
        put("Southend-on-Sea", "GBR-2679");
        put("Orkney", "GBR-2744");
        put("Highland", "GBR-2745");
        put("Argyll and Bute", "GBR-2746");
        put("Shetland Islands", "GBR-2747");
        put("West Sussex", "GBR-2748");
        put("Northamptonshire", "GBR-2749");
        put("Warwickshire", "GBR-2750");
        put("Oxfordshire", "GBR-2751");
        put("Luton", "GBR-2752");
        put("Hillingdon", "GBR-2753");
        put("Kingston upon Thames", "GBR-2754");
        put("Surrey", "GBR-2755");
        put("Swindon", "GBR-2756");
        put("Wiltshire", "GBR-2757");
        put("Isle of Wight", "GBR-2758");
        put("Portsmouth", "GBR-2759");
        put("Peterborough", "GBR-2760");
        put("Leicester", "GBR-2761");
        put("Rutland", "GBR-2762");
        put("Nottingham", "GBR-2763");
        put("Nottinghamshire", "GBR-2764");
        put("Hackney", "GBR-2765");
        put("Haringey", "GBR-2766");
        put("Harrow", "GBR-2767");
        put("Lambeth", "GBR-2768");
        put("Lewisham", "GBR-2769");
        put("Newham", "GBR-2770");
        put("Southwark", "GBR-2771");
        put("Eilean Siar", "GBR-2772");
        put("Moyle", "GBR-2773");
        put("Warrington", "GBR-2774");
        put("Herefordshire", "GBR-2775");
        put("Worcestershire", "GBR-2776");
        put("Staffordshire", "GBR-2777");
        put("Stoke-on-Trent", "GBR-2778");
        put("Shropshire", "GBR-2779");
        put("Kent", "GBR-3409");
        put("City", "GBR-4809");
        put("Newcastle upon Tyne", "GBR-5661");
        put("North Tyneside", "GBR-5662");
        put("South Tyneside", "GBR-5663");
        put("Sunderland", "GBR-5664");
        put("Gateshead", "GBR-5665");
        put("Knowsley", "GBR-5666");
        put("Sefton", "GBR-5667");
        put("Liverpool", "GBR-5668");
        put("Merseyside", "GBR-5669");
        put("Blackpool", "GBR-5670");
        put("Kirklees", "GBR-5671");
        put("Calderdale", "GBR-5672");
        put("Bradford", "GBR-5673");
        put("Leeds", "GBR-5674");
        put("Wakefield", "GBR-5675");
        put("Salford", "GBR-5676");
        put("Wigan", "GBR-5677");
        put("Bolton", "GBR-5678");
        put("Bury", "GBR-5679");
        put("Rochdale", "GBR-5680");
        put("Oldham", "GBR-5681");
        put("Tameside", "GBR-5682");
        put("Stockport", "GBR-5683");
        put("Manchester", "GBR-5684");
        put("Trafford", "GBR-5685");
        put("Rotherham", "GBR-5686");
        put("Sheffield", "GBR-5687");
        put("Barnsley", "GBR-5688");
        put("Doncaster", "GBR-5689");
        put("Birmingham", "GBR-5690");
        put("Sandwell", "GBR-5691");
        put("Dudley", "GBR-5692");
        put("Wolverhampton", "GBR-5693");
        put("Walsall", "GBR-5694");
        put("Solihull", "GBR-5695");
        put("Coventry", "GBR-5696");
        put("Central Bedfordshire", "GBR-5697");
        put("Bedford", "GBR-5698");
        put("Reading", "GBR-5699");
        put("West Berkshire", "GBR-5700");
        put("Wokingham", "GBR-5701");
        put("Bracknell Forest", "GBR-5702");
        put("Royal Borough of Windsor and Maidenhead", "GBR-5703");
        put("Slough", "GBR-5704");
        put("Barnet", "GBR-5705");
        put("Cheshire East", "GBR-5706");
        put("Cheshire West and Chester", "GBR-5707");
        put("Halton", "GBR-5708");
        put("Isles of Scilly", "GBR-5988");
        put("Isle of Man", "IMN+00");
	}};
}
