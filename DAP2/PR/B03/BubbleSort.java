public class BubbleSort{

  public static void main(String[] args) {
    if(args.length == 1){
      try {
        String para1 = args[0];
        if(para1.equals("start")){
          int[] array = new int[5000];
          long tStart, tEnd, msecs;
          fillArray("ab", array);
          tStart = System.currentTimeMillis();
          bubbleSort(array);
          tEnd = System.currentTimeMillis();
          msecs = tEnd - tStart;
          System.out.println("Die Laufzeit von bubbleSort auf einem Array mit 5000 Elemente mit absteigender Reihenfolge betraegt: " + msecs + "ms");
        }
        else{
          System.out.println("Bitte geben sie start an um das Programm zu starten");
        }
      } catch(IllegalArgumentException e) {
        System.out.println("Bitte geben sie start an um das Programm zu starten");
        return;
      }
    }
  }






  public static int[] bubbleSort(int[] array){
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
    return array;
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
