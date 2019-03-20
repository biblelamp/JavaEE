package net.jforum.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.lang.reflect.Method;

public class MessagesUtils {

	//final static String PATH = "C:/Users/lamp/itrivio/com.itrivio/src/main/resources/";
	final static String PATH = "C:/Users/lamp/itrivio/com.itrivio/src/main/java/com/itrivio/gwt/ui/client/constants/";
	
	final static String FIRST_FILE = "Constants_cs_CZ.properties";
	final static String SECOND_FILE = "Constants_cs.properties";
	
	final static String CLASS_NAME = "com.itrivio.gwt.ui.client.constants.Constants";

	public static void main(String... args) throws ClassNotFoundException {
		compareProperties(PATH + FIRST_FILE, PATH + SECOND_FILE);
		
		//compareJavaAndProperties(PATH + FIRST_FILE, CLASS_NAME);
	}

	private static void compareJavaAndProperties(String fileName, String className) throws ClassNotFoundException {
		Map<String, String> messages = loadProperties(fileName);
		List<String> list = getNamesOfMethods(className);
		
		for (String key : list) {
			if (messages.containsKey(key)) {
				messages.remove(key);
			} else {
				System.out.println(key + " - not found in " + fileName);
			}
		}
		
		for (String key : messages.keySet()) {
			System.out.println(key + " = " + messages.get(key));
		}
	}
	
	private static List<String> getNamesOfMethods(String className) throws ClassNotFoundException {
		Class<?> c = Class.forName(className);
		List<String> list = new LinkedList<>();
		for (Method m : c.getDeclaredMethods()) {
			list.add(m.getName());
		}
		return list;
	}

	private static void compareProperties(String fileName1, String fileName2) {
		Map<String, String> msg1 = loadProperties(fileName1);
		Map<String, String> msg2 = loadProperties(fileName2);
		
		System.out.println("Entries not contain in " + fileName2);
		
		for (String key : msg1.keySet()) {
			if (msg2.containsKey(key)) {
				msg2.remove(key);
			} else {
				System.out.println(key + " = " + msg1.get(key));
			}
		}

		String[] keys = msg2.keySet().stream().toArray(String[]::new);
		java.util.Arrays.sort(keys);

		System.out.println("\nRemaining entries in " + fileName2);

		for (String key : keys) {
			System.out.println(key + "=" + msg2.get(key));
		}
	}

    private static Map<String,String> loadProperties(String fileName) {
    	Map<String, String> propertyMap = new HashMap<>();
		Properties properties = new Properties();

    	try (FileReader reader = new FileReader(fileName)) {
    		properties.load(reader);
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}

		Enumeration<?> keys = properties.propertyNames();
	    while(keys.hasMoreElements()) {
	    	String key = (String)keys.nextElement();
	    	propertyMap.put(key, properties.getProperty(key));
	    }
	    return propertyMap;
    }
}
