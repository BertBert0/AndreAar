public class Oppgave2 extends EasyGraphics {   // L14

  public static void main(String[] args) {
    launch(args);
  }

  public void run() {
    int bh = 400;
    int toDel = bh/2;
    int svar = 2;
    makeWindow("Oppgave2", bh, bh);
    if (svar == 1){
    // Øverst
    drawCircle(toDel/2,toDel/2, toDel/2);
    drawRectangle(toDel, 0, toDel, toDel);

    // Nederst
    drawRectangle(0, toDel, toDel, toDel);
    drawCircle(toDel+toDel/2,toDel+toDel/2, toDel/2);
}

if (svar == 2){
	// Øverst
    drawRectangle(0, 0, toDel, toDel);
    drawCircle(toDel+toDel/2,toDel/2, toDel/2);

    // Nederst
    drawCircle(toDel/2,toDel+toDel/2, toDel/2);
    drawRectangle(toDel, toDel, toDel, toDel);

  }
}
}