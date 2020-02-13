package tika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thebuzzmedia.exiftool.ExifTool;
import com.thebuzzmedia.exiftool.ExifToolBuilder;
import com.thebuzzmedia.exiftool.Tag;
import com.thebuzzmedia.exiftool.core.UnspecifiedTag;

public class HelloTika {

    private final static Logger log = LoggerFactory.getLogger(HelloTika.class);

    private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01";
    //private final static String PATH = "C:\\temp\\upl-ws\\testdata\\MSK_Sada_01_bad_files";

    private final static String[] DATE_PARSE_PATTERNS = new String[] { "yyyy:mm:dd", "yyyy-mm-dd", "mm/dd/yy",
            "EEE, MMM dd, yyyy", "EEE, dd MMM, yyyy", "EEE MMM dd HH:mm:ss yyyy", "d-MMM-YYYY", "HH:mm MM/dd/yyyy",
            "yyyy MMM dd", "HH:mm a EEE, MMM dd, yyyy", "(HH:mm a EEE, MMM dd, yyyy)" };

    private static int total = 0;
    private static int error = 0;
    private static int empty = 0;

    public static void main(String[] args) throws IOException, java.text.ParseException {
        List<Path> files = Files.walk(Paths.get(PATH))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        log.info("Start reading");

        //Tika tika = new Tika();

        ExifTool exifTool = new ExifToolBuilder().withPath("C:\\Program Files\\Java\\jdk1.8\\bin\\exiftool.exe").build();

        for (Path p : files) {
            //String mimeType = tika.detect(p);
            //log.info("{}\t{}", mimeType, p);

            Map<Tag, String> metadata = exifTool.getImageMeta(p.toFile());
            String createDate = metadata.get(new UnspecifiedTag("CreateDate"));
            String modifyDate = metadata.get(new UnspecifiedTag("ModifyDate"));
            String comments = metadata.get(new UnspecifiedTag("Comments"));
            String keywords = metadata.get(new UnspecifiedTag("Keywords"));
            if (StringUtils.isEmpty(comments)) {
                comments = null;
            }
            if (StringUtils.isEmpty(keywords)) {
                keywords = null;
            }

            //System.out.println(createDate);
            //System.out.println(modifyDate);
            //System.out.println(comments);
            //System.out.println(keywords);
        
            total += 2;
        
            if (createDate == null) {
                createDate = metadata.get(new UnspecifiedTag("FileCreateDate"));
            }
            if (createDate != null) {
                try {
                    String modifyCreateDate = normalizeFormatDate(createDate, DATE_PARSE_PATTERNS);
                    if (modifyCreateDate != null) {
                        Instant.parse(modifyCreateDate);
                    }
                } catch (Exception e) {
                    log.error("Error parsing date {}", createDate);
                    error++;
                }
            } else {
                empty++;
            }

            if (modifyDate == null) {
                modifyDate = metadata.get(new UnspecifiedTag("FileModifyDate"));
            }
            if (modifyDate != null) {
                try {
                    String modifyModifyDate = normalizeFormatDate(modifyDate, DATE_PARSE_PATTERNS);
                    if (modifyModifyDate != null) {
                        Instant.parse(modifyModifyDate);
                    }
                } catch (Exception e) {
                    log.error("Error parsing date {}", modifyDate);
                    error++;
                }
            } else {
                empty++;
            }

            //            for (Entry<Tag, String> entry : metadata.entrySet()) {
            //                System.out.println(entry.getKey().getName() + ": " + entry.getValue());
            //            }

            //            try (TikaInputStream inputStream = TikaInputStream.get(p)) {
            //                BodyContentHandler handler = new BodyContentHandler(-1);
            //                Metadata metadataFile = new Metadata();
            //                ParseContext context = new ParseContext();
            //                Parser parser = new AutoDetectParser();
            //                parser.parse(inputStream, handler, metadataFile, context);
            //            } catch (Exception e) {
            //                log.error("Error reading metadata from {} {}", mimeType, p);
            //                e.printStackTrace();
            //            }

        }
        log.info("TOTAL {} ERROR {} NULL {}", total, error, empty);
    }

    /**
     * Normalize format date from exif to standard view like '2020-02-11T09:24:08Z'
     * 
     * @param date in string
     * @param parsePatterns list of patters for parsing date
     * @return String
     */
    static String normalizeFormatDate(String date, String[] parsePatterns) {
        if (date == null) {
            return null;
        } else {
            Date dt;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            for (final String pattern : parsePatterns) {
                try {
                    dt = new SimpleDateFormat(pattern, Locale.ENGLISH).parse(date);
                    return df.format(dt);
                } catch (ParseException ignore) {
                    // the pattern didn't fit this date
                }
            }
            error++;
            log.error("Error in date string {}", date);
            return null;
        }
    }

}