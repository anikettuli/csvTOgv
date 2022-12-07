import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class csvTOgv {
  public static void main(String[] args) throws Exception {
    String end = "graph RoadNetwork{ \n\n";
    try {
      BufferedReader br = new BufferedReader(new FileReader("src\\roadtrip (2).csv"));
      for (String line = br.readLine(); line != null; line = br.readLine()) {

        if (!line.equals("origin,destination,distance")) {
          int temp = line.indexOf("\",\"");
          String origin = line.substring(1, temp);
          int temp2 = line.indexOf("\",", temp + 3);
          String destination = line.substring(temp + 3, temp2);
          String distance = line.substring(temp2 + 2);
          String text = "    " + origin + " -- " + destination + "[distance=" + distance + "];\n";
          end = end + text;
        }
      }
      end = end + "\n}";
      Path fileName = Path.of("src\\RoadNetwork.gv");
      Files.writeString(fileName, end);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
}