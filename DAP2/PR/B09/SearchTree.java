public class SearchTree{

  //Globale Variabeln
  private SearchTree leftChild;
  private SearchTree rightChild;
  private Integer value;

  //Konstruktor leerer Baum
  public SearchTree(){
    leftChild = null;
    rightChild = null;
    value = null;
  }

  //Konstruktor
  public SearchTree(int value){
    leftChild = null;
    rightChild = null;
    this.value = value;
  }

  //get-Methoden
  public SearchTree getLeftChild(){
    return leftChild;
  }

  public SearchTree getRightChild(){
    return rightChild;
  }

  public int getValue(){
    return value;
  }

  //Hilfsmethode ob es sich um einen leeren Baum handelt
  public boolean isEmpty(){
    return leftChild == null && rightChild == null && value == null;
  }

  //Hilfsmethode zur ermittlung ob es sich um ein Blatt handelt
  public boolean isLeaf(){
    return leftChild == null && rightChild == null;
  }

  //Methode zum einfuegen von einem Wert
  public void setValue(Integer val){
    //Wenn der Baum leer ist dann Erstelle Wurzel
    if(isEmpty()){
      value = val;
    }
    //sonst wenn der Wert groesser ist fuege ihn rechts ein
    else if(val > value){
      if(rightChild == null){
        rightChild = new SearchTree(val);
      }
      else{
        rightChild.setValue(val);
      }
    }
    //sonst wenn der Wert kleiner ist fuege ihn links ein
    else if(val < value){
      if(leftChild == null){
        leftChild = new SearchTree(val);
      }
      else{
        leftChild.setValue(val);
      }
    }
  }


  //Ausgabe eines Pre-Order Durchlaufes
  public void showPreOrder(){
    if(isLeaf()){
      System.out.print(value + " ");
    }
    else{
      //Erst Ausgabe der Wurzel
      System.out.print(value + " ");
      //Dann Ausgabe des Linken teilbaumes
      if(leftChild != null){
        leftChild.showPreOrder();
      }
      //und des Rechten teilbaumes
      if(rightChild != null){
        rightChild.showPreOrder();
      }
    }
  }

  //Ausgabe eines In-Order Durlaufes
  public void showInOrder(){
    if(isLeaf()){
      System.out.print(value + " ");
    }
    else{
      //Erst Ausgabe des linken Teilbaumes
      if(leftChild != null){
        leftChild.showInOrder();
      }
      //Dann die Wurzel
      System.out.print(value + " ");
      //und dann des rechten Teilbaumes
      if(rightChild != null){
        rightChild.showInOrder();
      }
    }
  }

  //Asugabe eines Post-Order Durchlaufes
  public void showPostOrder(){
    if(isLeaf()){
      System.out.print(value + " ");
    }
    else{
      //Erst Ausgabe des linken Teilbaumes
      if(leftChild != null){
        leftChild.showPostOrder();
      }
      //Dann des rechten Teilbaumes
      if(rightChild != null){
        rightChild.showPostOrder();
      }
      //und dann der Wurzel
      System.out.print(value + " ");
    }
  }


}
