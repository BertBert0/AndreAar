import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/*  Programmet lar brukeren velge en bilmodell vha to
 *  kombinasjonsbokser - en for bilmerker og en for bilmodeller.
 *  Når brukeren velger bilmerke fra den første listen,
 *  blir den andre listen oppdatert med aktuelle modeller.
 *
 *  NB! Ved kompilering av koden kommer det en advarsel/note,
 *  men det er laget class-fil og programmet kan kjøres.
 ***********************************************************
 *  Merk at i Java7 er JComboBox endret til generisk datatype
 *  og da kreves det at datatypen (her String) til elementene
 *  i nedtrekkslista blir angitt i kallet på konstruktøren:
 *
 *  JComboBox<String> merker = new JComboBox<String>(merkeTabell);
 *
 *  Dette temaet blir behandlet i kapittel 9.
 */

public class Bilmodeller extends JFrame implements ActionListener {
 
  private JComboBox merker, modeller;
  
  private String[] merkeTabell =
    { "Ford", "Opel", "Toyota" };
    
  private String[][] modellTabell = 
    {
      { "Fiesta", "Focus", "Mondeo" },
      { "Corsa",  "Astra", "Insignia", "Ampera" },
      { "Auris",  "Prius", "Avensis",  "RAV4" }
    };
 
 
  public static void main(String[] args) {
    Bilmodeller vindu = new Bilmodeller();
    vindu.setTitle("Bilmodeller");
    vindu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    vindu.opprettGUI();
    vindu.pack();
    vindu.setLocationRelativeTo(null);
    vindu.setVisible(true);
  }
 
 
  public void opprettGUI() {
    setLayout(new FlowLayout());
 
    JLabel lblMerker = new JLabel("Merker: ");
    merker = new JComboBox(merkeTabell);
    JLabel lblModeller = new JLabel("Modeller: ");
    modeller = new JComboBox();

    add(lblMerker);
    add(merker);
    add(lblModeller);
    add(modeller);
 
    merker.addActionListener(this);
    
    visAktuelle(0); // Default-valget
  }
 
 
  public void actionPerformed(ActionEvent e) {
    int merkeNr = merker.getSelectedIndex();
    modeller.removeAllItems();
    visAktuelle(merkeNr);
  }
  
  
  private void visAktuelle(int merkeNr) {
    String[] aktuelle = modellTabell[merkeNr];
    for (String m : aktuelle)
      modeller.addItem(m);
  }
 
}