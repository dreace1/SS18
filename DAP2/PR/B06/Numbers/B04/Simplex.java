public abstract class Simplex{
  //Simplex gibt die Eckpunkte an

  //Attribute fuer Simplex
  private int dim;
  private Point[] points;

  //Konstruktor
  public Simplex(int d, Point...points){

    if(points != null && points.length == d+1){ //Pruefung ob die Parameter richtig angegeben wurden
      dim = d;
      this.points = points;
    }
    else{
      throw new IllegalArgumentException("Es wurden fehlerhafte Parameter Uebergeben");
    }
  }

  //abstrakte Methode ob die Instanz einen validen Simplex darstellt
  public abstract boolean validate();

  //Methode zur errechnung der Summe der Seitenlaengen
  public double perimeter(){
    double sum = 0.0;

    for(int i = 0; i < points.length; i++){
      EuclidDistance eDist = new EuclidDistance();   //Pythagoras
      for(int j = i+1; j < points.length; j++){
        sum += eDist.distance(points[i], points[j]); //Errechnung des Umfangs fuer das Dreieck
      }
    }
    return sum;
  }

  //get Methoden
  public int getDim(){
    return dim;
  }

  public Point[] getPoints(){
    return points;
  }

  public Point getPoint(int i){
    if(i >= 0 && i <= points.length){ //Pruefung ob sich der Index im richtigen Bereich befindet
      return points[i];
    }
    else{
      throw new IllegalArgumentException("Es wurden fehlerhafte Parameter Uebergeben");
    }
  }

}
