public class Article{
  private int value;
  private int weight;

  public Article(int value, int weight){
    this.value = value;
    this.weight = weight;
  }

  public int getValue(){
    return value;
  }

  public int getWeight(){
    return weight;
  }

  public void setValue(int value){
    this.value = value;
  }

  public void setWeight(int weight){
    this.weight = weight;
  }
}
