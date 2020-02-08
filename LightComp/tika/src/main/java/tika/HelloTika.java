package tika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloTika {

    final static String path = "C:\\Users\\lamp\\JavaEE\\LightComp\\tika";

    public static void main(String[] args) throws IOException {
        Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .forEach(System.out::println);
    }
}