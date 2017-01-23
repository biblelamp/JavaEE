/*
 * Creating tables for CID
 *  Note:
 *  a) Download sqlite-jdbc-(VER).jar from https://bitbucket.org/xerial/sqlite-jdbc/downloads
 *  b) Put this jar into ...\jre\lib\ext
 *  c) See also http://www.tutorialspoint.com/sqlite/sqlite_java.htm
 */
import java.sql.*;

public class CIDCreateTables {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException {
        Class.forName(CIDConnect.dbDriver); // download driver
        Connection connect = DriverManager.getConnection(CIDConnect.dbURL); // CIDConnect.user, CIDConnect.password);
        Statement statement = connect.createStatement();
        for (int i = 0; i < CIDSQL.sql.length; i++) {
            System.out.println(CIDSQL.sql[i]);
            try {
                statement.executeUpdate(CIDSQL.sql[i]);
            } catch (SQLException sqlEx) {
                System.err.println("Probably a 'drop table' failed");
            }
        }
        statement.close();
        connect.close();
    }
}