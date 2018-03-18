import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.*;


/**
  *  Programmet tilbyr et GUI for å søke i en SQLite-database.
  *  Brukeren skriver inn et varenummer, og programmet viser
  *  navnet, prisen og lagerantallet for varen.
  *
  *  Det er forutsatt at SQL-skript nederst i denne filen
  *  er kjørt, slik at tabellen Vare finnes i databasen butikk.db.
  *  
  */
public class VareSok extends JFrame 
  implements ActionListener, WindowListener {
 
  private String jdbcDriver = "org.sqlite.JDBC";
  private String url        = "jdbc:sqlite:butikk.db";
    
  private Connection conn = null;
    
  private JTextField txtNr, txtNavn, txtPris, txtAntall;
  private JButton    btnSøk, btnRensk;
  
  
  public static void main(String[] args) {
    VareSok gui = new VareSok();
    gui.setTitle("Varesøk");
    gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
    gui.opprettGUI();
    gui.pack();
    gui.setVisible(true);
    gui.kobleOpp();
  }
  
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnRensk)
      rensk();
    else
      finnVare();
  }
  
  
  // Lukker databaseforbindelsen når programmet avslutter
  public void windowClosing(WindowEvent e) {
    kobleNed();
  }
  
  
  // Disse kan bare stå tomme
  public void windowActivated(WindowEvent e)   {}
  public void windowDeactivated(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowIconified(WindowEvent e)   {}
  public void windowClosed(WindowEvent e)      {}
  public void windowOpened(WindowEvent e)      {}
  
  
  // Oppretter brukergrensesnittet
  private void opprettGUI() {
    JPanel pnlSkjema = new JPanel();
    
    // Setter opp etiketter og tekstfelt i 4 rader 
    // og 2 kolonner, med 5 piksler luft horisontalt
    // og vertikalt. Gir et brukbart resultat, men
    // litt for mye luft til venstre i vinduet.
    GridLayout layout = new GridLayout(4, 2, 5, 5);
    pnlSkjema.setLayout(layout);
    
    txtNr     = new JTextField(15);
    txtNavn   = new JTextField(15);
    txtPris   = new JTextField(15);
    txtAntall = new JTextField(15);
    
    leggTilFelt(pnlSkjema, "Varenr:", txtNr);
    leggTilFelt(pnlSkjema, "Navn:",   txtNavn);
    leggTilFelt(pnlSkjema, "Pris:",   txtPris);
    leggTilFelt(pnlSkjema, "Antall:", txtAntall);
    
    JPanel knapper = new JPanel();
    btnSøk   = new JButton("Søk");
    btnRensk = new JButton("Rensk");
    knapper.add(btnSøk);
    knapper.add(btnRensk);
    
    add(pnlSkjema, BorderLayout.CENTER);
    add(knapper,   BorderLayout.SOUTH);
    
    txtNr.addActionListener(this);
    btnSøk.addActionListener(this);
    btnRensk.addActionListener(this);
    
    txtNavn.setEditable(false);
    txtPris.setEditable(false);
    txtAntall.setEditable(false);
    
  }  
  
  
  // Hjelpemetode som legger ut et tekstfelt med
  // tilhørende etikett i et panel.
  private void leggTilFelt(JPanel panel, String ledetekst, JTextField txt) {
    JLabel label = new JLabel(ledetekst);
    label.setHorizontalAlignment(JLabel.RIGHT);
    panel.add(label);
    panel.add(txt);
  }
  
  
  // Kobler opp til databasen.
  private void kobleOpp() {
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
  private void kobleNed() {
    try {
      if (conn != null)
        conn.close();
    }
    catch (SQLException e) {
      feil("Klarte ikke å lukke forbindelsen til databasen " + url + "\n" + e.toString());
    }
  }
  
  
  // Utfører databasesøk og oppdaterer tekstfeltene.
  private void finnVare() {
    try {
      String nr = txtNr.getText();
      String sql = "SELECT * FROM Vare WHERE Nr = " + nr;
      
      Statement stmt = conn.createStatement();
      ResultSet rs   = stmt.executeQuery(sql);

      DecimalFormat df = new DecimalFormat("#.00");
    
      String navn;
      double pris;
      int    antall;
    
      // Likhetstest på en primærnøkkelkolonne medfører
      // at vi får 0 eller 1 rad i spørreresultatet.
    
      if (rs.next()) {
        navn   = rs.getString("Navn");
        pris   = rs.getDouble("Pris");
        antall = rs.getInt("Antall");
    
        txtNavn.setText(navn);
        txtPris.setText(df.format(pris));
        txtAntall.setText(Integer.toString(antall));
      }
      else 
        rensk();
    }
    catch (SQLException e) {
      feil("Spørring mot databasen feilet.\n" + e.toString());
    }
  }
  
  
  // Rensker tekstfeltene
  private void rensk() {
    txtNr.setText("");
    txtNavn.setText("");
    txtPris.setText("");
    txtAntall.setText("");
  }
  
  
  // Viser en feilmelding, lukker forbindelsen til
  // databasen og avslutter programmet.
  private void feil(String msg) {
    kobleNed();
    JOptionPane.showMessageDialog(this, msg);
    System.exit(-1);
  }
  
}



/* 

create table Vare
(
  Nr     integer primary key,
  Navn   varchar(50),
  Pris   decimal(8,2),
  Antall integer
);

insert into Vare values(1, 'Spade', 220.50, 23);
insert into Vare values(2, 'Hakke', 199.00, 17);
insert into Vare values(3, 'Spett', 170.00, 58);

*/

