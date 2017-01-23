/* 
 * Filling and testing the CID database
 */
import java.sql.*;

public class CIDLoadDB {
    Statement statement;
    Connection connection;
    CIDTestSet tset;

    public CIDLoadDB(CIDTestSet tset) throws SQLException {
        this.tset = tset;
        try {
            Class.forName(CIDConnect.dbDriver);
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace(System.err);
        }
        connection = DriverManager.getConnection(CIDConnect.dbURL);//, CIDConnect.user, CIDConnect.password);
        statement = connection.createStatement();
   }

    public void dispose() throws SQLException {
        statement.close();
        connection.close();
    }

    public void executeInsert(Object[] data) {
        String sql = "Insert Into " + data[0] + " Values(";
        for (int i = 1; i < data.length; i++) {
            if (data[i] instanceof String)
                sql += "'" + data[i] + "'";
            else
                sql += data[i];
            if (i < data.length - 1)
                sql += ", ";
        }
        sql += ')';
        System.out.println(sql);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException sqlEx) {
            System.err.println("Insert failed.");
            while (sqlEx != null) {
                System.err.println(sqlEx.toString());
                sqlEx = sqlEx.getNextException();
            }
        }
    }

    public void load() {
        for (int i = 0; i < tset.data.length; i++)
            executeInsert(tset.data[i]);
    }
   
    public static void main(String[] args) throws SQLException {
        CIDLoadDB db = new CIDLoadDB(new CIDTestSet());
        db.load();
        try {
            ResultSet rs = db.statement.executeQuery("Select "
               + "e.EVT_TITLE, m.MEM_LNAME, m.MEM_FNAME "
               + "from EVENTS e, MEMBERS m, EVTMEMS em "
               + "Where em.EVT_ID = 2 " + "and e.EVT_ID = em.EVT_ID "
               + "and m.MEM_ID = em.MEM_ID");
            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2)
                    + ", " + rs.getString(3));
        }
        finally {
            db.dispose();
        }
    }
}