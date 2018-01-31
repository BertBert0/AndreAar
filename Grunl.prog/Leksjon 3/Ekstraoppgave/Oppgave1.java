import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;


public class Oppgave1 {

  public static void main(String[] args) {
    int ht1 = 17;
    int ht2 = 23;
    int stigende = ht1;
    int avtagende = ht2;
    String linje1= "";
    String linje2= "";
    for (int i=0; stigende<=ht2 ;i++ ) {
    	linje1 += stigende + " ";
    	linje2 += avtagende + " ";
    	stigende++;
    	avtagende--;
    	
    }
    out.println(linje1 + "\n" + linje2);

  }
}