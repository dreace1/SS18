public class GeometrischeSuche{

  //Hannes Rosenkranz
  //180904


  public static void main(String[] args) {
    if(args.length == 2){
      try {
        float para1 = Float.parseFloat(args[0]);
        int para2 = Integer.parseInt(args[1]);

        if(para1 > 0 && para2 > 0 && para2 < 100){
          int[] array = new int[1000];
          int status = 1;
          long tStart, tEnd, msecs;           //Laufzeitmessung

          fillArray("ab", array);              //Feld wird mit absteigender Reihenfolge gefuellt
          System.out.println("");
          System.out.println("Durchlaeufe: ");
          while(status >= 1){
            msecs = 0;
            for(int x = 0; x < para2; x++){
              tStart = System.currentTimeMillis(); //Zeitmessung wird gestartet
              bubbleSort(array);
              tEnd = System.currentTimeMillis(); //Zeitmessung wird gestoppt

              boolean check = isSorted(array);
              assert check : "Fehler bei der finalen Uebgergabe des Algorithmus"; //Assert zum Ueberpruefen ob der Algorithmus nach Plan verlief
              msecs = tEnd - tStart;
            }
            msecs = msecs / para2;                //Berechnen der Toleranz
            System.out.println("Status " + status + ": " + msecs + "ms" );
            status++;

            if(msecs > para1*1000){
              status = 0;
            }
            else{
              array = new int[array.length*2];
              fillArray("ab", array);
            }
          }

          System.out.println("");

          //Errechnen des Mittelwertes
          int right = array.length;
          int left = array.length/2;
          int tmpArraySize = ((right - left)/2) + left; //zur Positionierung wird der kleinere Wert addiert

          int start = 0;
          int status2 = 1;
          System.out.println("BinaereSuche gestartet.");
          System.out.println("Durchlaeufe: ");
          while(status2 >= 1){
            array = new int[tmpArraySize];
            fillArray("ab", array);
            msecs = 0;
            for(int x = 0; x < para2; x++){
              tStart = System.currentTimeMillis();
              bubbleSort(array);
              tEnd = System.currentTimeMillis();

              boolean check = isSorted(array);
              assert check : "Fehler bei der finalen Uebgergabe des Algorithmus.";
              msecs = tEnd - tStart;
            }
            msecs = msecs / para2;
            if(msecs < (para1*1000)+50 && msecs > (para1*1000)-50){
              System.out.println("Status " + status2 + ": " + msecs + "ms, " + array.length + "Felder");
              System.out.println("Ergebnis: " + array.length + "Felder, diese werden in " + msecs/1000 + "," + (msecs/100)%10 + "s durlaufen");
              status2 = 0;
            }
            else if (msecs < para1*1000){
              System.out.println("Status " + status2 + ": " + msecs + "ms, " + array.length + "Felder");
              left = tmpArraySize;
              tmpArraySize = ((right - left)/2) + left;
              status2++;
            }
            else if (msecs > para1*1000){
              System.out.println("Status " + status2 + ": " + msecs + "ms, " + array.length + "Felder");
              right = tmpArraySize;
              tmpArraySize = ((right - left)/2) + left;
              status2++;
            }
            else{
              System.out.println("lul");
            }
          }
        }
        else{
          System.out.println("Es wurden fehlerhafte Parameter uebergeben.");
        }
      } catch(NumberFormatException e){
        System.out.println("Parameter ist keine Zahl!");
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
