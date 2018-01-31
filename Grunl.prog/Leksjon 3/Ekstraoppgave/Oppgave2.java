public class Oppgave2 extends EasyGraphics {   // L14

  public static void main(String[] args) {
    launch(args);
  }

  public void run() {
    int bh = 400;
    int n = 4;
makeWindow("Target", bh,bh);

for (int i=1; i<=n ;i++ ) {
  drawCircle(bh/2,bh/2,bh/n/2*i);
  
}
  }
}