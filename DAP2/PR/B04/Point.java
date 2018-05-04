public class Point{

  private double arr[];
  private int dim;

  public Point(int d, double... values){

    if(values != null){
      dim = d;
      arr = values;
    }
    else{
      throw new IllegalArgumentException();
    }
  }

  public double get(int i){
    if(i >= 0 && i <= arr.length){
      return arr[i];
    }
    else{
      throw new IllegalArgumentException();
    }
  }

  public int dim(){
    return dim;
  }





}
