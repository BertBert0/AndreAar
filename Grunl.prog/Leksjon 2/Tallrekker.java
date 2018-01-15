import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;


public class Tallrekker {

  public static void main(String[] args) {
  	int[] tabellA = new int[5];
  	int[] tabellB = new int[5];
    
  	    for (int j=0; j<tabellA.length; j++)
    	tabellA[j] = trekkTall(0,9);

  	    for (int x=0; x<tabellA.length; x++)
    	tabellB[x] = trekkTall(0,9);

    // Equals?
    String bolSvar = "";
    boolean tEquals = Arrays.equals(tabellA, tabellB);
    if (tEquals == true)
    	bolSvar = "Tabellene er like!";
    else{
    	bolSvar = "Tabellene er IKKE like";
    }

    //sortering
int[] tabellASort = new int[5];
int[] tabellBSort = new int[5];

 arraycopy(tabellA, 0, tabellASort, 0, tabellA.length);
 arraycopy(tabellB, 0, tabellBSort, 0, tabellB.length);

Arrays.sort(tabellASort);
Arrays.sort(tabellBSort);

int[] storTab = samle(tabellA, tabellB);
Arrays.sort(storTab);

//Finnes i tabell
int inTall = parseInt(showInputDialog("Gi et heltall (0-9)"));

boolean finnesA = false;
boolean finnesB = false;
for (int tA = 0; tA<tabellA.length; tA++)
if (tabellA[tA] == inTall)
	finnesA = true;


for (int tB = 0; tB<tabellB.length; tB++)
if ( tabellB[tB] == inTall)
	finnesB = true;

String finnesSvar= "";
if (finnesA == true && finnesB == true)
finnesSvar = "begge tabellene";

else
	finnesSvar = "en av tabellene";

if (finnesA == false && finnesB == false)
	finnesSvar = "ingen av tabellene";

    // Utkrift
    String utTekst = "Tabell A: " + Arrays.toString(tabellA) + "\n"
    		+ "Tabell B: " + Arrays.toString(tabellB) + "\n"
    		+ bolSvar + "\n"
    		+ "Tabell A sortert: "+ Arrays.toString(tabellASort) + "\n"
    		+ "Tabell B sortert: "+ Arrays.toString(tabellBSort) + "\n"
    		+ "Største tall i tabellene: " + storTab[9] + "\n"
    		+ "Tallet " + inTall + " finnes i " + finnesSvar ;


    // Trekk og skriv ut 5 ulike siffer i en tallrekke.
    		utTekst += "\n" + "Ny tallrekke: ";
    		char[] tallrekkeTemp = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    		for (int nr= 1; nr <= 5; nr++)
    			int teller = 0;
    			do{
    				teller = trekkTall(0,4);
    			} while (tallrekkeTemp[teller] == '*')
    			

    		//Utskrift
    showMessageDialog(null, utTekst);

  }
  // Metode for å trekke et tilfeldig heltall i området min - max    
  private static int trekkTall(int min, int max) {
    return min + (int)( Math.random()*(max-min+1) );
  }

    public static int[] samle (int[] tabA, int[] tabB) {
    int nyLengde = tabA.length + tabB.length;
    int[] nyTab = new int[nyLengde];
    for (int i=0; i<tabA.length; i++) {
      nyTab[i] = tabA[i];
    }
    for (int j=0; j<tabB.length; j++) {
      nyTab[tabA.length+j] = tabB[j];
    }
    return nyTab ; // Returnerer referanse til ny tabell
  }
}