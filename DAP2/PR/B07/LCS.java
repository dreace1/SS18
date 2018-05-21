import java.util.Random;

public class LCS{
  public static void main(String[] args) {
    if(args.length == 1){
      try {
        //Auslesen des ersten Parameters und Konvertierung von String in Integer
        int para1 = Integer.parseInt(args[0]);

        String a, b;

        //Laufzeitmessung
        long tStart, tEnd, msecs;

        if(para1 >= 0){
          a = randStr(para1, new Random());
          b = randStr(para1, new Random());

          //Strings ausgeben
          System.out.println(a.toString());
          System.out.println(b.toString());
          System.out.println();

          //Laufzeitmessung starten
          tStart = System.currentTimeMillis();

          //Laengste gemeinsame Teilfolge berechnen
          int[][] arr = lcs(a.toCharArray(), b.toCharArray());

          //Laufzeitmessung beenden
          tEnd = System.currentTimeMillis();

          //Zeitdifferenz berechnen
          msecs = tEnd - tStart;

          show(arr);

          System.out.println("Die Laufzeit von LCS lautet: " + msecs + " ms");

          System.out.println("Resulting String: " + StringSequence(arr, b.toString()));
        }
        else{
          System.out.println("Bitte einen positiven Integer angeben");
          System.exit(1);
        }
      }
      catch (NumberFormatException e){
        System.out.println("Es wurden fehlerhafte Parameter uebergeben");
        System.err.println(e);
        System.exit(1);
      }
    }
    else{
      System.out.println("Bitte geben Sie nur einen Parameter an!");
      System.exit(1);
    }


  }

  //Zufallsfolge von Strings
  public static String randStr(int n, Random r){
    String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder res = new StringBuilder(n);

    while(--n >= 0){
      res.append(alphabet.charAt(r.nextInt(alphabet.length())));
    }
    return res.toString();
  }

  public static int[][] lcs(char[] x, char[] y){
    int m = x.length;
    int n = y.length;

    //Tabelle anlegen
    int[][] a = new int[m+1][n+1];

    //Erste Spalte auf 0 setzen
    for(int i = 0; i <= m; i++){
      a[i][0] = 0;
    }
    //Erste Reihe auf 0 setzen
    for(int j = 0; j <= n; j++){
      a[0][j] = 0;
    }

    for(int i = 1; i <= m; i++){
      for(int j = 1; j <= n; j++)
      {
        calculateLenght(x, y, a, i, j);
      }
    }
    return a;
  }

  public static void calculateLenght(char[] x, char[] y, int[][] a, int i, int j){
    //Wenn die chars gleich sind wird das naechste groessere gewaelt
    if(x[i-1] == y[j-1]){
      a[i][j] = a[i-1][j-1]+1;
    }
    else{
      if(a[i-1][j] >= a[i][j-1]){
        a[i][j] = a[i-1][j];
      }
      else{
        a[i][j] = a[i][j-1];
      }
    }
  }

  //Ausgabe der Tabelle
  private static void show(int[][] a){
    for(int i = 0; i  < a.length; i++){
      for(int j = 0; j < a.length; j++){
        System.out.print(a[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private static String StringSequence(int[][] table, String str){
    String result = "";
    int counter = 0;
    for(int i = 1; i < str.length()+1; i++){
      if(table[i][i] > counter){
        result += str.charAt(i-1);
        counter++;
      }
    }
    return result;
  }
}
