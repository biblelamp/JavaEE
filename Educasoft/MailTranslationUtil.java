package com.itrivio.server.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailTranslationUtil {

	final static String PATH_TO_TEMPLATES = "C:/Users/lamp/itrivio/com.itrivio/src/main/resources/emails";

	public static void main(String... args) {
		scanFolder(PATH_TO_TEMPLATES);
	}

	public static void scanFolder(String path) {
		File folder = new File(path);
		for (File file : folder.listFiles()) {
			if (file.isFile() && file.getName().endsWith(".vm")) {
				String name = file.getName();
				String key = name.substring(0, name.length() - 6);
				String language = name.substring(name.length() - 5, name.length() - 3);
				String value = reaFileAsString(file).replace("\t", "\\t");
				if (language.equals("de")) {
					System.out.println("    \"" + key + "\": \"" + value + "\",");
				}
			}
		}
	}

	private static String reaFileAsString(File file) {
		return String.join("\\n", readFileAsList(file));
	}

    private static List<String> readFileAsList(File file) {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                //if (!line.startsWith("#") && !line.isEmpty()) {
                    list.add(line);
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}