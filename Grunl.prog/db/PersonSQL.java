/*  Kap. 7: Person-objekt og database
 *  RMI - feb. 2018
 *****************************************************
 *  Håndterer personopplysninger i en SQLite-database. 
 *
 *  Oppretter databasetabellen Person i en ny database
 *  person.db, se SQL-skript nederst i filen.
 */
 
import static javax.swing.JOptionPane.*; 
import java.sql.*;  
import java.io.*;
 
public class PersonSQL {
 
  private static String jdbcDriver = "org.sqlite.JDBC";
                                     // Navn på databasen?
  private static String url        = "jdbc:sqlite:butikk.db";

 
  private static Connection conn   = null; 
 
  public static void main(String[] args) {
    String utTxt = "";
    kobleOpp();  // Kontakter databasen 
  
    try {
      Statement stmt = conn.createStatement();

      /* Endre alder på alle ansatte 
      String sql = "update person set alder = alder + 1";
      stmt.executeUpdate(sql);
      */

      // Opprette databasen 
      /**/
      String sql = sqlNyDB(); // Spørring def i hjelpemetode
      stmt.executeUpdate(sql);
      utTxt = "Databasen er opprettet - ok!"; 
      

      /* Lister ut alle personer i databasen 
      String sql = "select * from Person;";*/
      // String sql = "select * from Person where Alder > 30;";

      /* Lister alle som har jubileum . runde årstall : 25, 30, 35 */
      // String sql = "select * from Person where Alder % 5 = 0";

      ResultSet rs   = stmt.executeQuery(sql); // rs blir her en rad i tabellen
      String fornavn, etternavn, stilling;
      int nr, alder;
 
      while (rs.next()) { // Automatisk splitter radene opp i enkle verdier pga rs.
        nr        = rs.getInt("Nr");
        fornavn   = rs.getString("Fornavn");
        etternavn = rs.getString("Etternavn");
        alder     = rs.getInt("Alder");
        stilling  = rs.getString("Stilling");
        utTxt += etternavn + ", " + fornavn + " (" + alder + ") - " + stilling + "\n";
      } 
/*
      // Tar en "backup" til tekstfil
        // Åpner fil
        PrintWriter skriver = new PrintWriter("ansatt.txt", "utf-8");
        // Overfører data
        skriver.println(utTxt);
        // Lukker fil
        skriver.close();*/

    }
    catch (Exception e) {  
      utTxt = "Databasespørring feilet!";
    } 
 
    showMessageDialog(null, utTxt);
    kobleNed();
  } 
 
  // Noen hjelpemetoder til databasehåndteringen

  // Kobler opp til databasen.
  private static void kobleOpp() {
    try {
      Class.forName(jdbcDriver);
      conn = DriverManager.getConnection(url);  
    }
    catch (ClassNotFoundException e) {
      feil("Fant ikke JDBC-driver " + jdbcDriver + "\n" + e.toString());
    }
    catch (SQLException e) {
      feil("Oppkobling til databasen " + url + " feilet.\n" + e.toString());
    }
  } 
 
  // Lukker forbindelsen til databasen.
  private static void kobleNed() {
    try {
      conn.close();
    }
    catch (SQLException e) { }
  } 
 
  // Viser feilmelding og avslutter
  private static void feil(String msg) {
    if (conn != null)
      kobleNed();
    showMessageDialog(null, msg);
    System.exit(-1);
  }

  private static String sqlNyDB() {
    return "create table if not exists Person(Nr integer primary key, Fornavn varchar(50), Etternavn varchar(50), Alder integer, Stilling  varchar(50) );"
            + "insert into Person values( 1, 'Per',      'Olsen',  24, 'Selger');"
            + "insert into Person values( 2, 'Anne',     'Hansen', 31, 'Mellomleder');" 
            + "insert into Person values( 3, 'Jon Ola',  'Bakken', 41, 'Reparatør');"
            + "insert into Person values( 4, 'Oda Lise', 'Li',     29, 'Saksbehandler');" 
            + "insert into Person values( 5, 'Petra',    'By',     44, 'Selger');"
            + "insert into Person values( 6, 'Anna',     'Jensen', 51, 'Mellomleder');"
            + "insert into Person values( 7, 'Johan O.', 'Berg',   48, 'Reparatør');"
            + "insert into Person values( 8, 'Odd Erik', 'Mo',     27, 'Saksbehandler');"
            + "insert into Person values( 9, 'Petter',   'Holen',  24, 'Selger');"
            + "insert into Person values(10, 'Pernille', 'Sem',    24, 'Selger');"
            + "insert into Person values(11, 'Rolf',     'Smedby', 24, 'Selger');"
            + "insert into Person values(12, 'Berit',    'Nilsen', 31, 'Saksbehandler');"
            + "insert into Person values(13, 'Jens J.',  'Brun',   41, 'Reparatør');"
            + "insert into Person values(14, 'Ida Marie','Hegge',  28, 'Saksbehandler');"
            + "insert into Person values(15, 'Edel',     'Steen',  44, 'Selger');"
            + "insert into Person values(16, 'Karl',     'Fosse',  45, 'Daglig leder');"
            + "insert into Person values(17, 'Kjell',    'Valme',  49, 'Reparatør');"
            + "insert into Person values(18, 'Diderik',  'Torve',  27, 'Saksbehandler');";             
  }

  /* Noen nyttige spørringer
  sql = drop table Person;
  sql = select * from Person order by Etternavn;
  sql = select * from Person where Alder <= 30;
  sql = update Person set Alder = 41 where Nr = 10;
  sql = select count(*) from Person;
  sql = select min(Alder) from Person;
 */
}