package tika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tika.Tika;

public class HelloTika {

    final static String PATH = "C:\\Users\\lamp\\JavaEE\\LightComp\\tika";

    public static void main(String[] args) throws IOException {
        List<Path> files = Files.walk(Paths.get(PATH))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        Tika tika = new Tika();

        for (Path path : files) {
            String mimeType = tika.detect(path);
            System.out.println(mimeType + " " + path);
        }
    }
}