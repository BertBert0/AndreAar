import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;


public class Oppgave3 {

  public static void main(String[] args) {
  	int[] tabell = new int[9];
  	int[] random = {0,1,2,3,4,5,6,7,8,9};
  	int trekk;
  	String utskrift = "";
  	for (int i=0; i<tabell.length; i++) {
  		do {
  		trekk = trekkTall(0,9);
  		tabell[i] = random[trekk];
  	} while (tabell[i] == 11);
  		random[trekk] = 11;
  		utskrift += tabell[i] + " ";
  	}
  	int svar = antIndekslike(tabell);
  	out.println(utskrift + "\n" + "Antall indeks like: " + svar);


  }
    private static int trekkTall(int min, int max) {
    return min + (int)( Math.random()*(max-min+1) );
  }

        public static int antIndekslike(int[] tab){
      int antallLike = 0;
      for (int i=0; i<tab.length; i++)
        if ( tab[i] == i )
          antallLike++;
        return antallLike;
    }
}