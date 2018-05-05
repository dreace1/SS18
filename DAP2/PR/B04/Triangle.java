public class Triangle extends Simplex{

  //Attribute fuer das Objekt Triangle
  int dim;
  Point[] arr;

  public Triangle(int d, Point...points){
    super(d, points);

    if(points != null){
      dim = d;
      arr = points;
    }
    else{
      throw new IllegalArgumentException("Es wurden fehlerhafte Parameter Uebergeben");
    }
  }

  public boolean validate(){
    if(arr.length == 3){                                                //Pruefung ob drei Punkte vorhanden sind
      if(arr[0].dim() == 2 && arr[1].dim() == 2 && arr[2].dim == 2){    //Pruefung ob diese eine Dimension von 2 haben
        return true;
      }
      else{
        return false;
      }
    }
    else{
      throw new IllegalArgumentException("Die Laenge des Arrays ist zu kurz ");
    }
  }



}
