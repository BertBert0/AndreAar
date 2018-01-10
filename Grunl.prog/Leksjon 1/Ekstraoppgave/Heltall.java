    public class Heltall {

      public static int positiveTall(int[] tab){
      int antallPos = 0;
      for (int i=0; i<tab.length; i++)
        if ( tab[i] > 0 )
          antallPos++;
        return antallPos;
    }

      public static int negativeTall(int[] tab){
      int antallNeg = 0;
      for (int i=0; i<tab.length; i++)
        if ( tab[i] < 0 )
          antallNeg++;
        return antallNeg;
    }

      public static int antallNegative(int x, int y, int z){
      int antallNeg = 0;
        if ( x < 0 )
          antallNeg++;
        if ( y < 0 )
          antallNeg++;
        if ( z < 0 )
          antallNeg++;

        return antallNeg;
}
}