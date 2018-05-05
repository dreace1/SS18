public class Application{

  public static void main(String[] args){

  //Anlegen der Parameter
  double x1 = 0;
  double x2 = 0;
  double x3 = 0;

  double y1 = 0;
  double y2 = 0;
  double y3 = 0;

  //Zufallszahlen
  java.util.Random numberGenerator = new java.util.Random();

  if(args.length == 6){
    try{

      //Auslesen der Parameter und Konvertierung von String in double
      x1 = Double.parseDouble(args[0]);
      y1 = Double.parseDouble(args[1]);
      x2 = Double.parseDouble(args[2]);
      y2 = Double.parseDouble(args[3]);
      x3 = Double.parseDouble(args[4]);
      y3 = Double.parseDouble(args[5]);
    }
    catch (NumberFormatException e){
      System.out.println("Bitte geben Sie die Parameter richtig ein");
    }
  }
  else if(args.length == 0){
    //Wenn kein Parameter angegeben wurde werden die Punkte mit Zufallszahlen gefuellt
    x1 = numberGenerator.nextInt(2000)-1000.0;
    y1 = numberGenerator.nextInt(2000)-1000.0;
    x2 = numberGenerator.nextInt(2000)-1000.0;
    y2 = numberGenerator.nextInt(2000)-1000.0;
    x3 = numberGenerator.nextInt(2000)-1000.0;
    y3 = numberGenerator.nextInt(2000)-1000.0;

    System.out.println("Es wurden keine Parameter angegeben, es wird ein zufaelliges Dreieck erzeugt");
  }
  else{
    System.out.println("Bitte geben Sie die Parameter richtig ein, z.B. java Application x1 y1 x2 y2 x3 y3 oder ohne Parameter");
  }

  //Erzeugen der Punkte
  Point p1 = new Point(2, x1, y1);
  Point p2 = new Point(2, x2, y2);
  Point p3 = new Point(2, x3, y3);

  //Erzeugen des Dreieckes
  Triangle t1 = new Triangle(2, p1, p2, p3);
  if(t1.validate()){
    System.out.println("======================================================================"
                      + "\nSeitenlänge des Dreiecks: " + t1.perimeter()
                      + "\n======================================================================");
    }
    else{
      System.out.println("Es wurde kein gueltiges Dreieck erstellt.");
    }
  }




}
