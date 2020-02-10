package tika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

public class HelloTika {

    //private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01"; // 
    private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01_bad_files"; //"C:\\Users\\lamp\\JavaEE\\LightComp\\tika";

    public static void main(String[] args) throws IOException {
        List<Path> files = Files.walk(Paths.get(PATH))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        Tika tika = new Tika();

        for (Path p : files) {
            String mimeType = tika.detect(p);
            //System.out.print(mimeType + "\t" + p);

            try (TikaInputStream inputStream = TikaInputStream.get(p)) {
                BodyContentHandler handler = new BodyContentHandler(-1);
                Metadata metadataFile = new Metadata();
                ParseContext context = new ParseContext();
                Parser parser = new AutoDetectParser();
                parser.parse(inputStream, handler, metadataFile, context);
            } catch (Exception e) {
                System.out.println(mimeType + "\t" + p);
                e.printStackTrace();
            }

        }
    }
}