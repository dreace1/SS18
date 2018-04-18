public class Eratosthenes{ //4Punkte

  //Hannes Rosenkranz
  //180904

  public static void main(String[] args) {
    //Fehlerbehandlung
    if(args.length > 0 && args.length < 3){
      try{
        int n = Integer.parseInt(args[0]); //Auslesen des ersten Parameters und Konvertierung von String in einen Integer
        boolean[] isPrim = sieve(n+1); //Anlegen eines Arrays des Types boolean mit der Laenge des ausgelesen Parameters

        if(args.length > 1){ //Ueberpruefen ob es einen weiteren Parameter gibt
          if(args[1].equals("-o")){ //Ueberpruefen ob der zweite Parameter -o ist
            int primeCount = 0;
            for(int j = 2; j < isPrim.length; j++){ //Array durchlaufen und alle Integers die true sind ausgeben
              if(isPrim[j] == true){
                System.out.println(j);
                primeCount++;
              }
            }
            System.out.println("Die Anzahl der Primzahlen betraegt: " + primeCount);
          }
          else{
            System.out.println("Bitte als zweites Argument -o fuer output angeben!"); //Falls ein anderer String angegeben wurde
          }
        }
        else{ //Falls kein Ausgabeparameter angegeben wurde
          int count = 0;
          for(int l = 2; l < n; l++){ //Array durchlaufen und alle Primzahlen zaehlen
            if(isPrim[l] == true){
              count++;
            }
          }
          System.out.println("Die Anzahl der Primzahlen betraegt: " + count); //Ausgabe der Anzahl der Primzahlen
        }
      }
      catch(NumberFormatException numberFormatException){
        System.out.println("Der erste Parameter ist keine Zahl.");
        return;
      }

    }
    else{
      System.out.println("Bitte geben Sie die Parameter richtig ein, z.B. java Eratosthenes 100 -o");
    }

  }

  public static boolean[] sieve(int n){
    boolean[] isPrim = new boolean[n];

    for(int k = 0; k < n; k++){ //Laut Aufgabenstellung werden erst alle als potentielle Primzahlen gesehen
      isPrim[k] = true;
    }
    for(int i = 2; i < n; i++){ //Durchlaufen aller Zahlen, da 1 nach Definition keine Pimzahl ist beginnt die Schleife bei 2
      if(isPrim[i] == true){
        int vielfaches = i;
        while(vielfaches+i < n){ //Alle Vielfachen der Zahl als Primzahl ausschliessen
          vielfaches += i; //Vielfaches bestimmen und dann auf false setzen
          isPrim[vielfaches] = false;
        }
      }
    }
    return isPrim;
  }


}
