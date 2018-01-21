import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;


public class Oppgave3 {

  public static void main(String[] args) {
  	int[] tabell = {4, 8, 3, 2, 6, 4, -7, 1};
    int svar = antPartall(tabell);
    //skriver ut
    String utTekst = "Ant. partall: "+ svar;
    showMessageDialog(null, utTekst);


  }
   public static int antPartall(int[] tab){
      int antPar = 0;
      for (int i=0; i<tab.length; i++)
        if ( tab[i]%2 == 0 )
          antPar++;
        return antPar;
}
}