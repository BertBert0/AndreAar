import static javax.swing.JOptionPane.*; 
import static java.lang.System.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.*;
import java.io.*;

public class obligGUI extends JFrame 
  //implements ActionListener 
{
 
  //private String jdbcDriver = "org.sqlite.JDBC";
  //private String url        = "jdbc:sqlite:butikk.db";
    
  //private Connection conn = null;
  private JPanel        pnlTopBar, pnlButtons, pnlCenter;
  private JLabel        tlfAdd, fNavnAdd, eNavnAdd, adrAdd, kontAdd ;  
  private JButton       btnAdd, saveBack, loadBack ;
  private JTextField    txtTlf, txtFNavn, txtEnavn, txtAdr, txtStatus ;
  private JTextArea     txtKont ;
  private JComboBox     sortList;

  public static void main(String[] args) {

    obligGUI vindu = new obligGUI();
    vindu.setTitle("Kontakter");
    vindu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    vindu.setSize(600, 600);
    vindu.setVisible(true);



  }

    public obligGUI() {
  String[] sortString = { "Telefonnummer", "Fornavn", "Etternavn" };

 // Lag øvrige GUI-objekter
    pnlTopBar = new JPanel();
    pnlButtons = new JPanel();
    pnlCenter = new JPanel();
    btnAdd = new JButton("Legg til");
    saveBack = new JButton("Lagre backup");
    loadBack = new JButton("Last backup");
    txtKont = new JTextArea(40, 30);
    txtStatus = new JTextField(80);
    txtTlf = new JTextField(6);
    txtFNavn = new JTextField(5);
    txtEnavn = new JTextField(5);
    txtAdr = new JTextField(10);
    kontAdd = new JLabel("Kontakter");
    tlfAdd = new JLabel("Tlf:");
    fNavnAdd = new JLabel("Fnavn:");
    eNavnAdd = new JLabel("Enavn:");
    adrAdd = new JLabel("Adr:");
    sortList = new JComboBox<>(sortString);


 
    // Sett layout
   this.setLayout(new BorderLayout());
    FlowLayout FLTopBar = new FlowLayout();
    FlowLayout FLBut = new FlowLayout();
    FlowLayout FLCent = new FlowLayout();
    FLTopBar.setAlignment(FlowLayout.CENTER);
    FLBut.setAlignment(FlowLayout.CENTER);
    FLCent.setAlignment(FlowLayout.CENTER);
    pnlTopBar.setLayout(FLTopBar);
    pnlButtons.setLayout(FLBut);
    pnlCenter.setLayout(FLCent);


  

    // Legg komponenter inn i paneler
    pnlButtons.add(saveBack);
    pnlButtons.add(loadBack);
    pnlButtons.add(sortList);

    pnlTopBar.add(tlfAdd);
    pnlTopBar.add(txtTlf);
    
    pnlTopBar.add(fNavnAdd);
    pnlTopBar.add(txtFNavn);
    
    pnlTopBar.add(eNavnAdd);
    pnlTopBar.add(txtEnavn);

    pnlTopBar.add(adrAdd);
    pnlTopBar.add(txtAdr);

    pnlTopBar.add(btnAdd);

    pnlCenter.add(txtKont);
    pnlCenter.add(kontAdd);

    this.add(pnlTopBar, BorderLayout.NORTH);
    this.add(pnlButtons, BorderLayout.EAST); 
    this.add(pnlCenter, BorderLayout.WEST);
    this.add(txtStatus, BorderLayout.SOUTH);

/*
    // Lytt på relevante komponenter og menyvalg
    btnKlippUt.addActionListener(this);
    btnKopier.addActionListener(this);
    btnLimInn.addActionListener(this);
    mitNy.addActionListener(this);
    mitÅpne.addActionListener(this);
    mitLagre.addActionListener(this);
    mitAvslutt.addActionListener(this);
    mitKlippUt.addActionListener(this);
    FlowLayout FLTopBar = new FlowLayout();
    mitKopier.addActionListener(this);
    mitLimInn.addActionListener(this);
    this.addWindowListener(this);
  */
  }


}