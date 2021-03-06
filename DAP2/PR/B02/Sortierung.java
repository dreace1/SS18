public class Sortierung{//1. 4P, 2. 4P

  //Hannes Rosenkranz
  //180904



  public static void main(String[] args) {
    if(args.length > 0){
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
      else if(args.length == 1 || args.length == 2){              //Fall 4: kein zweiter Parameter, das Feld wird mit Zufallszahlen gefuellt
        for(int a = 0; a < para1; a++){
          feld[a] = numberGenerator.nextInt();
        }
      }
      else if(args.length < 1){
        System.out.println("Geben Sie die Parameter richtig ein, z.B. java Sortierung 1000 rand bzw. java Sortierung 1000 merge rand");
        return;
      }


      tStart = 0;
      tEnd = 0;

      //Sortierung
      if(args.length > 1){
        if(args[1].equals("insert")){         //Falls insert angegeben wurde wird mit insertionSort sortiert
          System.out.println("insertionSort");
          tStart = System.currentTimeMillis(); //Laufzeitmessung starten
          insertionSort(feld);
          tEnd = System.currentTimeMillis();   //Laufzeitmessung stoppen
        }
        else{
          int[] tmpArr = new int[feld.length]; //Hilfarray fuer mergeSort
          System.out.println("mergeSort");     //Falls merge angegeben wurde, wird mit mergeSort sortiert
          tStart = System.currentTimeMillis();
          mergeSort(feld, tmpArr, 0, feld.length-1);
          tEnd = System.currentTimeMillis();
        }
      }
      else{                                  //Falls kein 2. Parameter angegeben wurde soll mit mergeSort sortiert werden
        int[] tmpArr = new int[feld.length];
        System.out.println("mergeSort");
        tStart = System.currentTimeMillis();
        mergeSort(feld, tmpArr, 0, feld.length-1);
        tEnd = System.currentTimeMillis();
      }



      //Zeitdifferenz berechnen
      msecs = tEnd - tStart;

      //Zeit ausgeben
      System.out.println("Die Laufzeit lautet: " + msecs + " ms");

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
  else{
    System.out.println("Geben Sie die Parameter richtig ein, z.B. java Sortierung 1000 rand bzw. java Sortierung 1000 merge rand");

  }

  }

  public static void insertionSort(int[] array){
    int key = 0;
    int i = 0;

    for(int j = 1; j < array.length; j++){
      key = array[j];
      i = j-1;
      while(i >= 0 && array[i] > key){ //Fuer jedes Element welches vor der j-ten Stelle liegt wird der Nachfolger als aktuelle Element
        array[i+1] = array[i];         //gewaelt solange es groesser ist
        i = i-1;
      }
      array[i+1] = key;               //zum schluss muss key noch ein ites Element weiter gesetzt werden
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
  public static void mergeSort(int[] array, int[] tmpArr, int left, int right){
    if(left < right){
      int q =(left+right) / 2;               //Mitte berechnen
      mergeSort(array, tmpArr, left, q);     //Sortiere linke Haelfte
      mergeSort(array, tmpArr, q+1, right);  //Sortiere rechte Haelfte
      merge(array, tmpArr, left, q, right);  //Zusammfuehren der Haelften
    }
  }

  //Hilfsmethode zum Zusammfuehren der sortierten Haelften
  public static void merge(int[] array, int[] tmpArr, int left, int q, int right){
    int tmpLeft = left;
    int tmpMid  = q+1;

    for(int i = left; i <= right; ++i){
      if(tmpLeft <= q && tmpMid <= right){     //Pruefen ob tmpLeft und tmpRight in ihrem Bereich liegen
        if(array[tmpLeft] < array[tmpMid]){
          tmpArr[i] = array[tmpLeft++];        //Vergleichen und einfuegen der Werte in das Hilfsarray
        }
        else{
          tmpArr[i] = array[tmpMid++];
        }
      }
      else if(tmpLeft <= q){                   //Wenn tmpLeft oder tmpRight ausserhalb ihrers Bereiches liegen wird das array
        tmpArr[i] = array[tmpLeft++];          //mit den restlichen Werten gefuellt
      }
      else{
        tmpArr[i] = array[tmpMid++];
      }
    }
    for(int i = left; i <= right; ++i){      //das Array wird mit den sortierten Werten gefuellt
      array[i] = tmpArr[i];
    }

  }


}
