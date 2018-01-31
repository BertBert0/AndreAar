import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;


public class Tekstanalyse {

  public static void main(String[] args) {


//Leser inn
  	String tekst = showInputDialog("Gi en tekst: ");
  	// a) Slår om til kun små bokstaver
  	String tekstSmå = tekst.toLowerCase();

  	// b) Avgjør om bokstaven 'e' er representert i teksten tekst
  	String charTest ="" ;
  	boolean bokstav = tekst.contains("e");
  	if(bokstav == true)
  		charTest = "Bokstaven 'e' finnes i teksten";
  	else
  		charTest = "Bokstaven 'e' finnes ikke i teksten";

  	// c) forekomster av gitt bokstav
  	char antBokstav = 't';

  	// d) Tell antall forekomster av alle de engelske bokstavene.


//Skriver ut    
String utTekst = "Inn: " + tekst + "\n"
				+ "Små: " + tekstSmå + "\n"
				+ charTest + "\n"
				+"Antall " + antBokstav + " i teksten: " + antallAv('t', tekst) ;
out.println(utTekst);
  }

          public static int antallAv(char c, String txt){
      int antallAv = 0;
      for (int i=0; i<txt.length(); i++)
        if ( txt.charAt(i) == c )
          antallAv++;
        return antallAv;
    }

              public static int antallEngelsk( String txt){
      int antallAv = 0;
      for (int i=0; i<txt.length(); i++)
        if ( txt.charAt(i) == c )
          antallAv++;
        return antallAv;
    }
}