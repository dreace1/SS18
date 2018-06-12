import java.util.StringTokenizer;

public class SearchTreeMain{

  public static void main(String[] args) {
    try{
      if(args.length == 1){
        //leeren Baum Erstellen
        SearchTree tree = new SearchTree();
        StringTokenizer token = new StringTokenizer(args[0], ",");

        //Eingabe Auslesen und in den Surchbaum einfuegen
        while(token.hasMoreElements()){
          tree.setValue(Integer.valueOf(token.nextToken()));
        }

        //Ausgabe der Durchlauefe
        //Pre-Order
        System.out.println("Pre-Order Durchlauf: ");
        tree.showPreOrder();
        System.out.println();

        //In-Order
        System.out.println("In-Order Durchlauf: ");
        tree.showInOrder();
        System.out.println();

        //Post-Order
        System.out.println("Post-Order Durchlauf");
        tree.showPostOrder();
        System.out.println();
      }
      else{
        System.out.println("Bitte geben Sie nur EIN Array an.");
        System.exit(1);
      }
    }
    catch(NumberFormatException e){
      System.out.println("Fehler bei der Parameteruerbergabe!");
      System.err.println(e);
      System.exit(1);
    }
  }
}
