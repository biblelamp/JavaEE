package tika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HelloTika {

    final static String PATH = "C:\\Users\\lamp\\JavaEE\\LightComp\\tika";

    public static void main(String[] args) throws IOException {
        List<Path> files = Files.walk(Paths.get(PATH))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        for (Path path : files) {
            System.out.println(path);
        }
    }
}