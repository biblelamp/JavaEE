package cz.educasoft.trombon.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConvertLogStrings {

    static String path = "C:/Users/lamp/eclipse-workspace/Trombon-core/src/main/java";
    static String ext = ".java";
    static String search = "log.";
    static String plus = "+";

    public static void main(String[] args) {
        new ConvertLogStrings().search(new File(path));
    }

    private void readAndChangeFile(File file) {
        boolean isChanged = false;
        List<String> list = readFileAsList(file);

        for (String str : list) {
            if (str.indexOf(search) > -1 && str.indexOf(plus) > -1) {

                if (!isChanged) {
                    System.out.println("\n" + file.getName() + "\n");
                    isChanged = true;
                }

                int x1 = str.indexOf("(") + 1;
                int x2 = str.lastIndexOf(")");

                if (str.indexOf(", e") > -1 && str.lastIndexOf("+") < str.indexOf(", e")) {
                    x2 = str.lastIndexOf(", e");
                }

                String s1 = str.substring(x1, x2);

                String s2 = str.substring(0, x1) + transformString(s1) + str.substring(x2);

                System.out.println(str.trim());
                System.out.println(s2.trim());

            }
        }
    }

    private String transformString(String str) {
        boolean isString = false;
        List<String> args = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        StringBuffer arg = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '"') {
                isString = !isString;

                if (isString && arg.length() > 0) {
                    args.add(arg.toString().trim());
                    arg.setLength(0);
                }

            } else {
                if (isString) {
                    sb.append(str.charAt(i));
                } else {
                    if (str.charAt(i) == '+') {
                        if (sb.length() == 0) {
                            sb.append("{}");
                        } else {
                            if (sb.charAt(sb.length() - 1) == '{') {
                                sb.append("}");
                            } else {
                                sb.append("{");
                            }
                        }
                    } else {
                        arg.append(str.charAt(i));
                    }
                }
            }
        }

        if (sb.charAt(sb.length() - 1) == '{') {
            sb.append("}");
        }

        if (arg.length() > 0) {
            args.add(arg.toString().trim());
        }

        String listArgs = args.toString();

        return "\"" + sb.toString() + "\", "+ listArgs.substring(1, listArgs.length() - 1);
    }
    
    private List<String> readFileAsList(File file) {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void search(File file) {
        if (file.isDirectory()) {
            //System.out.println("Scanning directory ... " + file.getAbsoluteFile());

            if (file.canRead()) {
                for (File item : file.listFiles()) {
                    if (item.isDirectory()) {
                        search(item);
                    } else {
                        if (item.getName().toLowerCase().endsWith(ext)) {
                            readAndChangeFile(item);
                        }
                    }
                }
            } else {
                System.out.println(file.getAbsoluteFile() + " permission Denied");
            }
        }
    }
}