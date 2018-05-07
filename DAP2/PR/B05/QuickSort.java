public class QuickSort{

  //Hannes Rosenkranz
  //180904

  public static void main(String[] args) {

    if(args.length == 1){
      try{

        //Zufallszahlen
        java.util.Random numberGenerator = new java.util.Random();

        //Laufzeitmessung
        long tStart, tEnd, msecs;

        int para1 = Integer.parseInt(args[0]); //ersten String Auslesen und Konvertierung von String in Interger



        //Erstellen des Arrays mit der Laenge von para1
        if(para1 >= 0){
          int[] arr = new int[para1];
          //Fuellen des Arrays mit Zufallszahlen
          for(int i = 0; i < arr.length; i++){
            arr[i] = numberGenerator.nextInt();
          }

          //Laufzeitmessung beginnen
          tStart = System.currentTimeMillis();

          //Array Sortierung
          quickSort(arr, 0, arr.length-1);

          //Hier wird mit den Assertions geprueft ob der Algorithmus nach Plan abgelaufen ist
          boolean check = isSorted(arr);
  				assert check : "Fehler bei der finalen Uebergabe des Algorithmus";

          //Laufzeitmessung beenden
          tEnd = System.currentTimeMillis();

          //Zeitdifferenz berechnen
          msecs = tEnd - tStart;

          System.out.println("Die Laufzeit von QuickSort lautet: " + msecs + "ms");

          //Sortierung Pruefen
          if(isSorted(arr)){
            System.out.println("Das Feld ist sortiert");
            //toString(arr);
          }
          else{
            System.out.print("Das Feld ist nicht sortiert");
          }
        }
        else{
          System.out.println("Der Parameter sollte groesser als 0 sein.");
          return;
        }
      }
      catch (NumberFormatException e){
        System.out.println("Fehler bei der Parameteruebergabe");
        return;
      }
    }
    else{
      System.out.println("Bitte geben Sie nur einen Interger an.");
      return;
    }







  }

  //QuickSort Algorithmus
  public static void quickSort(int[] a, int l, int r){

    //Linke Grenze muss groesser sein, sind sie gleich ist das Feld sortiert, ist sie groesser liegt eine Fehler vor
    if(l < r){

      //Speichern der Grenzen
      int i = l;
      int j = r;

      //Pivotelement bestimmen, Mittelwert aus l und r
      int pivot = a[(l+r)/2];

      //Schranken bestimmen
      while(i <= j){
        //Solange das  linke Element kleiner als das Pivot ist, wird die linke Schranke nach rechts geschoben
        while(a[i] < pivot){
          i++;
        }
        //Solange das rechte Element kleiner ist, wird die rechte Schranke nach links geschoben
        while(a[j] > pivot){
          j--;
        }
        //Falls die linke Schranke kleiner als die rechte ist, werden die Elemente getauscht und die Schranken um eins geschoben
        if(i <= j){
          int tmp = a[i];
          a[i] = a[j];
          a[j] = tmp;
          i++;
          j--;
        }
      }
      //Rekursive Aufrufe fuer die linke und reche Haelfte
      quickSort(a, l, j);
      quickSort(a, i, r);
    }
  }

  public static boolean isSorted(int[] a){
    for(int i = 0; i < a.length-1; i++){
      if(a[i+1] < a[i]){             //Ueberpruefen ob die aktuelle Zahl kleiner ist als der Nachfolger
        return false;
      }
    }
    return true;
  }

  public static void toString(int[] a){
    for(int i = 0; i < a.length; i++){
      System.out.println(a[i]);
    }
    System.out.println();
  }




}
