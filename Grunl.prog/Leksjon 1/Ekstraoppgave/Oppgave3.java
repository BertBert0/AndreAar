import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;


public class Oppgave3 {

  public static void main(String[] args) {
    int svar = Heltall.antallNegative(-1,2,-3);
    //skriver ut
    String utTekst = "Ant. neg. heltall: "+ svar;
    showMessageDialog(null, utTekst);


  }
}