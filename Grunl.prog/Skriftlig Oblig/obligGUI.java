import static javax.swing.JOptionPane.*; 
import static java.lang.System.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class obligGUI extends JFrame 
implements ActionListener {
 
  private static String jdbcDriver = "org.sqlite.JDBC";
  private static String url        = "jdbc:sqlite:kontakter.db";
 

  private static Connection conn = null;


  private JLabel        tlfAdd, fNavnAdd, eNavnAdd, adrAdd, kontLab, sortLab  ;  
  private JButton       btnAdd, saveBack, loadBack, btnDelete ;
  private JTextField    txtTlf, txtFNavn, txtEnavn, txtAdr, txtStatus, tlfDel ;
  private JTextArea     txtKont, txtTut ;
  private JComboBox     sortList;

  public static void main(String[] args) {
    // Kjører dbCheck
    dbCheck();
    // GUI
    obligGUI vindu = new obligGUI();
    vindu.setTitle("Kontakter");
    vindu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    vindu.setSize(600, 600);
    vindu.setVisible(true);
    vindu.setResizable(false);
  


  } 

  // hvis database eksisterer, dont do shit. if no. Lag database
  public static void dbCheck() {
    String utTxt = ""; 

     File f = new File("kontakter.db");
    if(f.exists()) { 
    // do nuffin
        }
    else{     
        try {
      kobleOpp();
      Statement stmt = conn.createStatement();

      String sql = sqlNyDB(); // Spørring def i hjelpemetode
      stmt.executeUpdate(sql);

        utTxt = "Databasen er opprettet - ok!"; 
          }
    catch (Exception e) {  
      utTxt = "Databasesen ikke oprettet OK";
    } 
      showMessageDialog(null,utTxt);

    kobleNed(); 
    }
}

    public obligGUI() {



        // ----
  String[] sortString = { "Telefonnummer", "Fornavn", "Etternavn" };

  String tutTxt = "Dette programmet leser " + "\n" 
                + "en database fil som " + "\n" 
                + "inneholder kontakter. " + "\n"
                + "\n" 
                + "Slik bruker du dette programmet: " + "\n"
                + "\n"
                + "1. For å legge til en kontakt, " + "\n" 
                + "skriv inn informasjon i feltene " + "\n" 
                + "helt øverst og trykk på <Legg til>" + "\n"
                + "\n"
                + "2. For å slette en kontakt skriv " + "\n" 
                + "inn Telefonnummer i feltet " + "\n" 
                + "under og trykk <Slett kontakt>" + "\n"
                + "\n"
                + "3. For å sortere listen " + "\n" 
                + "med kontakter basert på " + "\n" 
                + "telefonnummer, fornavn " + "\n" 
                + "eller etternavn, velg i " + "\n" 
                + "menyene over" + "\n"
                + "\n"
                + "4. For å lagre eller laste en " + "\n" 
                + "sikkerhetskopi av kontaktlisten, " + "\n" 
                + "trykk på henholdsvis <Lagre backup> " + "\n" 
                + "eller <Last backup>";
 // Lag øvrige GUI-objekter
   


    btnAdd = new JButton("Legg til");
    btnDelete = new JButton("Slett kontakt");
    saveBack = new JButton("Lagre backup");
    loadBack = new JButton("Last backup");

    txtKont = new JTextArea(40, 30);
    txtTut = new JTextArea(tutTxt, 30, 20 );

    txtStatus = new JTextField(30);
    txtTlf = new JTextField(6);
    txtFNavn = new JTextField(5);
    txtEnavn = new JTextField(5);
    txtAdr = new JTextField(10);

    tlfDel = new JTextField(6);


    kontLab = new JLabel("Kontakter");
    tlfAdd = new JLabel("Tlf:");
    fNavnAdd = new JLabel("Fnavn:");
    eNavnAdd = new JLabel("Enavn:");
    adrAdd = new JLabel("Adr:");
    sortLab = new JLabel("Sortering:");

    sortList = new JComboBox<>(sortString);

    // Sett layout
    // Center Border
JPanel CPanel = new JPanel (new BorderLayout() );
JPanel CNpanel = new JPanel (new FlowLayout() );
JPanel CSpanel = new JPanel (new FlowLayout() );


CPanel.add(txtKont);
    txtKont.setEditable(false);

CNpanel.add(kontLab);
CSpanel.add(txtStatus);

    txtStatus.setEditable(false);

add(CPanel, BorderLayout.CENTER);
CPanel.add(CNpanel, BorderLayout.NORTH);
CPanel.add(CSpanel, BorderLayout.SOUTH);


    // East Border
JPanel EPanel = new JPanel (new BorderLayout() );
JPanel ESpanel = new JPanel (new FlowLayout() );
JPanel ENpanel = new JPanel (new FlowLayout() );
JPanel ECpanel = new JPanel (new FlowLayout() );


ESpanel.add(tlfDel);
ESpanel.add(btnDelete);
ENpanel.add(sortLab);
ENpanel.add(sortList);
ECpanel.add(txtTut);
    txtTut.setEditable(false);


add(EPanel, BorderLayout.EAST);
EPanel.add(ESpanel, BorderLayout.SOUTH);
EPanel.add(ENpanel, BorderLayout.NORTH);
EPanel.add(ECpanel, BorderLayout.CENTER);

    // North Border
JPanel NPanel = new JPanel();


    NPanel.add(tlfAdd);
    NPanel.add(txtTlf);    
    NPanel.add(fNavnAdd);
    NPanel.add(txtFNavn);
    NPanel.add(eNavnAdd);
    NPanel.add(txtEnavn);
    NPanel.add(adrAdd);
    NPanel.add(txtAdr);
    NPanel.add(btnAdd);
    
    add(NPanel, BorderLayout.NORTH);


    // South Border

JPanel SPanel = new JPanel (new BorderLayout() );
JPanel SEpanel = new JPanel (new FlowLayout() );
JPanel SWpanel = new JPanel (new FlowLayout() );

SWpanel.add(txtStatus);
    txtStatus.setEditable(false);
SEpanel.add(saveBack);
SEpanel.add(loadBack);

add(SPanel, BorderLayout.SOUTH);
    SPanel.add(SWpanel, BorderLayout.WEST);
    SPanel.add(SEpanel, BorderLayout.EAST);

    // Lytt på relevante komponenter og menyvalg ||  btnAdd, saveBack, loadBack 
    btnAdd.addActionListener(this);
    btnDelete.addActionListener(this);
    saveBack.addActionListener(this);
    loadBack.addActionListener(this);
    sortList.addActionListener(this);


        // Kobler opp databasen
String tlfSort = "tlf;";

String utTxt = getKont(tlfSort);
txtKont.setText(utTxt);
  
  }


  public void actionPerformed(ActionEvent e)  {
  String s = (String) sortList.getSelectedItem();
  String utTxt = "";
String tlfSort = "Tlf;";
String fNavnSort = "Fornavn;";
String eNavnSort = "Etternavn;";

    // Setter opp legg til kontakt
    if (e.getSource() == btnAdd){
      txtStatus.setText("Kontakt lagt til");
       try {
          
          kobleOpp(); 
          int addTlf = Integer.parseInt(txtTlf.getText() );
          String addFNavn = txtFNavn.getText() ;    
          String addENavn = txtEnavn.getText() ;    
          String addAdr = txtAdr.getText() ;

            if (addFNavn.isEmpty() )
                addFNavn = "-";

            if (addENavn.isEmpty() )
                addENavn = "-";

            if (addAdr.isEmpty() )
                addAdr = "-";    
    
            if (addTlf > 0 ){
                Statement stmt = conn.createStatement();
            String sql = "Insert into kontakt values(" + addTlf +", '" + addFNavn + "',  '" + addENavn + "', '" + addAdr + "');";
            stmt.executeUpdate(sql);
            utTxt = getKont(tlfSort);
            txtKont.setText(utTxt);

            // Sletter innhold i boksene
            txtTlf.setText("");
            txtFNavn.setText("");
            txtEnavn.setText("");
            txtAdr.setText("");

        }
                }
                 catch (Exception f) {  
             } 
             kobleNed(); 
    
}


    // Setter opp sletting av kontakt
    else if (e.getSource() == btnDelete){
      txtStatus.setText("Kontakt slettet");
    
  try {
          kobleOpp(); 
        
        int delTlf = Integer.parseInt(tlfDel.getText() );
        Statement stmt = conn.createStatement();
        String sql = "Delete from kontakt where Tlf = " + delTlf + ";";
        stmt.executeUpdate(sql);
            utTxt = getKont(tlfSort);
            txtKont.setText(utTxt);

            // Sletter innhold i boksene
            tlfDel.setText("");
  }
        catch (Exception f) {  
        } 
             kobleNed(); 
         
}

    // Setter opp lagring av backup
    else if (e.getSource() == saveBack){
      txtStatus.setText("Backup av Kontakter lagret");

      try {

    File orig = new File("kontakter.db");
     File f = new File("kontakterBackup.db");
    Path path = Paths.get("kontakterBackup.db");
        if(f.exists()) {
     Files.delete(path);
    }

    File backup = new File ("kontakterBackup.db");
    copyFile(orig, backup);
    }
            catch (Exception f) {  
                f.printStackTrace();

        } 
}

        // Setter opp lasting ac backup
    else if (e.getSource() == loadBack){
      txtStatus.setText("Backup av Kontakter lastet");

        try {
      
        

    /*
    File backup = new File("kontakterBackup.db");

     File f = new File("kontakter.db");
    Path path = Paths.get("kontakter.db");
        if(f.exists()) {
     Files.delete(path);
    }



    File orig = new File ("kontakter.db");
    copyFile(backup, orig);
    */

    Path source = Paths.get("kontakterBackup.db");

    Files.move(source, source.resolveSibling("kontakter.db"), REPLACE_EXISTING);


            utTxt = getKont(tlfSort);
            txtKont.setText(utTxt);
    }
            catch (Exception f) {  
        f.printStackTrace(); 
                

        }    
        

}
    // Setter opp sortering funksjonalitet
    else if (e.getSource() == sortList){
      txtStatus.setText("Du sorterte etter: " + s);
               
      if (s.equals("Telefonnummer") )
        
        utTxt = getKont(tlfSort);
        txtKont.setText(utTxt);
              

      if (s.equals("Fornavn") ) 
        utTxt = getKont(fNavnSort);
        txtKont.setText(utTxt);
          

       if (s.equals("Etternavn") ) 
        
        utTxt = getKont(eNavnSort);
        txtKont.setText(utTxt);
                                         
    }
}

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
  

private static String getKont(String x) {
        // Gjør database og SQL ting
    String utTxt = ""; 
      kobleOpp(); 

    try {
      Statement stmt = conn.createStatement();

      String sql = "Select * from kontakt ORDER BY " + x; // Spørring def i hjelpemetode 


       ResultSet rs   = stmt.executeQuery(sql); // rs blir her en rad i tabellen
      String fNavn, eNavn, adr;
      int tlf;
 
      while (rs.next()) { // Automatisk splitter radene opp i enkle verdier pga rs.
        tlf        = rs.getInt("Tlf");
        fNavn   = rs.getString("Fornavn");
        eNavn = rs.getString("Etternavn");
        adr       = rs.getString("Adresse");
        utTxt += tlf + ", " + fNavn + " " + eNavn + " , " + adr + "\n";
      }
            }
    catch (Exception e) { 
        e.printStackTrace(); 
      utTxt = "Databasespørring feilet!";
      showMessageDialog(null,utTxt);
    } 
      kobleNed(); 

 return utTxt;    
}



  private static String sqlNyDB() {
    return "create table if not exists kontakt(Tlf integer primary key, Fornavn varchar(50), Etternavn varchar(50) DEFAULT '-', Adresse varchar(50) DEFAULT '-' );"
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

  private static void copyFile(File s, File d) throws IOException {
    Files.copy(s.toPath(), d.toPath());
}
}