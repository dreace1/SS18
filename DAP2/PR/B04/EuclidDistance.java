class EuclidDistance implements Distance{

  //Methode zur errechnung der Euclidischen Distanz zwischen 2 Punkten
  public double distance(Point p1, Point p2){
    if(p1.dim() == p2.dim()){
      double result = 0.0;

      for(int i = 0; i < p1.dim(); i++){
        result += Math.pow(p1.get(i) - p2.get(i), 2);        //Pythagoras
      }
      System.out.println("Distanz: " + Math.sqrt(result));

      return Math.sqrt(result);                             //Pythagoras
    }
    else{
      throw new IllegalArgumentException("Punkte sollten nicht die gleiche Dimension haben");
    }
  }


}
