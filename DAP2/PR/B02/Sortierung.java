public class Sortierung{

  //Hannes Rosenkranz
  //180904



  public static void main(String[] args) {
    try{

      //Zufallszahlen
      java.util.Random numberGenerator = new java.util.Random();

      //Laufzeitmessung
      long tStart, tEnd, msecs;

      //Hauptmethode
      int para1 = Integer.parseInt(args[0]); //Auslesen des ersten Parameters und Konvertierung von String in Integer
      assert para1 > 0 : para1 + " ist nicht groesser als 0!";
      int[] feld = new int[para1];           //Erstellen eines arrays der mit der Laenge des 1. Argumentes


      //Aufgabe 1 + 2
      if(args.length == 3){

        if(args[2].equals("rand")){          //Fall 1: zweiter Parameter "rand", das Feld wird mit Zufallszahlen gefuellt
          for(int a = 0; a < para1; a++){
            feld[a] = numberGenerator.nextInt();
          }
        }
        else if(args[2].equals("auf")){        //Fall 2: zweiter Parameter "auf", das Feld wird aufsteigend gefuellt
          for(int b = 0; b < para1; b++){
            feld[b] = b;
          }
        }
        else if(args[2].equals("ab")){
          for(int c = 0; c < para1; c++){      //Fall 3: zweiter Parameter "ab", das Feld wird absteigend gefuellt
            feld[c] = para1 - c;
          }
        }
        //Aufgabe2
      }
      else if(args.length == 1){              //Fall 4: kein zweiter Parameter, das Feld wird mit Zufallszahlen gefuellt
        for(int a = 0; a < para1; a++){
          feld[a] = numberGenerator.nextInt();
        }
      }
      else if(args.length < 1){
        System.out.println("Geben Sie die Parameter richtig ein, z.B. java Sortierung 1000 rand bzw. java Sortierung 1000 merge rand");
      }


      //Laufzeitmessung starten
      tStart = System.currentTimeMillis();

      //Sortierung
      if(args.length > 1){
        if(args[1].equals("insert")){         //Falls insert angegeben wurde wird mit insertionSort sortiert
          System.out.println("insertionSort");
          insertionSort(feld);
        }
        else if(args[1].equals("merge")){
          System.out.println("mergeSort");    //Falls merge angegeben wurde, wird mit mergeSort sortiert
          mergeSort(feld, 0, feld.length-1);
        }
      }

      //Laufzeitmessung stoppen
      tEnd = System.currentTimeMillis();

      //Zeitdifferenz berechnen
      msecs = tEnd - tStart;

      //Zeit ausgeben
      System.out.println("Die Laufzeit lautet: " + msecs);

      //Ausgabe des Feldes falls das erste Argument kleiner als 100 ist
      if(para1 <= 100){
        for(int d = 0; d < para1; d++){
          System.out.println((d+1) + ". " + feld[d]);
        }
      }

      //Sortierung Ueberpruefen
      if(isSorted(feld)){
        System.out.println("Das Feld ist sortiert.");
      }
      else{
        System.out.println("Das Feld ist nicht sortiert.");
      }
    }
    catch(NumberFormatException numberFormatException){ //Try - Catch Fehlerbehandlung, falls keine gueltige Zahl angegeben wurde
      System.out.println("Ungueltige Angabe! Bitte ganze Zahl angeben.");
      return;
    }

  }

  public static void insertionSort(int[] array){
    int key = 0;
    int i = 0;

    for(int j = 1; j < array.length; j++){
      key = array[j];
      i = j-1;
      while(i >= 0 && array[i] > key){
        array[i+1] = array[i];
        i = i-1;
      }
      array[i+1] = key;
    }
  }

  public static boolean isSorted(int[] array){
    for(int i = 0; i < array.length-1; i++){
      if(array[i+1] < array[i]){             //Ueberpruefen ob die aktuelle Zahl kleiner ist als der Nachfolger
        return false;
      }
    }
    return true;
  }

  //Aufgabe 2
  public static void mergeSort(int[] array, int p, int r){
    if(p < r){
      int q = (p + r) / 2;                 //Mitte berechnen
      mergeSort(array, p, q);              //Sortiere linke Haelfte
      mergeSort(array, q + 1, r);          //Sortiere rechte Haelfte
      merge(array, p, q, r);               //Zusammfuehren der sortierten Haelften
    }
  }

  //Hilfsmethode zum Zusammfuehren der sortierten Haelften
  public static void merge(int[] array, int p, int q, int r){
    int[] arr = new int[array.length];     //Hilfsarray mit der Laenge des vorgegebenen arrays

    for(int i = p; i <= r; i++){
      arr[i] = array[i];                   //Einfuegen der werde des vorgegebenen arrays
    }

    int i = p;
    int j = q + 1;
    int k = p;

    //Einfuegen der kleinsten Werte von Links und Rechts in das vorgegebene Array
    while(i <= q && j <= r){
      if(arr[i] <= arr[j]){                //Falls das erste Objekt aus der linken Haelfte kleiner oder gleich ist, wird die linke Schranke erhoeht
        array[k] = arr[i];
        i++;
      }
      else{
        array[k] = arr[j];                //Sonst wird die rechte Schranke erhoeht
        j++;
      }
      k++;                                //Alle Stellen vor k sind sortiert
    }
    //Der Rest der linken Haelfte wird in das vorgegebene Array eingefuegt
    while(i <= q){
      array[k] = arr[i];
      k++;
      i++;
    }
  }

}
