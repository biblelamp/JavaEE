package tika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tika.Tika;

import com.thebuzzmedia.exiftool.ExifTool;
import com.thebuzzmedia.exiftool.ExifToolBuilder;
import com.thebuzzmedia.exiftool.Tag;
import com.thebuzzmedia.exiftool.core.UnspecifiedTag;

public class HelloTika {

    private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01\\JPG_14"; // 
    //private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01_bad_files"; //"C:\\Users\\lamp\\JavaEE\\LightComp\\tika";

    public static void main(String[] args) throws IOException {
        List<Path> files = Files.walk(Paths.get(PATH))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        Tika tika = new Tika();
        
        ExifTool exifTool = new ExifToolBuilder().withPath("C:\\Program Files\\Java\\jdk1.8\\bin\\exiftool.exe").build();

        for (Path p : files) {
            String mimeType = tika.detect(p);
            System.out.println(mimeType + "\t" + p);

            Map<Tag, String> metadata = exifTool.getImageMeta(p.toFile());
            System.out.println(metadata.get(new UnspecifiedTag("FileCreateDate")));
            System.out.println(metadata.get(new UnspecifiedTag("ModifyDate")));
            System.out.println(metadata.get(new UnspecifiedTag("UserComment")));
            System.out.println(metadata.get(new UnspecifiedTag("Keywords")));

            //for (Entry<Tag, String> entry : metadata.entrySet()) {
            //    System.out.println(entry.getKey().getName() + ": " + entry.getValue());
            //}

            /*
            try (TikaInputStream inputStream = TikaInputStream.get(p)) {
                BodyContentHandler handler = new BodyContentHandler(-1);
                Metadata metadataFile = new Metadata();
                ParseContext context = new ParseContext();
                Parser parser = new AutoDetectParser();
                parser.parse(inputStream, handler, metadataFile, context);
            } catch (Exception e) {
                System.out.println(mimeType + "\t" + p);
                e.printStackTrace();
            }*/

        }
    }
}