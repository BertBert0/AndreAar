/* Leksjon, jobb:
*/
import static java.lang.System.*;
import static java.lang.Integer.*;	//Trenger parseInt
import static java.lang.Double.*; 	//Trenger parseDouble
import static javax.swing.JOptionPane.*;

public class Oppgave1 {

  public static void main(String[] args) {
  	
// t inneholder et positivt heltall
int t = 101010101;
// koden begynner her
String s = "";
while ( t % 10 > 0 ) {
s = t % 10 + s;
t = t / 10;
}
s = "s = " + s;
out.println(s);

  }

}