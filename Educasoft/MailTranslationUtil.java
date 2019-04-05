package com.itrivio.server.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MailTranslationUtil {

	final static String PATH_TO_TEMPLATES = "C:/Users/lamp/itrivio/com.itrivio/src/main/resources/emails/";
	final static String JSON_FILE_PL = "C:/Users/lamp/Downloads/itrivio-e-mail-templates-pl-PL.json";
	final static String LANG_IN_FILE_NAME = "_pl";
	final static String EXT_EMAIL_FILE = ".vm";

	public static void main(String... args) throws JSONException {
		//scanFolder(PATH_TO_TEMPLATES);
		createTranslationFiles(new File(JSON_FILE_PL));
	}

	public static void createTranslationFiles(File file) throws JSONException {
		JSONObject json = new JSONObject(String.join("\n", readFileAsList(file)));
		JSONArray names = json.names();
		for (int i = 0; i < names.length(); i++) {
			String key = names.getString(i);
			String text = (String)json.get(key);
			System.out.println(key);
			saveTextToFile(new File(PATH_TO_TEMPLATES + key + LANG_IN_FILE_NAME + EXT_EMAIL_FILE), text);
		}
	}

	public static void scanFolder(String path) {
		File folder = new File(path);
		for (File file : folder.listFiles()) {
			if (file.isFile() && file.getName().endsWith(EXT_EMAIL_FILE)) {
				String name = file.getName();
				String key = name.substring(0, name.length() - 6);
				String language = name.substring(name.length() - 5, name.length() - 3);
				String value = reaFileAsString(file).replace("\t", "\\t");
				if (language.equals("sk")) {
					System.out.println("\"" + key + "\": \"" + value + "\",");
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

    private static void saveTextToFile(File file, String text) {
    	try (OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
    		ow.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}