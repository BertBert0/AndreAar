public class Student {
  int    studNr;
  String fornavn;
  String etternavn;
  int fødÅr;
  char kjønn;

 //Konstruktør
  public Student(int studNr, String fornavn, String etternavn, 
                 int fødÅr, char kjønn) {

    this.studNr = studNr;
    this.fornavn = fornavn;
    this.etternavn = etternavn;
    this.fødÅr = fødÅr;
    this.kjønn = kjønn;

  }

  // OverLaster konstruktøren
    public Student(int studNr) {

    this.studNr = studNr; // Eneste inndata
    this.fornavn = "Ola";
    this.etternavn = "Nordmann";
    this.fødÅr = -1;
    this.kjønn = '#';
  }
  public Student(int studNr, String fornavn, String etternavn, char kjønn) {

    this(studNr, fornavn, etternavn, -1, kjønn);

  }

  public void skrivUt() {
    System.out.println(studNr + ": " + fornavn + " " + etternavn + "\n"
               +"Fødselsår: " + fødÅr + "\n"
               + "Kjønn: " + kjønn);
  }

}