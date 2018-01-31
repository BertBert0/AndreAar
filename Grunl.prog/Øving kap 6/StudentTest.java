import static java.lang.System.*;
 
public class StudentTest {
 
  public static void main(String[] args) {
 /*
    // Oppretter og initierer et studentobjekt
    Student s1 = new Student();
    s1.studNr = 123456;
    s1.fornavn = "Per";
    s1.etternavn = "Karlsen";
    s1.fødÅr = 1999;
    s1.kjønn = 'M';
*/
/* 
    // Skriver ut utvalgte data om studenten
     out.println(s1.studNr + ": " + s1.etternavn);

 
    // Endrer etternavn på studenten
    s1.etternavn = "Andersen";
 
    // Skriver ut studentdata på nytt
    out.println(s1.studNr + ": " + s1.fornavn + " " + s1.etternavn + "\n"
                        +"Fødselsår: " + s1.fødÅr + "\n"
                        + "Kjønn: " + s1.kjønn);
*/

    Student s2 = new Student(111222, "Anne", "By", 1999, 'K');
    //Skriver ut studentdata
    s2.skrivUt();

    Student s3 = new Student(333222);
    //Skriver ut studentdata
    s3.skrivUt();

    Student s4 = new Student(333444, "Jo", "Li", 'M');
    //Skriver ut studentdata
    s4.skrivUt();

   
   
  }
 
}