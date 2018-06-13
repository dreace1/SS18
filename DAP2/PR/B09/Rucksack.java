public class Rucksack{
  //Hannes Rosenkranz
  //180904
  //4P. 4P.

  public static void main(String[] args) {
    if(args.length == 3){
      try {
        //Auslesen der Parameter und Konvertierung von String in Integer
        int n = Integer.parseInt(args[0]);
        int W = Integer.parseInt(args[1]);
        int p = Integer.parseInt(args[2]);

        //Array der Artikel mit der Laenge des gegebenen Parameters
        Article[] article = new Article[n];

        //Laufzeivariabeln
        long tStart, tEnd, msecs, msecs2;

        //Tabelle
        int[][] result;

        //Zufallsgenerator
        java.util.Random numberGenerator = new java.util.Random();

        //Erstellen von den Zufallswerten fuer
        //Value [100, 1000]
        //Weight [0.8p, 1.25p]
        for(int i = 0; i < n; i++){
          article[i] = new Article(
            numberGenerator.nextInt(900) + 100,
            numberGenerator.nextInt((int)Math.round(p*0.45))+ (int)Math.round(p*0.8));
            System.out.println("Artikel: " + (i+1) + " , Wert: " + article[i].getValue() +
                               ", Gewicht: " + article[i].getWeight());
        }


        System.out.println("Anzahl der Artikel: " + n);
        System.out.println("Gewichtslimit " + W);
        System.out.println();

        //Greedy Rucksackproblem Ansatz
        tStart = System.currentTimeMillis();

        System.out.println("Die Summe der Werte: " + RucksackGreedy(article, W));

        tEnd = System.currentTimeMillis();

        msecs = tEnd - tStart;

        System.out.println("Die Laufzeit von RucksackGreedy betraegt: " + msecs + "ms");
        System.out.println();


        //Dynamischer Rucksackproblem Ansatz
        tStart = System.currentTimeMillis();

        result = RucksackDynamic(article, W);

        tEnd = System.currentTimeMillis();

        msecs2 = tEnd - tStart;

        show(result);

        System.out.println("Das Optimale Ergebnis betraegt fuer die Werte: " + returnOptimalResult(result, article, W));
        System.out.println();

        System.out.println("Die Laufzeit von RucksackDynamic betraegt: " + msecs2 + " ms");

      } catch(NumberFormatException e){
        System.out.println("Fehler bei der Parameter Uebergabe!");
        System.err.println(e);
        System.exit(1);
      }
    }
    else{
      System.out.println("Fehler, bitte geben Sie 3 Integer Werte ein!");
      System.exit(1);
    }

  }

  //Der Dynamische Algorithmus des Rucksackproblemes waehlt Objekte die einen moeglichst grossen Gesamtwert haben
  public static int[][] RucksackDynamic(Article[] article, int W){
    //Erstellen der Tabelle
    int[][] opt = new int[article.length][W+1];

    //Fuellen mit 0
    for(int j = 0; j <= W; j++){
      opt[0][j] = 0;
    }

    //Dynamischer Rucksack Algorithmus
    for(int i = 1; i < article.length; i++){
      for(int j = 0; j <= W; j++){
        //Fuellen der Tabelle mit einer optimalen Loesung
        //Wenn der Artikel noch in den Rucksack passt
        if(i > 0 && j >= article[i].getWeight()){
          opt[i][j] = Math.max(opt[i-1][j], article[i].getValue() + opt[i-1][j-article[i].getWeight()]);
        }
        //Wenn es nicht mehr in den Rucksack passt
        else{
          opt[i][j] = opt[i-1][j];
        }
      }
    }
    return opt;
  }

  //Hilfsmethode zur Ausgabe des Optimalen Ergebnisses
  private static int returnOptimalResult(int table[][], Article[] article, int W){
    return table[article.length-1][W];
  }

  //Hilfsmethode zur Ausgabe der Tabelle
  private static void show(int[][] table){
    System.out.println("==================================================");
    System.out.println();
    System.out.println("Dynamischer Ansatz fuer das Rucksackproblem:");
    for(int i = 0; i  < table.length; i++){
      for(int j = 0; j < table[i].length; j++){
        System.out.print("\t" + table[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  //Greedy Ansatz fuer das Rucksack Problem
  public static int RucksackGreedy(Article[] article, int W){
    //Array fuer den Rucksack und verfuegbarkeit der Artikel
    int greedyRucksack[] = new int[article.length];
    boolean available[] = new boolean[article.length];

    System.out.println("Gieriger Ansatz, waehle die Artikel mit dem besten Verhaeltnis von Wert zu Gewicht: ");
    System.out.println();

    //Es ist noch nichts im Rucksack alle Artikel sind verfuegbar
    for(int i = 0; i < article.length; i++){
      available[i] = true;
    }

    //Ermittel das Verhaeltnis von Wert zu Gewicht
    for(int i = 0; i < article.length; i++){
      greedyRucksack[i] = article[i].getValue()/article[i].getWeight();
      System.out.println("Artikel Nr. " + (i+1) + " hat Prozent: " + greedyRucksack[i]);
    }

    int count = 0;
    int max = 0;
    int result = 0;
    int maxIndex = 0;
    int currentWeight = 0;

    //Waehle die besten Artikel

    //Durchlaufe bis das Gewichtlimit erreicht ist
    while(currentWeight <= W && count < article.length){
      for(int i = 0; i < article.length; i++){
        //Wenn Artikel verfuegbar sind
        if(available[i]){
          //Waehle das naechste besste
          if(greedyRucksack[i] > max){
            max = greedyRucksack[i];
            maxIndex = i;
          }
        }
      }
      count++;
      //Fuege das Gewicht ein
      currentWeight += article[maxIndex].getWeight();

      //Angabe welche Artikel hinzugefuegt wurden
      if(currentWeight <= W){
        System.out.println("...fuege Artikel ["+(maxIndex+1)+"] hinzu" );
        result += article[maxIndex].getValue();
      }

      available[maxIndex] = false;
      max = 0;
    }

    return result;
  }
}
