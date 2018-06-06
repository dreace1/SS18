import java.util.ArrayList;

//Hannes Rosenkranz
//180904
//4Punkte 4Punkte

public class EditDistance{
  public static void main(String[] args) {
    if(args.length > 0){
      try{

        if(args.length == 2){
          String para1 = args[0];
          String para2 = args[1];

          //Ausgabe von der 1. Sequenz
          System.out.println("Sequenz 1: " + para1);

          //Ausgabe von der 2. Sequenz
          System.out.println("Sequenz 2: " + para2);
          System.out.println();

          int[][] dist = distance(para1, para2);
          //Ausgabe der Tabelle
          show(dist, para1, para2);

          //Ausgabe der Kosten
          System.out.println("Die Kosten betragen: " + cost(dist, para1, para2));

        }
        else if(args.length == 3 && args[2].equals("-o")){
          String para1 = args[0];
          String para2 = args[1];

          //Ausgabe der Operationen
          printEditOperations(para1, para2);
        }
        else{
          System.out.println("Fehler: Bitte geben Sie entweder 2 Strings an oder 2 Strings und den Ausgabeparameter -o");
          System.exit(1);
        }
      }
      catch (IllegalArgumentException e){
        System.out.println("Fehler bei der Parameter Uebergabe.");
        System.err.println(e);
        System.exit(1);
      }
    }
    else{
      System.out.println("Fehler: Bitte geben Sie 2 Strings an.");
      System.exit(1);
    }

  }

  //Levenstein-Algorithmus: Errechnung der Mindestzahl von Editieroperationen von zwei Strings
  public static int[][] distance(String a, String b){
    int lengthA = a.length()+1;
    int lengthB = b.length()+1;
    int cost;

    //Erstellen der Tabelle
    int[][] dist = new int[lengthA][lengthB];

    //Fuellen der Spalten
    for(int i = 0; i < lengthA; i++){
      dist[i][0] = i;
    }

    //Fuellen der Zeilen
    for(int j = 0; j < lengthB; j++){
      dist[0][j] = j;
    }

    //Dynamischer Alg
    for(int i = 1; i < lengthA; i++){
      for(int j = 1; j < lengthB; j++){
        //Wenn a und b an dieser Position den gleichen Buchstaben haben betragen die Kosten 0
        if(a.charAt(i-1) == b.charAt(j-1)){
          cost = 0;
        }
        else{
          cost = 1;
        }
        //Bestimmen welches die geringsten Kosten hat, loeschen, einfuegen, tauschen
        dist[i][j] = min(dist[i-1][j]+1, dist[i][j-1]+1, dist[i-1][j-1]+cost);
      }
    }
    return dist;
  }

  //Hilfsmethode zur Bestimmung des minimums von 3 Integer Werten
  private static int min(int i, int j, int k) {
    if(i<=j && i<=k){
      return i;
    }
    else if(j<=k && j<=i){
      return j;
    }
    else{
      return k;
    }
  }

  //Ausgabe der Kosten, also des Wertes ganz unten rechts in der Tabelle
  private static int cost(int[][] dist, String a, String b){
    return dist[a.length()][b.length()];
  }




  //Ausgabe der Tabelle
  private static void show(int[][] table, String a, String b){
    System.out.println("Die Matrix der Editierdistanz von Sequenz 1 und 2: ");
    for(int i = 0; i  < a.length()+1; i++){
      for(int j = 0; j < b.length()+1; j++){
        System.out.print("\t" + table[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }



  //Ausgabe der verschiedenen Operationen
  private static void printEditOperations(String a, String b){

    //Tabelle der Editierdistanz
    int[][] dist = distance(a, b);

    //Ausgabe der Strings und der Kosten
    System.out.println("Loesung fuer \"" + a + "\" --> \"" + b + "\" mit Gesamtkosten " + cost(dist, a, b) + ":");
    System.out.println("===============================================================");

    //ArrayListen fuer die Operationen
    ArrayList<String> out = new ArrayList<String>();
    ArrayList<Character> in = new ArrayList<Character>();

    //Fuellen der ArrayList mit den chars von b
    for(int i = 0; i < b.length(); i++){
      in.add(b.charAt(i));
    }

    //String fuer den aktuellen Schritt
    String step = "";

    //Laufvariabel
    int i = a.length();
    int j = b.length();

    while(i > 0 && j > 0){
      //Fall: Loeschen
      if(dist[i-1][j] == min(dist[i-1][j], dist[i-1][j-1], dist[i][j-1]) && dist[i-1][j] < dist[i][j]){
        //vor dem loeschen, wird geprueft ob es einen char a[i-1] an der Position j gab
        step = "Kosten 1: " + "Loesche " + a.charAt(i-1) + " an Position " + String.valueOf(j+1) + " --> " + in.toString();
        in.add(j, a.charAt(i-1));
        i--;
      }
      //Fall: Verschieben
      else if(dist[i-1][j-1] == min(dist[i-1][j], dist[i-1][j-1], dist[i][j-1])){
        //vor dem verschieben, wird geprueft ob es vorher ein character a[i-1] an der position j-1 war
        if(dist[i-1][j-1] == dist[i][j]){
          step = "Kosten 0: " + a.charAt(i-1) + " an Position " + String.valueOf(j) + " --> " + in.toString();
        }
        else{
          step = "Kosten 1: " + "Ersetze " + a.charAt(i-1) + " durch " + in.get(j-1) + " an Position " + String.valueOf(j)
                + " --> " + in.toString();
          in.remove(j-1);
          in.add(j-1, a.charAt(i-1));
        }
        i--;
        j--;
      }
      //Fall: Einfuegen
      else{
        //Es war kein character vor dem einfuegen an der Stelle j-1. Der einzufuegende Wert sollte bei in[j-1] sein
        step = "Kosten 1: " + "Fuege " + in.get(j-1) + " an Position " + String.valueOf(j) + " --> " + in.toString();
        in.remove(j-1);
        j--;
      }
      //Schritte werden im output Array gespeichert
      out.add(step);
    }

    //Sonderfaelle falls i oder j noch nicht 0 ist
    while(i > 0){
      step = "Kosten 1: " + "Loesche " + a.charAt(i-1) + " an Position " + String.valueOf(j+1) + " --> " + in.toString();
      in.add(a.charAt(i-1));
      i--;
      out.add(step);
    }

    while(j > 0){
      step = "Kosten 1: " + " Fuege " + in.get(j-1) + " an Position " + String.valueOf(j) + " --> " + in.toString();
      in.remove(j-1);
      j--;
      out.add(step);
    }

    //Finale Ausgabe der Operationen mit Schritten
    for(int p = out.size()-1; p >= 0; p--){
      System.out.println(String.valueOf(out.size()-p) + ") " + out.get(p));
    }
    System.out.println();
  }

}
