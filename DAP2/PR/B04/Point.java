public class Point{
  //Point beschreibt einen Punkt im Vektorraum der reellen Zahlen hoch d

  //Attribute fuer das Objekt Point
  double arr[];
  int dim;

  //Konstruktor
  public Point(int d, double... values){

    if(values != null){ //Pruefung ob die Parameter richtig angegeben wurden
      dim = d;
      arr = values;
    }
    else{
      throw new IllegalArgumentException("Es wurden fehlerhafte Parameter Uebergeben");
    }
  }

  //get Methoden
  public double get(int i){
    if(i >= 0 && i <= arr.length){
      return arr[i];
    }
    else{
      throw new IllegalArgumentException("Es wurde ein falscher Index angegeben");
    }
  }

  public int dim(){
    return dim;
  }





}
