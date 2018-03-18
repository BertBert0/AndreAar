import javax.swing.*;

public class GUIdemo extends JFrame {

  public static void main(String[] args) {
    GUIdemo vindu = new GUIdemo();
    vindu.setTitle("GUIdemo");
    vindu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    vindu.opprettGUI();
    vindu.pack();
    vindu.setVisible(true);
  }

  public void opprettGUI() {
    JLabel etikett = new JLabel("Hei verden!");
    add(etikett);
  }

}