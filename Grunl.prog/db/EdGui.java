import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/*
 *  Programmet utgjør brukergrensesnittet til en enkel editor. 
 *  Det er satt opp lyttere til knapper og menyvalg, men selve 
 *  hendelsesmetoden actionPerformed inneholder kun dummy-kode.
 *
 *  Merk: Menyer blir omtalt på side 346/347 i boken, men bruker 
 *  klasser fra AWT og ikke Swing. Alle forekomster av klassene 
 *  MenuBar, Menu og MenuItem bør altså erstattes av henholdsvis
 *  JMenuBar, JMenu og JMenuItem, og metoden setMenuBar bør erstattes
 *  av setJMenuBar. Dette er rettet opp i koden under.
 *
 */
public class EdGui extends JFrame
  implements ActionListener, WindowListener {

  private JPanel     pnlKnapper;
  private JButton    btnKlippUt, btnKopier, btnLimInn;
  private JTextArea  txtEd;
  private JTextField txtStatus;
  private JMenuBar   menylinje;
  private JMenu      mnuFil, mnuRediger;
  private JMenuItem  mitNy, mitÅpne, mitLagre, mitAvslutt,
                     mitKlippUt, mitKopier, mitLimInn;

                     
  public static void main(String[] args) {
    EdGui gui = new EdGui();
    gui.setTitle("Editor");
    gui.setDefaultCloseOperation(EXIT_ON_CLOSE);
    gui.setSize(600, 600);
    gui.setVisible(true);
    
  }

  
  public  EdGui() {
  
    // Lag meny-objekter
    menylinje = new JMenuBar();
    mnuFil = new JMenu("Fil");
    mnuRediger = new JMenu("Rediger");
    mitNy = new JMenuItem("Ny");
    mitÅpne = new JMenuItem("Åpne");
    mitLagre = new JMenuItem("Lagre");
    mitAvslutt = new JMenuItem("Avslutt");
    mitKlippUt = new JMenuItem("Klipp ut");
    mitKopier = new JMenuItem("Kopier");
    mitLimInn = new JMenuItem("Lim inn");

    // Lag øvrige GUI-objekter
    pnlKnapper = new JPanel();
    btnKlippUt = new JButton("Klipp ut");
    btnKopier = new JButton("Kopier");
    btnLimInn = new JButton("Lim inn");
    txtEd = new JTextArea(80, 40);
    txtStatus = new JTextField(80);

    // Bygg menysystem
    mnuFil.add(mitNy);
    mnuFil.add(mitÅpne);
    mnuFil.add(mitLagre);
    mnuFil.add(mitLagre);
    mnuFil.addSeparator();
    mnuFil.add(mitAvslutt);
    mnuRediger.add(mitKlippUt);
    mnuRediger.add(mitKopier);
    mnuRediger.add(mitLimInn);
    menylinje.add(mnuFil);
    menylinje.add(mnuRediger);
    this.setJMenuBar(menylinje);

    // Sett layout
    this.setLayout(new BorderLayout());
    FlowLayout FL = new FlowLayout();
    FL.setAlignment(FlowLayout.LEFT);
    pnlKnapper.setLayout(FL);

    // Legg komponenter inn i paneler
    pnlKnapper.add(btnKlippUt);
    pnlKnapper.add(btnKopier);
    pnlKnapper.add(btnLimInn);
    this.add(pnlKnapper, BorderLayout.NORTH);
    this.add(txtEd, BorderLayout.CENTER);
    this.add(txtStatus, BorderLayout.SOUTH);

    // Lytt på relevante komponenter og menyvalg
    btnKlippUt.addActionListener(this);
    btnKopier.addActionListener(this);
    btnLimInn.addActionListener(this);
    mitNy.addActionListener(this);
    mitÅpne.addActionListener(this);
    mitLagre.addActionListener(this);
    mitAvslutt.addActionListener(this);
    mitKlippUt.addActionListener(this);
    mitKopier.addActionListener(this);
    mitLimInn.addActionListener(this);
    this.addWindowListener(this);
  }

  
  // Dummy-versjon av actionPerformed som bare kvitterer
  // med hvilken knapp/menyvalg brukeren har valgt.
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnKlippUt || e.getSource() == mitKlippUt)
      txtStatus.setText("Du valgte: Klipp ut");
    else if (e.getSource() == btnKopier || e.getSource() == mitKopier)
      txtStatus.setText("Du valgte: Kopier");
    else if (e.getSource() == btnLimInn || e.getSource() == mitLimInn)
      txtStatus.setText("Du valgte: Lim inn");
    else if (e.getSource() == mitNy)
      txtStatus.setText("Du valgte: Ny");
    else if (e.getSource() == mitÅpne)
      txtStatus.setText("Du valgte: Åpne");
    else if (e.getSource() == mitLagre)
      txtStatus.setText("Du valgte: Lagre");
    else if (e.getSource() == mitAvslutt) {
      txtStatus.setText("Du valgte: Avslutt");
      avslutt();
    }
  }

  
  
  public void windowClosing(WindowEvent e) {
    avslutt();
  }

  
  public void windowIconified(WindowEvent e)   {}
  public void windowOpened(WindowEvent e)      {}
  public void windowClosed(WindowEvent e)      {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e)   {}
  public void windowDeactivated(WindowEvent e) {}

  
  private void avslutt() {
    System.exit( 0 );
  }
  
}
