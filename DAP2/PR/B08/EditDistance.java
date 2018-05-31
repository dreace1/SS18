import java.util.ArrayList;


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

          int[][] table = distance(para1, para2);
          show(table);

          //Ausgabe der Distanz
          System.out.println("Die Distanz betraegt: " + table[table.length-1][table[0].length-1]);

        }
        else if(args.length == 3 && args[2].equals("-o")){
          String para1 = args[0];
          String para2 = args[1];

          ArrayList<String> list = new ArrayList<String>();
          list.add(para1);
          list.add(para2);

          printEditOperations(list);
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
    }

  }

  public static int[][] distance(String a, String b){
    int lengthA = a.length()+1;
    int lengthB = b.length()+1;
    //Erstellen der Tabelle
    int[][] table = new int[lengthA][lengthB];

    //Fuellen der Spalten
    for(int i = 0; i < lengthA; i++){
      table[i][0] = i;
    }

    //Fuellen der Zeilen
    for(int j = 0; j < lengthB; j++){
      table[0][j] = j;
    }

    //Dynamischer Alg
    for(int i = 1; i < lengthA; i++){
      for(int j = 1; j < lengthB; j++){
        table[i][j] = evaluate(a.charAt(i-1), b.charAt(j-1), table[i-1][j-1], table[i-1][j], table[i][j-1]);
      }
    }
    //show(table);
    //return table[table.length-1][table[0].length-1];
    return table;
  }

  //Hilfsmethode zur Bestimmung des min
  private static int min(int[] values){
    int result = values[0];
    for(int i = 1; i < values.length; i++){
      if(result > values[i]){
        result = values[i];
      }
    }
    return result;
  }

  private static int evaluate(char a, char b, int... values){
    if(a == b){
      return values[0];
    }
    else{
      return min(values)+1;
    }
  }

  public static void printEditOperations(ArrayList<String> list){
    //Strings der uerbergebenen Parameter
    String a = list.get(0);
    String b = list.get(1);

    System.out.println("Loesung fuer \"" + a + "\" --> \"" + b + "\" mit Gesamtkosten " + distance(a, b) + ":");
    System.out.println("===================================================");

    //Variabel fuer den aktuellen Schritt
    int step = 1;
    int i = 0;

    //Fehlerabfrage
    if(a.length() < b.length()){
      System.err.println("Fehler: der erste String ist kuerzer als der zweite String, bitte tauschen");
      System.exit(1);

    }

    while(!a.equals(b)){
      //Fall: Loeschen
      if((a.length() - (a.length() - b.length())) == i && a.length() > b.length()){
        a = delete(a, i);
        System.out.println(step + ") Kosten 1: Loesche " + a.charAt(i-1) + " an Position " + (i+1) + " --> " + a);
      }
      else{
        char charA = a.charAt(i);
        char charB = b.charAt(i);

        if(charA == charB){
          System.out.println(step + ") Kosten 0: " + charA + " an Position " + (i+1) + " --> " + a);
          i++;
        }
        else if(charA != charB && a.length()-i < 2 || b.length()-i < 2){
          a = replace(a, i, charB);
          System.out.println(step + ") Kosten 1: Ersetze " + charA + " durch " + charB + " an Position " + (i+1) + " --> " + a);
          i++;
        }
        else if(charA != charB && b.charAt(i+1) == charA){
          a = add(a, i, charB);
          System.out.println(step + ") Kosten 1: Fuege " + charB + " an Position " + (i+1) + " ein --> " + a);
          i++;
        }
        else if(charA != charB && a.charAt(i+1) == charB){
          a = delete(a, i);
          System.out.println(step + ") Kosten 1: Loesche " + charA + " an Position " + (i+1) + " --> " + a);
        }
        else{
          a = replace(a, i, charB);
          System.out.println(step + ") Kosten 1: Ersetze " + charA + " durch " + charB + " an Position " + (i+1) + " --> " + a);
        }
        step++;
      }

      if(a.charAt(a.length()-1) == b.charAt(b.length()-1) && list.get(0).length() == list.get(1).length()){
        System.out.println(step + ") Kosten 0: " + a.charAt(a.length()-1) + " an Position " + (i+1) + " --> " + a);
        i++;
      }
    }
  }

    //Operationen:

    //Ersetzen
    private static String replace(String a, int index, char c){
      String temp = "";

      for(int i = 0; i < index; i++){
        temp += a.charAt(i);
      }
      temp += c;

      for(int i = index+1; i < a.length(); i++){
        temp += a.charAt(i);
      }
      return temp;
    }

    //Loeschen
    private static String delete(String a, int index){
      String temp = "";

      for(int i = 0; i < index; i++){
        temp += a.charAt(i);
      }

      for(int i = index+1; i < a.length(); i++){
        temp += a.charAt(i);
      }
      return temp;
    }

    //Einfuegen
    private static String add(String a, int index, char c){
      String temp = "";

      for(int i = 0; i < index; i++){
        temp += a.charAt(i);
      }
      temp += c;

      for(int i = index; i < a.length(); i++){
        temp += a.charAt(i);
      }
      return temp;
    }




  //Ausgabe der Tabelle
  private static void show(int[][] a){
    System.out.println("Die Matrix der Editierdistanz von Sequenz 1 und 2: ");
    for(int i = 0; i  < a.length; i++){
      for(int j = 0; j < a.length; j++){
        System.out.print("\t" + a[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
