public class Triangle extends Simplex{



  public Triangle(int d, Point...points){
    super(d, points); //Aufruf des Konstruktors der Oberklasse
  }


  public boolean validate(){
    if(getPoints().length == 3){                        //Pruefe ob 3 Punkte vorhanden sind
      for(int i = 0; i < getPoints().length; i++){
        if(getPoints()[i].dim() != 2){                  //Vergleich ob diese eine Dimension von 2 haben
          return false;
        }
      }
      return !samePoints(3);                            //Pruefe ob die Werte gleich sind
    }
    return false;


  }

  //Hilfsmethode zum Ueberpruefen ob die Punkte gleich sind
  private boolean samePoints(int n){
    for(int i = 0; i < n; i++){
      for(int j = i+1; j < n; j++){
        if(sameValues(getPoints()[i], getPoints()[j])){ //Es wird verglichen ob der Nächste Punkt gleich dem davorigen ist
          return true;
        }
      }
    }
    return false;
  }

  //Hilfsmethode zum Ueberpruefen ob die double Werte der Punkte gleich sind
  public boolean sameValues(Point p1, Point p2){
    if(p1.dim() != p2.dim()){
      throw new IllegalArgumentException("Die Punkte haben nicht die gleich Dimension"); //Ueberpruefen ob die Punkte die gleiche Dimension haben
    }
    else{
      for(int i = 0; i < p1.dim(); i++){
        if(p1.get(i) != p2.get(i)){           //Vergleichen der double Werte
          return false;
        }
      }
    }
    return true;
  }






  //public boolean validate(){
  //  if(getPoints().length == 3){
  //    if(getPoints()[0].dim() == 2 && getPoints()[1].dim() == 2 && getPoints()[2].dim() == 2){
  //      return true;
  //    }
  //    else{
  //      return false;
  //    }
  //  }
  //  else{
  //    throw new IllegalArgumentException("Die Laenge des Arrays ist zu kurz ");
  //  }
  //}




}
