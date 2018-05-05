abstract class Simplex{
  //Simplex

  //Attribute fuer Simplex
  int dim;
  Point[] points;

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
    //EuclidDistance dist = new EuclidDistance();


    for(int i=0; i<points.length; ++i){
                EuclidDistance eDist = new EuclidDistance();
                for(int j=i+1; j<points.length; ++j){
                    sum += eDist.distance(points[i], points[j]);
                }
            }




    //for(int i = 0; i < points.length; i++){         //Errechnung des Abstandes zwischen den Einzelnen Punkten
    //  sum += dist.distance(points[0], points[i+1]);
    //}

    //sum += dist.distance(points[0], points[points.length-1]); //Abstand des ersten und letzten Punktes
    return sum;
  }

  //get Methoden
  public int getDim(){
    return dim;
  }

  public Point[] getPoints(){
    return points;
  }

  public Point get(int i){
    if(i >= 0 && i <= points.length){
      return points[i];
    }
    else{
      throw new IllegalArgumentException("Es wurden fehlerhafte Parameter Uebergeben");
    }
  }

}
