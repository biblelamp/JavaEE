/*
 * Set to fill the CID database
 */
class CIDTestSet {

    Object[][] data = {
        { "MEMBERS", new Integer(1), "dbartlett", "Bartlett", "David",
               "123 Mockingbird Lane", "Gettysburg", "PA", "19312",
               "123.456.7890", "bart@you.net" },
        { "MEMBERS", new Integer(2), "beckel", "Eckel", "Bruce",
               "123 Over Rainbow Lane", "Crested Butte", "CO", "81224",
               "123.456.7890", "beckel@you.net" },
        { "MEMBERS", new Integer(3), "rcastaneda", "Castaneda", "Robert",
               "123 Downunder Lane", "Sydney", "NSW", "12345",
               "123.456.7890", "rcastaneda@you.net" },
        { "LOCATIONS", new Integer(1), "Center for Arts", "Betty Wright",
               "123 Elk Ave.", "Crested Butte", "CO", "81224",
               "123.456.7890", "Go this way then that." },
        { "LOCATIONS", new Integer(2), "Witts End Conference Center",
               "John Wittig", "123 Music Drive", "Zoneville", "PA",
               "19123", "123.456.7890", "Go that way then this." },
        { "EVENTS", new Integer(1), "Project Management Myths",
               "Software Development", new Integer(1), new Float(2.50),
               "2000-07-17 19:30:00" },
        { "EVENTS", new Integer(2), "Life of the Crested Dog",
               "Archeology", new Integer(2), new Float(0.00),
               "2000-07-19 19:00:00" },
         // Match some people with events
        { "EVTMEMS", new Integer(1), // Dave is going to
               new Integer(1), // the Software event.
               new Integer(0) },
        { "EVTMEMS", new Integer(2), // Bruce is going to
               new Integer(2), // the Archeology event.
               new Integer(0) },
        { "EVTMEMS", new Integer(3), // Robert is going to
               new Integer(1), // the Software event.
               new Integer(1) },
        { "EVTMEMS", new Integer(3), // ... and
               new Integer(2), // the Archeology event.
               new Integer(1) },
    };

    public CIDTestSet() {
    }

    public CIDTestSet(Object[][] dat) {
        data = dat;
    }
}