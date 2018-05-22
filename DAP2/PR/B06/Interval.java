public class Interval implements Comparable<Interval>{
  //Die Klasse Interval gibt den Start und das Ende eines Intervals an

  //Attribute
  private int start, end;

  //Konstruktor
  public Interval(int start, int end){
    this.start = start;
    this.end = end;
  }

  //get-Methoden
  public int getStart(){
    return start;
  }

  public int getEnd(){
    return end;
  }

  public String toString(){
    return "[" + getStart() + ", " + getEnd() + "]";
  }

  public int compareTo(Interval other){
    if (this.getEnd() < other.getEnd()){
      return -1;
    }
    else if(this.getEnd() == other.getEnd()){
      return 0;
    }
    else{
      return +1;
    }
  }

}
