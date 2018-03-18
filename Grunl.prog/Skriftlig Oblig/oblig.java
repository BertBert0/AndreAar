import static javax.swing.JOptionPane.*; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class oblig {

  private static String jdbcDriver = "org.sqlite.JDBC";
                                     // Navn på databasen?
  private static String url        = "jdbc:sqlite:kontakter.db";

  private static Connection conn   = null; 

  public static void main(String[] args) {

/*****************************************
******************************************
*********Opretter databasen***************
******************************************
*****************************************/

    String utTxt = "";
    kobleOpp();  // Kontakter databasen 
  
    try {
      Statement stmt = conn.createStatement();

      String sql = sqlNyDB(); // Spørring def i hjelpemetode
      stmt.executeUpdate(sql);
      utTxt = "Databasen er opprettet - ok!"; 



       ResultSet rs   = stmt.executeQuery(sql); // rs blir her en rad i tabellen
      String fornavn, etternavn, adr;
      int nr;
 
      while (rs.next()) { // Automatisk splitter radene opp i enkle verdier pga rs.
        nr        = rs.getInt("Tlf");
        fornavn   = rs.getString("Fornavn");
        etternavn = rs.getString("Etternavn");
        adr       = rs.getString("Adresse");
        utTxt += nr + ", " + fornavn + " " + etternavn + " - " + adr + "\n";
      }

      }
    catch (Exception e) {  
      utTxt = "Databasespørring feilet!";
    } 
 
    showMessageDialog(null, utTxt);
    kobleNed();
  } 




/*****************************************
******************************************
**************Hjelpemetoder***************
******************************************
*****************************************/


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
    return "create table if not exists kontakt(tlf integer primary key, Fornavn varchar(50), Etternavn varchar(50) DEFAULT '-', Adresse varchar(50) DEFAULT '-' );"
            + "insert into kontakt values(24131577, 'Per',      'Olsen', 'Bakken 4, 3800 Bø');"
            + "insert into kontakt values(13151731, 'Anne',     'Hansen', 'Mellomlia, 5020 Bergen');" 
            + "insert into kontakt values(41131527, 'Jon Ola',  'Bakken', '-');"
            + "insert into kontakt values(12153729, 'Oda Lise', 'Li',     '-');" 
            + "insert into kontakt values(44131507, 'Petra',    'By',     '-');"
            + "insert into kontakt values(11154751, 'Anna',     '-', '-');"
            + "insert into kontakt values(48131557, 'Johan O.', 'Berg',   '-');"
            + "insert into kontakt values(14156727, 'Odd Erik', 'Mo',     'Stien 2, Larvik');"
            + "insert into kontakt values(33131587, 'Petter',   'Holen',  'Oslo');"
            + "insert into kontakt values(15159724, 'Pernille', 'Sem',    'Bodø');"
            + "insert into kontakt values(45131507, 'Rolf',     'Smedby', '-');"
            + "insert into kontakt values(31131517, 'Berit',    'Nilsen', '-');"
            + "insert into kontakt values(42415271, 'Jens J.',  '-',   '-');"
            + "insert into kontakt values(21515378, 'Ida Marie','Hegge',  '-');"
            + "insert into kontakt values(43615374, 'Edel',     'Steen',  'Skien');"
            + "insert into kontakt values(44715175, 'Karl',     'Fosse',  'Vegen 1, 3800 Bø');"
            + "insert into kontakt values(45315709, 'Kjell',    'Valme',  '-');"
            + "insert into kontakt values(22815747, 'Diderik',  'Torve',  '-');"
            + "insert into kontakt values(77766655, 'Ola',  'Lienes',  '-');"
            + "insert into kontakt values(88112233, 'Anne',  'Almelia',  'Vardø');"
            + "insert into kontakt values(66725175, 'Kari Mari',  'Vossen',  'Stigt. 4, 3800 Bø');"
            + "insert into kontakt values(77345709, 'Knut',  'Mølmen',  '-');"
            + "insert into kontakt values(44885547, 'Dina',  'Tjorven',  '-');"
            + "insert into kontakt values(99665511, 'Olga',  'Lia',  'Stubben 12, Eina');"
            + "insert into kontakt values(12312388, 'Alf',  'Andersen',  'Vadsø');";
  }

}