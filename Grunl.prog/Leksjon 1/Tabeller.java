import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;


public class Tabeller {

  public static void main(String[] args) {
    // Lager heltallstabell
    int[] heltall = {3, -5, 17, 2, 0, 1, -8, 3};

    // Metodekall
    int svar = positiveTall(heltall);

    int svarN = negativeTall(heltall);
    //skriver ut
    String utTekst = "Ant. pos. heltall: "+ svar + "\n"
                      + "Ant. neg. heltall: " +svarN;
    showMessageDialog(null, utTekst);


  }
      public static int positiveTall(int[] tab){
      int antallPos = 0;
      for (int i=0; i<tab.length; i++)
        if ( tab[i] > 0 )
          antallPos++;
        return antallPos;
    }

      public static int negativeTall(int[] tab){
      int antallPos = 0;
      for (int i=0; i<tab.length; i++)
        if ( tab[i] < 0 )
          antallPos++;
        return antallPos;
    }
}