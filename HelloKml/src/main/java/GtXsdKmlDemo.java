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
import java.util.List;

/**
 * from https://gis.stackexchange.com/questions/4549/how-to-parse-kml-data-using-geotools
 *
 * @seee http://docs.geotools.org/latest/userguide/library/xml/geometry.html
 */

public class GtXsdKmlDemo {
    // file created by https://www.freemaptools.com/kml-file-creator.htm
    static String kmlOrlik = "KML-Orlik-POINT.kml";

    // files created by https://earth.google.com/
    static String kmlLondon = "KML-London-POINT.kml";
    static String kmlPragRomeLine = "KML-Prag-Rome-LINE.kml";
    static String kmlPrahuePolygon = "KML-Prague-POLYGON.kml";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        try (InputStream is = new FileInputStream(kmlPrahuePolygon)) {
            Parser parser = new Parser(new KMLConfiguration());
            SimpleFeature simpleFeature = (SimpleFeature) parser.parse(is);
            for (Property property : simpleFeature.getValue()) {
                System.out.println("p: " + property);
                System.out.println("t: " + property.getType());
                System.out.println("v: " + property.getValue());
                if (property.getType().getName().getURI().equals(KML.Geometry.getLocalPart())) {
                    System.out.println("result:\n" + property.getValue());
                }
                if (property.getType().getName().getURI().equals(KML.Feature.getLocalPart())) {
                    List<SimpleFeature> simpleFeatures = (List<SimpleFeature>) property.getValue();
                    for (SimpleFeature sf : simpleFeatures) {
                        for (Property p : sf.getValue()) {
                            System.out.println("p: " + p);
                            System.out.println("t: " + p.getType());
                            System.out.println("v: " + p.getValue());
                            if (p.getType().getName().getURI().equals(KML.Geometry.getLocalPart())) {
                                System.out.println("result:\n" + p.getValue());
                            }
                        }
                    }
                }
            }
        }
    }
}
