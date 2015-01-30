package com.xzymon.elearning.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Part;

public class HttpPartUtils {
	public static Map<String, Map<String, String>> headersMapProducer(Part part) {
		Map<String, String> particularHeaderMap = null;
		String[] fragments = null;
		String fragment = null;
		String[] pair = null;
		String name = null;
		String value = null;
		Map<String, Map<String, String>> headersMap = new HashMap<String, Map<String, String>>();
		for (String headerName : part.getHeaderNames()) {
			//logger.info("map: " + headerName);
			particularHeaderMap = new HashMap<String, String>();
			for (String header : part.getHeaders(headerName)) {
				fragments = header.split("; ");
				if(fragments.length>0){
					//logger.info("putting key:" + headerName + " value:" + fragments[0]);
					particularHeaderMap.put(headerName, fragments[0]);
				}
				for(int headerLoop=1; headerLoop<fragments.length; headerLoop++){
					fragment = fragments[headerLoop];
					pair = fragment.split("=\"");
					if(pair.length==2){
						name = pair[0];
						value = pair[1].substring(0, pair[1].length()-1);
						//logger.info("putting key:" + name + " value:" + value);
						particularHeaderMap.put(name, value);
					} else {
						//logger.info("pair.length <> 2");
					}
				}
			}
			headersMap.put(headerName, particularHeaderMap);
		}
		return headersMap;
	}
}
