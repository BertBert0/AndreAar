import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static javax.swing.JOptionPane.*;
import java.util.*;
import java.io.*;

public class Setereservasjon extends EasyGraphics {   // L14

  public static void main(String[] args) {
    launch(args);
  }

  public void run() {
    int bh = 1000;
    int bred=400, høy=800;
makeWindow("Setereservasjon", bred,høy);

int s = bred/5;
int h = høy/10;

// Datamodell for reserveringene
boolean[][] opptattV = new boolean[10][2];
boolean[][] opptattH = new boolean[10][2];
// Registrering pågår (simulering)
for (int i=0; i<7 ;i++ ) {
  int r = trekkTall(0,9);
  int k = trekkTall(0,1);
  opptattV[r][k] =true;
}
for (int k=0; k<7 ;k++ ) {
  int r2 = trekkTall(0,9);
  int k2 = trekkTall(0,1);
  opptattH[r2][k2] =true;

}



for(int rad = 0; rad<10; rad++){
  for(int kol=0; kol<2; kol++){
  drawRectangle(s*kol, s*rad, s-2, s-2);

  if (opptattV[rad][kol])
    fillRectangle(s*kol, s*rad, s-2, s-2);
  }
  }

  for(int rad2 = 0; rad2<10; rad2++){
  for(int kol2=3; kol2<5; kol2++){
  drawRectangle(s*kol2, s*rad2, s-2, s-2);

   if (opptattH[rad2][kol2-3])
    fillRectangle(s*kol2, s*rad2, s-2, s-2);
  }
  }
  // Setter inn radnummer
  for (int radNr= 0; radNr<11; radNr++ ) {
    setFont("Arial", bred/10);
  drawString(""+radNr, bred/2-5, s*radNr-bred/10);    
  }
}
 // Metode for å trekke et tilfeldig heltall i området min - max    
  private static int trekkTall(int min, int max) {
    return min + (int)( Math.random()*(max-min+1) );
  }
}