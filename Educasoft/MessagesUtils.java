package net.jforum.util;

import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.gwt.user.client.rpc.core.java.util.Arrays;
import com.sun.tools.javac.code.Attribute.Array;

public class MessagesUtils {

	final static String PATH = "C:/Users/lamp/itrivio/com.itrivio/src/main/resources/";
	
	final static String FIRST_FILE = "jforumConfig/languages/en_US.properties";
	final static String SECOND_FILE = "messages_en.properties";

	public static void main(String... args) throws Exception {
		Map<String, String> msgForum = loadProperties(PATH + FIRST_FILE);
		Map<String, String> msgMain = loadProperties(PATH+ SECOND_FILE);
		
//		System.out.println(msgForum.size());
//		System.out.println(msgMain.size());
		
		for (String key : msgForum.keySet()) {
			if (msgMain.containsKey(key)) {
				msgMain.remove(key);
				 //System.out.println(msgForum.size());
			} else {
				//System.out.println(msgForum.size());
			}
			//System.out.print(key);
			//System.out.println(" - " + msgForum.remove(key));
		}

		String[] keys = msgMain.keySet().stream().toArray(String[]::new);
		java.util.Arrays.sort(keys);

		for (String key : keys) {
			System.out.println(key + "=" + msgMain.get(key));
		}
		System.out.println("Done.");
	}

    private static Map<String,String> loadProperties(String fileName) throws Exception {
    	Map<String, String> propertyMap = new HashMap<>();

    	FileReader reader = new FileReader(fileName);
    	Properties p = new Properties();
    	p.load(reader);
 
		Enumeration keys = p.propertyNames();
	    while(keys.hasMoreElements()) {
	    	String key = (String)keys.nextElement();
	    	propertyMap.put(key, p.getProperty(key));
	    }
	    reader.close();
	    return propertyMap;
    }

}
