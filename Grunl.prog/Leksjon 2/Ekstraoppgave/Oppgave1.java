
/* Leksjon, jobb:
*/
import static java.lang.System.*;
import static java.lang.Integer.*;	//Trenger parseInt
import static java.lang.Double.*; 	//Trenger parseDouble
import static javax.swing.JOptionPane.*;

public class Oppgave1 {

  public static void main(String[] args) {
  	
// t inneholder et positivt heltall
int s = 4;
int t = 123;
boolean finnes = false;
// koden begynner her
int temp = t;
do{
if (t%10 == s){
 finnes = true;
}
t = t/10;
 }while(t != 0);

 out.println(finnes);
  }

}