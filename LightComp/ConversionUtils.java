package com.lightcomp.nda.servicecommon.conversion;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversionUtils {

    private static final Logger log = LoggerFactory.getLogger(ConversionUtils.class);

    /**
     * Vrátí hodnotu pole objektu jako String nebo null
     * @param fieldName
     * @param object
     * @return String
     */
    public static String getDataFromField(String fieldName, Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (fieldName.equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(object);
                    field.setAccessible(false);
                    if (value != null && field.getType().getName().equals("java.util.List")) {
                        // seznam polí pro objekt
                        String names = null;
                        if (((List<?>) value).get(0).getClass().getName().endsWith("SouvisejiciOsoba")) {
                            names = "jmeno,identifikator,druhVztahu";
                        }
                        return convertListToString((List<?>) value, names);
                    }
                    if (value != null) {
                        return (String) value.toString();
                    } else {
                        return null;
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("Fail to getting field value", e);
                    throw new IllegalStateException("Fail to getting field value");
                }
            }
        }
        return null;
    }

    /**
     * Vrátí seznam hodnot polí ze seznamu objektů nebo null
     * @param list of object
     * @param name seznam zobrazených polí nebo null (všechna pole)
     * @return String
     */
    public static String convertListToString(List<?> list, String names) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.get(0).getClass().getName().equals("java.lang.String")) {
            return list.toString();
        }
        List<String> listNames = null;
        if (names != null) {
            listNames = Arrays.asList(names.split(","));
        }
        StringBuilder result = new StringBuilder();
        for (Object o : list) {
            result.append("(");
            for (Field field : o.getClass().getDeclaredFields()) {
                if (names == null || listNames.contains(field.getName())) {
                    String value;
                    try {
                        field.setAccessible(true);
                        value = field.get(o).toString();
                        field.setAccessible(false);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        value = "null";
                    }
                    result.append(value + ", ");
                }
            }
            result.delete(result.length() - 2, result.length()).append("),");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    /**
     * Vrátí seznam hodnot polí ze seznamu objektů nebo null
     * @param list of object
     * @return String
     */
    public static String convertListToString(List<?> list) {
        return convertListToString(list, null);
    }

}
