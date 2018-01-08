public class Franskflagg extends EasyGraphics {   // L14

  public static void main(String[] args) {
    launch(args);
  }

  public void run() {
makeWindow("VIVA LA FRANCE!", 2000,1000);
  int w = 100;
  setColor(255,0,0);
  drawLine(5*w, 3*w, 3*w, w);
  setColor(0,255,0);
  fillRectangle(w, w, 2*w, w);     
  fillRectangle(3*w, 2*w, w, 2*w); 
  }
}