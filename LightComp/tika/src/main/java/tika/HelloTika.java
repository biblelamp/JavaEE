package tika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thebuzzmedia.exiftool.ExifTool;
import com.thebuzzmedia.exiftool.ExifToolBuilder;
import com.thebuzzmedia.exiftool.Tag;
import com.thebuzzmedia.exiftool.core.UnspecifiedTag;

public class HelloTika {

    private final static Logger log = LoggerFactory.getLogger(HelloTika.class);

    private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01\\DOC_67";
    //private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01_bad_files";

    public static void main(String[] args) throws IOException {
        List<Path> files = Files.walk(Paths.get(PATH))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        log.info("Start reading");

        Tika tika = new Tika();

        ExifTool exifTool = new ExifToolBuilder().withPath("C:\\Program Files\\Java\\jdk1.8\\bin\\exiftool.exe").build();

        for (Path p : files) {
            String mimeType = tika.detect(p);
            log.info("{}\t{}", mimeType, p);

            Map<Tag, String> metadata = exifTool.getImageMeta(p.toFile());
            String createDate = modifyDateString(metadata.get(new UnspecifiedTag("CreateDate")));
            String modifyDate = modifyDateString(metadata.get(new UnspecifiedTag("ModifyDate")));
            String comments = metadata.get(new UnspecifiedTag("Comments"));
            String keywords = metadata.get(new UnspecifiedTag("Keywords"));
            if (StringUtils.isEmpty(comments)) {
                comments = null;
            }
            if (StringUtils.isEmpty(keywords)) {
                keywords = null;
            }

            System.out.println(createDate);
            System.out.println(modifyDate);
            System.out.println(comments);
            System.out.println(keywords);

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
                log.error("Error reading metadata from {} {}", mimeType, p);
                e.printStackTrace();
            }*/

        }
        log.info("Finish reading");
    }

    /**
     * Converting date from exif to standard form like 2020-02-11T09:24:08Z
     * 
     * @param date like '2020:02:11 09:24:08+01:00'
     * @return String like '2020-02-11T09:24:08Z' or null
     */
    static String modifyDateString(String date) {
        if (date == null) {
            return null;
        } else {
            try {
                return date.substring(0, 4) + '-' + date.substring(5, 7) + '-' + date.substring(8, 10) +
                        'T' + date.substring(11, 19) + 'Z';
            } catch (Exception e) {
                log.error("Error in date string {}", date);
                return null;
            }
        }
    }

}