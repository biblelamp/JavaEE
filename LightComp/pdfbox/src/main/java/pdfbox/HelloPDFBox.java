package pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class HelloPDFBox {

    private static final String PDF_FILE =
            "C:\\temp\\eskartace\\data\\uo\\zz\\1585738589717\\reports\\CZNDA10000010ESK1_seznam_k_provedeni_vyberu_1585765478765.pdf";

    public static void main(String[] args) throws IOException {
        try (PDDocument document = PDDocument.load(new File(PDF_FILE))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                // split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                for (String line : lines) {
                    System.out.println(line);
                }
            }
        }
    }
}
