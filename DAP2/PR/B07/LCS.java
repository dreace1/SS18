import java.util.Random;

//Hannes Rosenkranz
//180904
//4Punkte 4Punkte
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
          //Erstellen der zwei Zufallsfolgen
          a = randStr(para1, new Random());
          b = randStr(para1, new Random());

          //Strings ausgeben
          System.out.println(a.toString());
          System.out.println(b.toString());
          System.out.println();

          //Laufzeitmessung starten
          tStart = System.currentTimeMillis();

          //Laengste gemeinsame Teilfolge berechnen
          int[][] table = lcs(a.toCharArray(), b.toCharArray());

          //Laufzeitmessung beenden
          tEnd = System.currentTimeMillis();

          //Zeitdifferenz berechnen
          msecs = tEnd - tStart;

          //Ausgabe der Tabelle
          show(table);

          //Ausgabe der Zeit
          System.out.println("Die Laufzeit von LCS lautet: " + msecs + " ms");

          String sequence = StringSequence(table, b.toString());

          //Ausgabe der Teilfolge
          if(sequence.length() == 0){
            System.out.println("Es gibt keine Teilfolge.");
          }
          else{
            System.out.println("Resulting String: " + sequence);
          }
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
    //Initialisierung der Tabelle
    for(int i = 1; i <= m; i++){
      for(int j = 1; j <= n; j++)
      {
        calculateLenght(x, y, a, i, j);
      }
    }
    return a;
  }

  public static void calculateLenght(char[] x, char[] y, int[][] a, int i, int j){
    //Wenn die chars gleich sind wird das Feld auf den diagonalen Vorgaenger gesetzt
    if(x[i-1] == y[j-1]){
      a[i][j] = a[i-1][j-1]+1;
    }
    else{
      //Wenn der linke Wert groesser ist als der oebere wird das neue Feld auf den groesseren gesetzt
      if(a[i-1][j] >= a[i][j-1]){
        a[i][j] = a[i-1][j];
      }
      else{
        //Sonst wird wird das neue Feld auf den oberen groessten Wert gesetzt
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

  //Ausgabe der Teilfolge
  private static String StringSequence(int[][] table, String str){
    String result = "";
    int counter = 0;
    //Durchlaufen der Tabelle
    for(int i = 1; i < str.length()+1; i++){
      //Wenn ein Wert noch nicht aufgenommen wurde wird er hinzugefuegt
      if(table[i][i] > counter){
        result += str.charAt(i-1);
        counter++;
      }
    }
    return result;
  }
}
