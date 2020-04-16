package jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.type.PdfaConformanceEnum;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class HelloJasperReport {

    public static void main(String[] args) throws IOException, JRException {

        DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
        SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();

        config.setPdfaConformance(PdfaConformanceEnum.PDFA_1A);
        config.setIccProfilePath(HelloJasperReport.class.getClassLoader().getResource("icc/sRGB2014.icc").toString());
        config.setTagLanguage("CZ-cz");
        config.setTagged(true);
        config.setDisplayMetadataTitle(true);

        JasperReport template;
        try (InputStream is = HelloJasperReport.class.getClassLoader().getResourceAsStream("hello.jrxml")) {
            template = JasperCompileManager.compileReport(is);
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("hello", "Hello, world!");

        SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
        JasperFillManager fillManager = JasperFillManager.getInstance(context);
        JasperPrint jasperPrint = fillManager.fill(template, parameters, new JREmptyDataSource());

        // Generate output to PDF
        try (OutputStream os = Files.newOutputStream(new File("hello.pdf").toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            JRPdfExporter exporter = new JRPdfExporter(context);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(config);
            exporter.exportReport();
        }
    }
}
