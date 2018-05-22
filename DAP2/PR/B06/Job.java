public class Job implements Comparable<Job>{
  //Die Klasse Job gibt den Start und das Ende eines Intervals an

  //Attribute
  private int dauer, deadline;

  //Konstruktor
  public Job(int dauer, int deadline){
    this.dauer = dauer;
    this.deadline = deadline;
  }

  //get-Methoden
  public int getDauer(){
    return dauer;
  }

  public int getDeadline(){
    return deadline;
  }

  public String toString(){
    return "[" + getDauer() + ", " + getDeadline() + "]";
  }

  public int compareTo(Job other){
    if (this.getDeadline() < other.getDeadline()){
      return -1;
    }
    else if(this.getDeadline() == other.getDeadline()){
      return 0;
    }
    else{
      return +1;
    }
  }

}
