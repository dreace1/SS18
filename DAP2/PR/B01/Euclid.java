public class Euclid{ //4Punkte

  //Hannes Rosenkranz
  //180904

  public static void main(String[] args) {
    //Fehlerbehandlung
    if(args.length == 2){
      try{
        //Versuche die Zahl zu parsen
        int a = Integer.parseInt(args[0]); //Auslesen des ersten Parameters und Konvertierung von String in Integer
        int b = Integer.parseInt(args[1]); //Auslesen des ersten Parameters und Konvertierung von String in Integer
        if(a <= b){
          System.out.println("Der ggT lautet: " + euclid(a,b)); //Aufruf der euclid Methode
        }
        else{
          System.out.println("Der zweite Parameter sollte groesser oder gleich dem ersten sein!");
        }

      }
      catch(NumberFormatException numberFormatException){
        System.out.println("Parameter ist kein Integer");
        return;
      }
    }

    else{
      System.out.println("Bitte geben Sie die Parameter richtig ein, z.B. java Euclid 264 846");
    }
  }



  public static int euclid(int a, int b)
  {
    if(a < 0 || b < 0){ //Laut Aufgabenstellung duerfen keine negativen Integer gewaelt werden
      throw new IllegalArgumentException("Bitte einen positiven Wert angeben.");
    }
    else{
      if(b == 0){ //Abbruchbedingung
        return a;
      }
      else{
        return euclid(b, a%b);
      }
    }

  }
}
