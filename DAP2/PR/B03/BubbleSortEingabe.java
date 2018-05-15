public class BubbleSortEingabe{
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
          bubbleSort(arr);

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

    public static void bubbleSort(int[] array){
      int n = array.length;

      for(int i = 1; i < n; i++){               //Schleife fuer gesamte Laenge des Arrays
        for(int j = 0; j < n-1; j++){           //Schleife fuer die einzelnen Durchlaeufe
          if(array[j] > array[j+1]){            //Wenn die nachfolgende Zahl kleiner ist wird getauscht
            int tmp = array[j];
            array[j] = array[j+1];
            array[j+1] = tmp;
          }
        }
        boolean check = partSorted(array,((array.length-1)-i)); //Es wird mit Assertions geprueft ob der Algorithmus richtig Funktioniert
        assert check : "Fehler bei dem Algorithmus";
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

    public static boolean partSorted(int[] array, int loc){
      for(int i = array.length-1; i > loc; i--){
        if(array[i] < array[i-1]){
          return false;
        }
      }
      return true;
    }

    public static int[] fillArray(String direction, int[] array){
      if(direction.equals("auf")){                                //Array wird aufsteigend gefuellt
        for(int i = 1; i <= array.length; i++){
          array[i-1] = i;
        }
      }
      else if (direction.equals("ab")){                           //Array wird absteigend gefuellt
        int tmp = array.length;
        for(int i = 0; i < array.length; i++){
          array[i] = tmp;
          tmp--;
        }
      }
      else{
        java.util.Random numberGenerator = new java.util.Random(); //Zuffalssgenerator aus den Hinweisen
        for(int i = 0; i < array.length; i++){
          array[i] = numberGenerator.nextInt();
        }
      }
      return array;
    }
}
