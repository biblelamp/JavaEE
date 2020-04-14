package pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.AdobePDFSchema;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.schema.XMPBasicSchema;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.xml.XmpSerializer;

import javax.xml.transform.TransformerException;
import java.io.*;

/**
 * Error in pdf validation
 *
 * http://apache-fop.1065347.n5.nabble.com/PDFA-3-validation-fails-because-of-CID-font-set-td44900.html
 * https://stackoverflow.com/questions/39791591/how-to-load-jasperreports-font-extensions-into-spring-based-application
 * https://stackoverflow.com/questions/36625929/jasper-reports-font-extension-not-working
 */

public class HelloPDFBox {

    private static final String PDF_FILE =
            "C:\\temp\\eskartace\\data\\uo\\zz\\1585738589717\\reports\\"+
                    //"CZNDA10000010ESK1_seznam_k_provedeni_vyberu_1585765478765";
                    "CZNDA10000010ESK1_seznam_k_provedeni_vyberu_1586878637718";

    private static String getXMLMetadata(PDDocument document) throws IOException {
        PDDocumentCatalog catalog = document.getDocumentCatalog();
        PDMetadata metadata = catalog.getMetadata();

        InputStream xmlInputStream = metadata.createInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(xmlInputStream));
        StringBuffer sb = new StringBuffer();
        String str;
        while((str = reader.readLine())!= null){
            sb.append(str);
        }
        return sb.toString();
    }

    private static String changePartNumber(String xml) {
        xml = xml.replace("part>1<", "part>3<");
        xml = xml.replaceAll(">\\s+<", "><");
        return xml;
    }

    private static String[] getStrippedText(PDDocument document) throws IOException {
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);

        PDFTextStripper tStripper = new PDFTextStripper();
        String pdfFileInText = tStripper.getText(document);

        // split by whitespace
        return pdfFileInText.split("\\r?\\n");
    }

    public static void main(String[] args) throws IOException {
        try (PDDocument document = PDDocument.load(new File(PDF_FILE + ".pdf"))) {

            PDDocumentInformation pddInfo = document.getDocumentInformation();
            for (String str : pddInfo.getMetadataKeys()) {
                //System.out.println(str + ": " + pddInfo.getCustomMetadataValue(str));
            }

            String xmlMetadata = getXMLMetadata(document);
            System.out.println(changePartNumber(xmlMetadata));

            PDDocumentCatalog catalog = document.getDocumentCatalog();
            PDMetadata metadata = catalog.getMetadata();

            try {
                // change XMP metadata
                XMPMetadata xmp = XMPMetadata.createXMPMetadata();

                DublinCoreSchema dcSchema = xmp.createAndAddDublinCoreSchema();
                dcSchema.setFormat("application/pdf");
                dcSchema.setTitle(pddInfo.getTitle());
                dcSchema.addCreator(pddInfo.getAuthor());

                AdobePDFSchema pdfSchema = xmp.createAndAddAdobePDFSchema();
                pdfSchema.setProducer(pddInfo.getProducer());

                PDFAIdentificationSchema id = xmp.createAndAddPFAIdentificationSchema();
                id.setPart(3);  // value => 2|3
                id.setConformance("A"); // value => A|B|U

                XMPBasicSchema basicSchema = xmp.createAndAddXMPBasicSchema();
                basicSchema.setCreateDate(pddInfo.getCreationDate());
                basicSchema.setModifyDate(pddInfo.getModificationDate());
                basicSchema.setCreatorTool(pddInfo.getCreator());
                //basicSchema.setMetadataDate(pddInfo.getCreationDate());

                XmpSerializer serializer = new XmpSerializer();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                serializer.serialize(xmp, baos, true);

                //metadata.importXMPMetadata(baos.toByteArray());
                metadata.importXMPMetadata(changePartNumber(xmlMetadata).getBytes());
                catalog.setMetadata(metadata);

            } catch (BadFieldValueException | TransformerException e) {
                e.printStackTrace();
            }

            document.save(new File(PDF_FILE + "_new.pdf"));

            if (!document.isEncrypted()) {
                for (String line : getStrippedText(document)) {
                    //System.out.println(line);
                }
            }
        }
    }
}
