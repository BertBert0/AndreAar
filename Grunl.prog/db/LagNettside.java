import static java.lang.System.*;
import static java.lang.Double.*;
import java.text.DecimalFormat;  // Pene tall...
import java.sql.*;
import java.util.Scanner;

public class LagNettside {

  public static void main(String[] args) {
    try {
      Scanner leser = new Scanner(in);
      double prisGrense = leser.nextDouble();
      visVarer(prisGrense);
    }
    catch (Exception e) {
      out.println("Databasefeil: " + e.toString());
    }
  }

  public static void visVarer(double prisGrense) throws Exception {
    String driver = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:butikk.db";
    String sql = "select * from Vare " +
                 "where Pris < " + prisGrense;

    // Opprett forbindelsen til databasen
    Class.forName(driver);
    Connection con = DriverManager.getConnection(url);

    // Utfør SQL-spørringen.
    Statement stmt = con.createStatement();
    ResultSet rs   = stmt.executeQuery(sql);

    DecimalFormat df = new DecimalFormat("#.00");
    String vareNr, varenavn;
    double pris;
    int antall;

    while (rs.next()) { // Behandle hver rad
      vareNr   = rs.getString("Nr");
      varenavn = rs.getString("Navn");
      pris     = rs.getDouble("Pris");
      antall   = rs.getInt("Antall");

      out.println("Nr: " + vareNr
                + ", Navn: " + varenavn
                + ", Pris: " + df.format(pris)
                + ", Antall: " + antall);
    }

    con.close(); // Lukk forbindelsen til databasen
  }

}
