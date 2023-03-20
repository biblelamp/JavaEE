import de.micromata.opengis.kml.v_2_2_0.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Based on https://stackoverflow.com/questions/15636303/extract-coordinates-from-kml-file-in-java
 */

public class KmlParseDemo {
    static String kmlFile = "KML-Orlik-19012023-hz6d6xbb.kml";

    public static void main(String[] args) {
        Kml kml = Kml.unmarshal(new File(kmlFile));
        Feature feature = kml.getFeature();
        if (feature != null) {
            if (feature instanceof Placemark) {
                Placemark placemark = (Placemark) feature;
                Geometry geometry = placemark.getGeometry();
                Map<String, Object> geodata = parseGeometry(geometry);
                System.out.println(geodata);
            }
        }
    }

    public static Map<String, Object> parseGeometry(Geometry geometry) {
        Map<String, Object> response = new HashMap<String, Object>();
        if (geometry instanceof Point) {
            Point point = (Point) geometry;
            List<Coordinate> coordinates = point.getCoordinates();
            if (coordinates != null && !coordinates.isEmpty()) {
                for(Coordinate coordinate : coordinates) {
                    response.put("lon", coordinate.getLongitude());
                    response.put("lat", coordinate.getLatitude());
                    response.put("category", "POINT");
                }
            }
        } else if (geometry instanceof LineString) {
            // TODO
        } else if (geometry instanceof Polygon) {
            // TODO
        } else if (geometry instanceof MultiGeometry) {
            // TODO
        } else {
            response.put("NULL", "Geometry is undefined");
        }

        return response;
    }
}
