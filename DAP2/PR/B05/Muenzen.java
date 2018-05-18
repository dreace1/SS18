public class Muenzen{

  //Hannes Rosenkranz
  //180904

  //4Punkte

  public static void main(String[] args) {
    if(args.length == 2){
      try {
        String para1 = args[0];                     //Ersten String aus args Auslesen
        int para2 = Integer.parseInt(args[1]);      //Auslesen des 2. Parameters und Konvertierung von Strin in Integer

        if(para2 < 0){
          System.out.println("Bitte als 2. Parameter positive Zahl angeben!");
          return;
        }

        //Auswaehlen der Waehrung
        if(para1.equals("Euro")){
          int[] w = {200, 100, 50, 20, 10, 5, 2, 1};
          toString(change(para2, w));                   //Ausgabe und Muenzwechsel
        }
        else if(para1.equals("Alternative")){
          int[] w = {200, 100, 50, 20, 10, 5, 4, 2, 1};
          toString(change(para2, w));
        }
        else{
          System.out.println("Bitte geben Sie Euro oder Alternative als 1. Parameter ein.");
        }
      }
      catch (IllegalArgumentException e){
        System.out.println("Fehler bei der Parameter Uebergabe!");
        return;
      }
    }
    else{
      System.out.println("Bitte geben Sie 2 Parameter ein");
      return;
    }


  }

  //Muenzwechsel
  public static int[] change(int value, int[] currency){
    for(int i = 0; i < currency.length; i++){
      int count = 0;

      //Solange man die Muenze von dem Betrag abziehen kann
      while(value - currency[i] >= 0){
        value -= currency[i];
        count++;
      }
      //in count steht die Menge der aktuellen Muenze, die von dem Betrag abgezogen werden kann
      currency[i] = count;
    }
    return currency;
  }

  public static void toString(int[] a){
    System.out.println("========================");
    System.out.print("[");
    for(int i = 0; i < a.length-1; i++){
      System.out.print(a[i] + ", ");
    }
    System.out.print(a[a.length-1]);
    System.out.print("]");
    System.out.println();
    System.out.println("========================");
  }
}
