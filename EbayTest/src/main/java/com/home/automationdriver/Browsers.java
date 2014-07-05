package com.home.automationdriver;

import java.util.HashMap;
import java.util.Map;

public enum Browsers {// enum with browsers Browsers constants list
	FIREFOX("firefox"),
	IE("ie"),
	CHROME("chrome"),
	OPERA("opera");
	
	private String browserKey;
	private static Map<String, Browsers> browserMap = new HashMap<String, Browsers>();
	static {
		for(Browsers type: Browsers.values()){
			browserMap.put(type.key(), type);//complete map
		}
	}
	
	private Browsers(String key){
		browserKey = key;
	}
	
	private String key(){
		return this.browserKey;
	}
	
	public static Browsers get(String key){
		if (browserMap.containsKey(key)){// return true if value "key" is specified
			return browserMap.get(key);
		}
		return FIREFOX;
	}

}
