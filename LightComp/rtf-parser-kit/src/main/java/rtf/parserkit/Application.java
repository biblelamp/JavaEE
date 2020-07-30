package rtf.parserkit;

import com.rtfparserkit.converter.text.StringTextConverter;
import com.rtfparserkit.parser.RtfStreamSource;

import java.io.FileInputStream;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        StringTextConverter converter = new StringTextConverter();

        converter.convert(new RtfStreamSource(new FileInputStream("src/main/resources/wordpad.rtf")));
        String extractedText = converter.getText();
        System.out.println(extractedText);

        converter.convert(new RtfStreamSource(new FileInputStream("src/main/resources/googledoc.rtf")));
        extractedText = converter.getText();
        System.out.println(extractedText);

        converter.convert(new RtfStreamSource(new FileInputStream("src/main/resources/libreoffice.rtf")));
        extractedText = converter.getText();
        System.out.println(extractedText);
    }
}
