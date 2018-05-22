import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Anwendung{

  //Intervalscheduling aus der Vorlesung, welches die Anfrage waehlt die am fruehesten fertig ist
  public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals){

    ArrayList<Interval> array = new ArrayList<Interval>();
    try{
      if(!intervals.isEmpty()){
        //Erstes Element wird immer hinzugefuegt da das Array schon sortiert ist
        array.add(intervals.get(0));
        int j = 0;
        for(int i = 1; i < intervals.size(); i++){
          //Falls der Anfagnswert des Vorgaengers groesser oder gleich dem Endwert des
          //Nachfolgers ist, wird der Prozess mit dem Anfangswert in die Liste aufgenommen
          if(intervals.get(i).getStart() >= intervals.get(j).getEnd()){
            array.add(intervals.get(i));
            //j wird auf den Vorgaenger gesetzt
            j = i;
          }
        }
        return array;
      }
      else{
        throw new IllegalArgumentException("Es wurde ein leeres Interval uebergeben.");
      }
    }
    catch(IllegalArgumentException e){
      System.out.println("Die Liste ist leer");
      System.err.println(e);
      System.exit(1);
    }
    catch(IndexOutOfBoundsException e){
      System.out.println("Du bist Ausserhalb der Grenzen");
      System.err.println(e);
      System.exit(1);
    }
    return array;
  }

  //Latenessscheduling welches die Reihenfolge waehlt die die maximale Verspaetung minimiert indem er die fruehesten Deadlines waehlt
  public static int[] latenessScheduling(ArrayList<Job> jobs){
    try{
      if(!jobs.isEmpty()){
        //Erstellen des Arrays anhand der anzahl von jobs
        int[] arr = new int[jobs.size()];
        int l = 0;
        //Durchlaufen des Arrays
        for(int i = 0; i < jobs.size(); i++){
          arr[i] = l;
          l += jobs.get(i).getDauer();
        }
        return arr;
      }
      else{
        throw new IllegalArgumentException("Es wurde ein leerer Job angegeben");
      }
    }
    catch(IllegalArgumentException e){
      System.out.println("Die Liste ist leer");
      System.err.println(e);
      System.exit(1);
    }
    catch(IndexOutOfBoundsException e){
      System.out.println("Du bist Ausserhalb der Grenzen");
      System.err.println(e);
      System.exit(1);
    }
    return new int[0];
  }

  //Hilfmethode zur Ausgabe einer Interval Arrays
  public static void intervalArrAusgabe(ArrayList<Interval> arr) {
		System.out.print("[");
		for (int i = 0; i < arr.size(); i++){
			if (i != arr.size() - 1){
				System.out.print(arr.get(i).toString());
				System.out.print(", ");
			}
      else{
				System.out.print(arr.get(i).toString());
			}
		}
		System.out.println("]");
	}

  //Hilfmethode zur Ausgabe eines Job int Arrays
  public static void jobIntArrAusgabe(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length-1; i++){
			System.out.print(arr[i]);
			System.out.print(", ");
		}
		System.out.print(arr[arr.length-1]);
		System.out.println("]");
	}

  //Hilfmethode zur Ausgabe von einem Job Arrays
	public static void jobArrAusgabe(ArrayList<Job> arr){
		System.out.print("[");
		for (int i = 0; i < arr.size(); i++){
			if (i != arr.size() - 1){
				System.out.print(arr.get(i).toString());
				System.out.print(", ");
			}
      else{
				System.out.print(arr.get(i).toString());
			}
		}
		System.out.println("]");
	}


  public static void main(String[] args) {

    //Dateiname
    String datei = "";

    //Attribute fuer die Parameter
    String path = "";
    String methode = "";

    //Start und Ende des Intervalls
    int start = 0;
    int end = 0;

    //ArrayListen aus den beiden Intervallen
    ArrayList<Interval> iList = new ArrayList<Interval>();
    ArrayList<Job> jList = new ArrayList<Job>();

    //Intervalle
    Interval interval;
    Job job;

    //Inhalt der ersten Zeile
    String zeile = "";

    //Gelesene Zeilen
    int Zeilen = 0;

    if(args.length == 2){
      methode = args[0];
      path = args[1];

      //Dateinamen auslesen
      StringTokenizer st_datei = new StringTokenizer(path, "\\");
      while(st_datei.hasMoreTokens()){
        datei = st_datei.nextToken();
      }
    }
    else{
      System.out.println("Die Parameter wurden nicht richtig angegeben.");
      System.exit(1);
    }

    //Fehlerbehandlung
    try{

      //File Reader
      RandomAccessFile file = new RandomAccessFile(path, "r");

      if(methode.equals("Interval")){
        //Durchlaufen der Zeilen
        while((zeile = file.readLine()) != null){

          //Einzelne Zeichen auslesen
          StringTokenizer st = new StringTokenizer(zeile, ",");

          //Start und Ende des Intervals
          start = Integer.parseInt(st.nextToken());
          end = Integer.parseInt(st.nextToken());

          //Interval erzeugen
          interval = new Interval(start, end);

          //hinzugefuegen an die Liste
          iList.add(interval);

          //Zeileanzahl erhoehen
          Zeilen++;
        }
        file.close();
      }
      else if(methode.equals("Lateness")){
        //Durchlaufen der Zeilen
        while((zeile = file.readLine()) != null){

          //Einzelne Zeichen auslesen
          StringTokenizer st = new StringTokenizer(zeile, ",");

          //Start und Ende des Intervals
          int dauer = Integer.parseInt(st.nextToken());
          int deadline = Integer.parseInt(st.nextToken());

          //Interval erzeugen
          job = new Job(dauer, deadline);

          //hinzugefuegen an die Liste
          jList.add(job);

          //Zeileanzahl erhoehen
          Zeilen++;
        }
        file.close();
      }
      else{
        System.out.println("Bitte geben Sie entweder Lateness oder Interval an");
        System.exit(1);
      }

    //Fehlerbehandlung
    }
    catch(FileNotFoundException e){
      System.out.println("Datei nicht gefunden!");
      System.err.println(e);
      System.exit(1);
    }
    catch(IOException e){
      System.out.println("Zeile konnte nicht gelesen werden!");
      System.err.println(e);
      System.exit(1);
    }
    catch(NumberFormatException e){
      System.out.println("Es wurde kein gueltiger Interger angegeben!");
      System.err.println(e);
      System.exit(1);
    }

    //Dateinamen ausgeben
    System.out.println("Bearbeite Datei: \"" + datei + "\"" + ". \n");

    //Eingelesenes Interval ausgeben
    System.out.println("Es wurden " + Zeilen + " Zeilen mit folgenden Inhalt gelesen: ");
    System.out.print("[");

    if(methode.equals("Interval")){
      //Ausgabe der unsortierten Liste
      intervalArrAusgabe(iList);

      //Sortiertes Interval ausgeben
      System.out.print("\n\nSortiert:");

      //Sortieren von Interval
      Collections.sort(iList);

      //Ausgabe der sortierten Liste
      System.out.print("\n");
      intervalArrAusgabe(iList);

      //Ausgabe von Interval Scheduling
      ArrayList<Interval> scheduled = intervalScheduling(iList);
      System.out.print("\n\nBerechnetes Intervallscheduling:");

      //Ausgabe der gescheduledten Liste
      System.out.print("\n");
      intervalArrAusgabe(scheduled);
    }
    else if(methode.equals("Lateness")){
      //Ausgabe der unsortierten Liste
      jobArrAusgabe(jList);

      //Sortierten fuer Job ausgeben
      System.out.println("\n\nSortiert:");

      //Sortieren der Liste
      Collections.sort(jList);

      //Ausgabe der sortierten Liste
      System.out.print("\n");
      jobArrAusgabe(jList);

      //Ausgabe von Lateness Scheduling
      int[] latenessScheduled = latenessScheduling(jList);
      System.out.print("\n\nBerechnetes Latenessscheduling:");

      //Ausgabe von der gescheduledten Liste
      System.out.print("\n");
      jobIntArrAusgabe(latenessScheduled);

      //Ausgabe Maximale Verspaetung
      int late = 0;
      late = (latenessScheduled[latenessScheduled.length-1] + jList.get(jList.size()-1).getDauer()) - jList.get(jList.size()-1).getDeadline();
      System.out.print("\n\nBerechnete maximale Verspaetung: " + late);
    }
  }
}
