public class Euclid{

  //Hannes Rosenkranz
  //180904

  public static void main(String[] args) {
    if(args.length != 2){
            throw new IllegalArgumentException("2 Integer Werte verwenden");
        }else{
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            System.out.println(euclid(a,b));
        }
  }




  public static int euclid(int a, int b)
  {
    if ( a < 0 || b < 0 )
    {
      throw new IllegalArgumentException( "Keine negativen Zahlen" );
    }
        else
    {
      if(b==0)
    {
      return a;
    }
      else
    {
      return euclid(b, a%b);
    }
    }
  }
}
