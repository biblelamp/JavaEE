package cz.educasoft.trombon.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

/**
 * Java. Tool to replace text messages in log|logger.[info|error|debug] expression
 *       like (“one = “ + one + “ two = “ + two) to (“one = {} two = {}”, one, two)
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Feb 19, 2019
 */

public class ConvertLogStrings {

    static String path = "C:/Users/lamp/eclipse-workspace/DocumentTemplater/src/main/java";
    static String ext = ".java";
    static String search = "log.";
    static String plus = "+";
    static String comma = ",";
    static String leftBracket = "(";
    static String rightBracket = ")";
    static String quote = "\"";
    
    static String finalLogger = "final Logger";

    boolean saveChanges = true;

    public static void main(String[] args) {
        new ConvertLogStrings().search(new File(path));
    }

    private void readAndChangeFile(File file) {
        boolean isChanged = false;
        List<String> lines = readFileAsList(file);

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).trim().startsWith(finalLogger)) { // variable name of logger
                String[] fields = lines.get(i).split(" ");
                search = fields[2] + ".";
            }
            if (lines.get(i).indexOf(search) > -1 && (lines.get(i).indexOf(plus) > -1 || lines.get(i).indexOf(");") == -1)) { // exclude string without '+'

                if (!isChanged) {
                    System.out.println("\n" + file.getName() + "\n");
                    isChanged = true;
                }

                int x1 = lines.get(i).indexOf(leftBracket) + 1;
                int x2 = lines.get(i).lastIndexOf(rightBracket);

                if (lines.get(i).indexOf(comma) > -1 
                        && lines.get(i).lastIndexOf(plus) < lines.get(i).indexOf(comma)
                        && lines.get(i).lastIndexOf(quote) < lines.get(i).indexOf(comma)) {
                    x2 = lines.get(i).lastIndexOf(comma);
                }

                if (x2 < 0) { // unclosed string
                    x2 = x1;
                }
                
                String s1 = lines.get(i).substring(x1, x2);

                String s2 = lines.get(i).substring(0, x1) + transformString(s1) + lines.get(i).substring(x2);

                System.out.println(lines.get(i).trim());
                System.out.println(s2.trim());

                lines.set(i, s2);                
            }
        }

        if (isChanged && saveChanges) {
            writeFile(file, lines);
        }
    }

    private void writeFile(File file, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String str : lines) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
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

        if (sb.length() > 1 && sb.charAt(sb.length() - 1) == '{') {
            sb.append("}");
        }

        if (arg.length() > 0) {
            args.add(arg.toString().trim());
        }

        String listArgs = args.toString();

        if (sb.length() > 0) {
            return "\"" + sb.toString() + "\", "+ listArgs.substring(1, listArgs.length() - 1);
        } else {
            return "";
        }
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