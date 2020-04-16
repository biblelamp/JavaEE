package pdfbox;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
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
 * Embedding fonts in a pdf file using pdfBox
 * https://pdfbox.apache.org/1.8/cookbook/workingwithfonts.html
 * https://stackoverflow.com/questions/53818960/how-to-embed-an-standard-font-into-generated-pdf-with-pdfbox
 *
 * [file] jasperreports_extension.properties:
 * net.sf.jasperreports.extension.registry.factory.simple.font.families=net.sf.jasperreports.engine.fonts.SimpleFontExtensionsRegistryFactory
 * net.sf.jasperreports.extension.simple.font.families.dejavu=fonts/fonts.xml
 *
 * [file] fonts/fonts.xml
 * <?xml version="1.0" encoding="UTF-8"?>
 * <fontFamilies>
 *     <fontFamily name="DejaVu Sans">
 *         <normal><![CDATA[fonts/DejaVuSans.ttf]]></normal>
 *         <bold><![CDATA[fonts/DejaVuSans-Bold.ttf]]></bold>
 *         <pdfEncoding>Identity-H</pdfEncoding>
 *         <pdfEmbedded><![CDATA[true]]></pdfEmbedded>
 *     </fontFamily>
 * </fontFamilies>
 *
 * Embedding full font set in pdf usibg jasperreport 6.11
 *
 * https://community.jaspersoft.com/questions/1112706/embedding-full-font-set-pdf
 *
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

    private static final String DEJAVU_FONT = "C:\\Windows\\Fonts\\DejaVuSans.ttf";

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

            // font embedding
            //PDPage page = new PDPage();
            //document.addPage(page);
            //PDFont font = PDTrueTypeFont.loadTTF(document, new File(DEJAVU_FONT));
            //PDPageContentStream contentStream = new PDPageContentStream(document, page);
            //contentStream.setFont(font, 10);
            //contentStream.close();*/

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
