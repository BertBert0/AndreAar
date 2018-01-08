public class Oppgave2 extends EasyGraphics {   // L14

  public static void main(String[] args) {
    launch(args);
  }

  public void run() {
    int bh = 400;
    int tDel = bh/3;
    makeWindow("Oppgave2", bh, bh);
    // Øverst
    drawRectangle(tDel*2, 0, tDel, tDel);
    // Midten
    drawRectangle(tDel, tDel, tDel, tDel);
    drawCircle(bh/2, bh/2, bh/2);

    // Nederst
    drawRectangle(0, tDel*2, tDel, tDel);
  }
}