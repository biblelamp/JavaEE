import org.geotools.kml.KML;
import org.geotools.kml.KMLConfiguration;
import org.geotools.xsd.Parser;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * from https://gis.stackexchange.com/questions/4549/how-to-parse-kml-data-using-geotools
 *
 * @seee http://docs.geotools.org/latest/userguide/library/xml/geometry.html
 */

public class GtXsdKmlDemo {
    static String kmlFile = "KML-Orlik-19012023-hz6d6xbb.kml";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        try (InputStream is = new FileInputStream(kmlFile)) {
            Parser parser = new Parser(new KMLConfiguration());
            SimpleFeature sf = (SimpleFeature) parser.parse(is);
            for (Property property : sf.getValue()) {
                System.out.println(property);
                System.out.println(property.getType());
                System.out.println(property.getValue());
                if (property.getType().getName().getURI().equals(KML.Geometry.getLocalPart())) {
                    System.out.println(property.getValue());
                }
            }
        }
    }
}
