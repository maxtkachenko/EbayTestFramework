package com.home.automationdriver;

import java.util.HashMap;
import java.util.Map;

public enum Browsers {
	FIREFOX("firefox"),
	IE("ie"),
	CHROME("chrome"),
	OPERA("opera");
	
	private String browserKey;
	private static Map<String, Browsers> browserMap = new HashMap<String, Browsers>();
	static {
		for(Browsers type: Browsers.values()){
			browserMap.put(type.key(), type);
		}
	}
	
	private Browsers(String key){
		browserKey = key;
	}
	
	private String key(){
		return this.browserKey;
	}
	
	public static Browsers get(String key){
		if (browserMap.containsKey(key)){
			return browserMap.get(key);
		}
		return FIREFOX;
	}

}
